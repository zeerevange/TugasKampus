/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eo_management.view;

import eo_management.UserSession;
import eo_management.koneksi.koneksi;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
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
import java.io.File;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author it
 */
public class Dashboard extends javax.swing.JFrame {
            private Connection conn = new koneksi().connect();
            
            
            //waktu sekarang
            private void setJam() {
            ActionListener listener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
            
            }
            
            };
            new Timer(1000, listener).start();
            }
    /**
     * Creates new form Menu
     */
    public Dashboard() {
        initComponents();
        // inisialisasi objek userSession
        UserSession karyawanSession = new UserSession();
        setJam();
        
        updateJumlahData("pelanggan"); // untuk memperbarui jumlah pelanggan
        updateJumlahData("karyawan"); // untuk memperbarui jumlah karyawan
        updateJumlahData("inventaris"); // untuk memperbarui jumlah inventaris
        updateJumlahData("paket_layanan"); // untuk memperbarui jumlah inventaris
        updateJumlahData("pesanan_layanan"); // untuk memperbarui jumlah pesanan layanan
        
         //set ketengah
        initUI();
        // Menambahkan aksi ketika tombol close di klik pada menubar
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(Dashboard.this, "Apakah Anda yakin ingin menutup program?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
               
        //menampilkan nama dari fungsi UserSession
        labelUser_Id.setText(karyawanSession.getU_id());
        
        labelUsername.setText(karyawanSession.getU_username());
        
        labelEmail.setText(karyawanSession.getU_Email());
        
        labelNoHp.setText(karyawanSession.getU_NoHp());
        
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
                } else if (jenisData.equals("pesanan_layanan")) {
                    sql = "SELECT COUNT(*) AS id FROM pesanan_layanan";        
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
                        } else if (jenisData.equals("pesanan_layanan")) {
                            jmlPesanan.setText(String.valueOf(jumlah));          
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

        jPanel10 = new javax.swing.JPanel();
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
        jPanel18 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        labelEmail = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        labelNoHp = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        labelUsername = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labelUser_Id = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMaster = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuKaryawan = new javax.swing.JMenuItem();
        menuJabatan = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuSupplier1 = new javax.swing.JMenuItem();
        menuPermainan = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuPelanggan = new javax.swing.JMenuItem();
        menuPerusahaanPelanggan = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuPaketLayanan = new javax.swing.JMenuItem();
        menuAddon = new javax.swing.JMenu();
        menuKategoriAddon = new javax.swing.JMenuItem();
        menuSubKategoriAddon = new javax.swing.JMenuItem();
        menuPaketAddon = new javax.swing.JMenuItem();
        menuAddon1 = new javax.swing.JMenu();
        menuRincianAcara = new javax.swing.JMenuItem();
        menuCrewAcara = new javax.swing.JMenuItem();
        menuRincianGameAcara = new javax.swing.JMenuItem();
        menuInventaris = new javax.swing.JMenuItem();
        menuMaster1 = new javax.swing.JMenu();
        menuPesananLayanan = new javax.swing.JMenuItem();
        menuPesananAddonLayanan = new javax.swing.JMenuItem();
        menuPembayaran = new javax.swing.JMenuItem();
        menuMaster2 = new javax.swing.JMenu();
        menuReportPaketLayanan = new javax.swing.JMenuItem();
        menuReportPaketAddonLayanan = new javax.swing.JMenuItem();
        menuReportDataPelanggan = new javax.swing.JMenuItem();
        menuReportTransaksiPesananLayanan = new javax.swing.JMenuItem();
        menuReportTransaksiPesananAddon = new javax.swing.JMenuItem();
        menuReportRincianAcara = new javax.swing.JMenuItem();
        menuMaster3 = new javax.swing.JMenu();
        menuUsers = new javax.swing.JMenuItem();
        menuRole = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Utama");
        setPreferredSize(new java.awt.Dimension(1265, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMaximumSize(new java.awt.Dimension(647, 3647));
        jPanel6.setPreferredSize(new java.awt.Dimension(84, 943));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Karyawan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
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
                    .addComponent(jmlKywn, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlKywn))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pelanggan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jmlPlgn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlPlgn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paket Layanan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
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
                    .addComponent(jmlPkt, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jmlPkt))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventaris", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
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
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlInv))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesanan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
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
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jmlPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlPesanan))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/globe.gif"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(90, 100));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 43, 77));
        jLabel4.setText("DASHBOARD");
        jLabel4.setPreferredSize(new java.awt.Dimension(51, 100));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(713, 713, 713))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(0, 135, 242));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 135, 242));

        jPanel14.setBackground(new java.awt.Color(0, 135, 242));
        jPanel14.setPreferredSize(new java.awt.Dimension(377, 311));

        jPanel7.setBackground(new java.awt.Color(232, 245, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(247, 47));

        labelEmail.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(0, 135, 242));
        labelEmail.setText("pengguna@gmail.com");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/mail.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(232, 245, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(247, 47));

        labelNoHp.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelNoHp.setForeground(new java.awt.Color(0, 135, 242));
        labelNoHp.setText("+6200000000000");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/phone-call.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelNoHp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(232, 245, 255));

        labelUsername.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelUsername.setForeground(new java.awt.Color(0, 135, 242));
        labelUsername.setText("Nama Pengguna");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/user-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(232, 245, 255));

        labelUser_Id.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelUser_Id.setForeground(new java.awt.Color(0, 135, 242));
        labelUser_Id.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelUser_Id.setText("ID Pengguna");
        labelUser_Id.setAlignmentY(1.0F);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/user-id.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUser_Id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelUser_Id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnLogout.setBackground(new java.awt.Color(255, 204, 0));
        btnLogout.setText("Logout");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/icon/user.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 280, 470));

        jPanel15.setBackground(new java.awt.Color(1, 86, 153));
        jPanel15.setPreferredSize(new java.awt.Dimension(341, 100));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eo_management/image/horizontal-logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, -1));

        jPanel17.setBackground(new java.awt.Color(1, 118, 211));

        jLabel2.setBackground(new java.awt.Color(2, 138, 245));
        jLabel2.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(232, 245, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("V 1.0.0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 290, 50));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        getContentPane().add(jPanel10, java.awt.BorderLayout.PAGE_START);

        menuMaster.setText("Master");
        menuMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMasterActionPerformed(evt);
            }
        });

        jMenu3.setText("Data Karyawan");

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

        jMenu2.setText("Data Game");

        menuSupplier1.setText("Kategori Game");
        menuSupplier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSupplier1ActionPerformed(evt);
            }
        });
        jMenu2.add(menuSupplier1);

        menuPermainan.setText("Game");
        menuPermainan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPermainanActionPerformed(evt);
            }
        });
        jMenu2.add(menuPermainan);

        menuMaster.add(jMenu2);

        jMenu4.setText("Data Pelanggan");

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

        jMenu5.setText("Data Layanan");

        menuPaketLayanan.setText("Paket Layanan");
        menuPaketLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPaketLayananActionPerformed(evt);
            }
        });
        jMenu5.add(menuPaketLayanan);

        menuMaster.add(jMenu5);

        menuAddon.setText("Data Addon");
        menuAddon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddonActionPerformed(evt);
            }
        });

        menuKategoriAddon.setText("Kategori Addon");
        menuKategoriAddon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKategoriAddonActionPerformed(evt);
            }
        });
        menuAddon.add(menuKategoriAddon);

        menuSubKategoriAddon.setText("Sub Kategori Addon");
        menuSubKategoriAddon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSubKategoriAddonActionPerformed(evt);
            }
        });
        menuAddon.add(menuSubKategoriAddon);

        menuPaketAddon.setText("Paket Addon");
        menuPaketAddon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPaketAddonActionPerformed(evt);
            }
        });
        menuAddon.add(menuPaketAddon);

        menuMaster.add(menuAddon);

        menuAddon1.setText("Data Acara");
        menuAddon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddon1ActionPerformed(evt);
            }
        });

        menuRincianAcara.setText("Rincian Acara");
        menuRincianAcara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRincianAcaraActionPerformed(evt);
            }
        });
        menuAddon1.add(menuRincianAcara);

        menuCrewAcara.setText("Crew Acara");
        menuCrewAcara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCrewAcaraActionPerformed(evt);
            }
        });
        menuAddon1.add(menuCrewAcara);

        menuRincianGameAcara.setText("Rincian Game Acara");
        menuRincianGameAcara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRincianGameAcaraActionPerformed(evt);
            }
        });
        menuAddon1.add(menuRincianGameAcara);

        menuMaster.add(menuAddon1);

        menuInventaris.setText("Data Inventaris");
        menuInventaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInventarisActionPerformed(evt);
            }
        });
        menuMaster.add(menuInventaris);

        jMenuBar1.add(menuMaster);

        menuMaster1.setText("Transaksi");

        menuPesananLayanan.setText("Pesanan Layanan");
        menuPesananLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPesananLayananActionPerformed(evt);
            }
        });
        menuMaster1.add(menuPesananLayanan);

        menuPesananAddonLayanan.setText("Pesanan Addon Layanan");
        menuPesananAddonLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPesananAddonLayananActionPerformed(evt);
            }
        });
        menuMaster1.add(menuPesananAddonLayanan);

        menuPembayaran.setText("Pembayaran");
        menuPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPembayaranActionPerformed(evt);
            }
        });
        menuMaster1.add(menuPembayaran);

        jMenuBar1.add(menuMaster1);

        menuMaster2.setText("Laporan");

        menuReportPaketLayanan.setText("Paket Layanan");
        menuReportPaketLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportPaketLayananActionPerformed(evt);
            }
        });
        menuMaster2.add(menuReportPaketLayanan);

        menuReportPaketAddonLayanan.setText("Paket Addon");
        menuReportPaketAddonLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportPaketAddonLayananActionPerformed(evt);
            }
        });
        menuMaster2.add(menuReportPaketAddonLayanan);

        menuReportDataPelanggan.setText("Data Pelanggan");
        menuReportDataPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportDataPelangganActionPerformed(evt);
            }
        });
        menuMaster2.add(menuReportDataPelanggan);

        menuReportTransaksiPesananLayanan.setText("Transaksi Pesanan Layanan");
        menuReportTransaksiPesananLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportTransaksiPesananLayananActionPerformed(evt);
            }
        });
        menuMaster2.add(menuReportTransaksiPesananLayanan);

        menuReportTransaksiPesananAddon.setText("Transaksi Pesanan Addon");
        menuReportTransaksiPesananAddon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportTransaksiPesananAddonActionPerformed(evt);
            }
        });
        menuMaster2.add(menuReportTransaksiPesananAddon);

        menuReportRincianAcara.setText("Rincian Acara");
        menuReportRincianAcara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportRincianAcaraActionPerformed(evt);
            }
        });
        menuMaster2.add(menuReportRincianAcara);

        jMenuBar1.add(menuMaster2);

        menuMaster3.setText("Sistem Administrator");

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

    private void menuJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuJabatanActionPerformed
       new JabatanKaryawan(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuJabatanActionPerformed

    private void menuKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKaryawanActionPerformed
        new Karyawan(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuKaryawanActionPerformed

    private void menuRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRoleActionPerformed
        new Role(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuRoleActionPerformed

    private void menuPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPelangganActionPerformed
        new Pelanggan(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPelangganActionPerformed

    private void menuPaketLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPaketLayananActionPerformed
        new PaketLayanan(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPaketLayananActionPerformed

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

    private void menuSupplier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSupplier1ActionPerformed
        new KategoriGames (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuSupplier1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screenDevice = env.getDefaultScreenDevice();
        int screenWidth = screenDevice.getDisplayMode().getWidth();
        int screenHeight = screenDevice.getDisplayMode().getHeight();

        
        int width = (int) (screenWidth * 0.9); // Menggunakan 80% lebar layar
        int height = (int) (screenHeight * 0.9); // Menggunakan 80% tinggi layar
        setSize(width, height);
        
        setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        updateJumlahData("pelanggan"); // untuk memperbarui jumlah pelanggan
        updateJumlahData("karyawan"); // untuk memperbarui jumlah karyawan
        updateJumlahData("inventaris"); // untuk memperbarui jumlah inventaris
        updateJumlahData("paket_layanan"); // untuk memperbarui jumlah inventaris
        updateJumlahData("pesanan_layanan"); // untuk memperbarui jumlah pesanan
    }//GEN-LAST:event_jPanel6MouseEntered

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        String ObjButton[] = {"YES","NO"};
        int pilihan = JOptionPane.showOptionDialog(null,"Are you sure you want to log out ?","Message", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null,ObjButton,ObjButton[1]);
        if(pilihan == 0){
            //System.exit(0);
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void menuPaketAddonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPaketAddonActionPerformed
        // TODO add your handling code here:
        new PaketAddon (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPaketAddonActionPerformed

    private void menuPerusahaanPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPerusahaanPelangganActionPerformed
        new PerusahaanPelanggan (this, rootPaneCheckingEnabled, "0 ").setVisible(true);
    }//GEN-LAST:event_menuPerusahaanPelangganActionPerformed

    private void menuAddonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddonActionPerformed
        // TODO add your handling code here:
//        Addon addon = new Addon();
//        addon.setVisible(true);
    }//GEN-LAST:event_menuAddonActionPerformed

    private void menuPesananLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPesananLayananActionPerformed
        // TODO add your handling code here:
        new PesananLayanan (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPesananLayananActionPerformed

    private void menuPesananAddonLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPesananAddonLayananActionPerformed
        // TODO add your handling code here:
        new PesananAddonLayanan (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPesananAddonLayananActionPerformed

    private void menuReportTransaksiPesananLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportTransaksiPesananLayananActionPerformed
        try {
            File namafile = new File("src/eo_management/reports/DataTransaksi.jasper");
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, "File tidak ditemukan "+e);
        }
    }//GEN-LAST:event_menuReportTransaksiPesananLayananActionPerformed

    private void menuReportDataPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportDataPelangganActionPerformed
        // TODO add your handling code here:
        try {
            
            File namafile = new File("src/eo_management/reports/DataPelanggan.jasper");
            
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, "File tidak ditemukan "+e);
        }
    }//GEN-LAST:event_menuReportDataPelangganActionPerformed

    private void menuReportTransaksiPesananAddonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportTransaksiPesananAddonActionPerformed
        // TODO add your handling code here:
        try {
            File namafile = new File("src/eo_management/reports/DataTransaksiAddon.jasper");
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, "File tidak ditemukan "+e);
        }
    }//GEN-LAST:event_menuReportTransaksiPesananAddonActionPerformed

    private void menuReportPaketLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportPaketLayananActionPerformed
        // TODO add your handling code here:
        try {
            
            File namafile = new File("src/eo_management/reports/DataPaketLayanan.jasper");
            
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, "File tidak ditemukan "+e);
        }
    }//GEN-LAST:event_menuReportPaketLayananActionPerformed

    private void menuReportPaketAddonLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportPaketAddonLayananActionPerformed
        // TODO add your handling code here:
        try {
            File namafile = new File("src/eo_management/reports/DataPaketAddonLayanan.jasper");
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, "File tidak ditemukan "+e);
        }
    }//GEN-LAST:event_menuReportPaketAddonLayananActionPerformed

    private void menuPermainanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPermainanActionPerformed
        // TODO add your handling code here:
        new Games (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPermainanActionPerformed

    private void menuRincianAcaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRincianAcaraActionPerformed
        // TODO add your handling code here:
        new RincianAcara (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuRincianAcaraActionPerformed

    private void menuCrewAcaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCrewAcaraActionPerformed
        // TODO add your handling code here:
        new CrewAcara (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuCrewAcaraActionPerformed

    private void menuRincianGameAcaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRincianGameAcaraActionPerformed
        // TODO add your handling code here:
         new RincianGameAcara (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuRincianGameAcaraActionPerformed

    private void menuAddon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddon1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuAddon1ActionPerformed

    private void menuReportRincianAcaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportRincianAcaraActionPerformed
        // TODO add your handling code here:
        try {
            
            File namafile = new File("src/eo_management/reports/DataRincianAcara.jasper");
            
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, "File tidak ditemukan "+e);
        }
    }//GEN-LAST:event_menuReportRincianAcaraActionPerformed

    private void menuPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPembayaranActionPerformed
        // TODO add your handling code here:
        new Pembayaran (this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuPembayaranActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jmlInv;
    private javax.swing.JLabel jmlKywn;
    private javax.swing.JLabel jmlPesanan;
    private javax.swing.JLabel jmlPkt;
    private javax.swing.JLabel jmlPlgn;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelNoHp;
    private javax.swing.JLabel labelUser_Id;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JMenu menuAddon;
    private javax.swing.JMenu menuAddon1;
    private javax.swing.JMenuItem menuCrewAcara;
    private javax.swing.JMenuItem menuInventaris;
    private javax.swing.JMenuItem menuJabatan;
    private javax.swing.JMenuItem menuKaryawan;
    private javax.swing.JMenuItem menuKategoriAddon;
    private javax.swing.JMenu menuMaster;
    private javax.swing.JMenu menuMaster1;
    private javax.swing.JMenu menuMaster2;
    private javax.swing.JMenu menuMaster3;
    private javax.swing.JMenuItem menuPaketAddon;
    private javax.swing.JMenuItem menuPaketLayanan;
    private javax.swing.JMenuItem menuPelanggan;
    private javax.swing.JMenuItem menuPembayaran;
    private javax.swing.JMenuItem menuPermainan;
    private javax.swing.JMenuItem menuPerusahaanPelanggan;
    private javax.swing.JMenuItem menuPesananAddonLayanan;
    private javax.swing.JMenuItem menuPesananLayanan;
    private javax.swing.JMenuItem menuReportDataPelanggan;
    private javax.swing.JMenuItem menuReportPaketAddonLayanan;
    private javax.swing.JMenuItem menuReportPaketLayanan;
    private javax.swing.JMenuItem menuReportRincianAcara;
    private javax.swing.JMenuItem menuReportTransaksiPesananAddon;
    private javax.swing.JMenuItem menuReportTransaksiPesananLayanan;
    private javax.swing.JMenuItem menuRincianAcara;
    private javax.swing.JMenuItem menuRincianGameAcara;
    private javax.swing.JMenuItem menuRole;
    private javax.swing.JMenuItem menuSubKategoriAddon;
    private javax.swing.JMenuItem menuSupplier1;
    private javax.swing.JMenuItem menuUsers;
    // End of variables declaration//GEN-END:variables
}
