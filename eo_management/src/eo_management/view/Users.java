/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.view;

import java.awt.event.KeyEvent;

/**
 *
 * @author it
 */
public class Users extends javax.swing.JDialog {

    /**
     * Creates new form Users
     */
    public Users(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        txtNama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxRole = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbxJabatan1 = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();
        btnBatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        footer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        header.setBackground(new java.awt.Color(11, 36, 71));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Manage Users");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(522, Short.MAX_VALUE)
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
        jLabel3.setText("Password");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("Role                        :");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setText("ID User                   :");

        txtId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtNama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setText("Karyawan ID           :");

        cbxRole.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan" }));
        cbxRole.setName(""); // NOI18N
        cbxRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRoleActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel7.setText("Username               :");

        cbxJabatan1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxJabatan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan" }));
        cbxJabatan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxJabatan1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(cbxRole, 0, 250, Short.MAX_VALUE)
                    .addComponent(cbxJabatan1, 0, 250, Short.MAX_VALUE)
                    .addComponent(jPasswordField1))
                .addContainerGap())
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jPasswordField1))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxRole, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxJabatan1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout midLayout = new javax.swing.GroupLayout(mid);
        mid.setLayout(midLayout);
        midLayout.setHorizontalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(btnBatal)))
                .addGap(18, 18, 18)
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(midLayout.createSequentialGroup()
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        midLayout.setVerticalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(midLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimpan)
                            .addComponent(btnUbah)
                            .addComponent(btnHapus)
                            .addComponent(btnBatal)
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(midLayout.createSequentialGroup()
                        .addGroup(midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCari, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(btnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)))
                .addGap(52, 52, 52))
        );

        getContentPane().add(mid, java.awt.BorderLayout.CENTER);

        footer.setBackground(new java.awt.Color(11, 36, 71));
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRoleActionPerformed
        //        int idx = cbxJabatan.getSelectedIndex();
        //
        //        if (arrJabatan.size() > 0) {
            //            id_jab = arrJabatan.get(idx).getId();
            //            gaji = arrJabatan.get(idx).getGaji();
            //        }
    }//GEN-LAST:event_cbxRoleActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
//        disableButton();
//        clear();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
//        int ok = JOptionPane.showConfirmDialog(null,"Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
//        if (ok == 0) {
//            String sql = "Delete FROM karyawan WHERE id = '" + txtId.getText()+"'";
//            try {
//                PreparedStatement stat = conn.prepareStatement(sql);
//                stat.executeUpdate();
//                JOptionPane.showMessageDialog(null, "data berhasil terhapus");
//                clear();
//                disableButton();
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "data gagal terhapus " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//            dataTable();
//        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
//        String nama = txtNama.getText().trim();
//        String email = txtEmail.getText().trim();
//        String noTelpon = txtNoTelpon.getText().trim();
//        String pilihKelamin = "";
//        String pilihJabatan = cbxRole.getSelectedItem().toString();
//
//        if (radioLaki.isSelected()) {
//            pilihKelamin = "Laki-Laki";
//        } else if (radioPerempuan.isSelected()) {
//            pilihKelamin = "Perempuan";
//        }
//
//        if (!nama.matches("^[a-zA-Z\\s]+$")) {
//            JOptionPane.showMessageDialog(null, "Isi nama hanya boleh mengandung huruf dan spasi");
//            txtNama.requestFocus();
//        } else if (pilihKelamin.equals("")) {
//            JOptionPane.showMessageDialog(null, "Pilih kelamin terlebih dahulu");
//            radioLaki.requestFocus();
//        } else if (noTelpon.isEmpty() || !noTelpon.matches("\\d+")) {
//            JOptionPane.showMessageDialog(null, "Isi no telpon dengan format angka saja");
//            txtNoTelpon.requestFocus();
//        } else if (txtEmail.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Isi email terlebih dahulu");
//            txtEmail.requestFocus();
//        } else if (!isValidEmailAddress(email)) {
//            JOptionPane.showMessageDialog(null, "Alamat email tidak valid");
//            txtEmail.requestFocus();
//        } else if (pilihJabatan.equals("Pilihan")) {
//            JOptionPane.showMessageDialog(null, "Pilih jabatan terlebih dahulu");
//            cbxRole.requestFocus();
//        } else {
//            String sql = "INSERT INTO karyawan VALUES (?,?,?,?,?,?)";
//            try {
//                PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                stat.setString(1, txtId.getText());
//                stat.setString(2, txtNama.getText());
//                stat.setString(3, pilihKelamin);
//                stat.setString(4, txtNoTelpon.getText());
//                stat.setString(5, txtEmail.getText());
//                stat.setString(6, cbxRole.getSelectedItem().toString());
//                stat.executeUpdate();
//
//                // Ambil nilai id yang di-generate oleh basis data
//                //                            ResultSet generatedKeys = stat.getGeneratedKeys();
//                //                            if (generatedKeys.next()) {
//                    //                                int idBaru = generatedKeys.getInt(1);
//                    //                                JOptionPane.showMessageDialog(null,"Data Tersimpan dengan ID " + idBaru);
//                    //                            }
//                JOptionPane.showMessageDialog(null,"Data Tersimpan");
//
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null,"Gagal tersimpan. Pesan error : " +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        clear();
//        kode_id_otomatis();
//        dataTable();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
//        enableButton();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
//        String nama = txtNama.getText().trim();
//        String email = txtEmail.getText().trim();
//        String noTelpon = txtNoTelpon.getText().trim();
//        String pilihKelamin = "";
//        String pilihJabatan = cbxRole.getSelectedItem().toString();
//
//        if (radioLaki.isSelected()) {
//            pilihKelamin = "Laki-Laki";
//        } else if (radioPerempuan.isSelected()) {
//            pilihKelamin = "Perempuan";
//        }
//
//        if (!nama.matches("^[a-zA-Z\\s]+$")) {
//            JOptionPane.showMessageDialog(null, "Isi nama hanya boleh mengandung huruf dan spasi");
//            txtNama.requestFocus();
//        } else if (pilihKelamin.equals("")) {
//            JOptionPane.showMessageDialog(null, "Pilih kelamin terlebih dahulu");
//            radioLaki.requestFocus();
//        } else if (noTelpon.isEmpty() || !noTelpon.matches("\\d+")) {
//            JOptionPane.showMessageDialog(null, "Isi no telpon dengan format angka saja");
//            txtNoTelpon.requestFocus();
//        } else if (txtEmail.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Isi email terlebih dahulu");
//            txtEmail.requestFocus();
//        } else if (!isValidEmailAddress(email)) {
//            JOptionPane.showMessageDialog(null, "Alamat email tidak valid");
//            txtEmail.requestFocus();
//        } else if (pilihJabatan.equals("Pilihan")) {
//            JOptionPane.showMessageDialog(null, "Pilih jabatan terlebih dahulu");
//            cbxRole.requestFocus();
//        } else {
//            try {
//                String sql = "UPDATE karyawan SET nama=? , jenis_kelamin=? , no_telp=? , email=? , id_jabatan_karyawan=? WHERE id = '"
//                + txtId.getText()+"'";
//                PreparedStatement stat = conn.prepareStatement(sql);
//                stat.setString(1, txtNama.getText());
//                stat.setString(2, pilihKelamin);
//                stat.setString(3, txtNoTelpon.getText());
//                stat.setString(4, txtEmail.getText());
//                stat.setString(5, cbxRole.getSelectedItem().toString());
//                stat.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
//                dataTable();
//                disableButton();
//                clear();
//                kode_id_otomatis();
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Data Gagal Diubah. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            dataTable();
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariKeyTyped

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
//        dataTable();
    }//GEN-LAST:event_btnCariActionPerformed

    private void cbxJabatan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxJabatan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxJabatan1ActionPerformed

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
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbxJabatan1;
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
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel mid;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
