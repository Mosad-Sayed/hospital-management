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
-- Table structure for table `ms_bedroom`
--

CREATE TABLE `ms_bedroom` (
  `bed` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ms_bedroom`
--

INSERT INTO `ms_bedroom` (`bed`, `room`, `id`, `available`) VALUES
('ISO', '219', 1, b'1'),
('1', '218', 2, b'1'),
('1', '217', 3, b'1'),
('ISO', '216', 4, b'1'),
('1', '215', 5, b'1'),
('1', '214', 6, b'1'),
('1', '213', 7, b'1'),
('1', '212', 8, b'1'),
('1', '211', 9, b'1'),
('1', '210', 10, b'1'),
('1', '209', 11, b'1'),
('1', '208', 12, b'1'),
('ISO+', '219', 13, b'1'),
('1+', '218', 14, b'1'),
('1+', '217', 15, b'1'),
('ISO+', '216', 16, b'1'),
('1+', '215', 17, b'1'),
('1+', '214', 18, b'1'),
('1+', '213', 19, b'1'),
('1+', '212', 20, b'1'),
('1+', '211', 21, b'1'),
('1+', '210', 22, b'1'),
('1+', '209', 23, b'1'),
('1+', '208', 24, b'1'),
('2', '218', 25, b'1'),
('2', '217', 26, b'1'),
('2', '215', 27, b'1'),
('2', '214', 28, b'1'),
('2', '213', 29, b'1'),
('2', '212', 30, b'1'),
('2', '211', 31, b'1'),
('2', '210', 32, b'1'),
('2', '209', 33, b'1'),
('2', '208', 34, b'1'),
('2+', '218', 35, b'1'),
('2+', '217', 36, b'1'),
('2+', '215', 37, b'1'),
('2+', '214', 38, b'1'),
('2+', '213', 39, b'1'),
('2+', '212', 40, b'1'),
('2+', '211', 41, b'1'),
('2+', '210', 42, b'1'),
('2+', '209', 43, b'1'),
('2+', '208', 44, b'1'),
('3', '218', 45, b'1'),
('3', '217', 46, b'1'),
('3', '215', 47, b'1'),
('3', '214', 48, b'1'),
('3', '213', 49, b'1'),
('3', '212', 50, b'1'),
('3', '211', 51, b'1'),
('3', '210', 52, b'1'),
('3', '209', 53, b'1'),
('3', '208', 54, b'1'),
('3+', '218', 55, b'1'),
('3+', '217', 56, b'1'),
('3+', '215', 57, b'1'),
('3+', '214', 58, b'1'),
('3+', '213', 59, b'1'),
('3+', '212', 60, b'1'),
('3+', '211', 61, b'1'),
('3+', '210', 62, b'1'),
('3+', '209', 63, b'1'),
('3+', '208', 64, b'1'),
('4', '218', 65, b'1'),
('4', '217', 66, b'1'),
('4', '215', 67, b'1'),
('4', '214', 68, b'1'),
('4', '213', 69, b'1'),
('4', '212', 70, b'1'),
('4', '211', 71, b'1'),
('4', '210', 72, b'1'),
('4', '209', 73, b'1'),
('4', '208', 74, b'1'),
('4+', '218', 75, b'1'),
('4+', '217', 76, b'1'),
('4+', '215', 77, b'1'),
('4+', '214', 78, b'1'),
('4+', '213', 79, b'1'),
('4+', '212', 80, b'1'),
('4+', '211', 81, b'1'),
('4+', '210', 82, b'1'),
('4+', '209', 83, b'1'),
('4+', '208', 84, b'1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ms_bedroom`
--
ALTER TABLE `ms_bedroom`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ms_bedroom`
--
ALTER TABLE `ms_bedroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
