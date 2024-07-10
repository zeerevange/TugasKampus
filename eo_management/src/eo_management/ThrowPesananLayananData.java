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
    private static String id_pelanggan, id_karyawan, nama_karyawan, id_paket_layanan, jumlah_peserta, mode_input, id_pesanan_layanan, id_sub_kategori_layanan, id_rincian_acara, id_game, nama_game;
    
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
    
    public String getId_Karyawan() {
        return this.id_karyawan;
    }
    
    public void setId_Karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }
    
    public String getNama_Karyawan() {
        return this.nama_karyawan;
    }
    
    public void setNama_Karyawan(String nama_karyawan) {
        this.nama_karyawan = nama_karyawan;
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
    
    //    RINCIAN ACARA 
    
    public static String getId_RincianAcara() {
        return id_rincian_acara;
    }
    
    public static void setId_RincianAcara(String id_rincian_acara) {
        ThrowPesananLayananData.id_rincian_acara = id_rincian_acara;
    }
    
    
    //    RINCIAN GAME
    
    public static String getId_Game() {
        return id_game;
    }
    
    public static void setId_Game(String id_game) {
        ThrowPesananLayananData.id_game = id_game;
    }
    
    public static String getNama_Game() {
        return nama_game;
    }
    
    public static void setNama_Game(String nama_game) {
        ThrowPesananLayananData.nama_game = nama_game;
    }

    
    
}
