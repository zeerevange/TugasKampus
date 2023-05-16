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
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author it
 */
public class Karyawan extends javax.swing.JDialog {
        private Connection conn = new koneksi().connect();
        private DefaultTableModel tabmode;
        
    /**
     * Creates new form Karyawan
     */
    public Karyawan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //set ketengah layar
        initUI();
//        dataToComboBox();
        dataTable();
        enableButton();
        editButton();
        disableButton();
        clear();
        kode_id_otomatis();
        combobox();
        //fungsi cari
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
    
    //memunculkan list departement ke combo box dari database
//    private void dataToComboBox() {
//        try {
//            String sql = "SELECT * FROM jabatan_karyawan";
//            PreparedStatement stat = conn.prepareStatement(sql);
//            ResultSet rs = stat.executeQuery();
//
//            while (rs.next()) {
//                cbxJabatan.addItem(rs.getString("id"));
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Departemen pada combobox gagal di tampilkan. Pesan error : " + e.getMessage());
//        }
//}
    
    private void enableButton() {
        txtNama.setEnabled(true);
        radioLaki.setEnabled(true);
        radioPerempuan.setEnabled(true);
        txtNoTelpon.setEnabled(true);
        txtEmail.setEnabled(true);
        cbxJabatan.setEnabled(true);
        btnSimpan.setEnabled(true);
    }
    
    private void editButton(){
        txtNama.setEnabled(true);
        radioLaki.setEnabled(true);
        radioPerempuan.setEnabled(true);
        txtNoTelpon.setEnabled(true);
        txtEmail.setEnabled(true);
        cbxJabatan.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }
    
    private void clear() {
        txtId.setText("");
        txtNama.setText("");
        pilihKelamin.clearSelection();
        txtNoTelpon.setText("");
        txtEmail.setText("");
        cbxJabatan.setSelectedIndex(0);
    }
    
    private void disableButton(){
        txtId.setEnabled(false);
        txtNama.setEnabled(false);
        radioLaki.setEnabled(false);
        radioPerempuan.setEnabled(false);
        txtNoTelpon.setEnabled(false);
        txtEmail.setEnabled(false);
        cbxJabatan.setEnabled(false);
        btnTambah.setEnabled(true);
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
            String sql = "SELECT * FROM karyawan ORDER BY id DESC";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()){
                String kode = rs.getString("id").substring(2);
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
                
                txtId.setText("ID" + Nol + AN);
            } else {
                txtId.setText("ID00001");
            }
        }catch (SQLException e){ 
            JOptionPane.showMessageDialog(null, "Id otomatis tidak berjalan. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void dataTable() {
        Object[] header = {"ID", "Nama Karyawan", "Jenis Kelamin", "No Telpon" ," Email", "Jabatan"};
        tabmode = new DefaultTableModel (null, header);
        String cariitem = txtCari.getText();
        
        try {
            String sql = "SELECT * FROM karyawan WHERE id LIKE '%"
                    + cariitem+ "%' or nama LIKE '%" 
                    + cariitem+ "%' or jenis_kelamin LIKE '%"
                    + cariitem+ "%' or no_telp LIKE '%"
                    + cariitem+ "%' ORDER BY id asc";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tabmode.addRow(new Object[] {
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                });
            } tabelKaryawan.setModel(tabmode);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void combobox(){
        try {
            String sql = "SELECT id, nama FROM jabatan_karyawan";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                    int id = hasil.getInt("id");
                    String nama = hasil.getString("nama");
                    String item = id + " - " + nama;
                    cbxJabatan.addItem(item);
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

        pilihKelamin = new javax.swing.ButtonGroup();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ButtonClose = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        footer = new javax.swing.JPanel();
        mid = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKaryawan = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNoTelpon = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxJabatan = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        radioLaki = new javax.swing.JRadioButton();
        radioPerempuan = new javax.swing.JRadioButton();
        btnBatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Karyawan");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1310, 700));
        setResizable(false);

        header.setBackground(new java.awt.Color(11, 36, 71));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Karyawan");

        ButtonClose.setBackground(new java.awt.Color(11, 36, 71));
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
                .addGap(563, 563, 563)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 491, Short.MAX_VALUE)
                .addComponent(ButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(ButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        footer.setBackground(new java.awt.Color(11, 36, 71));
        footer.setPreferredSize(new java.awt.Dimension(1368, 30));

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1344, Short.MAX_VALUE)
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        mid.setBackground(new java.awt.Color(255, 255, 255));
        mid.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mid.setPreferredSize(new java.awt.Dimension(1300, 600));

        tabelKaryawan.setAutoCreateRowSorter(true);
        tabelKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelKaryawan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelKaryawan.setOpaque(false);
        tabelKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKaryawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelKaryawan);

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
        });

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/Search.png"))); // NOI18N
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Karyawan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel3.setText("Nomor Telpon        :");

        txtNoTelpon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setText("Jenis Kelamin         :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("Email                      :");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setText("ID Karyawan           :");

        txtId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtNama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setText("Jabatan Karyawan :");

        cbxJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan" }));
        cbxJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxJabatanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel7.setText("Nama Karyawan     :");

        radioLaki.setBackground(new java.awt.Color(255, 255, 255));
        pilihKelamin.add(radioLaki);
        radioLaki.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        radioLaki.setText("Laki-Laki");

        radioPerempuan.setBackground(new java.awt.Color(255, 255, 255));
        pilihKelamin.add(radioPerempuan);
        radioPerempuan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        radioPerempuan.setText("Perempuan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cbxJabatan, javax.swing.GroupLayout.Alignment.LEADING, 0, 250, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNoTelpon, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(radioLaki, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioPerempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioLaki)
                    .addComponent(radioPerempuan))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNoTelpon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                                .addGap(22, 22, 22)
                                .addComponent(btnTambah)
                                .addGap(0, 0, 0)
                                .addComponent(btnSimpan)
                                .addGap(0, 0, 0)
                                .addComponent(btnUbah)
                                .addGap(0, 0, 0)
                                .addComponent(btnHapus)
                                .addGap(0, 0, 0)
                                .addComponent(btnBatal))
                            .addGroup(midLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        midLayout.setVerticalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCari)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(midLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimpan)
                            .addComponent(btnUbah)
                            .addComponent(btnHapus)
                            .addComponent(btnBatal)
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addGap(106, 106, 106))
        );

        getContentPane().add(mid, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        disableButton();
        clear();
        enableButton();
        kode_id_otomatis();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
            String nama = txtNama.getText().trim();
            String email = txtEmail.getText().trim();
            String noTelpon = txtNoTelpon.getText().trim();
            String pilihKelamin = "";
            String pilihJabatan = cbxJabatan.getSelectedItem().toString();

            if (radioLaki.isSelected()) {
                pilihKelamin = "Laki-Laki";
            } else if (radioPerempuan.isSelected()) {
                pilihKelamin = "Perempuan";
            } 
            
            if (!nama.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(null, "Isi nama hanya boleh mengandung huruf dan spasi");
            txtNama.requestFocus();
            } else if (pilihKelamin.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih kelamin terlebih dahulu");
            radioLaki.requestFocus();
            } else if (noTelpon.isEmpty() || !noTelpon.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Isi no telpon dengan format angka saja");
            txtNoTelpon.requestFocus();
            } else if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Isi email terlebih dahulu");
            txtEmail.requestFocus();
            } else if (!isValidEmailAddress(email)) {
            JOptionPane.showMessageDialog(null, "Alamat email tidak valid");
            txtEmail.requestFocus();
            } else if (pilihJabatan.equals("Pilihan")) {
            JOptionPane.showMessageDialog(null, "Pilih jabatan terlebih dahulu");
            cbxJabatan.requestFocus();
            } else {
                String sql = "INSERT INTO karyawan VALUES (?,?,?,?,?,?)";
                    try {
                            PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            stat.setString(1, txtId.getText());
                            stat.setString(2, txtNama.getText());
                            stat.setString(3, pilihKelamin);
                            stat.setString(4, txtNoTelpon.getText());
                            stat.setString(5, txtEmail.getText());
                            String selectedItem = cbxJabatan.getSelectedItem().toString();
                            int selectedId = Integer.parseInt(selectedItem.split(" - ")[0]);
                            stat.setInt(6, selectedId);
                            stat.executeUpdate();

                            // Ambil nilai id yang di-generate oleh basis data
//                            ResultSet generatedKeys = stat.getGeneratedKeys();
//                            if (generatedKeys.next()) {
//                                int idBaru = generatedKeys.getInt(1);
//                                JOptionPane.showMessageDialog(null,"Data Tersimpan dengan ID " + idBaru);
//                            }
                            JOptionPane.showMessageDialog(null,"Data Tersimpan");

                        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Gagal tersimpan. Pesan error : " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                  }
            }
            clear();
            kode_id_otomatis();
            dataTable();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        String nama = txtNama.getText().trim();
            String email = txtEmail.getText().trim();
            String noTelpon = txtNoTelpon.getText().trim();
            String pilihKelamin = "";
            String pilihJabatan = cbxJabatan.getSelectedItem().toString();

            if (radioLaki.isSelected()) {
                pilihKelamin = "Laki-Laki";
            } else if (radioPerempuan.isSelected()) {
                pilihKelamin = "Perempuan";
            } 
            
            if (!nama.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(null, "Isi nama hanya boleh mengandung huruf dan spasi");
            txtNama.requestFocus();
            } else if (pilihKelamin.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih kelamin terlebih dahulu");
            radioLaki.requestFocus();
            } else if (noTelpon.isEmpty() || !noTelpon.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Isi no telpon dengan format angka saja");
            txtNoTelpon.requestFocus();
            } else if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Isi email terlebih dahulu");
            txtEmail.requestFocus();
            } else if (!isValidEmailAddress(email)) {
            JOptionPane.showMessageDialog(null, "Alamat email tidak valid");
            txtEmail.requestFocus();
            } else if (pilihJabatan.equals("Pilihan")) {
            JOptionPane.showMessageDialog(null, "Pilih jabatan terlebih dahulu");
            cbxJabatan.requestFocus();
            } else {
                    try {
                        String sql = "UPDATE karyawan SET nama=? , jenis_kelamin=? , no_telp=? , email=? , id_jabatan_karyawan=? WHERE id = '"
                                + txtId.getText()+"'";
                        PreparedStatement stat = conn.prepareStatement(sql);
                        stat.setString(1, txtNama.getText());
                        stat.setString(2, pilihKelamin);
                        stat.setString(3, txtNoTelpon.getText());
                        stat.setString(4, txtEmail.getText());
                        String selectedItem = cbxJabatan.getSelectedItem().toString();
                        int selectedId = Integer.parseInt(selectedItem.split(" - ")[0]);
                        stat.setInt(5, selectedId);
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                        
                        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Data Gagal Diubah. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                String sql = "Delete FROM karyawan WHERE id = '" + txtId.getText()+"'";
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "data berhasil terhapus");
                    clear();
                    disableButton();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "data gagal terhapus " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                dataTable();
            }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        disableButton();
        clear();
        kode_id_otomatis();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
         dataTable();
    }//GEN-LAST:event_btnCariActionPerformed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariKeyTyped

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dataTable();
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void cbxJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxJabatanActionPerformed
//        int idx = cbxJabatan.getSelectedIndex();
//        
//        if (arrJabatan.size() > 0) {
//            id_jab = arrJabatan.get(idx).getId();
//            gaji = arrJabatan.get(idx).getGaji();
//        }
    }//GEN-LAST:event_cbxJabatanActionPerformed

    private void tabelKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKaryawanMouseClicked
        int bar = tabelKaryawan.getSelectedRow();
        txtId.setText(tabelKaryawan.getValueAt(bar,0).toString());
        txtNama.setText(tabelKaryawan.getValueAt(bar,1).toString());
        String kelamin = tabmode.getValueAt(bar, 2).toString();
        if ("Laki-Laki".equals(kelamin)) {
            radioLaki.setSelected(true);
        } else {
            radioPerempuan.setSelected(true);
        }
        txtNoTelpon.setText(tabelKaryawan.getValueAt(bar,3).toString());
        txtEmail.setText(tabelKaryawan.getValueAt(bar,4).toString());
        String jabatanValue = tabelKaryawan.getValueAt(bar,5).toString();
        String jabatanId = jabatanValue.split(" - ")[0];

        DefaultComboBoxModel<String> cbxModel = (DefaultComboBoxModel<String>) cbxJabatan.getModel();
        for (int i = 0; i < cbxModel.getSize(); i++) {
            String item = cbxModel.getElementAt(i);
            String itemId = item.split(" - ")[0];
        if (itemId.equals(jabatanId)) {
            cbxJabatan.setSelectedIndex(i);
            break;
        }
    }
        editButton();
    }//GEN-LAST:event_tabelKaryawanMouseClicked

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
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Karyawan dialog = new Karyawan(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbxJabatan;
    private javax.swing.JLabel exit;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mid;
    private javax.swing.ButtonGroup pilihKelamin;
    private javax.swing.JRadioButton radioLaki;
    private javax.swing.JRadioButton radioPerempuan;
    private javax.swing.JTable tabelKaryawan;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoTelpon;
    // End of variables declaration//GEN-END:variables
}
