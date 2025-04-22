/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
import java.util.logging.Level;


public class MySQLConnection {
    
    private static final String url = "jdbc:mysql://localhost:3306/ap4usertable";
    private static final String user = "root";
    private static final String pass = "";
//    private static final String url = "jdbc:mysql://172.28.37.17:3306/ap4usertable"; // Remplacez l'adresse par celle de votre serveur pour l'épreuve.
//    private static final String user = "ap4bts2";                                    // Si c'est pas fait, créez un utilisateur sur phpmyadmin/adminer.
//    private static final String pass = "Btssio82300";
    
    private static Connection con = null;
    
    public static Connection getConnexion() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
            } catch (Exception ex) {
                Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
}
