-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 16, 2025 at 12:52 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `pedia_bedroom`
--

CREATE TABLE `pedia_bedroom` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `bed` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pedia_bedroom`
--

INSERT INTO `pedia_bedroom` (`id`, `available`, `bed`, `room`) VALUES
(1, b'1', 'ISO', '3'),
(2, b'1', 'ISO', '2'),
(3, b'1', 'ISO', '1'),
(4, b'1', 'ISO+', '3'),
(5, b'1', 'ISO+', '2'),
(6, b'1', 'ISO+', '1'),
(7, b'1', '1', '3'),
(8, b'1', '1', '2'),
(9, b'1', '1', '1'),
(10, b'1', '1+', '3'),
(11, b'1', '1+', '2'),
(12, b'1', '1+', '1'),
(13, b'1', '2', '3'),
(14, b'1', '2', '2'),
(15, b'1', '2', '1'),
(16, b'1', '2+', '3'),
(17, b'1', '2+', '2'),
(18, b'1', '2+', '1'),
(19, b'1', '3', '3'),
(20, b'1', '3', '2'),
(21, b'1', '3', '1'),
(22, b'1', '3+', '3'),
(23, b'1', '3+', '2'),
(24, b'1', '3+', '1'),
(25, b'1', '4', '3'),
(26, b'1', '4', '2'),
(27, b'1', '4', '1'),
(28, b'1', '4+', '3'),
(29, b'1', '4+', '2'),
(30, b'1', '4+', '1'),
(31, b'1', '5', '3'),
(32, b'1', '5', '2'),
(33, b'1', '5', '1'),
(34, b'1', '5+', '3'),
(35, b'1', '5+', '2'),
(36, b'1', '5+', '1'),
(37, b'1', '6', '3'),
(38, b'1', '6', '2'),
(39, b'1', '6', '1'),
(40, b'1', '6+', '3'),
(41, b'1', '6+', '2'),
(42, b'1', '6+', '1'),
(43, b'1', '7', '3'),
(44, b'1', '7', '2'),
(45, b'1', '7', '1'),
(46, b'1', '7+', '3'),
(47, b'1', '7+', '2'),
(48, b'1', '7+', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pedia_bedroom`
--
ALTER TABLE `pedia_bedroom`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pedia_bedroom`
--
ALTER TABLE `pedia_bedroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
