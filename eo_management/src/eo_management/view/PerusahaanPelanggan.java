/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.view;

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
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author it
 */
public class PerusahaanPelanggan extends javax.swing.JDialog {
         private Connection conn = new koneksi().connect();
         private DefaultTableModel tabmode;
         public String id, nama, notelpon, email;
         private String id_pelanggan;
    /**
     * Creates new form Pelanggan
     */
    
    public PerusahaanPelanggan(java.awt.Frame parent, boolean modal, String id_pelanggan) {
        super(parent, modal);
        initComponents();
        //set ketengah layar
        initUI();
        dataTable();
        enableButton();
        kode_id_otomatis();
        
        this.id_pelanggan = id_pelanggan;
        
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
        
        txtIdPelanggan.setText(this.id_pelanggan);
        txtIdPelanggan.setEnabled(false);
        txtId.setEnabled(false);
    }
     
    //colorchange
    public void changecolor(JPanel hover, Color rand) {
        hover.setBackground(rand);
    }
    
    //method insert nama pelanggan dari popuppelanggan
    public void itemTerpilih(){
        PopUpPelanggan Pp = new PopUpPelanggan();
        Pp.plgn = this;
        txtNama.setText(nama);
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
        txtNama.setEnabled(true);
        txtJenis.setEnabled(true);
        txtAlamat.setEnabled(true);
        txtIdPelanggan.setEnabled(true);
        btnSimpan.setEnabled(true);
    }
    
    private void editButton(){
        txtNama.setEnabled(true);
        txtJenis.setEnabled(true);
        txtAlamat.setEnabled(true);
        txtIdPelanggan.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }
    
    private void clear() {
        txtId.setText("");
        txtNama.setText("");
        txtJenis.setText("");
        txtAlamat.setText("");
        txtIdPelanggan.setText(""); 
     }
    
    private void disableButton(){
        txtId.setEnabled(false);
        txtNama.setEnabled(false);
        txtJenis.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtIdPelanggan.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
    }
    
    //pengecekan format txtEmail diisi dengan benar
    private boolean isValidEmailAddress(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." 
                        + "[a-zA-Z0-9_+&*-]+)*@"
                        + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                        + "A-Z]{2,7}$";
                          
    Pattern pat = Pattern.compile(emailRegex);
    if (email == null)
        return false;
    return pat.matcher(email).matches();
}
    
    //memberikan kode id otomatis kepada id pelanggan
    private void kode_id_otomatis(){
    try {
        String sql = "SELECT * FROM perusahaan_pelanggan ORDER BY id_perusahaan_pelanggan DESC";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        if (rs.next()){
            String kode = rs.getString("id_perusahaan_pelanggan").substring(2);
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

            txtId.setText("1" + Nol + AN);
        } else {
            txtId.setText("100001");
        }
    }catch (SQLException e){ 
        JOptionPane.showMessageDialog(null, "Id otomatis tidak berjalan. Pesan error : " + e.getMessage());
    }
}
    
   public void dataTable() {
    Object[] header = {"ID", "Nama Perusahaan", "Jenis Perusahaan", "Alamat", "Pelanggan"};
    tabmode = new DefaultTableModel(null, header);
    String cariitem = txtCari.getText();

    try {
        String sql = "SELECT * FROM perusahaan_pelanggan JOIN pelanggan ON perusahaan_pelanggan.id_pelanggan = pelanggan.id_pelanggan WHERE perusahaan_pelanggan.id_perusahaan_pelanggan LIKE '%" + cariitem + "%' or perusahaan_pelanggan.nama_perusahaan_pelanggan LIKE '%" + cariitem + "%' or perusahaan_pelanggan.jenis_perusahaan_pelanggan LIKE '%" + cariitem + "%' or pelanggan.nama_pelanggan LIKE '%" + cariitem + "%'";
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        while (hasil.next()) {
            tabmode.addRow(new Object[]{
                hasil.getString("id_perusahaan_pelanggan"),
                hasil.getString("nama_perusahaan_pelanggan"),
                hasil.getString("jenis_perusahaan_pelanggan"),
                hasil.getString("alamat_perusahaan_pelanggan"),
                hasil.getString("pelanggan.id_pelanggan") + " - " + hasil.getString("pelanggan.nama_pelanggan")
            });
        }
        tabelPelanggan.setModel(tabmode);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Data gagal dipanggil: " + e.getMessage());
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

        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ButtonClose = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        footer = new javax.swing.JPanel();
        mid = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtJenis = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIdPelanggan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPelanggan = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Perusahaan Pelanggan");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1260, 730));
        setResizable(false);

        header.setBackground(new java.awt.Color(1, 86, 153));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Perusahaan Pelanggan");

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

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(358, 358, 358)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(ButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(ButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        footer.setBackground(new java.awt.Color(0, 135, 242));
        footer.setPreferredSize(new java.awt.Dimension(1366, 30));

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        mid.setBackground(new java.awt.Color(255, 255, 255));
        mid.setPreferredSize(new java.awt.Dimension(1361, 600));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Perusahaan Pelanggan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel3.setText("Jenis Perusahaan  :");

        txtJenis.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setText("ID Perusahaan       :");

        txtId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtNama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel7.setText("Nama Perusahaan :");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setText("Alamat                    :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("Pelanggan ID          :");

        txtIdPelanggan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(txtJenis)
                    .addComponent(txtIdPelanggan)
                    .addComponent(txtAlamat)
                    .addComponent(txtId))
                .addGap(311, 311, 311))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabelPelanggan.setAutoCreateRowSorter(true);
        tabelPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelPelanggan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelPelanggan.setOpaque(false);
        tabelPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPelanggan);

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

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

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

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/edit.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnUbah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbah.setFocusable(false);
        btnUbah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUbah.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout midLayout = new javax.swing.GroupLayout(mid);
        mid.setLayout(midLayout);
        midLayout.setHorizontalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, midLayout.createSequentialGroup()
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(midLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(midLayout.createSequentialGroup()
                        .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(midLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(midLayout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(midLayout.createSequentialGroup()
                                        .addComponent(btnSimpan)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnUbah)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnHapus)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnBatal))
                                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)))
                .addGap(69, 69, 69))
        );
        midLayout.setVerticalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCari)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(midLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimpan)
                            .addComponent(btnUbah)
                            .addComponent(btnHapus)
                            .addComponent(btnBatal)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(mid, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPelangganMouseClicked
        int bar = tabelPelanggan.getSelectedRow();
        txtId.setText(tabelPelanggan.getValueAt(bar,0).toString());
        txtNama.setText(tabelPelanggan.getValueAt(bar,1).toString());
        txtJenis.setText(tabelPelanggan.getValueAt(bar,2).toString());
        txtAlamat.setText(tabelPelanggan.getValueAt(bar,3).toString());
        
        String pelangganName = tabelPelanggan.getValueAt(bar,4).toString();
        String pelangganId = pelangganName.split(" - ")[0];
        
        txtIdPelanggan.setText(pelangganId);
        
        editButton();
        txtIdPelanggan.setEnabled(false);
    }//GEN-LAST:event_tabelPelangganMouseClicked

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

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (txtNama.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Isi nama hanya boleh mengandung huruf dan spasi");
        txtNama.requestFocus();
        } else if (txtJenis.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Isi jenis terlebih dahulu");
        txtJenis.requestFocus();
        } else if (txtAlamat.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Isi alamat terlebih dahulu");
        txtAlamat.requestFocus();
        } else {
            String sql = "INSERT INTO perusahaan_pelanggan VALUES (?,?,?,?,?)";
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.setString(1, txtId.getText());
                    stat.setString(2, txtNama.getText());
                    stat.setString(3, txtJenis.getText());
                    stat.setString(4, txtAlamat.getText());
                    stat.setString(5, txtIdPelanggan.getText());
                    stat.execute();
                    JOptionPane.showMessageDialog(null,"Data Tersimpan");
                    } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"Gagal tersimpan " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                    }
                }
        clear();
        kode_id_otomatis();       
        dataTable();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
         if (txtNama.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Isi nama hanya boleh mengandung huruf dan spasi");
        txtNama.requestFocus();
        } else if (txtJenis.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Isi jenis terlebih dahulu");
        txtJenis.requestFocus();
        } else if (txtAlamat.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Isi alamat terlebih dahulu");
        txtAlamat.requestFocus();
        } else {
                    try {
                        String sql = "UPDATE perusahaan_pelanggan SET nama_perusahaan_pelanggan=? , jenis_perusahaan_pelanggan=? , alamat_perusahaan_pelanggan=?, id_pelanggan=? WHERE id_perusahaan_pelanggan = '"
                                + txtId.getText()+"'";
                        PreparedStatement stat = conn.prepareStatement(sql);
                        stat.setString(1, txtNama.getText());
                        stat.setString(2, txtJenis.getText());
                        stat.setString(3, txtAlamat.getText());
                        stat.setString(4, txtIdPelanggan.getText());
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Data Gagal Diubah. Pesan error :  " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                 }
        dataTable();
        disableButton();
        clear();
        kode_id_otomatis();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                String sql = "Delete FROM perusahaan_pelanggan WHERE id_perusahaan_pelanggan = '" + txtId.getText()+"'";
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "data berhasil terhapus");

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "data gagal terhapus " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            clear();
            disableButton();
            dataTable();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        disableButton();
        clear();
        kode_id_otomatis();
    }//GEN-LAST:event_btnBatalActionPerformed

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

    public String getIdPelanggan() {
        return id_pelanggan;
    }
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
            java.util.logging.Logger.getLogger(PerusahaanPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerusahaanPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerusahaanPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerusahaanPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PerusahaanPelanggan dialog = new PerusahaanPelanggan(new javax.swing.JFrame(), true, "0");
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
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel exit;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mid;
    private javax.swing.JTable tabelPelanggan;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdPelanggan;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
