/*
Потокозащищенный буфер заданий

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */
package com.dreamkas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskBuffer {
    //список и мутекс для буфера ForBE
    private Lock mutexForFe;
    private List m_forFeList;

    //список и мутекс для буфера ForFE
    private Lock mutexForBe;
    private List m_forBeList;

    //выдать размер буфера для фронтенда
    public int buferForFeSize(){
        return m_forFeList.size();
    }

    //выдать размер буфера для бэкэнда
    public int buferForBeSize(){
        return m_forBeList.size();
    }

    //конструктор по-умолчанию
    public TaskBuffer()
    {
        mutexForFe = new ReentrantLock();
        mutexForBe = new ReentrantLock();

        m_forFeList = new ArrayList<>();
        m_forBeList = new ArrayList<>();
    }

    //add task for frontEnd to buffer
    public void addTaskForFrontEnd(Task task){
        mutexForFe.lock();
        m_forFeList.add(task);
        mutexForFe.unlock();
    }

    //get task for frontEnd from buffer
    public Task getTaskForFrontEnd(){
        mutexForFe.lock();
        Task task = (Task)m_forFeList.get(0);
        m_forFeList.remove(0);
        mutexForFe.unlock();
        return task;
    }

    //add task for backEnd to buffer
    public void addTaskForBackEnd(Task task){
        mutexForBe.lock();
        m_forBeList.add(task);
        mutexForBe.unlock();
    }

    //get task for backEnd from buffer
    public Task getTaskForBackEnd(){
        mutexForBe.lock();
        Task task = (Task)m_forBeList.get(0);
        mutexForBe.unlock();
        m_forBeList.remove(0);
        return task;
    }
}
