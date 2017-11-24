/*
Исполнить ssh команду

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */

package com.dreamkas;

public class ExecuteSshCommand extends Task {
    //каманда, которую "скормим" ssh у
    private String m_command;

    //конструктор по-умолчанию
    public ExecuteSshCommand(String command){
        super("ExecuteSshCommand");
        this.m_command = command;
    }

    //получить текст команды
    public String getCommand(){
        return m_command;
    }
}
