/*
Склонировать кассу по ip

@author  Kashitsyn Denis
@version 1.0
@since   2017-12-19

 */

package com.dreamkas;

public class CloneDrawer  extends Task  {
    //ip кассы, которую хотим склонить
    private String m_ip;

    //конструктор по-умолчанию
    public CloneDrawer(String ip){
        super("CloneDrawer");
        this.m_ip = ip;
    }

    //получить текст команды
    public String getDrawerIp(){
        return m_ip;
    }
}
