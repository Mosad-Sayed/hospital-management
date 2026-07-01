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
-- Table structure for table `mm_bedroom`
--

CREATE TABLE `mm_bedroom` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `bed` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mm_bedroom`
--

INSERT INTO `mm_bedroom` (`id`, `available`, `bed`, `room`) VALUES
(1, b'1', 'ISO', '12'),
(2, b'1', 'A', '11'),
(3, b'1', 'A', '10'),
(4, b'1', 'A', '9'),
(5, b'1', 'ISO', '8'),
(6, b'1', 'A', '7'),
(7, b'1', 'A', '6'),
(8, b'1', 'A', '5'),
(9, b'1', 'A', '4'),
(10, b'1', 'A', '3'),
(11, b'1', 'A', '2'),
(12, b'1', 'A', '1'),
(13, b'1', 'ISO+', '12'),
(14, b'1', 'A+', '11'),
(15, b'1', 'A+', '10'),
(16, b'1', 'A+', '9'),
(17, b'1', 'ISO+', '8'),
(18, b'1', 'A+', '7'),
(19, b'1', 'A+', '6'),
(20, b'1', 'A+', '5'),
(21, b'1', 'A+', '4'),
(22, b'1', 'A+', '3'),
(23, b'1', 'A+', '2'),
(24, b'1', 'A+', '1'),
(25, b'1', 'B', '11'),
(26, b'1', 'B', '10'),
(27, b'1', 'B', '9'),
(28, b'1', 'B', '7'),
(29, b'1', 'B', '6'),
(30, b'1', 'B', '5'),
(31, b'1', 'B', '4'),
(32, b'1', 'B', '3'),
(33, b'1', 'B', '2'),
(34, b'1', 'B', '1'),
(35, b'1', 'B+', '11'),
(36, b'1', 'B+', '10'),
(37, b'1', 'B+', '9'),
(38, b'1', 'B+', '7'),
(39, b'1', 'B+', '6'),
(40, b'1', 'B+', '5'),
(41, b'1', 'B+', '4'),
(42, b'1', 'B+', '3'),
(43, b'1', 'B+', '2'),
(44, b'1', 'B+', '1'),
(45, b'1', 'C', '11'),
(46, b'1', 'C', '10'),
(47, b'1', 'C', '9'),
(48, b'1', 'C', '7'),
(49, b'1', 'C', '6'),
(50, b'1', 'C', '5'),
(51, b'1', 'C', '4'),
(52, b'1', 'C', '3'),
(53, b'1', 'C', '2'),
(54, b'1', 'C', '1'),
(55, b'1', 'C+', '11'),
(56, b'1', 'C+', '10'),
(57, b'1', 'C+', '9'),
(58, b'1', 'C+', '7'),
(59, b'1', 'C+', '6'),
(60, b'1', 'C+', '5'),
(61, b'1', 'C+', '4'),
(62, b'1', 'C+', '3'),
(63, b'1', 'C+', '2'),
(64, b'1', 'C+', '1'),
(65, b'1', 'D', '11'),
(66, b'1', 'D', '10'),
(67, b'1', 'D', '9'),
(68, b'1', 'D', '7'),
(69, b'1', 'D', '6'),
(70, b'1', 'D', '5'),
(71, b'1', 'D', '4'),
(72, b'1', 'D', '3'),
(73, b'1', 'D', '2'),
(74, b'1', 'D', '1'),
(75, b'1', 'D+', '11'),
(76, b'1', 'D+', '10'),
(77, b'1', 'D+', '9'),
(78, b'1', 'D+', '7'),
(79, b'1', 'D+', '6'),
(80, b'1', 'D+', '5'),
(81, b'1', 'D+', '4'),
(82, b'1', 'D+', '3'),
(83, b'1', 'D+', '2'),
(84, b'1', 'D+', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mm_bedroom`
--
ALTER TABLE `mm_bedroom`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mm_bedroom`
--
ALTER TABLE `mm_bedroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
