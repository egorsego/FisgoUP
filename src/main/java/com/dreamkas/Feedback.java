/*
Класс фидбэкового сообщения для отображения
пользователю в гуе

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */

package com.dreamkas;

public class Feedback extends Task {
    private String m_message;

    //конструктор по-умолчанию
    public Feedback(String message) {
        super("Feedback");
        this.m_message = message;
    }

    //получить текст команды
    public String getMessage(){
        return m_message;
    }
}
