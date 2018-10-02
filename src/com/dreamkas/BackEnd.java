/*
Поток бэк энда, необходимый для обработки таск пользователя

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */

package com.dreamkas;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BackEnd extends Thread {
    private TaskBuffer m_tb;
    private Ssh        m_ssh;
    private Network    m_net;
    private Database   m_db;
    private final static int THREAD_TIMEOUT_MS = 1000;
    private final static String FISGO_UPDATE_TAR = "FisGoUpdate.tar";
    private LoaderFrame loaderFrame;
    private Thread progressBarThread;

    //рекурсивная удалялка файлов
    public static void recursiveDelete(File file) {
        // до конца рекурсивного цикла
        if (!file.exists())
            return;

        //если это папка, то идем внутрь этой папки и вызываем рекурсивное удаление всего, что там есть
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                // рекурсивный вызов
                recursiveDelete(f);
            }
        }
        // вызываем метод delete() для удаления файлов и пустых(!) папок
        file.delete();
        System.out.println("Удаленный файл или папка: " + file.getAbsolutePath());
    }

    //Исключение, которое бросаем когда обновление недоступно
    private class UpdateNotAvailableException extends Exception {
        UpdateNotAvailableException(String msg){
            super(msg);
        }
    }

    //распарсить таску от фронтенда
    private int parseTaskFromFe(Task task){
        try {
            String ip          = "";
            switch (task.getTaskName()) {
                case "ExecuteSshCommand":
                    if (m_ssh.executeSshCommand(((ExecuteSshCommand) task).getCommand()) < 0) {
                        throw new Exception("Failed to execute ssh command!");
                    }
                    break;
                case "UpdateDrawer":
                    //скачать базу с кассы и вынуть из нее текущую версию fiscat
                    ip = ((UpdateDrawer) task).getDrawerIp();
                    m_ssh.setIp(ip);
                    m_tb.addTaskForFrontEnd(new Feedback("Getting device version..."));
                    if (m_ssh.executeScpGet("./", "/FisGo/configDb.db") < 0) {
                        throw new Exception("Failed to get config base!");
                    }

                    String version = m_db.getKktVersion();
                    //сформировать url для запроса обновления
                    String url
                            = "https://update.dreamkas.ru/v1/projects/fisgo/" +
                            "products/dreamkasf/updates/" + version;

                    //скачать обновление
                    m_tb.addTaskForFrontEnd(new Feedback("Downloading update..."));
                    int res = m_net.getUpdate(url, FISGO_UPDATE_TAR);
                    if (res < 0) {
                        throw new Exception("Failed to download update artifact!");
                    } else if(res > 0){
                        throw new UpdateNotAvailableException("Update is not available!");
                    }

                    //положить его на кассу
                    m_tb.addTaskForFrontEnd(new Feedback("Updating device..."));
                    if(m_ssh.executeSshCommand("mkdir -p /download") < 0) {
                        throw new Exception("Failed to create /download directory!");
                    }

                    String[] fileNames = {FISGO_UPDATE_TAR, "cs"};

                    if(m_ssh.executeScpPut("/download/", fileNames) < 0) {
                        throw new Exception("Failed to copy update artifact!");
                    }

                    if(m_ssh.executeSshCommand("sync") < 0) {
                        throw new Exception("Failed to create /download directory!");
                    }

                    m_tb.addTaskForFrontEnd(new Feedback("Updating SUCCESS! Please, Reboot!"));
                    break;

                case "update factory":
                    //скачать артифакт обновления самой свежей версии
                    //сформировать url для запроса обновления
                    String urlUpdFactory
                            = "https://update.dreamkas.ru/v1/projects/fisgo/" +
                            "products/dreamkasf/updates/";

                    //скачать обновление
                    m_tb.addTaskForFrontEnd(new Feedback("Downloading update..."));
                    int UpdFactory = m_net.getUpdate(urlUpdFactory, FISGO_UPDATE_TAR);
                    if (UpdFactory < 0) {
                        throw new Exception("Failed to download update artifact!");
                    } else if(UpdFactory > 0){
                        throw new UpdateNotAvailableException("Update is not available!");
                    }

                    break;

                case "DownloadConfig":
                    //скачать базу с кассы и вынуть из нее текущую версию fiscat
                    ip = ((DownloadConfig) task).getDrawerIp();
                    m_ssh.setIp(ip);
                    if(!m_ssh.checkConnection()){
                        throw new Exception("Failed connection");
                    }

                    //проверка на то, что это ДК-Ф
                    if(!m_ssh.isDreamkasF()){
                        throw new Exception("It's no Dreamkas-F!");
                    }
                    m_tb.addTaskForFrontEnd(new Feedback("Getting device config..."));

                    if (m_ssh.executeScpGet("./", "/FisGo/configDb.db") < 0) {
                        throw new Exception("Failed to get config base!");
                    }

                    //распарсить базу в вид, пригодный для отображения в таблице
                    m_tb.addTaskForFrontEnd(new DownloadConfig("", m_db.getConfigTable()));
                    break;

                case "UploadConfig":
                    loaderFrame = new LoaderFrame();
                    progressBarThread = new Thread(loaderFrame);
                    progressBarThread.start();

                    m_db.setConfigTable(((UploadConfig) task).getUploadConfig());

                    loaderFrame.setProgressBar(10);

                    if(m_ssh.executeSshCommand("killall fiscat") < 0) {
                        loaderFrame.setOverProgressBar("ERRORx01! Failed to copy updated config!", Color.RED);
                        throw new Exception("Failed to copy updated config!");
                    }

                    loaderFrame.setProgressBar(20);

                    if(m_ssh.executeSshCommand("cd /FisGo/; rm updateConfigDb.sh") < 0) {
                        loaderFrame.setOverProgressBar("ERRORx02! Failed to copy updated config!", Color.RED);
                        throw new Exception("Failed to copy updated config!");
                    }

                    loaderFrame.setProgressBar(30);

                    String[] confFileName = {"updateConfigDb.sh"};

                    if(m_ssh.executeScpPut("/FisGo/", confFileName) < 0) {
                        loaderFrame.setOverProgressBar("ERRORx03! Failed to copy updated config!", Color.RED);
                        throw new Exception("Failed to copy updated config!");
                    }

                    loaderFrame.setProgressBar(40);

                    if(m_ssh.executeSshCommand("dos2unix -u /FisGo/updateConfigDb.sh /FisGo/updateConfigDb.sh;") < 0) {
                        loaderFrame.setOverProgressBar("ERRORx04! Failed to copy updated config!", Color.RED);
                        throw new Exception("Failed dos2unix!");
                    }

                    loaderFrame.setProgressBar(50);

                    if(m_ssh.executeSshCommand("chmod 755 /FisGo/updateConfigDb.sh") < 0) {
                        loaderFrame.setOverProgressBar("ERRORx05! Failed to copy updated config!", Color.RED);
                        throw new Exception("Failed chmod 755!");
                    }

                    loaderFrame.setProgressBar(65);

                    if(m_ssh.executeSshCommand("sync") < 0) {
                        loaderFrame.setOverProgressBar("ERRORx06! Failed to copy updated config!", Color.RED);
                        throw new Exception("Failed to copy updated config!");
                    }

                    loaderFrame.setProgressBar(80);

                    if(m_ssh.executeSshCommand("cd /FisGo/; ./updateConfigDb.sh") < 0) {
                        loaderFrame.setOverProgressBar("ERRORx07! Failed to copy updated config!", Color.RED);
                        throw new Exception("Failed to copy updated config!");
                    }

                    loaderFrame.setOverProgressBar("Операция успешно выполнена!", Color.GREEN);
                    break;

                case "CloneDrawer":
                    loaderFrame = new LoaderFrame();
                    progressBarThread = new Thread(loaderFrame);
                    progressBarThread.start();

                    loaderFrame.setProgressBar(5);
                    File fisGoArchive = new File("./FisGo.tar");

                    loaderFrame.setProgressBar(10);
                    //удаляю файл если есть
                    recursiveDelete(fisGoArchive);

                    loaderFrame.setProgressBar(15);
                    //скачать базу с кассы и вынуть из нее текущую версию fiscat
                    ip = ((CloneDrawer) task).getDrawerIp();
                    m_ssh.setIp(ip);
                    m_tb.addTaskForFrontEnd(new Feedback("Cloning device..."));

                    loaderFrame.setProgressBar(20);
                    if(m_ssh.executeSshCommand("cd /; rm FisGo.tar") < 0) {
                        loaderFrame.setOverProgressBar("ERRORx01! Failed to compress clone!", Color.RED);
                        throw new Exception("Failed to compress clone!");
                    }

                    loaderFrame.setProgressBar(55);
                    if(m_ssh.executeSshCommand("cd /; tar -cvf FisGo.tar /FisGo/") < 0) {
                        loaderFrame.setOverProgressBar("ERRORx02! Failed to compress clone!", Color.RED);
                        throw new Exception("Failed to compress clone!");
                    }

                    loaderFrame.setProgressBar(80);
                    if (m_ssh.executeScpGet("./", "/FisGo.tar") < 0) {
                        loaderFrame.setOverProgressBar("ERRORx03! Failed to compress clone!", Color.RED);
                        throw new Exception("Failed to get drawer clone!");
                    }

                    loaderFrame.setOverProgressBar("Операция успешно выполнена!", Color.GREEN);
                    break;

                default:
                    System.out.println("Unknown task type");
                    break;
            }
        }
        catch (UpdateNotAvailableException e) {
            m_tb.addTaskForFrontEnd(new Feedback("Update is not available!"));
            return 0;
        }
        catch(Exception e){
            System.out.println(e.toString());
            return -1;
        }
        return 0;
    }

    //установить буфер таск
    public void setBuffer(TaskBuffer tb){
        this.m_tb = tb;
    }

    public void run() {
        m_ssh = new Ssh();
        if(m_ssh == null) {
            System.out.println("Failed to start ssh daemon!");
            return;
        }

        m_net = new Network();
        if(m_net == null) {
            System.out.println("Failed to start Network daemon!");
            return;
        }

        m_db = new Database();
        if(m_db == null){
            System.out.println("Failed to start Database daemon!");
            return;
        }

        try {
            while (true) {
                if(m_tb.buferForBeSize() > 0)
                {
                    Task task = m_tb.getTaskForBackEnd();
                    if(parseTaskFromFe(task) == 0){
                        m_tb.addTaskForFrontEnd(new Feedback("Request success!"));
                    } else {
                        m_tb.addTaskForFrontEnd(new Feedback("Failed"));
                    }
                }
                Thread.sleep(THREAD_TIMEOUT_MS);
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
