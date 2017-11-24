/*
Класс работы с базой данных

@author  Kashitsyn Denis
@version 1.0
@since   2017-08-23

 */

package com.dreamkas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configDb.db");
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            Statement stmt    = null;

            for(int i = 1; i < table.size(); i++){
                String query = "";
                if(table.get(i).get(2).equals("12")){
                    /*pstmt.setString(1,
                            new String(table.get(i).get(1).getBytes(Charset.forName("cp866"))));*/
                    /*query = "UPDATE CONFIG SET " + table.get(i).get(0) + "='"
                            + new String(table.get(i).get(1).getBytes(Charset.forName("cp866"))) + "' WHERE ID=1";*/

                    query = "UPDATE CONFIG SET " + table.get(i).get(0) + "='"
                            + table.get(i).get(1) + "' WHERE ID=1";
                } else if(table.get(i).get(2).equals("4")) {
                    //pstmt.setInt(1, Integer.valueOf(table.get(i).get(1)));
                    /*query = "UPDATE CONFIG SET " + table.get(i).get(0) + "="
                            + new String(table.get(i).get(1).getBytes(Charset.forName("cp866"))) + " WHERE ID=1";*/
                    query = "UPDATE CONFIG SET " + table.get(i).get(0) + "="
                            + table.get(i).get(1) + " WHERE ID=1";
                } else {
                    query = "";
                }

                query = new String(query.getBytes(Charset.forName("cp866")));
                PreparedStatement pstmt = c.prepareStatement(query);
                if(query.equals("")) {
                    System.out.println("Error in creating query!");
                    continue;
                }

                /*PreparedStatement pstmt = con.prepareStatement(qry);
                pstmt.setString(1, value);
                pstmt.setInt(2, primaryId);
                pstmt.executeUpdate();*/

                /*stmt              = c.createStatement();
                stmt.executeUpdate( query );
                stmt.close();*/

                pstmt.executeUpdate();
            }

            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
    }
}
