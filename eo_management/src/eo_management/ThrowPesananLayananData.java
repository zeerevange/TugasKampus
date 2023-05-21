/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management;

/**
 *
 * @author muham
 */
public class ThrowPesananLayananData {
    private static String id_pelanggan, id_paket_layanan, jumlah_peserta, mode_input, id_pesanan_layanan, id_sub_kategori_layanan;
    
    public static String getId_PesananLayanan() {
        return id_pesanan_layanan;
    }
    
    public static void setId_PesananLayanan(String id_pesanan_layanan) {
        ThrowPesananLayananData.id_pesanan_layanan = id_pesanan_layanan;
    }
    
    public String getId_Pelanggan() {
        return this.id_pelanggan;
    }
    
    public void setId_Pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }
    
    public static String getId_PaketLayanan() {
        return id_paket_layanan;
    }
    
    public static void setId_PaketLayanan(String id_paket_layanan) {
        ThrowPesananLayananData.id_paket_layanan = id_paket_layanan;
    }
    
    public static String getJumlahPeserta() {
        return jumlah_peserta;
    }
    
    public static void setJumlahPeserta(String jumlah_peserta) {
        ThrowPesananLayananData.jumlah_peserta = jumlah_peserta;
    }
    
    public static String getModeInput() {
        return mode_input;
    }
    
    public static void setModeInput(String mode_input) {
        ThrowPesananLayananData.mode_input = mode_input;
    }
    
    
//    PESANAN ADDON LAYANAN 
    
    public static String getId_SubKategoriLayanan() {
        return id_sub_kategori_layanan;
    }
    
    public static void setId_SubKategoriLayanan(String id_sub_kategori_layanan) {
        ThrowPesananLayananData.id_sub_kategori_layanan = id_sub_kategori_layanan;
    }
    
    

    
    
}
