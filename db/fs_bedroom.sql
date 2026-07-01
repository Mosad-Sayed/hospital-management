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
-- Table structure for table `fs_bedroom`
--

CREATE TABLE `fs_bedroom` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `bed` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fs_bedroom`
--

INSERT INTO `fs_bedroom` (`id`, `available`, `bed`, `room`) VALUES
(1, b'1', '1', '9'),
(2, b'1', '1', '8'),
(3, b'1', '1', '7'),
(4, b'1', '1', '6'),
(5, b'1', '1', '5'),
(6, b'1', '1', '4'),
(7, b'1', 'ISO', '3'),
(8, b'1', '1', '2'),
(9, b'1', '1', '1'),
(10, b'1', '1+', '9'),
(11, b'1', '1+', '8'),
(12, b'1', '1+', '7'),
(13, b'1', '1+', '6'),
(14, b'1', '1+', '5'),
(15, b'1', '1+', '4'),
(16, b'1', 'ISO+', '3'),
(17, b'1', '1+', '2'),
(18, b'1', '1+', '1'),
(19, b'1', '2', '9'),
(20, b'1', '2', '8'),
(21, b'1', '2', '7'),
(22, b'1', '2', '6'),
(23, b'1', '2', '5'),
(24, b'1', '2', '4'),
(25, b'1', '2', '2'),
(26, b'1', '2', '1'),
(27, b'1', '2+', '9'),
(28, b'1', '2+', '8'),
(29, b'1', '2+', '7'),
(30, b'1', '2+', '6'),
(31, b'1', '2+', '5'),
(32, b'1', '2+', '4'),
(33, b'1', '2+', '2'),
(34, b'1', '2+', '1'),
(35, b'1', '3', '9'),
(36, b'1', '3', '8'),
(37, b'1', '3', '7'),
(38, b'1', '3', '6'),
(39, b'1', '3', '5'),
(40, b'1', '3', '4'),
(41, b'1', '3', '2'),
(42, b'1', '3', '1'),
(43, b'1', '3+', '9'),
(44, b'1', '3+', '8'),
(45, b'1', '3+', '7'),
(46, b'1', '3+', '6'),
(47, b'1', '3+', '5'),
(48, b'1', '3+', '4'),
(49, b'1', '3+', '2'),
(50, b'1', '3+', '1'),
(51, b'1', '4', '9'),
(52, b'1', '4', '8'),
(53, b'1', '4', '7'),
(54, b'1', '4', '6'),
(55, b'1', '4', '5'),
(56, b'1', '4', '4'),
(57, b'1', '4', '2'),
(58, b'1', '4', '1'),
(59, b'1', '4+', '9'),
(60, b'1', '4+', '8'),
(61, b'1', '4+', '7'),
(62, b'1', '4+', '6'),
(63, b'1', '4+', '5'),
(64, b'1', '4+', '4'),
(65, b'1', '4+', '2'),
(66, b'1', '4+', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fs_bedroom`
--
ALTER TABLE `fs_bedroom`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fs_bedroom`
--
ALTER TABLE `fs_bedroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
