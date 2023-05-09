/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management;

/**
 *
 * @author it
 */
public class KaryawanSession {
    private static int u_id;
    private static String u_username;
     
    public static int getU_id() {
        return u_id;
    }
 
    public static void setU_id(int u_id) {
        KaryawanSession.u_id = u_id;
    }
 
    public static String getU_username() {
        return u_username;
    }
    
    public static void setU_username(String u_username) {
        KaryawanSession.u_username = u_username;
    }
}
