/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.view;

/**
 *
 * @author it
 */
public class ClassJabatanKaryawan {
    int id, gaji;
    String nama;
    
    public ClassJabatanKaryawan (int id, int gaji, String nama) {
        this.id = id;
        this.gaji = gaji;
        this.nama = nama;
    }
    
    public int getId() {
        return id;
    }
    
    public int getGaji() {
        return gaji;
    }
    
    public String getNama() {
        return nama;
    }
      
}
