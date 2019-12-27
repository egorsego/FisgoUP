package com.dreamkas;

public class UpdateFiscat extends Task  {
    private String m_ip;

    //конструктор по-умолчанию
    UpdateFiscat(String ip){
        super("UpdateFiscat");
        this.m_ip = ip;
    }

    //получить текст команды
    public String getDrawerIp(){
        return m_ip;
    }
}
