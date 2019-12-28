/*
Загрузить проапдейченный конфиг на кассу

@author  Kashitsyn Denis
@version 1.0
@since   2017-11-22

 */

package com.dreamkas;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class UploadConfig extends Task {
    //ip кассы, на которую хотим загрузить конфиг
    private String m_ip;
    private Map<String, String> m_queryTab;

    //конструктор по-умолчанию
    public UploadConfig(String ip, Map<String, String> queryTab){
        super("UploadConfig");
        this.m_ip = ip;
        this.m_queryTab = queryTab;
    }

    //получить текст команды
    public String                     getDrawerIp(){
        return m_ip;
    }
   // public Vector<Vector<String>> getUploadConfig(){
    public Map<String, String> getUploadConfig(){
        return m_queryTab;
    }
}