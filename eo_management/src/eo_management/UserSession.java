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
public class UserSession {
    private static String u_id;
    private static String u_username;
    private static String u_email;
    private static String u_no_hp;
     
    public String getU_id() {
        return this.u_id;
    }
 
    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
 
    public String getU_username() {
        return this.u_username;
    }
    
    public void setU_username(String u_username) {
        this.u_username = u_username;
    }
    
    public void setU_Email(String u_email) {
        this.u_email = u_email;
    }
    
    public String getU_Email() {
        return this.u_email;
    }
    
    public void setU_NoHp(String u_no_hp) {
        this.u_no_hp = u_no_hp;
    }
    
    public String getU_NoHp() {
        return this.u_no_hp;
    }
}
