/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.view;

import eo_management.ThrowPesananLayananData;
import eo_management.koneksi.koneksi;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author it
 */
public class Pembayaran extends javax.swing.JDialog {
        private Connection conn = new koneksi().connect();
        private DefaultTableModel tabmode;
        
        java.util.Date date= new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
    /**
     * Creates new form Role
     */
    public Pembayaran(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initUI();
        dataTable();
        disableButton();
        combobox_tipe_pembayaran();
        combobox_bank();
        
        //fungsi pencarian
        txtCari.getDocument().addDocumentListener(new DocumentListener() {
            @Override
                public void insertUpdate(DocumentEvent e) {
                dataTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               dataTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                dataTable();
            }
        });
        
        ThrowPesananLayananData throwDataPesanan = new ThrowPesananLayananData();
        
        if (throwDataPesanan.getId_PesananLayanan() != null) {
            this.getIdTrx ();
            this.getDataIdPesananLayanan(throwDataPesanan.getId_PesananLayanan());
            
            btnSimpan.setEnabled(true);
            tglPembayaran.setEnabled(true);
            txtJumlahPembayaran.setEnabled(true);
            enableButton();
            
            try {
                String sql = "SELECT \n" +
                            "GROUP_CONCAT(pesanan_addon.id_pesanan_addon SEPARATOR ', ') AS id_pesanan_addon\n" +
                            "FROM pesanan_addon\n" +
                            "WHERE pesanan_addon.id_pesanan_layanan='"+ throwDataPesanan.getId_PesananLayanan() +"';";
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);

                if (rs.next()){
                    String id_pesanan_addon = rs.getString("id_pesanan_addon");
                    txtIdPesananAddon.setText(id_pesanan_addon);
                } else {
                    txtIdPesananAddon.setText("");
                }
            }catch (SQLException e){ 
                JOptionPane.showMessageDialog(null, "Id otomatis tidak berjalan. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            Integer total_tagihan = this.getTotalTagihan (throwDataPesanan.getId_PesananLayanan(), 3);
            txtJumlahTagihan.setText(new java.text.DecimalFormat("Rp#,##0").format(total_tagihan));
        }
        
        
    }
    //colorchange
    public void changecolor(JPanel hover, Color rand) {
        hover.setBackground(rand);
    }
    
    private void initUI(){ 
        getContentPane().setBackground(new Color(245, 245, 245));
        
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        setLocation(dx, dy);
    }
    
    private void enableButton() {
        btnSimpan.setEnabled(true);
        txtIdPembayaran.setEnabled(false);
        txtIdPesananLayanan.setEnabled(true);
        tglPembayaran.setEnabled(true);
        txtJumlahPembayaran.setEnabled(true);
        btnCariPesananLayanan.setEnabled(true);
        cbxTipePembayaran.setEnabled(true);
        cbxBankTujuan.setEnabled(true);
    }
    
    private void clear() {
        txtIdPembayaran.setText("");
        txtIdPesananLayanan.setText("");
        txtIdPesananAddon.setText("");
        tglPembayaran.setDate(null);
        txtJumlahPembayaran.setText("");
        txtJumlahTagihan.setText("");
        cbxTipePembayaran.setSelectedIndex(0);
        cbxBankTujuan.setSelectedIndex(0);
    }
    
    private void combobox_tipe_pembayaran(){
        cbxTipePembayaran.addItem("Transfer Bank");
        cbxTipePembayaran.addItem("QRIS");
        cbxTipePembayaran.addItem("Kartu Kredit");
    }
    
    private void combobox_bank(){
        cbxBankTujuan.addItem("BCA");
        cbxBankTujuan.addItem("BRI");
        cbxBankTujuan.addItem("Mandiri");
        cbxBankTujuan.addItem("CIMB Niaga");
    }
    
    private void disableButton(){
        txtIdPembayaran.setEnabled(false);
        txtIdPesananLayanan.setEnabled(false);
        txtIdPesananAddon.setEnabled(false);
        tglPembayaran.setEnabled(false);
        txtJumlahPembayaran.setEnabled(false);
        txtJumlahTagihan.setEnabled(false);
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnHapus.setEnabled(false);
        btnCariPesananLayanan.setEnabled(false);
        cbxTipePembayaran.setEnabled(false);
        cbxBankTujuan.setEnabled(false);
    }
    
    private void getIdTrx(){
        try {
            String sql = "SELECT * FROM pembayaran ORDER BY id_pembayaran DESC";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            if (rs.next()){
                String kode = rs.getString("id_pesanan_layanan").substring(5);
                String AN = "" + (Integer.parseInt(kode) + 1);
                String Nol = "";
                
                if (AN.length() == 1)
                {Nol = "0000";}
                else if (AN.length() == 2)
                {Nol = "000";}
                else if (AN.length() == 3)
                {Nol = "00";}
                else if (AN.length() == 4)
                {Nol = "0";}
                else if (AN.length() == 5)
                {Nol = "";}
                
                txtIdPembayaran.setText("TRX" + Nol + AN);
            } else {
                txtIdPembayaran.setText("TRX" + "00001");
            }
        }catch (SQLException e){ 
            JOptionPane.showMessageDialog(null, "Id otomatis tidak berjalan. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void getDataIdPesananLayanan(String data) {
        txtIdPesananLayanan.setText(data);
        txtIdPesananLayanan.setEnabled(false);
    }
    
    public void dataTable() {
        Object[] header = {"ID Transaksi", "Tanggal", "ID Pesanan", "Jumlah Pembayaran", "Sisa", "Tipe", "Tujuan"};
        tabmode = new DefaultTableModel (null, header);
        try {
            String cariitem = txtCari.getText();
            
            String sql = "SELECT * FROM pembayaran  WHERE id_pembayaran LIKE '%"
                    + cariitem+ "%' or id_pesanan_layanan LIKE '%" 
                    + cariitem+ "%' or tipe_pembayaran LIKE '%"
                    + cariitem+ "%' or tujuan_rekening_bank_pembayan LIKE '%"
                    + cariitem+ "%' ORDER BY id_pembayaran asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();

            
            while (hasil.next()) {
                 tabmode.addRow(new Object[] {
                    hasil.getString(1),
                    hasil.getString(3),
                    hasil.getString(2),
                    new java.text.DecimalFormat("Rp#,##0").format(hasil.getInt(4)),
                    new java.text.DecimalFormat("Rp#,##0").format(hasil.getInt(5)),
                    hasil.getString(6),
                    hasil.getString(7),
                 });
            }
            
            tabelPembayaran.setModel(tabmode);

            // Set header renderer untuk header di tabel
            JTableHeader headerTable = tabelPembayaran.getTableHeader();
            headerTable.setDefaultRenderer(new HeaderRenderer());

            // Set cell renderer untuk kolom-kolom di tabel
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            for (int i = 0; i < tabelPembayaran.getColumnCount(); i++) {
                tabelPembayaran.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}
    
private Integer getTotalTagihan (String id_pesanan_layanan, Integer type) {
    Integer total_tagihan = 0;
    Integer ppn_11_persen = 0;
    Integer grand_total = 0;
    Integer jumlah_pembayaran = 0;
    Integer sisa_tagihan = 0;
        
    try {
        //  CHECK TOTAL TAGIHAN PADA PESANAN LAYANAN & ADDON 
        String sql = "SELECT \n" +
                            "pesanan_layanan.id_pesanan_layanan,\n" +
                            "((paket_layanan.harga_paket_layanan * pesanan_layanan.jumlah_peserta_pesanan_layanan) + SUM(paket_addon.harga_paket_addon * pesanan_addon.jumlah_pesanan_addon)) AS subtotal,\n" +
                            "ROUND(((paket_layanan.harga_paket_layanan * pesanan_layanan.jumlah_peserta_pesanan_layanan) + SUM(paket_addon.harga_paket_addon * pesanan_addon.jumlah_pesanan_addon)) * 0.11) AS ppn_11_persen,\n" +
                            "ROUND(((paket_layanan.harga_paket_layanan * pesanan_layanan.jumlah_peserta_pesanan_layanan) + SUM(paket_addon.harga_paket_addon * pesanan_addon.jumlah_pesanan_addon)) + ((paket_layanan.harga_paket_layanan * pesanan_layanan.jumlah_peserta_pesanan_layanan) + SUM(paket_addon.harga_paket_addon * pesanan_addon.jumlah_pesanan_addon)) * 0.11) AS grand_total\n" +
                            "FROM pesanan_layanan\n" +
                            "LEFT JOIN paket_layanan ON pesanan_layanan.id_paket_layanan = paket_layanan.id_paket_layanan\n" +
                            "LEFT JOIN pesanan_addon ON pesanan_addon.id_pesanan_layanan = pesanan_layanan.id_pesanan_layanan\n" +
                            "LEFT JOIN paket_addon ON paket_addon.id_paket_addon = pesanan_addon.id_paket_addon\n" +
                            "WHERE pesanan_layanan.id_pesanan_layanan = '"+id_pesanan_layanan+"';";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet hasil_pesanan_layanan = ps.executeQuery();

        if (hasil_pesanan_layanan.next()){
            total_tagihan = hasil_pesanan_layanan.getInt("subtotal");
            ppn_11_persen = hasil_pesanan_layanan.getInt("ppn_11_persen");
            grand_total = hasil_pesanan_layanan.getInt("grand_total");
        } else {
            total_tagihan = 0;
            ppn_11_persen = 0;
            grand_total = 0;
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghitung total tagihan : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    try {
        //  CHECK TOTAL TAGIHAN PADA PESANAN LAYANAN & ADDON 
        String sql = "SELECT SUM(jumlah_pembayaran) AS jumlah_pembayaran FROM pembayaran WHERE id_pesanan_layanan = '"+id_pesanan_layanan+"';";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet hasil_pembayaran = ps.executeQuery();

        if (hasil_pembayaran.next()){
            sisa_tagihan =  grand_total - hasil_pembayaran.getInt("jumlah_pembayaran");
            jumlah_pembayaran = hasil_pembayaran.getInt("jumlah_pembayaran");
        } else {
            sisa_tagihan = 0;
            jumlah_pembayaran = 0;
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghitung sisa tagihan : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    if (type == 1) {
        return total_tagihan;
    } else if (type == 2) {
        return ppn_11_persen;
    } else if (type == 3) {
        return grand_total;
    } else if (type == 4) {
        return jumlah_pembayaran;
    }  else if (type == 5) {
        return sisa_tagihan;
    } else {
        return 0;
    }
    
}

private void updatePembayaran(String id_pesanan_layanan, Integer jumlah_pembayaran, String type) {
    if (id_pesanan_layanan != null) {
        
        Integer total_tagihan = this.getTotalTagihan(id_pesanan_layanan, 3);
        Integer sum_jumlah_pembayaran = this.getTotalTagihan(id_pesanan_layanan, 4);
        
        try {
//          UPDATE TAGIHAN PADA PESANAN LAYANAN & ADDON 
            Boolean status_lunas = false;
            
            if (type == "plus") {
                if ((jumlah_pembayaran + sum_jumlah_pembayaran) >= total_tagihan) {
                    status_lunas = true;
                } else {
                    status_lunas = false;
                }
            } else if (type == "min") {
                if (sum_jumlah_pembayaran >= total_tagihan) {
                    status_lunas = true;
                } else {
                    status_lunas = false;
                }
            }
            
            
            String sql = "UPDATE pesanan_layanan SET status_lunas_pesanan_layanan=? WHERE id_pesanan_layanan = '"+ txtIdPesananLayanan.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
                        
            stat.setBoolean(1, status_lunas);
                        
            int rowsAffected = stat.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data status tagihan berhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data status tagihan gagal diubah", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghitung total tagihan : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class HeaderRenderer implements TableCellRenderer {
    JLabel label;

    public HeaderRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(new Color(11, 36, 71)); // Set warna background header
        label.setForeground(Color.WHITE); // Set warna font header
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        label.setText(value.toString());
        return label;
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ButtonClose = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnTambah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPembayaran = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtIdPembayaran = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtJumlahPembayaran = new javax.swing.JTextField();
        tglPembayaran = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtIdPesananLayanan = new javax.swing.JTextField();
        btnCariPesananLayanan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtIdPesananAddon = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtJumlahTagihan = new javax.swing.JTextField();
        cbxTipePembayaran = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbxBankTujuan = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Kategori Games");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(1, 86, 153));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pembayaran");

        ButtonClose.setBackground(new java.awt.Color(1, 86, 153));
        ButtonClose.setPreferredSize(new java.awt.Dimension(60, 0));
        ButtonClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonCloseMouseExited(evt);
            }
        });
        ButtonClose.setLayout(new java.awt.BorderLayout());

        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/Close.png"))); // NOI18N
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
        });
        ButtonClose.add(exit, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(641, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(372, 372, 372)
                .addComponent(ButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(ButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(0, 135, 242));
        jPanel2.setPreferredSize(new java.awt.Dimension(799, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1355, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/add.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.setFocusable(false);
        btnTambah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTambah.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTambah);

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/save-file.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setFocusable(false);
        btnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimpan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSimpan);

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/delete.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.setFocusable(false);
        btnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHapus.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jToolBar1.add(btnHapus);

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/return.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusable(false);
        btnBatal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBatal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBatal);

        tabelPembayaran.setAutoCreateRowSorter(true);
        tabelPembayaran.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabelPembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelPembayaran.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelPembayaran.setOpaque(false);
        tabelPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPembayaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPembayaran);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Pembayaran", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setText("ID Pembayaran :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("Tanggal Pembayaran :");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setText("Jumlah Pembayaran :");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setText("ID Pesanan Layanan :");

        btnCariPesananLayanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/Search.png"))); // NOI18N
        btnCariPesananLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPesananLayananActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel7.setText("ID Pesanan Addon :");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setText("Jumlah Tagihan + PPn 11% :");

        cbxTipePembayaran.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxTipePembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan" }));
        cbxTipePembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipePembayaranActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel10.setText("Tipe Pembayaran :");

        cbxBankTujuan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxBankTujuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan" }));
        cbxBankTujuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBankTujuanActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel11.setText("Bank Tujuan :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(17, 17, 17))
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxBankTujuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxTipePembayaran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tglPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtJumlahPembayaran)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdPembayaran)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txtIdPesananLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariPesananLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIdPesananAddon)))
                    .addComponent(txtJumlahTagihan, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtIdPesananLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCariPesananLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIdPesananAddon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtJumlahTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(tglPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlahPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxBankTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/Search.png"))); // NOI18N
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCari)
                    .addComponent(btnCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        disableButton();
        clear();
        
        enableButton();
        getIdTrx();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (txtIdPesananLayanan.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Isi ID Pesanan Layanan terlebih dahulu");
                txtIdPembayaran.requestFocus();
            } else {
                try {
                    String sql = "INSERT INTO pembayaran (id_pembayaran, id_pesanan_layanan, tanggal_pembayaran, jumlah_pembayaran, sisa_pembayaran, tipe_pembayaran, tujuan_rekening_bank_pembayan) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stat = conn.prepareStatement(sql);
                    
                    stat.setString(1, txtIdPembayaran.getText());
                    stat.setString(2, txtIdPesananLayanan.getText());
                    stat.setString(3, sdf.format(tglPembayaran.getDate()));
                    stat.setString(4, txtJumlahPembayaran.getText());
                    
                    Integer sum_jumlah_pembayaran = this.getTotalTagihan(txtIdPesananLayanan.getText(), 4);
                    Integer sisa_pembayaran = Integer.parseInt(txtJumlahTagihan.getText().replace("Rp", "").replace(",", "").trim()) - (Integer.parseInt(txtJumlahPembayaran.getText()) + sum_jumlah_pembayaran);
                    stat.setInt(5, sisa_pembayaran);
                    
                    String selectedItemPembayaran = cbxTipePembayaran.getSelectedItem().toString();
                    
                    stat.setString(6, selectedItemPembayaran);
                    
                    String selectedItemBankTujuan = cbxBankTujuan.getSelectedItem().toString();
                    
                    stat.setString(7, selectedItemBankTujuan);
                    
                    int rowsAffected = stat.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Data pembayaran berhasil disimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Data pembayaran gagal disimpan", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
//                  UPDATE STATUS LUNAS TAGIHAN
                    this.updatePembayaran(txtIdPesananLayanan.getText(), Integer.parseInt(txtJumlahPembayaran.getText()), "plus");
                    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data pembayaran: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            clear();
            dataTable();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus data ini?", "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM pembayaran WHERE id_pembayaran=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtIdPembayaran.getText());
                int rowsAffected = stat.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil terhapus", "Hapus Data", JOptionPane.INFORMATION_MESSAGE);
                    
                    
//                  UPDATE STATUS LUNAS TAGIHAN
                    this.updatePembayaran(txtIdPesananLayanan.getText(), Integer.parseInt(txtJumlahPembayaran.getText()), "min");

                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal terhapus", "Hapus Data", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        clear();
        disableButton();
        dataTable();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        disableButton();
        clear();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void tabelPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPembayaranMouseClicked
        int bar = tabelPembayaran.getSelectedRow();
//         {"ID Transaksi", "Tanggal", "ID Pesanan", "Jumlah Pembayaran", "Sisa", "Tipe", "Tujuan"};
                
        txtIdPembayaran.setText(tabelPembayaran.getValueAt(bar,0).toString());
        
        txtIdPesananLayanan.setText(tabelPembayaran.getValueAt(bar,2).toString());
        java.util.Date tgl_x =null;
            try {
                tgl_x = sdf.parse(tabelPembayaran.getValueAt(bar,1).toString());
            } catch (ParseException ex) {
                Logger.getLogger(Pembayaran.class.getName()).log(Level.SEVERE, null,ex);
            }
            
        txtJumlahTagihan.setText("");
        tglPembayaran.setDate(tgl_x);;
       
        txtJumlahPembayaran.setText(tabelPembayaran.getValueAt(bar,3).toString().replace("Rp", "").replace(",", "").trim());
        
        btnSimpan.setEnabled(false);
        btnTambah.setEnabled(false);
        btnHapus.setEnabled(true);
    }//GEN-LAST:event_tabelPembayaranMouseClicked

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        String ObjButton[] = {"YES","NO"};
        int pilihan = JOptionPane.showOptionDialog(null,"Ingin keluar halaman ?","Message", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null,ObjButton,ObjButton[1]);
        if(pilihan == 0){
            this.dispose();
        }
    }//GEN-LAST:event_exitMouseClicked

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        changecolor(ButtonClose, Color.RED);
    }//GEN-LAST:event_exitMouseEntered

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        changecolor(ButtonClose, new Color (11,36,71));
    }//GEN-LAST:event_exitMouseExited

    private void ButtonCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonCloseMouseEntered

    }//GEN-LAST:event_ButtonCloseMouseEntered

    private void ButtonCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonCloseMouseExited

    }//GEN-LAST:event_ButtonCloseMouseExited

    private void btnCariPesananLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPesananLayananActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
        PopUpPesananLayanan popUpPesananLayanan = new PopUpPesananLayanan(new javax.swing.JFrame(), true);
        popUpPesananLayanan.setScreenType("Pembayaran");
        popUpPesananLayanan.setVisible(true);
    }//GEN-LAST:event_btnCariPesananLayananActionPerformed

    private void cbxTipePembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipePembayaranActionPerformed
        //        int idx = cbxJabatan.getSelectedIndex();
        //
        //        if (arrJabatan.size() > 0) {
            //            id_jab = arrJabatan.get(idx).getId();
            //            gaji = arrJabatan.get(idx).getGaji();
            //        }
    }//GEN-LAST:event_cbxTipePembayaranActionPerformed

    private void cbxBankTujuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBankTujuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBankTujuanActionPerformed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariKeyTyped

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dataTable();
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        dataTable();
    }//GEN-LAST:event_btnCariActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pembayaran dialog = new Pembayaran(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ButtonClose;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCariPesananLayanan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbxBankTujuan;
    private javax.swing.JComboBox<String> cbxTipePembayaran;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tabelPembayaran;
    private com.toedter.calendar.JDateChooser tglPembayaran;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIdPembayaran;
    private javax.swing.JTextField txtIdPesananAddon;
    private javax.swing.JTextField txtIdPesananLayanan;
    private javax.swing.JTextField txtJumlahPembayaran;
    private javax.swing.JTextField txtJumlahTagihan;
    // End of variables declaration//GEN-END:variables
}
