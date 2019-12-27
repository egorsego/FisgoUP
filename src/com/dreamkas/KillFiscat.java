package com.dreamkas;

public class KillFiscat extends Task  {
    private String m_ip;

    //конструктор по-умолчанию
    KillFiscat(String ip){
        super("KillFiscat");
        this.m_ip = ip;
    }

    //получить текст команды
    public String getDrawerIp(){
        return m_ip;
    }
}
