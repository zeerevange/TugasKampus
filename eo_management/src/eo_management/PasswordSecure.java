/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author muham
 */
public class PasswordSecure {
    public static String md5Encode(String input) {
        StringBuilder encodedString = new StringBuilder();
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            
            byte[] byteData = md.digest();
            
            for (byte b : byteData) {
                encodedString.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return encodedString.toString();
    }
    
    public static String md5Decode(String input) {
        StringBuilder sb = new StringBuilder();
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            
            byte[] byteData = md.digest();
            
            for (byte b : byteData) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return sb.toString();
    }
}
