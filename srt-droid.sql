-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2013 at 09:12 AM
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
(24, 1, 3, 4, 20),
(26, 1, 1, 4, 20),
(26, 3, 1, 1, 19),
(26, 4, 1, 1, 4),
(27, 1, 1, 4, 20),
(27, 3, 1, 1, 19),
(27, 4, 1, 1, 4),
(27, 5, 2, 1, 8),
(27, 6, 1, 1, 7),
(28, 1, 2, 4, 20),
(28, 3, 5, 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `kategori_menu`
--

CREATE TABLE IF NOT EXISTS `kategori_menu` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `kategori_menu`
--

INSERT INTO `kategori_menu` (`id`, `nama`) VALUES
(1, 'makanan'),
(2, 'minuman'),
(3, 'snack'),
(4, 'cuci mulut');

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
  `tersedia` tinyint(1) NOT NULL,
  `deskripsi` text,
  `jumlah_jual` int(11) NOT NULL,
  `aktif` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_kategori`,`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id_kategori`, `id`, `nama`, `harga_modal`, `harga`, `tersedia`, `deskripsi`, `jumlah_jual`, `aktif`) VALUES
(1, 4, 'Ayam goreng mentega', 80000, 10000, 0, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.', 0, 0),
(1, 6, 'Pecel Lele', 7000, 9000, 0, 'Pecel Lele', 0, 1),
(1, 7, 'Nasi Goreng', 8000, 10000, 0, 'Nasi Goreng', 0, 1),
(1, 8, 'Bebek Goreng', 14000, 17000, 0, 'Bebek Goreng', 0, 0),
(1, 9, 'Soto Ayam', 8000, 10000, 0, 'Soto Ayam', 0, 1),
(1, 19, 'ayam goreng', 7500, 15000, 0, 'top', 0, 1),
(2, 13, 'Es Teh Manis', 1500, 2500, 1, 'Es Teh Manis', 0, 1),
(2, 14, 'Es Jeruk', 2500, 3500, 0, 'Es Jeruk', 0, 1),
(2, 15, 'Tuak Nias', 2500, 5000, 0, 'Tuak asli dari Nias Timur', 0, 1),
(2, 17, 'Es Teler', 5000, 7500, 0, 'enak', 0, 1),
(3, 21, 'Kerupuk Udang', 2000, 3000, 0, 'Kerupuk udang asli', 0, 1),
(4, 20, 'Es Krim', 3000, 5000, 1, 'jakaja', 0, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id`, `status`, `no_meja`, `tanggal`, `username_koki`, `username_kasir`) VALUES
(20, 4, 3, '2013-04-17', NULL, NULL),
(24, 4, 6, '2013-04-21', NULL, NULL),
(25, 3, 10, '2013-05-20', NULL, NULL),
(26, 3, 1, '2013-05-21', NULL, NULL),
(27, 3, 1, '2013-05-21', NULL, NULL),
(28, 1, 5, '2013-05-21', NULL, NULL);

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
('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Jakarta', 15),
('indra', 'e24f6e3ce19ee0728ff1c443e4ff488d', 'indra', 'Depok', 1);

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
