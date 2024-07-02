-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 02, 2024 at 12:18 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eo_sistem_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `crew_acara`
--

CREATE TABLE `crew_acara` (
  `id_crew_acara` int(5) NOT NULL,
  `id_rincian_acara` int(10) NOT NULL,
  `id_karyawan` varchar(20) NOT NULL,
  `tipe_crew_acara` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id_game` int(5) NOT NULL,
  `nama_game` varchar(25) NOT NULL,
  `value_game` varchar(100) NOT NULL,
  `deskripsi_game` varchar(250) NOT NULL,
  `id_kategori_game` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`id_game`, `nama_game`, `value_game`, `deskripsi_game`, `id_kategori_game`) VALUES
(1, 'Arung Jeram', 'Teamwork', 'Game ini biasanya untuk karyawan yang ingin meningkatkan teamwork', 1),
(2, 'Trekking', 'Teamwork', 'Trekking adalah kegiatan yang biasanya dilakukan dengn berjalan kaki di alam terbuka', 2),
(3, 'Paint Ball', 'Teamwork', 'Game ini biasanya untuk memacu adrenalin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `inventaris`
--

CREATE TABLE `inventaris` (
  `id_inventaris` varchar(15) NOT NULL,
  `nama_inventaris` varchar(50) NOT NULL,
  `jumlah_inventaris` int(10) NOT NULL,
  `deskripsi_inventaris` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventaris`
--

INSERT INTO `inventaris` (`id_inventaris`, `nama_inventaris`, `jumlah_inventaris`, `deskripsi_inventaris`) VALUES
('IN00001', 'Megaphone', 4, 'Megaphone untuk crew'),
('IN00002', 'Monitor LG 21 Inch', 1, 'Untuk di kantor'),
('IN00003', 'Tali Tambang Besar', 3, 'Masing-masing 3 meter'),
('IN00004', 'Laptop', 3, 'Untuk oprasional lapangan');

-- --------------------------------------------------------

--
-- Table structure for table `jabatan_karyawan`
--

CREATE TABLE `jabatan_karyawan` (
  `id_jabatan_karyawan` int(15) NOT NULL,
  `nama_jabatan_karyawan` varchar(25) NOT NULL,
  `gaji_jabatan_karyawan` varchar(15) NOT NULL,
  `deskripsi_pekerjaan_jabatan_karyawan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jabatan_karyawan`
--

INSERT INTO `jabatan_karyawan` (`id_jabatan_karyawan`, `nama_jabatan_karyawan`, `gaji_jabatan_karyawan`, `deskripsi_pekerjaan_jabatan_karyawan`) VALUES
(1, 'Admin Web', '3000000', 'Input data karyawan'),
(2, 'HRD', '2000000', 'merekap data transaksi mingguan dan bulanan'),
(3, 'Manager', '7000000', 'Mengatur pengkondisian setia acara, membuat rundown acara, membuat laporan hasil acara untuk klien'),
(4, 'Fasilitatior', '3000000', 'Menyiapkan segala kebutuhan acara, memeriksa inventaris secara berkala setiap selesai acara');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` varchar(20) NOT NULL,
  `nama_karyawan` varchar(25) NOT NULL,
  `jenis_kelamin_karyawan` varchar(15) NOT NULL,
  `no_telp_karyawan` varchar(15) NOT NULL,
  `email_karyawan` varchar(25) NOT NULL,
  `id_jabatan_karyawan` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama_karyawan`, `jenis_kelamin_karyawan`, `no_telp_karyawan`, `email_karyawan`, `id_jabatan_karyawan`) VALUES
('00001', 'Administrator', 'Laki-Laki', '081224752917', 'admin@outoria.com', 1),
('ID00002', 'Muhamad Syarifudin', 'Laki-Laki', '085770249760', 'syarif@outoria.com', 3),
('ID00003', 'Sudrajat Ali Mukti', 'Laki-Laki', '0895789531398', 'sudrajat@outoria.com', 4),
('ID00004', 'Markus', 'Laki-Laki', '081572929182', 'markus@outria.com', 2),
('ID00005', 'Muhamad Fahmi', 'Laki-Laki', '081224752918', 'fahmi@outoria.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `kategori_addon`
--

CREATE TABLE `kategori_addon` (
  `id_kategori_addon` int(5) NOT NULL,
  `nama_kategori_addon` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori_addon`
--

INSERT INTO `kategori_addon` (`id_kategori_addon`, `nama_kategori_addon`) VALUES
(2, 'Transportasi'),
(3, 'Snack'),
(4, 'Pakaian'),
(6, 'Dokumentasi');

-- --------------------------------------------------------

--
-- Table structure for table `kategori_addon_layanan`
--

CREATE TABLE `kategori_addon_layanan` (
  `id_kategori_addon_layanan` int(5) NOT NULL,
  `nama_kategori_addon_layanan` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `kategori_game`
--

CREATE TABLE `kategori_game` (
  `id_kategori_game` int(5) NOT NULL,
  `nama_kategori_game` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori_game`
--

INSERT INTO `kategori_game` (`id_kategori_game`, `nama_kategori_game`) VALUES
(1, 'High Impact'),
(2, 'Low Impacts'),
(3, 'Education');

-- --------------------------------------------------------

--
-- Table structure for table `paket_addon`
--

CREATE TABLE `paket_addon` (
  `id_paket_addon` varchar(15) NOT NULL,
  `harga_paket_addon` int(15) NOT NULL,
  `include_paket_addon` varchar(250) NOT NULL,
  `deskripsi_paket_addon` varchar(250) NOT NULL,
  `id_sub_kategori_addon` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `paket_addon`
--

INSERT INTO `paket_addon` (`id_paket_addon`, `harga_paket_addon`, `include_paket_addon`, `deskripsi_paket_addon`, `id_sub_kategori_addon`) VALUES
('AN00001', 1200000, 'Sopir,\nBensin,\nAsuransi', '', 1),
('AN00002', 25000, 'Ubi, Singkong, Pisang, Air Mineral 150ml', '', 2),
('AN00003', 3000000, 'Softfile Google Drive,\nDVD,\nFoto-foto,\nVideo\n', '', 5);

-- --------------------------------------------------------

--
-- Table structure for table `paket_layanan`
--

CREATE TABLE `paket_layanan` (
  `id_paket_layanan` varchar(15) NOT NULL,
  `nama_paket_layanan` varchar(30) NOT NULL,
  `harga_paket_layanan` int(11) NOT NULL,
  `minimal_order_paket_layanan` int(11) NOT NULL,
  `include_paket_layanan` text NOT NULL,
  `deskripsi_paket_layanan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `paket_layanan`
--

INSERT INTO `paket_layanan` (`id_paket_layanan`, `nama_paket_layanan`, `harga_paket_layanan`, `minimal_order_paket_layanan`, `include_paket_layanan`, `deskripsi_paket_layanan`) VALUES
('PK00001', 'Paket Amazing Race', 350000, 20, 'Air Mineral,\nTopi Buket,\nTrekking Pole,\nKaos Team,\nBanner Welcome,\nWelcome Drink,\nLunch Box', 'Paket yang cocok untuk anda para karyawan yang ingin meningkatkan value dalam kerjasama team '),
('PK00002', 'Paket Trekking', 150000, 25, 'Air Mineral,\nTrekking Pole,\nGuide,\nTiket Wisata,\nRetribusi Perhutani', ''),
('PK00003', 'Paket Rafting', 170000, 15, 'Guide,\nKelapa Muda,\nGorengan,\nLunch Box,\nTiket Wisata,\nDokumentasi', ''),
('PK00004', 'Paket Paintball', 125000, 25, 'Safety Equipment,\nPeluru 30 Butir,\nWelcome Drink', 'Paket ini termasuk ke paket olahraga atau kegiatan luar ruangan yang mempunyai high impact value');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` varchar(15) NOT NULL,
  `nama_pelanggan` varchar(25) NOT NULL,
  `no_telp_pelanggan` varchar(15) NOT NULL,
  `email_pelanggan` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_telp_pelanggan`, `email_pelanggan`) VALUES
('PL00001', 'Fahmi', '087383838833', 'fahmi@adi.com'),
('PL00002', 'Fauzi', '087822828282', 'fauzi@aia.com'),
('PL00003', 'Riana', '082727282822', 'riana@bca.com'),
('PL00004', 'Fidia', '081227282232', 'fidia@abc.com'),
('PL00005', 'Elvira', '081227282266', 'elvira@tekno.com'),
('PL00006', 'Pedro', '081227283344', 'pedro@networkindo.com'),
('PL00007', 'Abidzar', '081227288877', 'abidzar@idx.com'),
('PL00008', 'Aldino', '081227288999', 'aldino@cosmos.com');

-- --------------------------------------------------------

--
-- Table structure for table `perusahaan_pelanggan`
--

CREATE TABLE `perusahaan_pelanggan` (
  `id_perusahaan_pelanggan` varchar(15) NOT NULL,
  `nama_perusahaan_pelanggan` varchar(30) NOT NULL,
  `jenis_perusahaan_pelanggan` varchar(15) NOT NULL,
  `alamat_perusahaan_pelanggan` varchar(100) NOT NULL,
  `id_pelanggan` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `perusahaan_pelanggan`
--

INSERT INTO `perusahaan_pelanggan` (`id_perusahaan_pelanggan`, `nama_perusahaan_pelanggan`, `jenis_perusahaan_pelanggan`, `alamat_perusahaan_pelanggan`, `id_pelanggan`) VALUES
('100001', 'PT. ABC', 'Pangan', 'Jl. Sentul Kab. Bogor 16810 Jawa Barat', 'PL00004'),
('100002', 'PT. Akurasi Data Indonesia', 'Teknologi', 'Ps. Minggu Jakarta Selatan DKI Jakarta', 'PL00001'),
('100003', 'PT. AIA Indonesia', 'Asuransi', 'Jl. Kuningan Jakarta Selatan DKI Jakarta', 'PL00002'),
('100004', 'BCA', 'Perbankan', 'Jl. Sudirman DKI Jakarta Indonesia', 'PL00003'),
('100005', 'Sanfas Teknovatif', 'Konsultan IT', 'Jl. Gn. pancar No. 46 Kec. Babakan Madang Kab. Bogor 16910 Jawa Barat', 'PL00005');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan_addon`
--

CREATE TABLE `pesanan_addon` (
  `id_pesanan_addon` varchar(25) NOT NULL,
  `id_paket_addon` varchar(15) NOT NULL,
  `id_pesanan_layanan` varchar(25) NOT NULL,
  `jumlah_pesanan_addon` int(5) NOT NULL,
  `status_lunas_pesanan_addon` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pesanan_addon`
--

INSERT INTO `pesanan_addon` (`id_pesanan_addon`, `id_paket_addon`, `id_pesanan_layanan`, `jumlah_pesanan_addon`, `status_lunas_pesanan_addon`) VALUES
('INV00001AN', 'AN00001', 'INV00002', 6, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pesanan_layanan`
--

CREATE TABLE `pesanan_layanan` (
  `id_pesanan_layanan` varchar(25) NOT NULL,
  `id_pelanggan` varchar(15) NOT NULL,
  `id_paket_layanan` varchar(15) NOT NULL,
  `jumlah_peserta_pesanan_layanan` int(5) NOT NULL,
  `status_lunas_pesanan_layanan` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pesanan_layanan`
--

INSERT INTO `pesanan_layanan` (`id_pesanan_layanan`, `id_pelanggan`, `id_paket_layanan`, `jumlah_peserta_pesanan_layanan`, `status_lunas_pesanan_layanan`) VALUES
('INV00001', 'PL00002', 'PK00001', 35, 0),
('INV00002', 'PL00001', 'PK00002', 120, 0),
('INV00003', 'PL00003', 'PK00003', 150, 0),
('INV00004', 'PL00004', 'PK00001', 100, 0),
('INV00005', 'PL00005', 'PK00003', 120, 0);

-- --------------------------------------------------------

--
-- Table structure for table `rincian_acara`
--

CREATE TABLE `rincian_acara` (
  `id_rincian_acara` int(10) NOT NULL,
  `id_pesanan_layanan` varchar(20) NOT NULL,
  `tanggal_rincian_acara` date NOT NULL,
  `venue_rincian_acara` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rincian_game_acara`
--

CREATE TABLE `rincian_game_acara` (
  `id_rincian_game_acara` int(10) NOT NULL,
  `id_rincian_acara` int(10) NOT NULL,
  `id_game` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `nama_role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `nama_role`) VALUES
(2, 'Sekretstis'),
(3, 'Admin'),
(4, 'Manager');

-- --------------------------------------------------------

--
-- Table structure for table `sub_kategori_addon`
--

CREATE TABLE `sub_kategori_addon` (
  `id_sub_kategori_addon` int(5) NOT NULL,
  `nama_sub_kategori_addon` varchar(25) NOT NULL,
  `id_kategori_addon` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sub_kategori_addon`
--

INSERT INTO `sub_kategori_addon` (`id_sub_kategori_addon`, `nama_sub_kategori_addon`, `id_kategori_addon`) VALUES
(1, 'Bus 36 Seat', '2'),
(2, 'Rebusan', '3'),
(4, 'Kaos Team', '4'),
(5, 'Drone Professional', '6');

-- --------------------------------------------------------

--
-- Table structure for table `supplier_addon_layanan`
--

CREATE TABLE `supplier_addon_layanan` (
  `id_supplier_addon_layanan` int(5) NOT NULL,
  `nama_supplier_addon_layanan` varchar(25) NOT NULL,
  `no_telp_suplier_addon_layanan` varchar(15) NOT NULL,
  `email_supplier_addon_layanan` varchar(25) NOT NULL,
  `alamat_supplier_addon_layanan` varchar(100) NOT NULL,
  `id_sub_kategori_addon` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(5) NOT NULL,
  `id_role` int(5) NOT NULL,
  `id_karyawan` int(5) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `id_role`, `id_karyawan`, `password`) VALUES
(1, 1, 1, '0192023a7bbd73250516f069df18b500');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `crew_acara`
--
ALTER TABLE `crew_acara`
  ADD PRIMARY KEY (`id_crew_acara`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id_game`);

--
-- Indexes for table `inventaris`
--
ALTER TABLE `inventaris`
  ADD PRIMARY KEY (`id_inventaris`);

--
-- Indexes for table `jabatan_karyawan`
--
ALTER TABLE `jabatan_karyawan`
  ADD PRIMARY KEY (`id_jabatan_karyawan`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `kategori_addon`
--
ALTER TABLE `kategori_addon`
  ADD PRIMARY KEY (`id_kategori_addon`);

--
-- Indexes for table `kategori_addon_layanan`
--
ALTER TABLE `kategori_addon_layanan`
  ADD PRIMARY KEY (`id_kategori_addon_layanan`);

--
-- Indexes for table `kategori_game`
--
ALTER TABLE `kategori_game`
  ADD PRIMARY KEY (`id_kategori_game`);

--
-- Indexes for table `paket_addon`
--
ALTER TABLE `paket_addon`
  ADD PRIMARY KEY (`id_paket_addon`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `perusahaan_pelanggan`
--
ALTER TABLE `perusahaan_pelanggan`
  ADD PRIMARY KEY (`id_perusahaan_pelanggan`);

--
-- Indexes for table `pesanan_addon`
--
ALTER TABLE `pesanan_addon`
  ADD PRIMARY KEY (`id_pesanan_addon`);

--
-- Indexes for table `pesanan_layanan`
--
ALTER TABLE `pesanan_layanan`
  ADD PRIMARY KEY (`id_pesanan_layanan`);

--
-- Indexes for table `rincian_acara`
--
ALTER TABLE `rincian_acara`
  ADD PRIMARY KEY (`id_rincian_acara`);

--
-- Indexes for table `rincian_game_acara`
--
ALTER TABLE `rincian_game_acara`
  ADD PRIMARY KEY (`id_rincian_game_acara`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indexes for table `sub_kategori_addon`
--
ALTER TABLE `sub_kategori_addon`
  ADD PRIMARY KEY (`id_sub_kategori_addon`);

--
-- Indexes for table `supplier_addon_layanan`
--
ALTER TABLE `supplier_addon_layanan`
  ADD PRIMARY KEY (`id_supplier_addon_layanan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `crew_acara`
--
ALTER TABLE `crew_acara`
  MODIFY `id_crew_acara` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id_game` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `jabatan_karyawan`
--
ALTER TABLE `jabatan_karyawan`
  MODIFY `id_jabatan_karyawan` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `kategori_addon`
--
ALTER TABLE `kategori_addon`
  MODIFY `id_kategori_addon` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `kategori_addon_layanan`
--
ALTER TABLE `kategori_addon_layanan`
  MODIFY `id_kategori_addon_layanan` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `kategori_game`
--
ALTER TABLE `kategori_game`
  MODIFY `id_kategori_game` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rincian_acara`
--
ALTER TABLE `rincian_acara`
  MODIFY `id_rincian_acara` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rincian_game_acara`
--
ALTER TABLE `rincian_game_acara`
  MODIFY `id_rincian_game_acara` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sub_kategori_addon`
--
ALTER TABLE `sub_kategori_addon`
  MODIFY `id_sub_kategori_addon` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
