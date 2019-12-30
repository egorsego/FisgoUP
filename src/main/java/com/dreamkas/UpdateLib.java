package com.dreamkas;

public class UpdateLib extends Task  {
    private String m_ip;

    //конструктор по-умолчанию
    UpdateLib(String ip){
        super("UpdateLib");
        this.m_ip = ip;
    }

    //получить текст команды
    public String getDrawerIp(){
        return m_ip;
    }
}
