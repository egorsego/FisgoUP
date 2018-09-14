/*
Класс для работы с SSH

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */

package com.dreamkas;

import java.io.*;
import java.io.IOException;

import ch.ethz.ssh2.*;

public class Ssh {
    private String m_ip;

    //конструктор по-умолчанию
    public Ssh() {

    }

    //Утановить ip адрес кассы
    public void setIp(String ip) {
        m_ip = ip;
    }

    //Выполнить команду bash по ssh
    public int executeSshCommand(String command) {
        System.out.println("Executing ssh command...");
        Connection conn = null;
        Session sess = null;
        try {
            conn = new Connection(m_ip);
            conn.connect();
            boolean isAuth = conn.authenticateWithPassword("root", "root");
            if (isAuth == false) {
                throw new IOException("Authentication failed.");
            }

            sess = conn.openSession();
            sess.execCommand(command);
            InputStream inp = sess.getStdout();
            InputStreamReader reader = new InputStreamReader(inp);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            sess.close();
            conn.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
            if (conn != null) {
                conn.close();
            }

            if (sess != null) {
                sess.close();
            }
            return -1;
        }
        return 0;
    }

    //Выполнить scp put
    public int executeScpPut(String path, String filename) {
        Connection conn = null;
        SCPClient scpc = null;
        try {
            conn = new Connection(m_ip);
            conn.connect();
            boolean isAuth = conn.authenticateWithPassword("root", "root");
            if (isAuth == false) {
                throw new IOException("Authentication failed.");
            }

            scpc = conn.createSCPClient();
            scpc.put(filename, path);

            conn.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
            if (conn != null) {
                conn.close();
                return -1;
            }
        }
        return 0;
    }

    //Выполнить scp put
    public int executeScpPut(String path, String[] filenames) {
        Connection conn = null;
        SCPClient scpc = null;
        try {
            conn = new Connection(m_ip);
            conn.connect();
            boolean isAuth = conn.authenticateWithPassword("root", "root");
            if (isAuth == false) {
                throw new IOException("Authentication failed.");
            }

            scpc = conn.createSCPClient();
            scpc.put(filenames, path);

            conn.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
            if (conn != null) {
                conn.close();
                return -1;
            }
        }
        return 0;
    }

    //Выполнить scp get
    public int executeScpGet(String localPath, String remotePath) {
        Connection conn = null;
        SCPClient scpc = null;
        try {
            conn = new Connection(m_ip);
            conn.connect();
            boolean isAuth = conn.authenticateWithPassword("root", "root");
            if (isAuth == false) {
                throw new IOException("Authentication failed.");
            }

            scpc = conn.createSCPClient();
            scpc.get(remotePath, localPath);

            conn.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
            if (conn != null) {
                conn.close();
                return -1;
            }
        }
        return 0;
    }
}
