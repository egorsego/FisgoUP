/*
Проапдейтить кассу по ip

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */

package com.dreamkas;

public class UpdateDrawer extends Task {
    //ip кассы, которую хотим обновить
    private String m_ip;

    //конструктор по-умолчанию
    public UpdateDrawer(String ip){
        super("UpdateDrawer");
        this.m_ip = ip;
    }

    //получить текст команды
    public String getDrawerIp(){
        return m_ip;
    }
}
