/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.view;

import eo_management.koneksi.koneksi;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author it
 */
public class SubKategoriAddon extends javax.swing.JDialog {
        private Connection conn = new koneksi().connect();
        private DefaultTableModel tabmode;
    /**
     * Creates new form Role
     */
    public SubKategoriAddon(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initUI();
        dataTable();
        disableButton();
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
    
    private void enableButton() {
        txtNama.setEnabled(true);
        btnSimpan.setEnabled(true);
        cbxKategori.setEnabled(true);
    }
    
    private void editButton(){
        txtNama.setEnabled(true);
        cbxKategori.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }
    
    private void clear() {
        txtId.setText("");
        txtNama.setText("");
        cbxKategori.setSelectedItem("");
    }
    
    private void disableButton(){
        txtId.setEnabled(false);
        txtNama.setEnabled(false);
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        cbxKategori.setEnabled(false);
    }
    
//    private void autoIncrement(){
//        try {
//                // Membuat pernyataan SQL untuk mengambil nilai auto increment selanjutnya
//                Statement stmt = conn.createStatement();
//                String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'eo_management' AND TABLE_NAME = 'sub_kategori_addon'";
//                ResultSet rs = stmt.executeQuery(sql);
//
//                // Mengekstrak nilai auto increment selanjutnya
//                int nextAutoIncrementValue = 1;
//                if (rs.next()) {
//                    nextAutoIncrementValue = rs.getInt("AUTO_INCREMENT");
//                }
//
//                // Menetapkan nilai auto increment selanjutnya ke JTextField
//                txtId.setText(String.valueOf(nextAutoIncrementValue));
//
//                // Menutup koneksi
////                rs.close();
////                stmt.close();
////                conn.close();
//            } catch (SQLException ex) {
//                // Tangani kesalahan koneksi atau eksekusi query
//                ex.printStackTrace();
//}
//    }
    private void combobox(){
        try {
            String sql = "SELECT id_kategori_addon, nama_kategori_addon FROM kategori_addon";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                    int id = hasil.getInt("id_kategori_addon");
                    String nama = hasil.getString("nama_kategori_addon");
                    String item = id + " - " + nama;
                    cbxKategori.addItem(item);
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error memunculkan combobox");
        }
        
    }
    
    public void dataTable() {
    Object[] header = {"ID Katagori", "Nama Sub Katagori Addon", "Katagori Addon"};
    tabmode = new DefaultTableModel (null, header);
    try {
        String sql = "SELECT * FROM sub_kategori_addon LEFT JOIN kategori_addon ON sub_kategori_addon.id_kategori_addon = kategori_addon.id_kategori_addon;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet hasil = ps.executeQuery();
             while (hasil.next()) {
                tabmode.addRow(new Object[] {
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(5),
             });
        }
        tabelKategori.setModel(tabmode);
//        // Set alignment for table cells to center
//            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//            for (int columnIndex = 0; columnIndex < tabelKategori.getColumnCount(); columnIndex++) {
//                tabelKategori.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
//            }
//
//            // Center align table header
//            ((DefaultTableCellRenderer) tabelKategori.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "data gagal dipanggil. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jPanel4 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxKategori = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelKategori = new javax.swing.JTable();
        btnBatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Sub Kategori Addon");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(1, 86, 153));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sub Katagori Addon");

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
                .addContainerGap(290, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(231, 231, 231)
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
            .addGap(0, 1021, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Sub Kategori Addon", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setText("ID Sub Kategori Addon       :");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setText("Nama Sub Kategori Addon :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("Kategori Addon                   :");

        cbxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtId)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbxKategori)
                        .addContainerGap())))
        );

        tabelKategori.setAutoCreateRowSorter(true);
        tabelKategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelKategori.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKategoriMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelKategori);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTambah)
                        .addGap(0, 0, 0)
                        .addComponent(btnSimpan)
                        .addGap(0, 0, 0)
                        .addComponent(btnUbah)
                        .addGap(0, 0, 0)
                        .addComponent(btnHapus)
                        .addGap(0, 0, 0)
                        .addComponent(btnBatal)
                        .addGap(480, 480, 480)))
                .addGap(13, 13, 13))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCari)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTambah)
                            .addComponent(btnSimpan)
                            .addComponent(btnUbah)
                            .addComponent(btnHapus)
                            .addComponent(btnBatal)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        disableButton();
        clear();
        enableButton();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String pilihKategoriAddon = cbxKategori.getSelectedItem().toString();
        
        if (txtNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Isi nama terlebih dahulu");
            txtNama.requestFocus();
        } else if (pilihKategoriAddon.equals("Pilihan")) {
            JOptionPane.showMessageDialog(null, "Pilih kategori terlebih dahulu");
            cbxKategori.requestFocus();
        } else {
            try {
                String sql = "INSERT INTO sub_kategori_addon (nama_sub_kategori_addon, id_kategori_addon) VALUES (?,?)";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtNama.getText());
                String selectedItem = cbxKategori.getSelectedItem().toString();
                int selectedId = Integer.parseInt(selectedItem.split(" - ")[0]);
                stat.setInt(2, selectedId);
                int rowsAffected = stat.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal disimpan", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        clear();
        dataTable();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        String pilihKategoriAddon = cbxKategori.getSelectedItem().toString();
        
        if (txtNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Isi nama terlebih dahulu");
            txtNama.requestFocus();
         } else if (pilihKategoriAddon.equals("Pilihan")) {
            JOptionPane.showMessageDialog(null, "Pilih kategori terlebih dahulu");
            cbxKategori.requestFocus();
        } else {
            try {
                String sql = "UPDATE sub_kategori_addon SET nama_sub_kategori_addon=? ,id_kategori_addon=? WHERE id_kategori_addon = '"
                                + txtId.getText()+"'";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtNama.getText());
                String selectedItem = cbxKategori.getSelectedItem().toString();
                int selectedId = Integer.parseInt(selectedItem.split(" - ")[0]);
                stat.setInt(2, selectedId);

                int rowsAffected = stat.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal diubah", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengubah data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        clear();
        disableButton();
        dataTable();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus data ini?", "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM sub_kategori_addon WHERE id_sub_kategori_addon=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtId.getText());
                int rowsAffected = stat.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil terhapus", "Hapus Data", JOptionPane.INFORMATION_MESSAGE);

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

    private void tabelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKategoriMouseClicked
        int bar = tabelKategori.getSelectedRow();
        txtId.setText(tabelKategori.getValueAt(bar,0).toString());
        txtNama.setText(tabelKategori.getValueAt(bar,1).toString());
        String kategoriValue = tabelKategori.getValueAt(bar,2).toString();
        System.out.println(kategoriValue);
        
        try {
                String sql_subkategori_check = "SELECT id_kategori_addon, nama_kategori_addon FROM kategori_addon WHERE nama_kategori_addon='"+kategoriValue+"'";
                PreparedStatement stat = conn.prepareStatement(sql_subkategori_check);
                ResultSet hasil = stat.executeQuery(sql_subkategori_check);
                if (hasil.next()) {
                    int id = hasil.getInt("id_kategori_addon");
                    
                    for (int i = 0; i < cbxKategori.getItemCount(); i++) {
                        String item = cbxKategori.getItemAt(i);
                        if (item.startsWith(id + " - ")) {
                            cbxKategori.setSelectedIndex(i);
                            break; // Exit loop once found
                        }
                    }
        
                }
                
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memanggil data kategori data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        editButton();
    }//GEN-LAST:event_tabelKategoriMouseClicked

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
            java.util.logging.Logger.getLogger(SubKategoriAddon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubKategoriAddon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubKategoriAddon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubKategoriAddon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                SubKategoriAddon dialog = new SubKategoriAddon(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cbxKategori;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelKategori;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
