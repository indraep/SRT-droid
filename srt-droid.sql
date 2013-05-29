-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2013 at 05:14 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `srt-droid`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_pesanan`
--

CREATE TABLE IF NOT EXISTS `detail_pesanan` (
  `id_pesanan` int(5) NOT NULL,
  `no` int(5) NOT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `id_kategori` int(5) NOT NULL,
  `id_menu` int(5) NOT NULL,
  PRIMARY KEY (`id_pesanan`,`no`),
  KEY `id_kategori` (`id_kategori`,`id_menu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_pesanan`
--

INSERT INTO `detail_pesanan` (`id_pesanan`, `no`, `jumlah`, `id_kategori`, `id_menu`) VALUES
(43, 1, 6, 1, 5),
(43, 2, 1, 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `kategori_menu`
--

CREATE TABLE IF NOT EXISTS `kategori_menu` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `kategori_menu`
--

INSERT INTO `kategori_menu` (`id`, `nama`) VALUES
(1, 'makanan'),
(2, 'minuman'),
(5, 'hua'),
(6, 'huaa'),
(7, 'Kuah');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `id_kategori` int(5) NOT NULL,
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `harga_modal` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `tersedia` tinyint(1) NOT NULL DEFAULT '1',
  `deskripsi` text,
  `jumlah_jual` int(11) NOT NULL,
  `aktif` tinyint(1) NOT NULL DEFAULT '1',
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_kategori`,`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id_kategori`, `id`, `nama`, `harga_modal`, `harga`, `tersedia`, `deskripsi`, `jumlah_jual`, `aktif`, `image`) VALUES
(1, 4, 'Soto Ayam', 8000, 10000, 1, 'Soto ayam dengan kuah bening yang segar', 0, 1, '2013-05-27_11-55-54.jpg'),
(1, 5, 'Nasi Goreng Spesial', 7500, 9000, 1, 'Nasi goreng dengan campuran ayam, telur, dan bakso', 0, 1, '2013-05-28_12-11-55.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE IF NOT EXISTS `pesanan` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL,
  `no_meja` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `username_pelayan` varchar(30) DEFAULT NULL,
  `username_koki` varchar(30) DEFAULT NULL,
  `username_kasir` varchar(30) DEFAULT NULL,
  `addition` text,
  PRIMARY KEY (`id`),
  KEY `username_koki` (`username_koki`),
  KEY `username_kasir` (`username_kasir`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id`, `status`, `no_meja`, `tanggal`, `username_pelayan`, `username_koki`, `username_kasir`, `addition`) VALUES
(43, 3, 1, '2013-05-23', 'admin', 'admin', 'admin', 'addition');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` text NOT NULL,
  `peran` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `nama`, `alamat`, `peran`) VALUES
('abc', '0cc175b9c0f1b6a831c399e269772661', 'abbc', 'a', 1),
('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Jakarta', 15),
('dev', '7815696ecbf1c96e6894b779456d330e', 'dev', 'fff', 10);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD CONSTRAINT `detail_pesanan_ibfk_1` FOREIGN KEY (`id_pesanan`) REFERENCES `pesanan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_pesanan_ibfk_2` FOREIGN KEY (`id_kategori`, `id_menu`) REFERENCES `menu` (`id_kategori`, `id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`username_koki`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pesanan_ibfk_2` FOREIGN KEY (`username_kasir`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
