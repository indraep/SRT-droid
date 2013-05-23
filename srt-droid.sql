-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2013 at 05:22 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `kategori_menu`
--

CREATE TABLE IF NOT EXISTS `kategori_menu` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `kategori_menu`
--

INSERT INTO `kategori_menu` (`id`, `nama`) VALUES
(1, 'makanan'),
(2, 'minuman'),
(5, 'hua'),
(6, 'huaa');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `id_kategori` int(5) NOT NULL,
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nama` varchar(30) NOT NULL,
  `harga_modal` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `tersedia` tinyint(1) NOT NULL DEFAULT '1',
  `deskripsi` text,
  `jumlah_jual` int(11) NOT NULL,
  `aktif` tinyint(1) NOT NULL DEFAULT '1',
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_kategori`,`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE IF NOT EXISTS `pesanan` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL,
  `no_meja` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `username_koki` varchar(30) DEFAULT NULL,
  `username_kasir` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username_koki` (`username_koki`),
  KEY `username_kasir` (`username_kasir`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id`, `status`, `no_meja`, `tanggal`, `username_koki`, `username_kasir`) VALUES
(32, 0, 1, '2013-05-23', NULL, NULL);

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
('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Jakarta', 15);

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
