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

/**
 *
 * @author it
 */
public class RincianGameAcara extends javax.swing.JDialog {
        private Connection conn = new koneksi().connect();
        private DefaultTableModel tabmode;
        private static String mode, id_rincian_game_acara;
    /**
     * Creates new form Role
     */
    public RincianGameAcara(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initUI();
        dataTable();
        
        if (this.getMode() == "edit") {
            editButton();
            txtIdRincianGameAcara.setText(this.getIdRincianGameAcara());
        } else {
            disableButton();
        }
        
        
        ThrowPesananLayananData throwDataPesanan = new ThrowPesananLayananData();
        
        if (throwDataPesanan.getId_Game() != null) {
            this.getDataIdGame(throwDataPesanan.getId_Game(), throwDataPesanan.getNama_Game());
            
            if (this.getMode() != "edit") {
                this.getLastId ();
                btnSimpan.setEnabled(true);
                enableButton();
            }
           
        }
        
        if (throwDataPesanan.getId_RincianAcara() != null) {
            this.getDataIdRincianAcara(throwDataPesanan.getId_RincianAcara());
            
            if (this.getMode() != "edit") {
                this.getLastId ();
                btnSimpan.setEnabled(true);
                enableButton();
            }
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
        txtIdRincianGameAcara.setEnabled(false);
        txtIdRincianAcara.setEnabled(true);
        txtIdGame.setEnabled(true);
        btnCariRincianAcara.setEnabled(true);
        btnCariGame.setEnabled(true);
    }
    
    private void editButton(){
        txtIdRincianGameAcara.setEnabled(false);
        txtIdRincianAcara.setEnabled(true);
        txtIdGame.setEnabled(true);
        
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnCariRincianAcara.setEnabled(true);
        btnCariGame.setEnabled(true);
        txtNamaGame.setEnabled(false);
    }
    
    private void clear() {
        txtIdRincianGameAcara.setText("");
        txtIdRincianAcara.setText("");
        txtIdGame.setText("");
        txtNamaGame.setText("");
    }
    
    private void disableButton(){
        txtIdRincianGameAcara.setEnabled(false);
        txtIdRincianAcara.setEnabled(false);
        txtIdGame.setEnabled(false);
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnCariGame.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnCariRincianAcara.setEnabled(false);
        txtNamaGame.setEnabled(false);
    }
    
    public static void setMode(String mode) {
        RincianGameAcara.mode = mode;
    }
    
    public String getMode() {
        return this.mode;
    }
    
    public static void setIdRincianGameAcara(String id_rincian_game_acara) {
        RincianGameAcara.id_rincian_game_acara = id_rincian_game_acara;
    }
    
    public String getIdRincianGameAcara() {
        return this.id_rincian_game_acara;
    }
    
    private void getLastId () {
        try {
            String sql = "SELECT * FROM crew_acara ORDER BY id_crew_acara DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();
            
            if (hasil.next()){
                String lastId = hasil.getString("id_crew_acara");
                txtIdRincianGameAcara.setText("" + (Integer.parseInt(lastId) + 1));
            } else {
                txtIdRincianGameAcara.setText("1");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data ID terakhir gagal dipanggil. Pesan error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void getDataIdGame(String data_id, String data_nama_game) {
        txtIdGame.setText(data_id);
        txtIdGame.setEnabled(false);
        txtNamaGame.setText(data_nama_game);
        txtNamaGame.setEnabled(false);
    }
    
    public void getDataIdRincianAcara(String data) {
        txtIdRincianAcara.setText(data);
        txtIdRincianAcara.setEnabled(false);
    }
    
    public void dataTable() {
        Object[] header = {"ID Rincian Game", "ID Acara", "ID Game", "Nama Game", "Kategori game", "Value Game"};
        tabmode = new DefaultTableModel (null, header);
        try {
            String sql = "SELECT \n" +
                        "rincian_game_acara.id_rincian_game_acara,\n" +
                        "rincian_game_acara.id_rincian_acara,\n" +
                        "game.id_game,\n" +
                        "game.nama_game,\n" +
                        "kategori_game.nama_kategori_game,\n" +
                        "game.value_game\n" +
                        "FROM rincian_game_acara\n" +
                        "LEFT JOIN game ON game.id_game = rincian_game_acara.id_game\n" +
                        "LEFT JOIN kategori_game ON kategori_game.id_kategori_game = game.id_kategori_game;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();

            while (hasil.next()) {
                 tabmode.addRow(new Object[] {
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                 });
            }
            tabelRincianAcara.setModel(tabmode);

            // Set header renderer untuk header di tabel
            JTableHeader headerTable = tabelRincianAcara.getTableHeader();
            headerTable.setDefaultRenderer(new HeaderRenderer());

            // Set cell renderer untuk kolom-kolom di tabel
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            for (int i = 0; i < tabelRincianAcara.getColumnCount(); i++) {
                tabelRincianAcara.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

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
        jToolBar1 = new javax.swing.JToolBar();
        btnTambah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelRincianAcara = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtIdRincianGameAcara = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdRincianAcara = new javax.swing.JTextField();
        btnCariRincianAcara = new javax.swing.JButton();
        txtIdGame = new javax.swing.JTextField();
        btnCariGame = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtNamaGame = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Kategori Games");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(1, 86, 153));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Rincian Game Acara");

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
                .addContainerGap(355, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(263, 263, 263)
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
            .addGap(0, 1151, Short.MAX_VALUE)
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
        jToolBar1.add(btnUbah);

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

        tabelRincianAcara.setAutoCreateRowSorter(true);
        tabelRincianAcara.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabelRincianAcara.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelRincianAcara.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelRincianAcara.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelRincianAcara.setOpaque(false);
        tabelRincianAcara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelRincianAcaraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelRincianAcara);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Rincian Game Acara", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setText("ID Rincian Game Acara :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("ID Game :");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setText("ID Rincian Acara :");

        btnCariRincianAcara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/Search.png"))); // NOI18N
        btnCariRincianAcara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariRincianAcaraActionPerformed(evt);
            }
        });

        btnCariGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/Search.png"))); // NOI18N
        btnCariGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariGameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel7.setText("Nama Game :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNamaGame)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtIdGame)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCariGame, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtIdRincianGameAcara)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtIdRincianAcara, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCariRincianAcara, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIdRincianGameAcara, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtIdRincianAcara, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCariRincianAcara, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7))
                    .addComponent(txtNamaGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        disableButton();
        clear();
        
        enableButton();
        getLastId ();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (txtIdRincianAcara.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Isi ID Pesanan Layanan terlebih dahulu");
                txtIdRincianGameAcara.requestFocus();
            } else {
                try {
                    String sql = "INSERT INTO rincian_game_acara (id_rincian_acara, id_game) VALUES (?, ?)";
                    PreparedStatement stat = conn.prepareStatement(sql);
                    
                    stat.setString(1, txtIdRincianAcara.getText());
                    stat.setString(2, txtIdGame.getText());
                    
                    int rowsAffected = stat.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Data gagal disimpan", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            clear();
            dataTable();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        if (txtIdRincianAcara.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Isi ID Pesanan Layanan terlebih dahulu");
                txtIdRincianGameAcara.requestFocus();
                } else {
                    try {
                        String sql = "UPDATE rincian_game_acara SET id_rincian_acara=?, id_game=? WHERE id_rincian_game_acara = '"+ txtIdRincianGameAcara.getText()+"'";
                        PreparedStatement stat = conn.prepareStatement(sql);
                        
                        stat.setString(1, txtIdRincianAcara.getText());
                        stat.setString(2, txtIdGame.getText());
                        
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
                String sql = "DELETE FROM rincian_game_acara WHERE id_rincian_game_acara=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtIdRincianGameAcara.getText());
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

    private void tabelRincianAcaraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelRincianAcaraMouseClicked
        int bar = tabelRincianAcara.getSelectedRow();
//        {"ID Rincian Game", "ID Acara", "ID Game", "Nama Game", "Kategori game", "Value Game"}
                
        txtIdRincianGameAcara.setText(tabelRincianAcara.getValueAt(bar,0).toString());
        
        txtIdRincianAcara.setText(tabelRincianAcara.getValueAt(bar,1).toString());
        txtIdGame.setText(tabelRincianAcara.getValueAt(bar,2).toString());
        txtNamaGame.setText(tabelRincianAcara.getValueAt(bar,3).toString());
        this.setMode("edit");
        this.setIdRincianGameAcara(tabelRincianAcara.getValueAt(bar,0).toString());
        
        editButton();
    }//GEN-LAST:event_tabelRincianAcaraMouseClicked

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

    private void btnCariRincianAcaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariRincianAcaraActionPerformed
        // TODO add your handling code here:
        ThrowPesananLayananData throwDataPesanan = new ThrowPesananLayananData();
        throwDataPesanan.setId_Karyawan(txtIdGame.getText());
        
        this.dispose();
        PopUpRincianAcara popUpRincianAcara = new PopUpRincianAcara(new javax.swing.JFrame(), true);
        popUpRincianAcara.setScreenType("Rincian Game Acara");
        popUpRincianAcara.setVisible(true);
    }//GEN-LAST:event_btnCariRincianAcaraActionPerformed

    private void btnCariGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariGameActionPerformed
        // TODO add your handling code here:
        ThrowPesananLayananData throwDataPesanan = new ThrowPesananLayananData();
        throwDataPesanan.setId_RincianAcara(txtIdRincianAcara.getText());
        this.dispose();
        PopUpGame popUpGame = new PopUpGame(new javax.swing.JFrame(), true);
        popUpGame.setVisible(true);
    }//GEN-LAST:event_btnCariGameActionPerformed

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
            java.util.logging.Logger.getLogger(RincianGameAcara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RincianGameAcara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RincianGameAcara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RincianGameAcara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                RincianGameAcara dialog = new RincianGameAcara(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCariGame;
    private javax.swing.JButton btnCariRincianAcara;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tabelRincianAcara;
    private javax.swing.JTextField txtIdGame;
    private javax.swing.JTextField txtIdRincianAcara;
    private javax.swing.JTextField txtIdRincianGameAcara;
    private javax.swing.JTextField txtNamaGame;
    // End of variables declaration//GEN-END:variables
}
