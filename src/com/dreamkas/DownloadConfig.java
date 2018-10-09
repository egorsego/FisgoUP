/*
Загрузить конфиг с кассы

@author  Kashitsyn Denis
@version 1.0
@since   2017-11-22

 */

package com.dreamkas;

import java.util.ArrayList;
import java.util.Map;

public class DownloadConfig extends Task {
    //ip кассы, c которой хотим скачать конфиг
    private String m_ip;
    private Map<String, String> m_config;

    //конструктор по-умолчанию
    public DownloadConfig(String ip, Map<String, String> config){
        super("DownloadConfig");
        this.m_ip = ip;
        this.m_config = config;
    }

    //получить текст команды
    public String                       getDrawerIp(){
        return m_ip;
    }
    public Map<String, String> getDownloadedConfig(){
        return m_config;
    }
}
