/*
Загрузить конфиг с кассы

@author  Kashitsyn Denis
@version 1.0
@since   2017-11-22

 */

package com.dreamkas;

import java.util.ArrayList;

public class DownloadConfig extends Task {
    //ip кассы, c которой хотим скачать конфиг
    private String m_ip;
    private ArrayList<ArrayList<String>> m_config;

    //конструктор по-умолчанию
    public DownloadConfig(String ip, ArrayList<ArrayList<String>> config){
        super("DownloadConfig");
        this.m_ip = ip;
        this.m_config = config;
    }

    //получить текст команды
    public String                       getDrawerIp(){
        return m_ip;
    }
    public ArrayList<ArrayList<String>> getDownloadedConfig(){
        return m_config;
    }
}
