/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.view;

import eo_management.PasswordSecure;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author it
 */
public class Users extends javax.swing.JDialog {
        private Connection conn = new koneksi().connect();
        private DefaultTableModel tabmode;
        private static String id_user, password, id_karyawan, nama_karyawan, mode;
        private static Integer id_role;
    /**
     * Creates new form Users
     */
    public Users(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initUI();
        
        dataTable("master");
        enableButton();
        editButton();
        disableButton();
        clear();
        combobox();
        
        //fungsi cari
        txtCari.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                dataTable("cari");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                dataTable("master");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                dataTable("master");
            }
        });
        
        ThrowPesananLayananData throwDataPesanan = new ThrowPesananLayananData();
        
        if (this.getMode() == "add") {
            enableButton();
            getLastId();
            txtIdKaryawan.setText(throwDataPesanan.getId_Karyawan() == null ? this.getIdKaryawan() : throwDataPesanan.getId_Karyawan());
            txtNamaKaryawan.setText(throwDataPesanan.getNama_Karyawan() == null ? this.getNamaKaryawan() : throwDataPesanan.getNama_Karyawan());
            
            cbxRole.setSelectedIndex(this.getIdRole() == null ? 0 : this.getIdRole());
            txtPassword.setText(this.getPassword() == null ? "" : this.getPassword());
            System.out.println(this.getPassword());
        } else if (this.getMode() == "edit") {
            disableButton();
            editButton();
            txtId.setText(this.getIdUser());
            txtIdKaryawan.setText(throwDataPesanan.getId_Karyawan() == null ? this.getIdKaryawan() : throwDataPesanan.getId_Karyawan());
            txtNamaKaryawan.setText(throwDataPesanan.getNama_Karyawan() == null ? this.getNamaKaryawan() : throwDataPesanan.getNama_Karyawan());
            
            cbxRole.setSelectedIndex(this.getIdRole() == null ? 0 : this.getIdRole());
            txtPassword.setText(this.getPassword() == null ? "" : this.getPassword());
            
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
        Users.mode = mode;
    }
    
    public String getMode() {
        return this.mode;
    }
    
    public static void setIdUser(String id_user) {
        Users.id_user = id_user;
    }
    
    public String getIdUser() {
        return this.id_user;
    }
    
    public static void setPassword(String password) {
        Users.password = password;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public static void setIdRole(Integer id_role) {
        Users.id_role = id_role;
    }
    
    public Integer getIdRole() {
        return this.id_role;
    }
    
    public static void setIdKaryawan(String id_karyawan) {
        Users.id_karyawan = id_karyawan;
    }
    
    public String getIdKaryawan() {
        return this.id_karyawan;
    }
    
    public static void setNamaKaryawan(String nama_karyawan) {
        Users.nama_karyawan = nama_karyawan;
    }
    
    public String getNamaKaryawan() {
        return this.nama_karyawan;
    }
    
    
    
    private void enableButton() {
        txtId.setEnabled(false);
        txtPassword.setEnabled(true);
        cbxRole.setEnabled(true);
        txtIdKaryawan.setEnabled(true);
        btnSimpan.setEnabled(true);
        btnCariIdKaryawan.setEnabled(true);
    }
    
    private void editButton(){
        txtId.setEnabled(true);
        txtPassword.setEnabled(true);
        cbxRole.setEnabled(true);
        txtIdKaryawan.setEnabled(true);
        btnCariIdKaryawan.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }
    
    private void clear() {
        txtId.setText("");
        txtPassword.setText("");
        cbxRole.setSelectedIndex(0);
        txtIdKaryawan.setText("");
        txtNamaKaryawan.setText("");
    }
    
    private void disableButton(){
        txtId.setEnabled(false);
        txtPassword.setEnabled(false);
        cbxRole.setEnabled(false);
        txtIdKaryawan.setEnabled(false);
        txtNamaKaryawan.setEnabled(false);
        btnCariIdKaryawan.setEnabled(false);
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
    }
    
    private void getLastId () {
        try {
            String sql = "SELECT * FROM user ORDER BY id_user DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();
            
            if (hasil.next()){
                String lastId = hasil.getString("id_user");
                txtId.setText("" + (Integer.parseInt(lastId) + 1));
            } else {
                txtId.setText("1");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data ID terakhir gagal dipanggil. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public void dataTable(String type_query) {
        Object[] header = {"ID", "Password", "Role", "ID Karyawan", "Nama Karyawan", "Kontak Karyawan"};
        tabmode = new DefaultTableModel (null, header);
        String cariitem = txtCari.getText();
        
        try {
            String sql = "SELECT user.id_user,"
                    + "user.password,"
                    + "role.nama_role,"
                    + "user.id_karyawan,"
                    + "karyawan.nama_karyawan,"
                    + "karyawan.no_telp_karyawan FROM user "
                    + "LEFT JOIN role ON role.id_role = user.id_role "
                    + "LEFT JOIN karyawan ON user.id_karyawan = karyawan.id_karyawan "
                    + "WHERE user.id_user LIKE "
                    + "'%"+cariitem+"%' OR karyawan.nama_karyawan LIKE "
                    + "'%"+cariitem+"%'";
            
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tabmode.addRow(new Object[] {
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6)
                });
            } tabelUsers.setModel(tabmode);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     private void combobox(){
        try {
            String sql = "SELECT id_role, nama_role FROM role";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                    int id = hasil.getInt("id_role");
                    String nama = hasil.getString("nama_role");
                    String item = id + " - " + nama;
                    cbxRole.addItem(item);
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error memunculkan combobox");
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
        mid = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxRole = new javax.swing.JComboBox<>();
        btnCariIdKaryawan = new javax.swing.JButton();
        txtIdKaryawan = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNamaKaryawan = new javax.swing.JTextField();
        btnBatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelUsers = new javax.swing.JTable();
        footer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Users Buat Login");
        setPreferredSize(new java.awt.Dimension(1200, 650));
        setResizable(false);

        header.setBackground(new java.awt.Color(1, 86, 153));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Manage Users");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(321, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(516, 516, 516))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        mid.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data User", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel3.setText("Password .         :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("Role                  :");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setText("ID User              :");

        txtId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setText("Karyawan ID       :");

        cbxRole.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan" }));
        cbxRole.setName(""); // NOI18N
        cbxRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRoleActionPerformed(evt);
            }
        });

        btnCariIdKaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/Search.png"))); // NOI18N
        btnCariIdKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariIdKaryawanActionPerformed(evt);
            }
        });

        txtIdKaryawan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel7.setText("Nama                :");

        txtNamaKaryawan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId)
                    .addComponent(cbxRole, 0, 254, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtIdKaryawan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariIdKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNamaKaryawan, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxRole, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(btnCariIdKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(txtIdKaryawan, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(txtNamaKaryawan, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

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

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/add-user.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnTambah.setFocusable(false);
        btnTambah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTambah.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

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

        tabelUsers.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelUsers);

        javax.swing.GroupLayout midLayout = new javax.swing.GroupLayout(mid);
        mid.setLayout(midLayout);
        midLayout.setHorizontalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midLayout.createSequentialGroup()
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(midLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(midLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnTambah)
                        .addGap(0, 0, 0)
                        .addComponent(btnSimpan)
                        .addGap(0, 0, 0)
                        .addComponent(btnUbah)
                        .addGap(0, 0, 0)
                        .addComponent(btnHapus)
                        .addGap(0, 0, 0)
                        .addComponent(btnBatal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(midLayout.createSequentialGroup()
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        midLayout.setVerticalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midLayout.createSequentialGroup()
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(midLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCari)
                            .addComponent(btnCari))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                    .addGroup(midLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimpan)
                            .addComponent(btnUbah)
                            .addComponent(btnHapus)
                            .addComponent(btnBatal)
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13))
        );

        getContentPane().add(mid, java.awt.BorderLayout.CENTER);

        footer.setBackground(new java.awt.Color(0, 135, 242));
        footer.setPreferredSize(new java.awt.Dimension(1366, 30));

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1184, Short.MAX_VALUE)
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRoleActionPerformed

    }//GEN-LAST:event_cbxRoleActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        disableButton();
        clear();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
                int ok = JOptionPane.showConfirmDialog(null,"Yakin ingin menghapus data tersebut ?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
                   if (ok == 0) {
                       String sql = "Delete FROM user WHERE id_user = '" + txtId.getText()+"'";
                       try {
                           PreparedStatement stat = conn.prepareStatement(sql);
                           stat.executeUpdate();
                           JOptionPane.showMessageDialog(null, "data user berhasil terhapus");
                           clear();
                           disableButton();
                       } catch (SQLException e) {
                           JOptionPane.showMessageDialog(null, "data gagal terhapus " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                       }
                       dataTable("master");
                   }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        
        String sql = "INSERT INTO user VALUES (?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtId.getText());
                            
            String selectedItem = cbxRole.getSelectedItem().toString();
            int selectedId = Integer.parseInt(selectedItem.split(" - ")[0]);
            stat.setInt(2, selectedId);
            
            stat.setString(3, txtIdKaryawan.getText());
            
            // Mengenkripsi password sebelum disimpan ke database
            String encryptedPassword = PasswordSecure.md5Encode(txtPassword.getText());
            stat.setString(4, encryptedPassword);
                            

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data User Tersimpan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal tersimpan. Pesan error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        clear();
        dataTable("master");
        disableButton();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        disableButton();
        clear();
        enableButton();
        getLastId();
        this.setMode("add");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        
        if (txtPassword.getText() == null) {
            String sql = "UPDATE user SET id_role=?, id_karyawan=? WHERE id_user = '"
                            + txtId.getText()+"'";
            
            try {
                PreparedStatement stat = conn.prepareStatement(sql);

                String selectedItem = cbxRole.getSelectedItem().toString();
                int selectedId = Integer.parseInt(selectedItem.split(" - ")[0]);
                stat.setInt(1, selectedId);

                stat.setString(2, txtIdKaryawan.getText());

                stat.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data Update Tersimpan");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal tersimpan. Pesan error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            String sql = "UPDATE user SET password=?, id_role=?, id_karyawan=? WHERE id_user = '"
                            + txtId.getText()+"'";
            
            
            try {
                PreparedStatement stat = conn.prepareStatement(sql);

                // Mengenkripsi password sebelum disimpan ke database
                String encryptedPassword = PasswordSecure.md5Encode(txtPassword.getText());
                stat.setString(1, encryptedPassword);

                String selectedItem = cbxRole.getSelectedItem().toString();
                int selectedId = Integer.parseInt(selectedItem.split(" - ")[0]);
                stat.setInt(2, selectedId);

                stat.setString(3, txtIdKaryawan.getText());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Tersimpan");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal tersimpan. Pesan error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    clear();
    dataTable("master");
    }//GEN-LAST:event_btnUbahActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dataTable("cari");
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariKeyTyped

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        dataTable("cari");
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnCariIdKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariIdKaryawanActionPerformed
        // TODO add your handling code here:
        
        this.setIdRole(cbxRole.getSelectedIndex());
        this.setPassword(txtPassword.getText());
        
        this.dispose();
        PopUpKaryawan popUpKaryawan = new PopUpKaryawan(new javax.swing.JFrame(), true);
        popUpKaryawan.setScreenType("Manage User");
        popUpKaryawan.setVisible(true);
        
       
        
    }//GEN-LAST:event_btnCariIdKaryawanActionPerformed

    private void tabelUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelUsersMouseClicked
        int bar = tabelUsers.getSelectedRow();
//        {"ID", "Password", "Role", "ID Karyawan", "Nama Karyawan", "Kontak Karyawan"};
        txtId.setText(tabelUsers.getValueAt(bar,0).toString());
        txtPassword.setText("");
        
        String roleValue = tabelUsers.getValueAt(bar, 2).toString();
        txtIdKaryawan.setText(tabelUsers.getValueAt(bar, 3).toString());
        try {
            txtNamaKaryawan.setText(tabelUsers.getValueAt(bar, 4).toString());
        } catch (Exception $e) {
            txtNamaKaryawan.setText("");
        }
        
        String roleId = roleValue.split("-")[0];
        
        this.setMode("edit");
        this.setIdUser(txtId.getText());
        
        try {
            String sql = "SELECT * FROM role WHERE nama_role = '"+roleId+"'";
            Statement stat = conn.createStatement();
            ResultSet hasil_cari = stat.executeQuery(sql);
            if (hasil_cari.next()){
                int id = hasil_cari.getInt("id_role");
                for (int i = 0; i < cbxRole.getItemCount(); i++) {
                        String item = cbxRole.getItemAt(i);
                        if (item.startsWith(id + " - ")) {
                            cbxRole.setSelectedIndex(i);
                            break; // Exit loop once found
                        }
                }
            }
        } catch (Exception e) {
            System.out.println("hahah error");
        }
        editButton();
    }//GEN-LAST:event_tabelUsersMouseClicked

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
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Users dialog = new Users(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCariIdKaryawan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbxRole;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mid;
    private javax.swing.JTable tabelUsers;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdKaryawan;
    private javax.swing.JTextField txtNamaKaryawan;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}
