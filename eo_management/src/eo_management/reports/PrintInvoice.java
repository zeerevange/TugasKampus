/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.reports;

import eo_management.koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author muhamadfahmi
 */
public class PrintInvoice {
    
    private Connection conn = new koneksi().connect();
    
    public void print(String invoiceId) {
        try {

            // Mengambil home directory dari sistem
            String userHome = System.getProperty("user.home");
            
             // Path untuk menyimpan file PDF
            String outputFile = userHome + "/KKP_Reports/invoice_" + invoiceId + ".pdf"; // Nama file disesuaikan dengan nomor invoice atau lainnya
            
            // Path ke file template Jasper
            String reportPath = "src/eo_management/reports/InvoiceTransaksiLayanan.jasper"; // Ubah sesuai dengan lokasi file template Anda

            // Memasukkan hasil query ke dalam Map
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("NO_INVOICE", invoiceId);
            
            // Kompilasi template laporan
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, conn);

            // Menampilkan konfirmasi sebelum menyimpan
            int option = JOptionPane.showConfirmDialog(null, "Your file will be save to "+ outputFile +" \nDo you want to save the invoice as PDF?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Ekspor laporan ke file PDF
                JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile);
                System.out.println("File PDF berhasil diunduh: " + outputFile);
            } else {
                System.out.println("Batal menyimpan file PDF.");
            }
            


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mencetak invoice: " + e.getMessage());
        }
    }
}
