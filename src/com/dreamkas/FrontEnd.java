/*
Поток фронт энда, необходимый для обработки таск пользователя

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */
package com.dreamkas;

import java.util.Map;

public class FrontEnd extends Thread {
    private final static int THREAD_TIMEOUT_MS = 1000;
    private TaskBuffer m_tb;
    private MainGui m_gui;

    //установить буфер таск
    public void setBuffer(TaskBuffer tb) {
        this.m_tb = tb;
    }

    public void updateDrawer(String ip) {
        m_tb.addTaskForBackEnd(new UpdateDrawer(ip));
    }

    public void updateFiscat(String ip) {
        m_tb.addTaskForBackEnd(new UpdateFiscat(ip));
    }

    public void downloadConfig(String ip) {
        m_tb.addTaskForBackEnd(new DownloadConfig(ip, null));
    }

    public void uploadConfig(String ip, Map<String, String> queryTab) {
        m_tb.addTaskForBackEnd(new UploadConfig(ip, queryTab));
    }

    public void cloneDrawer(String ip) {
        m_tb.addTaskForBackEnd(new CloneDrawer(ip));
    }

    //распарсить таску от бэк энда
    private int parseTaskFromBe(Task task) {
        System.out.println("FRONT_END: Parse msg from Back end");
        try {
            switch (task.getTaskName()) {
                case "Feedback":
                    Feedback fb = (Feedback) task;
                    System.out.println("FRONT_END: " + fb.getMessage());
                    m_gui.printLogString("FRONT_END: " + fb.getMessage() + "\n");

                    //при ошибке сбрасываем поля гуя в дефолтное состояние
                    if (fb.getMessage().contains("Failed")) {
                        m_gui.resetGui("Failed");
                    }

                    //при успехе сбрасываем поля гуя в дефолтное состояние
                    if (fb.getMessage().contains("Request success!")) {
                        m_gui.resetGui("Success");
                    }
                    break;

                case "DownloadConfig":
                    m_gui.drawConfigPanel(((DownloadConfig) task).getDownloadedConfig());
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return -1;
        }
        return 0;
    }

    //основной метод потока
    public void run() {
        m_gui = new MainGui();
        if (m_gui == null) {
            System.out.println("Failed to create gui!");
            System.out.println("Front end start failed!");
            return;
        }

        m_gui.setFrontEnd(this);
        m_gui.setVisible(true);

        try {
            while (true) {
                if (m_tb.buferForFeSize() > 0) {
                    Task task = m_tb.getTaskForFrontEnd();
                    if (parseTaskFromBe(task) != 0) {
                        System.out.println("Failed to parse "
                                + "task from back end!");
                    }
                }

                Thread.sleep(THREAD_TIMEOUT_MS);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void resetGui() {
        m_gui.resetGui("Success");
    }
}
