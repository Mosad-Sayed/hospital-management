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
-- Table structure for table `icu_bedroom`
--

CREATE TABLE `icu_bedroom` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `bed` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `icu_bedroom`
--

INSERT INTO `icu_bedroom` (`id`, `available`, `bed`, `room`) VALUES
(1, b'1', '1', 'ICU'),
(2, b'1', '2', 'ICU'),
(3, b'1', '3', 'ICU'),
(4, b'1', '4', 'ICU'),
(5, b'1', '5', 'ICU'),
(6, b'1', '6', 'ICU'),
(7, b'1', '7', 'ICU'),
(8, b'1', '8', 'ICU'),
(9, b'1', '9', 'ICU'),
(10, b'1', '10', 'ICU'),
(11, b'1', '11', 'ICU'),
(12, b'1', '12', 'ICU'),
(13, b'1', 'ICU', 'ICU'),
(14, b'1', 'ICU', 'ICU'),
(15, b'1', 'ICU', 'ICU'),
(16, b'1', 'ISO1', 'ICU'),
(17, b'1', 'ISO2', 'ICU'),
(18, b'1', '1+', 'ICU'),
(19, b'1', '2+', 'ICU'),
(20, b'1', '3+', 'ICU'),
(21, b'1', '4+', 'ICU'),
(22, b'1', '5+', 'ICU'),
(23, b'1', '6+', 'ICU'),
(24, b'1', '7+', 'ICU'),
(25, b'1', '8+', 'ICU'),
(26, b'1', '9+', 'ICU'),
(27, b'1', '10+', 'ICU'),
(28, b'1', '12+', 'ICU'),
(29, b'1', 'ICU', 'ICU'),
(30, b'1', 'ICU', 'ICU'),
(31, b'1', 'ICU', 'ICU'),
(32, b'1', 'ISO 1+', 'ICU'),
(33, b'1', 'ISO 2+', 'ICU');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `icu_bedroom`
--
ALTER TABLE `icu_bedroom`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `icu_bedroom`
--
ALTER TABLE `icu_bedroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
