/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author it
 */
public class koneksi {
           private Connection koneksi;
           public Connection connect() {
           try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil Terkoneksi");
                }
                    catch (ClassNotFoundException e) {
                    System.out.println("Gagal Terkoneksi" + e.getMessage());    
                }           
                    String url = "jdbc:mysql://devlab.santeknovatif.xyz/:3309/eo_management?autoReconnect=true&useSSL=true";
                    try {
                            koneksi = DriverManager.getConnection (url,"kelompok2","kelompok2kuy");
                            System.out.println("berhasil koneksi ke database");
                        } catch (SQLException e) {
                            System.out.println("gagal terkoneksi ke database" + e.getMessage());
                        }
                  return koneksi;
            }
}
