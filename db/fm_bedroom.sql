-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 16, 2025 at 12:51 AM
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
-- Table structure for table `fm_bedroom`
--

CREATE TABLE `fm_bedroom` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `bed` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fm_bedroom`
--

INSERT INTO `fm_bedroom` (`id`, `available`, `bed`, `room`) VALUES
(1, b'1', 'ISO', '1'),
(2, b'1', '1', '7'),
(3, b'1', '1', '6'),
(4, b'1', '1', '5'),
(5, b'1', '1', '4'),
(6, b'1', '1', '3'),
(7, b'1', '1', '2'),
(8, b'1', '1', '1'),
(9, b'1', 'ISO+', '1'),
(10, b'1', '1+', '7'),
(11, b'1', '1+', '6'),
(12, b'1', '1+', '5'),
(13, b'1', '1+', '4'),
(14, b'1', '1+', '3'),
(15, b'1', '1+', '2'),
(16, b'1', '1+', '1'),
(17, b'1', 'ISO', '2'),
(18, b'1', '2', '7'),
(19, b'1', '2', '6'),
(20, b'1', '2', '5'),
(21, b'1', '2', '4'),
(22, b'1', '2', '3'),
(23, b'1', '2', '2'),
(24, b'1', '2', '1'),
(25, b'1', 'ISO+', '2'),
(26, b'1', '2+', '7'),
(27, b'1', '2+', '6'),
(28, b'1', '2+', '5'),
(29, b'1', '2+', '4'),
(30, b'1', '2+', '3'),
(31, b'1', '2+', '2'),
(32, b'1', '2+', '1'),
(33, b'1', 'ISO', '3'),
(34, b'1', '3', '7'),
(35, b'1', '3', '6'),
(36, b'1', '3', '5'),
(37, b'1', '3', '4'),
(38, b'1', '3', '3'),
(39, b'1', '3', '2'),
(40, b'1', '3', '1'),
(41, b'1', 'ISO+', '3'),
(42, b'1', '3+', '7'),
(43, b'1', '3+', '6'),
(44, b'1', '3+', '5'),
(45, b'1', '3+', '4'),
(46, b'1', '3+', '3'),
(47, b'1', '3+', '2'),
(48, b'1', '3+', '1'),
(49, b'1', '4', '7'),
(50, b'1', '4', '6'),
(51, b'1', '4', '5'),
(52, b'1', '4', '4'),
(53, b'1', '4', '3'),
(54, b'1', '4', '2'),
(55, b'1', '4', '1'),
(56, b'1', '4+', '7'),
(57, b'1', '4+', '6'),
(58, b'1', '4+', '5'),
(59, b'1', '4+', '4'),
(60, b'1', '4+', '3'),
(61, b'1', '4+', '2'),
(62, b'1', '4+', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fm_bedroom`
--
ALTER TABLE `fm_bedroom`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fm_bedroom`
--
ALTER TABLE `fm_bedroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
