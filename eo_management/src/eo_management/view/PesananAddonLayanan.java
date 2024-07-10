/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.view;

import eo_management.ThrowPesananLayananData;
import eo_management.koneksi.koneksi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

/**
 *
 * @author it
 */
public class PesananAddonLayanan extends javax.swing.JDialog {
        private Connection conn = new koneksi().connect();
        private DefaultTableModel tabmode;
        private static String mode, id_pesanan_addon_layanan, id_pesanan_layanan, id_paket_addon, jumlah_pesanan;
        
    /**
     * Creates new form PaketLayanan
     */
    public PesananAddonLayanan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //set ketengah layar
        initUI();
        dataTable();
        kode_id_otomatis();
        
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
        
        if (this.getMode() == "add") {
            txtId.setEnabled(false);
            
            enableButton();
            kode_id_otomatis();
            this.getDataIdPesananLayanan(throwDataPesanan.getId_PesananLayanan() == null ? this.getIdPesananLayanan() : throwDataPesanan.getId_PesananLayanan());
            this.getDataIdSubKategoriAddonLayanan(throwDataPesanan.getId_SubKategoriLayanan());
        } else if (this.getMode() == "edit") {
            this.getDataIdPesananLayanan(throwDataPesanan.getId_PesananLayanan() == null ? this.getIdPesananLayanan(): throwDataPesanan.getId_PesananLayanan());
            this.getDataIdSubKategoriAddonLayanan(throwDataPesanan.getId_SubKategoriLayanan() == null ? this.getIdPaketAddon() : throwDataPesanan.getId_SubKategoriLayanan());
            txtId.setText(this.getIdPesananAddonLayanan());
            txtJumlahPesanan.setText(this.getJumlahPeserta());
            System.out.println(throwDataPesanan.getId_SubKategoriLayanan());
            editButton();
        } else {
            disableButton();
            txtId.setEnabled(false);
            txtIdAddon.setEnabled(false);
        }
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
    
    public static void setMode(String mode) {
        PesananAddonLayanan.mode = mode;
    }
    
    public String getMode() {
        return this.mode;
    }
    
    public static void setIdPesananAddonLayanan(String id_pesanan_addon_layanan) {
        PesananAddonLayanan.id_pesanan_addon_layanan = id_pesanan_addon_layanan;
    }
    
    public String getIdPesananAddonLayanan() {
        return this.id_pesanan_addon_layanan;
    }
    
    public static void setIdPesananLayanan(String id_pesanan_layanan) {
        PesananAddonLayanan.id_pesanan_layanan = id_pesanan_layanan;
    }
    
    public String getIdPesananLayanan() {
        return this.id_pesanan_layanan;
    }
    
    public static void setIdPaketAddon(String id_paket_addon) {
        PesananAddonLayanan.id_paket_addon = id_paket_addon;
    }
    
    public String getIdPaketAddon() {
        return this.id_paket_addon;
    }
    
    public static void setJumlahPesanan(String jumlah_pesanan) {
        PesananAddonLayanan.jumlah_pesanan = jumlah_pesanan;
    }
    
    public String getJumlahPeserta() {
        return this.jumlah_pesanan;
    }
    
    private void enableButton() {
        btnTambahItem.setEnabled(true);
        txtIdPesananUtama.setEnabled(true);
        btnSimpan.setEnabled(true);
        txtIdAddon.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        txtJumlahPesanan.setEnabled(true);
        btnCariPaketAddonLayanan.setEnabled(true);
        btnCariPesananLayanan.setEnabled(true);
    }
    
    private void editButton(){
        txtId.setEnabled(false);
        txtJumlahPesanan.setEnabled(true);
//        txtIdPelanggan.setText("0");
//        txtIdPaketLayanan.setText("0");
        btnTambahItem.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnCariPaketAddonLayanan.setEnabled(true);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }
    
    private void clear() {
        txtId.setText("");
        txtIdPesananUtama.setText("");
        txtIdAddon.setText("");
        txtJumlahPesanan.setText("");
    }
    
    private void disableButton(){
        txtId.setEnabled(false);
        txtIdPesananUtama.setEnabled(false);
        txtIdAddon.setEnabled(false);
        txtJumlahPesanan.setEnabled(false);
        btnTambahItem.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnCariPaketAddonLayanan.setEnabled(false);
        btnCariPesananLayanan.setEnabled(false);
    }
    
    
    private void getDataIdPesananLayanan(String data) {
        txtIdPesananUtama.setText(data);
        txtIdPesananUtama.setEnabled(false);
    }
    
    private void getDataIdSubKategoriAddonLayanan(String data) {
        txtIdAddon.setText(data);
        txtIdAddon.setEnabled(false);
    }
    
    //memberikan kode id otomatis kepada id pelanggan
    private void kode_id_otomatis(){
        try {
            String sql = "SELECT * FROM pesanan_addon ORDER BY id_pesanan_addon DESC LIMIT 1";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            if (rs.next()){
                String kode = rs.getString("id_pesanan_addon").substring(3).replace("AN", "") ;
                System.out.println(kode);
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
                
                txtId.setText("INV" + Nol + AN + "AN");
            } else {
                txtId.setText("INV" + "00001AN");
            }
        }catch (SQLException e){ 
            JOptionPane.showMessageDialog(null, "Id otomatis tidak berjalan. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void dataTable() {
        Object[] header = {"Id. Pesanan Addon", "No. Faktur Induk", "ID Addon", "Nama Addon", "ID Pelanggan", "Nama Pelanggan", "Jumlah Pesanan"};
        tabmode = new DefaultTableModel (null, header);
        String cariitem = txtCari.getText();
        
        try {
            String sql = "SELECT * FROM pesanan_addon\n" +
                    "LEFT JOIN pesanan_layanan ON pesanan_addon.id_pesanan_layanan = pesanan_layanan.id_pesanan_layanan\n" +
                    "LEFT JOIN pelanggan ON pesanan_layanan.id_pelanggan = pelanggan.id_pelanggan\n" +
                    "LEFT JOIN paket_addon ON pesanan_addon.id_paket_addon = paket_addon.id_paket_addon\n" +
                    "LEFT JOIN sub_kategori_addon ON paket_addon.id_sub_kategori_addon = sub_kategori_addon.id_sub_kategori_addon " +
                    "WHERE pesanan_addon.id_pesanan_addon LIKE '%"
                    + cariitem+ "%' or pelanggan.nama_pelanggan LIKE '%" 
                    + cariitem+ "%' or pesanan_layanan.id_pesanan_layanan LIKE '%"
                    + cariitem+ "%' or sub_kategori_addon.nama_sub_kategori_addon LIKE '%"
                    + cariitem+ "%'ORDER BY pesanan_addon.id_pesanan_addon ASC;";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tabmode.addRow(new Object[] {
                    hasil.getString(1),
                    hasil.getString(3),
                    hasil.getString(2),
                    hasil.getString(21),
                    hasil.getString(6),
                    hasil.getString(12),
                    hasil.getString(4),
                });
            } 
            tabelPesananAddonLayanan.setModel(tabmode);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jPanel4 = new javax.swing.JPanel();
        txtIdPesananUtama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtJumlahPesanan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIdAddon = new javax.swing.JTextField();
        btnUbah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnCariPaketAddonLayanan = new javax.swing.JButton();
        btnTambahItem = new javax.swing.JButton();
        btnCariPesananLayanan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPesananAddonLayanan = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Paket Layanan");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(1, 86, 153));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pesanan Addon Layanan");

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(365, 365, 365)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 344, Short.MAX_VALUE)
                .addComponent(ButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(ButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 63, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(0, 135, 242));
        jPanel2.setPreferredSize(new java.awt.Dimension(1366, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1351, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1320, 447));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Pesanan Addon Layanan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtIdPesananUtama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("ID Pesanan Utama :");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setText("ID Pesanan          :");

        txtId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setText("Jumlah Pesanan    :");

        txtJumlahPesanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setText("Jenis Addon         :");

        txtIdAddon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/edit.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnUbah.setFocusable(false);
        btnUbah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUbah.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/save-file.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnSimpan.setFocusable(false);
        btnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimpan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/return.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnBatal.setFocusable(false);
        btnBatal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBatal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/delete.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnHapus.setFocusable(false);
        btnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHapus.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnCariPaketAddonLayanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/Search.png"))); // NOI18N
        btnCariPaketAddonLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPaketAddonLayananActionPerformed(evt);
            }
        });

        btnTambahItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/add.png"))); // NOI18N
        btnTambahItem.setText("Tambah Item");
        btnTambahItem.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnTambahItem.setFocusable(false);
        btnTambahItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTambahItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTambahItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahItemActionPerformed(evt);
            }
        });

        btnCariPesananLayanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/Search.png"))); // NOI18N
        btnCariPesananLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPesananLayananActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdAddon, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariPaketAddonLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtJumlahPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtIdPesananUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCariPesananLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTambahItem)
                .addGap(9, 9, 9)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUbah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBatal)
                .addGap(96, 96, 96))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTambahItem)
                    .addComponent(btnSimpan)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdPesananUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCariPesananLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdAddon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtJumlahPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCariPaketAddonLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tabelPesananAddonLayanan.setAutoCreateRowSorter(true);
        tabelPesananAddonLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelPesananAddonLayanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelPesananAddonLayanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelPesananAddonLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPesananAddonLayananMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelPesananAddonLayanan);

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
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1339, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCari)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        disableButton();
        clear();
        kode_id_otomatis();
        this.setMode(null);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"Hapus Data Yang Dipilih ?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM pesanan_addon WHERE id_pesanan_addon = '" + txtId.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil terhapus");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal terhapus : " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        clear();
        disableButton();
        dataTable();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        String minimal = txtJumlahPesanan.getText().trim();

        if (minimal.isEmpty() || !minimal.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Isi minimal order dengan format angka saja");
            txtIdPesananUtama.requestFocus();
        } else {
            String sql = "UPDATE pesanan_addon SET id_paket_addon=? , id_pesanan_layanan=? , jumlah_pesanan_addon=? WHERE id_pesanan_addon = '"
            + txtId.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtIdAddon.getText());
                stat.setString(2, txtIdPesananUtama.getText());
                stat.setString(3, txtJumlahPesanan.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Update Telah Tersimpan");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Gagal tersimpan. Pesan error : " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        clear();
        kode_id_otomatis();
        dataTable();
        disableButton();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        
        String minimal = txtJumlahPesanan.getText().trim();

        if (minimal.isEmpty() || !minimal.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Isi minimal order dengan format angka saja");
            txtJumlahPesanan.requestFocus();
        } else {
            String sql = "INSERT INTO pesanan_addon VALUES (?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtId.getText());
                stat.setString(2, txtIdAddon.getText());
                stat.setString(3, txtIdPesananUtama.getText());
                stat.setString(4, txtJumlahPesanan.getText());
                stat.setInt(5, 0);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Pesanan Addon Layanan Tersimpan");
                
                String ObjButton[] = {"Ya, Tambahkan","Tidak"};
                int pilihan = JOptionPane.showOptionDialog(null,"Ingin menambahkan item addon lainnya ?","Message", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null,ObjButton,ObjButton[1]);
                if(pilihan == 0){
                    this.setMode("add");
                    txtIdAddon.setText("");
                    txtJumlahPesanan.setText("");
                    
                    btnCariPesananLayanan.setEnabled(false);
                } else {
                    disableButton();
                    clear();
                }
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Gagal tersimpan. Pesan error : " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        kode_id_otomatis();
        dataTable();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dataTable();
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariKeyTyped

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        dataTable();
    }//GEN-LAST:event_btnCariActionPerformed

    private void tabelPesananAddonLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPesananAddonLayananMouseClicked
        int bar = tabelPesananAddonLayanan.getSelectedRow();
//        Object[] header = {"No. Faktur", "No. Faktur Induk", "ID Addon", "Nama Addon", "ID Pelanggan", "Nama Pelanggan", "Jumlah Pesanan"};
        this.setMode("edit");
         
        txtId.setText(tabelPesananAddonLayanan.getValueAt(bar,0).toString());
        txtIdPesananUtama.setText(tabelPesananAddonLayanan.getValueAt(bar,1).toString());
        txtIdAddon.setText(tabelPesananAddonLayanan.getValueAt(bar,2).toString());
        txtJumlahPesanan.setText(tabelPesananAddonLayanan.getValueAt(bar,6).toString());
        
        this.setIdPesananAddonLayanan(tabelPesananAddonLayanan.getValueAt(bar,0).toString());
        this.setIdPesananLayanan(tabelPesananAddonLayanan.getValueAt(bar,1).toString());
        this.setIdPaketAddon(tabelPesananAddonLayanan.getValueAt(bar,2).toString());
        this.setJumlahPesanan(tabelPesananAddonLayanan.getValueAt(bar,6).toString());
        
        btnTambahItem.setEnabled(false);
        editButton();
    }//GEN-LAST:event_tabelPesananAddonLayananMouseClicked

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        String ObjButton[] = {"YES","NO"};
        int pilihan = JOptionPane.showOptionDialog(null,"Ingin keluar halaman ?","Message", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null,ObjButton,ObjButton[1]);
        if(pilihan == 0){
            this.dispose();
        }
    }//GEN-LAST:event_exitMouseClicked

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        ButtonClose.setBackground(Color.RED);
    }//GEN-LAST:event_exitMouseEntered

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        ButtonClose.setBackground(new Color (11,36,71));
    }//GEN-LAST:event_exitMouseExited

    private void ButtonCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonCloseMouseEntered
        
    }//GEN-LAST:event_ButtonCloseMouseEntered

    private void ButtonCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonCloseMouseExited
        
    }//GEN-LAST:event_ButtonCloseMouseExited

    private void btnCariPaketAddonLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPaketAddonLayananActionPerformed
        // TODO add your handling code here:
        this.dispose();
        PopUpAddonPaketLayanan popUpAddonPaketLayanan = new PopUpAddonPaketLayanan(new javax.swing.JFrame(), true);
        popUpAddonPaketLayanan.setVisible(true);
    }//GEN-LAST:event_btnCariPaketAddonLayananActionPerformed

    private void btnTambahItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahItemActionPerformed
        // TODO add your handling code here:
        disableButton();
        clear();
        enableButton();
        kode_id_otomatis();
        this.setMode("add");
        this.setIdPaketAddon("");
        this.setIdPesananLayanan("");
        this.setIdPesananAddonLayanan("");
        this.setJumlahPesanan("");
        ThrowPesananLayananData throwDataPesanan = new ThrowPesananLayananData();
        
        throwDataPesanan.setId_SubKategoriLayanan("");
        throwDataPesanan.setId_PesananLayanan("");
    }//GEN-LAST:event_btnTambahItemActionPerformed

    private void btnCariPesananLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPesananLayananActionPerformed
        // TODO add your handling code here:
        this.dispose();
        PopUpPesananLayanan popUpPesananLayanan = new PopUpPesananLayanan(new javax.swing.JFrame(), true);
        popUpPesananLayanan.setScreenType("Pesanan Addon");
        popUpPesananLayanan.setVisible(true);
    }//GEN-LAST:event_btnCariPesananLayananActionPerformed

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
            java.util.logging.Logger.getLogger(PesananAddonLayanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesananAddonLayanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesananAddonLayanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesananAddonLayanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesananAddonLayanan dialog = new PesananAddonLayanan(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCariPaketAddonLayanan;
    private javax.swing.JButton btnCariPesananLayanan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahItem;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabelPesananAddonLayanan;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdAddon;
    private javax.swing.JTextField txtIdPesananUtama;
    private javax.swing.JTextField txtJumlahPesanan;
    // End of variables declaration//GEN-END:variables
}
