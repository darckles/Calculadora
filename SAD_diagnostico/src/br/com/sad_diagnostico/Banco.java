/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad_diagnostico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author dpalmeida
 */
public class Banco {

    public String Sitomas(String ID) {
        Connection c = null;
        Statement stmt = null;
        String descricao = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:/Users/SIM/Documents/doenca.db");
            c.setAutoCommit(false);
            //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DESCRICAO FROM SINTOMAS WHERE IDENT ='" + ID + "'");
            while (rs.next()) {
                //   int ident = rs.getInt("IDENT");
                descricao = rs.getString("DESCRICAO");
                //    System.out.println("ID = " + ident);
                System.out.println("NAME = " + descricao);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return descricao + ID;

    }

    public String Pesquisa(int cont, String ID, ArrayList sintoma) {
        Connection c = null;
        Statement stmt = null;
        ArrayList<String> lista = new ArrayList<String>();

        lista.add("SINT1");

        String recebe = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:/Users/SIM/Documents/doenca.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = null;

            switch (cont) {

                case 1:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
                case 2:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
                case 3:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
                case 4:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
                case 5:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
                case 6:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
                case 7:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
                case 8:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
                case 9:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
                case 10:
                    rs = stmt.executeQuery("SELECT * FROM CATEGORIAS where SINT" + cont + " = '" + ID + "' ");
                    break;
            }

          //  while (rs.next()) {

                int id = rs.getInt("CAT");
                String name = rs.getString("DESCRICAO");
                String SINT = rs.getString("SINT" + cont);

                recebe = SINT;

                Sitomas(SINT);
                //  System.out.println("Sintoma" + name);
                //  System.out.println("Recebe" + recebe);
      //      }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        if (cont == 5) {
            //        listar();
        }

        return recebe;
    }

}
