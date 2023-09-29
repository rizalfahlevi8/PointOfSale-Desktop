-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2023 at 05:08 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `PointOfSale`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `JumlahPenjualan` (IN `tanggalPenjualan` DATE, OUT `JumlahPenjualan` INT)   BEGIN
    SELECT COUNT(*) INTO JumlahPenjualan
    FROM penjualan
    WHERE DATE(tanggal_penjualan) = tanggalPenjualan;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Keuntungan` (IN `tanggalPenjualan` DATE, OUT `totalKeuntungan` DECIMAL(10,2))   BEGIN
    SELECT SUM((p.harga_jual - p.harga_beli) * dp.jumlah) INTO totalKeuntungan
    FROM detail_penjualan dp
    JOIN produk p ON dp.kode_produk = p.kode_produk
    JOIN penjualan j ON dp.penjualan_Id = j.penjualan_Id
    WHERE DATE(j.tanggal_penjualan) = tanggalPenjualan;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ProdukKurangDari` (IN `input_stok` INT)   BEGIN
    SELECT *
    FROM produk
    WHERE stok_produk < input_stok;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TopProduk` (IN `input_count` INT)   BEGIN
    SELECT p.*
    FROM produk p
    INNER JOIN (
        SELECT kode_produk
        FROM nota_penjualan
        GROUP BY kode_produk
        ORDER BY SUM(jumlah) DESC
        LIMIT input_count
    ) AS top ON p.kode_produk = top.kode_produk;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TotalPendapatan` (IN `tanggalPenjualan` DATE, OUT `totalHargaPenjualan` DECIMAL(10,2))   BEGIN
    SELECT SUM(total_Pembayaran) INTO totalHargaPenjualan
    FROM penjualan
    WHERE DATE(tanggal_penjualan) = tanggalPenjualan;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_pembelian`
--

CREATE TABLE `detail_pembelian` (
  `pembelian_Id` int(11) NOT NULL,
  `kode_produk` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `detail_pembelian`
--
DELIMITER $$
CREATE TRIGGER `restock` AFTER INSERT ON `detail_pembelian` FOR EACH ROW BEGIN
INSERT INTO produk SET
produk.kode_produk = NEW.kode_produk, produk.stok_produk = NEW.jumlah ON DUPLICATE KEY UPDATE produk.stok_produk = produk.stok_produk + NEW.jumlah;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `jumlah` int(11) NOT NULL,
  `Subtotal` int(11) NOT NULL,
  `kode_produk` int(11) NOT NULL,
  `penjualan_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `detail_penjualan`
--
DELIMITER $$
CREATE TRIGGER `kurangiStok` BEFORE INSERT ON `detail_penjualan` FOR EACH ROW BEGIN
INSERT INTO produk SET
produk.kode_produk = NEW.kode_produk, produk.stok_produk = NEW.jumlah
ON DUPLICATE KEY UPDATE produk.stok_produk = produk.stok_produk - NEW.jumlah;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `kategori_Id` int(11) NOT NULL,
  `nama_kategori` varchar(30) NOT NULL,
  `no_rak` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Stand-in structure for view `laporan_penjualan`
-- (See below for the actual view)
--
CREATE TABLE `laporan_penjualan` (
`penjualan_Id` int(11)
,`tanggal_penjualan` date
,`total_pembayaran` int(50)
,`nama_user` varchar(30)
);

-- --------------------------------------------------------

--
-- Table structure for table `merek`
--

CREATE TABLE `merek` (
  `merek_Id` int(11) NOT NULL,
  `nama_merek` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Stand-in structure for view `nota_penjualan`
-- (See below for the actual view)
--
CREATE TABLE `nota_penjualan` (
`kode_produk` int(11)
,`user_Id` int(11)
,`penjualan_Id` int(11)
,`tanggal_penjualan` date
,`Total_pembayaran` int(50)
,`uang_diterima` int(50)
,`uang_kembalian` int(50)
,`jumlah` int(11)
,`Subtotal` int(11)
,`nama_user` varchar(30)
,`nama_produk` varchar(30)
,`harga_jual` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `pembelian_Id` int(11) NOT NULL,
  `tanggal_pembelian` date NOT NULL,
  `user_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `penjualan_Id` int(11) NOT NULL,
  `tanggal_penjualan` date NOT NULL,
  `Total_pembayaran` int(50) NOT NULL,
  `uang_diterima` int(50) NOT NULL,
  `uang_kembalian` int(50) NOT NULL,
  `user_Id` int(11) NOT NULL,
  `keuntungan` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `kode_produk` int(30) NOT NULL,
  `nama_produk` varchar(30) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `stok_produk` int(11) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `supplier_Id` int(11) NOT NULL,
  `kategori_Id` int(11) NOT NULL,
  `merek_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `supplier_Id` int(11) NOT NULL,
  `nama_supplier` varchar(30) NOT NULL,
  `alamat_supplier` varchar(255) NOT NULL,
  `telp_supplier` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Stand-in structure for view `tableproduk`
-- (See below for the actual view)
--
CREATE TABLE `tableproduk` (
`kode_produk` int(30)
,`nama_produk` varchar(30)
,`harga_beli` int(11)
,`harga_jual` int(11)
,`stok_produk` int(11)
,`nama_supplier` varchar(30)
,`nama_kategori` varchar(30)
,`nama_merek` varchar(30)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `tableusers`
-- (See below for the actual view)
--
CREATE TABLE `tableusers` (
`user_Id` int(11)
,`nama_user` varchar(30)
,`alamat_user` varchar(30)
,`telp_user` varchar(13)
,`username_user` varchar(30)
,`level_user` varchar(30)
,`status_user` varchar(30)
);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_Id` int(11) NOT NULL,
  `nama_user` varchar(30) NOT NULL,
  `alamat_user` varchar(30) NOT NULL,
  `telp_user` varchar(13) NOT NULL,
  `username_user` varchar(30) NOT NULL,
  `password_user` varchar(12) NOT NULL,
  `level_user` varchar(30) NOT NULL,
  `status_user` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_Id`, `nama_user`, `alamat_user`, `telp_user`, `username_user`, `password_user`, `level_user`, `status_user`) VALUES
(1, 'admin', '-', '-', 'admin', 'admin', 'PEMILIK', 'AKTIF'),
(2, 'karyawan', '-', '-', 'karyawan', 'karyawan', 'KARYAWAN', 'AKTIF');

-- --------------------------------------------------------

--
-- Structure for view `laporan_penjualan`
--
DROP TABLE IF EXISTS `laporan_penjualan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `laporan_penjualan`  AS SELECT `p`.`penjualan_Id` AS `penjualan_Id`, `p`.`tanggal_penjualan` AS `tanggal_penjualan`, `p`.`Total_pembayaran` AS `total_pembayaran`, `u`.`nama_user` AS `nama_user` FROM (`penjualan` `p` join `users` `u` on(`p`.`user_Id` = `u`.`user_Id`))  ;

-- --------------------------------------------------------

--
-- Structure for view `nota_penjualan`
--
DROP TABLE IF EXISTS `nota_penjualan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `nota_penjualan`  AS SELECT `detail_penjualan`.`kode_produk` AS `kode_produk`, `penjualan`.`user_Id` AS `user_Id`, `penjualan`.`penjualan_Id` AS `penjualan_Id`, `penjualan`.`tanggal_penjualan` AS `tanggal_penjualan`, `penjualan`.`Total_pembayaran` AS `Total_pembayaran`, `penjualan`.`uang_diterima` AS `uang_diterima`, `penjualan`.`uang_kembalian` AS `uang_kembalian`, `detail_penjualan`.`jumlah` AS `jumlah`, `detail_penjualan`.`Subtotal` AS `Subtotal`, `users`.`nama_user` AS `nama_user`, `produk`.`nama_produk` AS `nama_produk`, `produk`.`harga_jual` AS `harga_jual` FROM (((`penjualan` join `detail_penjualan` on(`penjualan`.`penjualan_Id` = `detail_penjualan`.`penjualan_Id`)) join `users` on(`penjualan`.`user_Id` = `users`.`user_Id`)) join `produk` on(`detail_penjualan`.`kode_produk` = `produk`.`kode_produk`))  ;

-- --------------------------------------------------------

--
-- Structure for view `tableproduk`
--
DROP TABLE IF EXISTS `tableproduk`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tableproduk`  AS SELECT `p`.`kode_produk` AS `kode_produk`, `p`.`nama_produk` AS `nama_produk`, `p`.`harga_beli` AS `harga_beli`, `p`.`harga_jual` AS `harga_jual`, `p`.`stok_produk` AS `stok_produk`, `s`.`nama_supplier` AS `nama_supplier`, `k`.`nama_kategori` AS `nama_kategori`, `m`.`nama_merek` AS `nama_merek` FROM (((`produk` `p` join `supplier` `s` on(`p`.`supplier_Id` = `s`.`supplier_Id`)) join `kategori` `k` on(`p`.`kategori_Id` = `k`.`kategori_Id`)) join `merek` `m` on(`p`.`merek_Id` = `m`.`merek_Id`))  ;

-- --------------------------------------------------------

--
-- Structure for view `tableusers`
--
DROP TABLE IF EXISTS `tableusers`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tableusers` AS
SELECT `users`.`user_Id` AS `user_Id`, `users`.`nama_user` AS `nama_user`, `users`.`alamat_user` AS `alamat_user`, `users`.`telp_user` AS `telp_user`, `users`.`username_user` AS `username_user`, `users`.`level_user` AS `level_user`, `users`.`status_user` AS `status_user`
FROM `users`;


--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD KEY `kode_produk` (`kode_produk`),
  ADD KEY `pembelian_Id` (`pembelian_Id`);

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD KEY `penjualan_Id` (`penjualan_Id`),
  ADD KEY `produkIDIndex` (`kode_produk`),
  ADD KEY `kode_produk` (`kode_produk`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kategori_Id`);

--
-- Indexes for table `merek`
--
ALTER TABLE `merek`
  ADD PRIMARY KEY (`merek_Id`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`pembelian_Id`),
  ADD KEY `user_Id` (`user_Id`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`penjualan_Id`),
  ADD KEY `user_Id` (`user_Id`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`kode_produk`),
  ADD KEY `NamaProdukIndex` (`nama_produk`),
  ADD KEY `kategori_Id` (`kategori_Id`),
  ADD KEY `merek_Id` (`merek_Id`),
  ADD KEY `supplier_Id` (`supplier_Id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplier_Id`),
  ADD KEY `NamaSupplierIndex` (`nama_supplier`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `kategori_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `merek`
--
ALTER TABLE `merek`
  MODIFY `merek_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `penjualan_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `supplier_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD CONSTRAINT `detail_pembelian_ibfk_1` FOREIGN KEY (`kode_produk`) REFERENCES `produk` (`kode_produk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_pembelian_ibfk_2` FOREIGN KEY (`pembelian_Id`) REFERENCES `pembelian` (`pembelian_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`penjualan_Id`) REFERENCES `penjualan` (`penjualan_Id`),
  ADD CONSTRAINT `detail_penjualan_ibfk_3` FOREIGN KEY (`kode_produk`) REFERENCES `produk` (`kode_produk`);

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`user_Id`) REFERENCES `users` (`user_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`user_Id`) REFERENCES `users` (`user_Id`);

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`kategori_Id`) REFERENCES `kategori` (`kategori_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `produk_ibfk_2` FOREIGN KEY (`merek_Id`) REFERENCES `merek` (`merek_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `produk_ibfk_3` FOREIGN KEY (`supplier_Id`) REFERENCES `supplier` (`supplier_Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

ALTER TABLE kategori AUTO_INCREMENT = 1;
ALTER TABLE supplier AUTO_INCREMENT = 1;
ALTER TABLE merek AUTO_INCREMENT = 1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
