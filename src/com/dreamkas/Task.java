/*
Абстрактный класс задачи

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */

package com.dreamkas;

public abstract class Task {
    //название операции
    private String m_taskName;

    //конструктор по-умолчанию
    public Task(String taskName){
        this.m_taskName = taskName;
    }

    //@Override
    public String getTaskName(){
        return m_taskName;
    }
}
