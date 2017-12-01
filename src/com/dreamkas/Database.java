/*
Класс работы с базой данных

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */

package com.dreamkas;
import org.sqlite.SQLiteConfig;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.*;

public class Database {
    private Connection c;

    public Database() {
        c = null;
    }

    /*
    Get kkt version
     */
    public String getKktVersion(){
        Statement stmt    = null;
        String    version = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configDb.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM CONFIG;" );

            while ( rs.next() ) {
                version = rs.getString("FISGO_VERSION");
                System.out.println( "FisGo Version = " + version );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
        return version;
    }

    /*
    Get config fields list
     */
    public ArrayList<ArrayList<String>> getConfigTable(){
        ArrayList<ArrayList<String>> fields = new ArrayList<>();

        try {
            Properties connInfo = new Properties();
            connInfo.put("user", "");
            connInfo.put("password", "");
            connInfo.put("charSet", "cp866");

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configDb.db", connInfo);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            Statement stmt    = null;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM CONFIG;" );

            ResultSet rsColumns = null;
            DatabaseMetaData meta = c.getMetaData();
            rsColumns
                    = meta.getColumns(null, null, "config", null);


            while ( rsColumns.next() ) {
                String colName  = rsColumns.getString("COLUMN_NAME");
                String dataType = rsColumns.getString("DATA_TYPE");
                ArrayList<String> field = new ArrayList<>();
                field.add(colName);
                field.add(dataType);
                fields.add(field);
            }

            try {
                SortedMap<String,Charset> registeredCharsets = Charset.availableCharsets();
                for ( Iterator<Charset> charsets = registeredCharsets.values().iterator(); charsets.hasNext(); ) {
                    Charset charset = charsets.next();
                    // Display name
                    System.out.print(charset.name() + " Aliases: [");
                    // Display aliases
                    for ( Iterator<String> aliases = charset.aliases().iterator(); aliases.hasNext(); ) {
                        System.out.print(aliases.next());
                        if ( aliases.hasNext() ) System.out.print(", ");
                    }
                    System.out.println("]");
                }


                System.out.println("\nIs cp866 supported? - " + Charset.isSupported("cp866")); // testing aliase
                System.out.println("Is IBM866 supported? - " + Charset.isSupported("IBM866"));
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }

            for(int i = 0; i < fields.size(); i++) {
                String value = new String(rs.getBytes(fields.get(i).get(0)), Charset.forName("cp866"));
                fields.get(i).add(value);
            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
        return fields;
    }

    /*
    Set config field list
     */
    public void setConfigTable(Vector<Vector<String>> table) {
        try {
            /*Properties connInfo = new Properties();
            connInfo.put("user", "");
            connInfo.put("password", "");
            connInfo.put("charSet", "cp866");

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configDb.db", connInfo);
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            Statement stmt    = null;*/

            PrintWriter pw = new PrintWriter("updateConfigDb.sh", "cp866");


            pw.println("#!/bin/sh");
            pw.append("\n");
            pw.println("sqlite3 <<EOF");
            pw.println(".open configDb.db");

            for(int i = 1; i < table.size(); i++){
                String query = "";
                if(table.get(i).get(2).equals("12")){
                    String value = new String(table.get(i).get(1));
                    query = "UPDATE CONFIG SET " + table.get(i).get(0) + "='"
                            + value + "' WHERE ID=1;";
                } else if(table.get(i).get(2).equals("4")) {
                    String value = new String(table.get(i).get(1));
                    query = "UPDATE CONFIG SET " + table.get(i).get(0) + "="
                            + value + " WHERE ID=1;";
                } else {
                    query = "";
                }

                if(query.equals("")) {
                    System.out.println("Error in creating query!");
                    continue;
                }


                pw.println(query);
            }

            pw.println("EOF");
            pw.append("\n");

            pw.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
    }
}
