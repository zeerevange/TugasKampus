/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.view;

import eo_management.UserSession;
import eo_management.Main;
import eo_management.koneksi.koneksi;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author it
 */
public class Menu extends javax.swing.JFrame {
            private Connection conn = new koneksi().connect();
            UserSession karyawanSession = new UserSession();
            
            //waktu sekarang
            private void setJam() {
            ActionListener listener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
            jam.setText(new WaktuSekarang().getWkt());
            }
            
            };
            new Timer(1000, listener).start();
            }
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        // inisialisasi objek userSession
        karyawanSession = new UserSession();
        setJam();
        
        updateJumlahData("pelanggan"); // untuk memperbarui jumlah pelanggan
        updateJumlahData("karyawan"); // untuk memperbarui jumlah karyawan
        updateJumlahData("inventaris"); // untuk memperbarui jumlah inventaris
        updateJumlahData("paket_layanan"); // untuk memperbarui jumlah inventaris
        
         //set ketengah
        initUI();
        // Menambahkan aksi ketika tombol close di klik pada menubar
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(Menu.this, "Apakah Anda yakin ingin menutup program?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
               
        //menampilkan nama dari fungsi UserSession
        labelUsername.setText(UserSession.getU_username());
        
        //membuat layar menu menjadi full screen
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
}
        
        public void windowActivated(WindowEvent e) {
            System.out.println("Window Activated Event");
        }

        public void windowDeactivated(WindowEvent e) {
            System.out.println("Window Deactivated Event");
        }

        private void initUI(){
            Dimension windowSize = getSize();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Point centerPoint = ge.getCenterPoint();

            int dx = centerPoint.x - windowSize.width / 2;
            int dy = centerPoint.y - windowSize.height / 2;    
            setLocation(dx, dy);

        }
        
        public void updateJumlahData(String jenisData) {
                String sql = "";
                if (jenisData.equals("pelanggan")) {
                    sql = "SELECT COUNT(*) AS id FROM pelanggan";
                } else if (jenisData.equals("karyawan")) {
                    sql = "SELECT COUNT(*) AS id FROM karyawan";
                } else if (jenisData.equals("inventaris")) {
                    sql = "SELECT COUNT(*) AS id FROM inventaris";
                } else if (jenisData.equals("paket_layanan")) {
                    sql = "SELECT COUNT(*) AS id FROM paket_layanan";        
                }
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        int jumlah = rs.getInt("id");
                        if (jenisData.equals("pelanggan")) {
                            jmlPlgn.setText(String.valueOf(jumlah));
                        } else if (jenisData.equals("karyawan")) {
                            jmlKywn.setText(String.valueOf(jumlah));
                       } else if (jenisData.equals("inventaris")) {
                            jmlInv.setText(String.valueOf(jumlah));
                       } else if (jenisData.equals("paket_layanan")) {
                            jmlPkt.setText(String.valueOf(jumlah));          
                        }
                    }
                    rs.close();
                    ps.close();
                } catch (SQLException e){
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil jumlah pengguna "+ e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
                  
        public void focusGained(FocusEvent fe){
            System.out.println("Focus gained in JPanel");
        }

        public void focusLost(FocusEvent fe){
            System.out.println("Focus lost in JPanel");
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        footer = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        labelUsername1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelJam = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jam = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jmlKywn = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jmlPlgn = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jmlPkt = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jmlInv = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jmlPesanan = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuSignOut = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();
        menuMaster = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuKaryawan = new javax.swing.JMenuItem();
        menuJabatan = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuSupplier1 = new javax.swing.JMenuItem();
        menuPermainan = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuKategoriAddon = new javax.swing.JMenuItem();
        menuSubKategoriAddon = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuPelanggan = new javax.swing.JMenuItem();
        menuPerusahaanPelanggan = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuPaketLayanan = new javax.swing.JMenuItem();
        menuInventaris = new javax.swing.JMenuItem();
        menuSupplier = new javax.swing.JMenuItem();
        menuMaster1 = new javax.swing.JMenu();
        menuMaster2 = new javax.swing.JMenu();
        menuMaster3 = new javax.swing.JMenu();
        menuUsers = new javax.swing.JMenuItem();
        menuRole = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Utama Coy");

        header.setBackground(new java.awt.Color(11, 36, 71));
        header.setPreferredSize(new java.awt.Dimension(1386, 100));

        jPanel1.setBackground(new java.awt.Color(11, 36, 71));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/globe.gif"))); // NOI18N
        jLabel1.setText("DASHBOARD");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(932, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(50, 592));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(150, 592));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/mini-logo-icon.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(491, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.LINE_END);

        footer.setBackground(new java.awt.Color(11, 36, 71));
        footer.setPreferredSize(new java.awt.Dimension(1366, 50));

        jPanel7.setBackground(new java.awt.Color(11, 36, 71));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Login As :");

        labelUsername.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        labelUsername.setForeground(new java.awt.Color(255, 255, 255));
        labelUsername.setText("username");

        labelUsername1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        labelUsername1.setForeground(new java.awt.Color(255, 255, 255));
        labelUsername1.setText("userId");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Login ID :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(labelUsername1))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(labelUsername)))
                .addContainerGap())
        );

        panelJam.setBackground(new java.awt.Color(11, 36, 71));
        panelJam.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        time.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time :");

        jam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jam.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Date :");

        tgl.setBackground(new java.awt.Color(11, 36, 71));
        tgl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tgl.setForeground(new java.awt.Color(255, 255, 255));
        tgl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tgl.setOpaque(true);

        javax.swing.GroupLayout panelJamLayout = new javax.swing.GroupLayout(panelJam);
        panelJam.setLayout(panelJamLayout);
        panelJamLayout.setHorizontalGroup(
            panelJamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJamLayout.createSequentialGroup()
                        .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJamLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jam, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelJamLayout.setVerticalGroup(
            panelJamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJamLayout.createSequentialGroup()
                .addGroup(panelJamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelJamLayout.createSequentialGroup()
                        .addComponent(jam, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJamLayout.createSequentialGroup()
                        .addComponent(time)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        tgl.setText(new WaktuSekarang().getTgl());
        tgl.setPreferredSize(new java.awt.Dimension(120,22));

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 806, Short.MAX_VALUE)
                .addComponent(panelJam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addComponent(panelJam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jumlah Karyawan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel2.setOpaque(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/user.gif"))); // NOI18N

        jmlKywn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jmlKywn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmlKywn.setText("lebel");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jmlKywn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlKywn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jumlah Pelanggan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel5.setOpaque(false);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/customer.gif"))); // NOI18N

        jmlPlgn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jmlPlgn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmlPlgn.setText("lebel");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jmlPlgn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlPlgn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 22, -1, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jumlah Paket", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel8.setOpaque(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/puzzle.gif"))); // NOI18N

        jmlPkt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jmlPkt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmlPkt.setText("lebel");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jmlPkt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlPkt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, -1, 210));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jumlah Inventaris", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel9.setOpaque(false);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/inventaris.gif"))); // NOI18N

        jmlInv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jmlInv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmlInv.setText("lebel");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jmlInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlInv)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, -1, 200));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jumlah Pesanan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel11.setOpaque(false);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/trolley-loop.gif"))); // NOI18N

        jmlPesanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jmlPesanan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmlPesanan.setText("lebel");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jmlPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlPesanan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, 200));

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        menuFile.setText("File");

        menuSignOut.setText("Sign Out");
        menuSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSignOutActionPerformed(evt);
            }
        });
        menuFile.add(menuSignOut);

        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        menuFile.add(menuExit);

        jMenuBar1.add(menuFile);

        menuMaster.setText("Master");
        menuMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMasterActionPerformed(evt);
            }
        });

        jMenu3.setText("Employee");

        menuKaryawan.setText("Karyawan");
        menuKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKaryawanActionPerformed(evt);
            }
        });
        jMenu3.add(menuKaryawan);

        menuJabatan.setText("Jabatan Karyawan");
        menuJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuJabatanActionPerformed(evt);
            }
        });
        jMenu3.add(menuJabatan);

        menuMaster.add(jMenu3);

        jMenu2.setText("Games");

        menuSupplier1.setText("Kategori Games");
        menuSupplier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSupplier1ActionPerformed(evt);
            }
        });
        jMenu2.add(menuSupplier1);

        menuPermainan.setText("Permainan");
        jMenu2.add(menuPermainan);

        menuMaster.add(jMenu2);

        jMenu1.setText("Katagory");

        menuKategoriAddon.setText("Kategori Addon");
        menuKategoriAddon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKategoriAddonActionPerformed(evt);
            }
        });
        jMenu1.add(menuKategoriAddon);

        menuSubKategoriAddon.setText("Sub Kategori Addon");
        menuSubKategoriAddon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSubKategoriAddonActionPerformed(evt);
            }
        });
        jMenu1.add(menuSubKategoriAddon);

        menuMaster.add(jMenu1);

        jMenu4.setText("Customer");

        menuPelanggan.setText("Pelanggan");
        menuPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPelangganActionPerformed(evt);
            }
        });
        jMenu4.add(menuPelanggan);

        menuPerusahaanPelanggan.setText("Perusahaan Pelanggan");
        menuPerusahaanPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPerusahaanPelangganActionPerformed(evt);
            }
        });
        jMenu4.add(menuPerusahaanPelanggan);

        menuMaster.add(jMenu4);

        jMenu5.setText("Services");

        menuPaketLayanan.setText("Paket Layanan");
        menuPaketLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPaketLayananActionPerformed(evt);
            }
        });
        jMenu5.add(menuPaketLayanan);

        menuMaster.add(jMenu5);

        menuInventaris.setText("Inventaris");
        menuInventaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInventarisActionPerformed(evt);
            }
        });
        menuMaster.add(menuInventaris);

        menuSupplier.setText("Supplier");
        menuMaster.add(menuSupplier);

        jMenuBar1.add(menuMaster);

        menuMaster1.setText("Transaction");
        jMenuBar1.add(menuMaster1);

        menuMaster2.setText("Report");
        jMenuBar1.add(menuMaster2);

        menuMaster3.setText("System Administrator");

        menuUsers.setText("Manage user");
        menuUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsersActionPerformed(evt);
            }
        });
        menuMaster3.add(menuUsers);

        menuRole.setText("Role");
        menuRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRoleActionPerformed(evt);
            }
        });
        menuMaster3.add(menuRole);

        jMenuBar1.add(menuMaster3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
            System.exit(0);
    }//GEN-LAST:event_menuExitActionPerformed

    private void menuJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuJabatanActionPerformed
       new JabatanKaryawan(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuJabatanActionPerformed

    private void menuKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKaryawanActionPerformed
        new Karyawan(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuKaryawanActionPerformed

    private void menuSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSignOutActionPerformed
        String ObjButton[] = {"YES","NO"};
        int pilihan = JOptionPane.showOptionDialog(null,"Sign Out ?","Message", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null,ObjButton,ObjButton[1]);
        if(pilihan == 0){
            //System.exit(0);
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_menuSignOutActionPerformed

    private void menuRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRoleActionPerformed
        new Role(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuRoleActionPerformed

    private void menuPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPelangganActionPerformed
        new Pelanggan(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPelangganActionPerformed

    private void menuPaketLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPaketLayananActionPerformed
        new PaketLayanan(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPaketLayananActionPerformed

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        updateJumlahData("pelanggan"); // untuk memperbarui jumlah pelanggan
        updateJumlahData("karyawan"); // untuk memperbarui jumlah karyawan
        updateJumlahData("inventaris"); // untuk memperbarui jumlah inventaris
        updateJumlahData("paket_layanan"); // untuk memperbarui jumlah inventaris
    }//GEN-LAST:event_jPanel6MouseEntered

    private void menuUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsersActionPerformed
        new Users(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuUsersActionPerformed

    private void menuInventarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInventarisActionPerformed
        new Inventaris (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuInventarisActionPerformed

    private void menuKategoriAddonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKategoriAddonActionPerformed
        new KategoriAddon (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuKategoriAddonActionPerformed

    private void menuSubKategoriAddonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSubKategoriAddonActionPerformed
        new SubKategoriAddon (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuSubKategoriAddonActionPerformed

    private void menuMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMasterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuMasterActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        new About (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void menuSupplier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSupplier1ActionPerformed
        new KategoriGames (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuSupplier1ActionPerformed

    private void menuPerusahaanPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPerusahaanPelangganActionPerformed
        new PerusahaanPelanggan (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPerusahaanPelangganActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jam;
    private javax.swing.JLabel jmlInv;
    private javax.swing.JLabel jmlKywn;
    private javax.swing.JLabel jmlPesanan;
    private javax.swing.JLabel jmlPkt;
    private javax.swing.JLabel jmlPlgn;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JLabel labelUsername1;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuInventaris;
    private javax.swing.JMenuItem menuJabatan;
    private javax.swing.JMenuItem menuKaryawan;
    private javax.swing.JMenuItem menuKategoriAddon;
    private javax.swing.JMenu menuMaster;
    private javax.swing.JMenu menuMaster1;
    private javax.swing.JMenu menuMaster2;
    private javax.swing.JMenu menuMaster3;
    private javax.swing.JMenuItem menuPaketLayanan;
    private javax.swing.JMenuItem menuPelanggan;
    private javax.swing.JMenuItem menuPermainan;
    private javax.swing.JMenuItem menuPerusahaanPelanggan;
    private javax.swing.JMenuItem menuRole;
    private javax.swing.JMenuItem menuSignOut;
    private javax.swing.JMenuItem menuSubKategoriAddon;
    private javax.swing.JMenuItem menuSupplier;
    private javax.swing.JMenuItem menuSupplier1;
    private javax.swing.JMenuItem menuUsers;
    private javax.swing.JPanel panelJam;
    private javax.swing.JLabel tgl;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
