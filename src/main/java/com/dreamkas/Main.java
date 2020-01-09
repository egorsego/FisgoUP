package com.dreamkas;

public class Main {

    public static void main(String[] args) {
        try {
            System.setProperty("file.encoding", "UTF-8");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

	    // write your code here
        TaskBuffer tb = new TaskBuffer();
        BackEnd beT = new BackEnd();
        FrontEnd feT = new FrontEnd();

        beT.setBuffer(tb);
        feT.setBuffer(tb);

        beT.start();
        feT.start();
    }
}
