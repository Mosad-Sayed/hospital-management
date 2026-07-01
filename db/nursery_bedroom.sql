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
-- Table structure for table `nursery_bedroom`
--

CREATE TABLE `nursery_bedroom` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `bed` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nursery_bedroom`
--

INSERT INTO `nursery_bedroom` (`id`, `available`, `bed`, `room`) VALUES
(1, b'1', 'ISO', '2'),
(2, b'1', 'ISO', '1'),
(3, b'1', 'ISO+', '2'),
(4, b'1', 'ISO+', '1'),
(5, b'1', '1', '2'),
(6, b'1', '1', '1'),
(7, b'1', '1+', '2'),
(8, b'1', '1+', '1'),
(9, b'1', '2', '2'),
(10, b'1', '2', '1'),
(11, b'1', '2+', '2'),
(12, b'1', '2+', '1'),
(13, b'1', '3', '2'),
(14, b'1', '3', '1'),
(15, b'1', '3+', '2'),
(16, b'1', '3+', '1'),
(17, b'1', '4', '2'),
(18, b'1', '4', '1'),
(19, b'1', '4+', '2'),
(20, b'1', '4+', '1'),
(21, b'1', '5', '2'),
(22, b'1', '5', '1'),
(23, b'1', '5+', '2'),
(24, b'1', '5+', '1'),
(25, b'1', '6', '2'),
(26, b'1', '6', '1'),
(27, b'1', '6+', '2'),
(28, b'1', '6+', '1'),
(29, b'1', '7', '2'),
(30, b'1', '7', '1'),
(31, b'1', '7+', '2'),
(32, b'1', '7+', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `nursery_bedroom`
--
ALTER TABLE `nursery_bedroom`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nursery_bedroom`
--
ALTER TABLE `nursery_bedroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
