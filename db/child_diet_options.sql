-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 16, 2025 at 12:50 AM
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
-- Table structure for table `child_diet_options`
--

CREATE TABLE `child_diet_options` (
  `id` bigint(20) NOT NULL,
  `category` enum('CHILD','HYPO_CHILD','OTHER_CHILD') DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `order_index` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `child_diet_options`
--

INSERT INTO `child_diet_options` (`id`, `category`, `label`, `order_index`, `value`) VALUES
(1, 'CHILD', 'P0 (6M-12M) Infant Normal', 1, 'P0 (6M-12M) Infant Normal'),
(2, 'CHILD', 'P1 (1Y-3Y) Normal', 2, 'P1 (1Y-3Y) Normal'),
(3, 'CHILD', 'P2 (4Y-7Y) Normal', 3, 'P2 (4Y-7Y) Normal'),
(4, 'CHILD', 'P3 (8Y-12Y) Normal', 4, 'P3 (8Y-12Y) Normal'),
(5, 'CHILD', 'Pedia BRAT Diet (SP)', 5, 'Pedia BRAT Diet (SP)'),
(6, 'CHILD', 'Pedia Soft Diet', 6, 'Pedia Soft Diet'),
(7, 'CHILD', 'Pedia Diabetic Diet (IDDM)', 7, 'Pedia Diabetic Diet (IDDM)'),
(8, 'CHILD', 'Pedia Fat Free Diet', 8, 'Pedia Fat Free Diet'),
(9, 'CHILD', 'Pedia Liquid Diet', 9, 'Pedia Liquid Diet'),
(10, 'CHILD', 'Pedia Cold Liquid Diet', 10, 'Pedia Cold Liquid Diet'),
(11, 'CHILD', 'Pedia Renal Diet', 11, 'Pedia Renal Diet'),
(12, 'CHILD', 'Pedia High Calorie,High CHON', 12, 'Pedia High Calorie,High CHON'),
(13, 'HYPO_CHILD', 'Pedia Hypo Meat', 1, 'Pedia Hypo Meat'),
(14, 'HYPO_CHILD', 'Pedia Hypo Fish', 2, 'Pedia Hypo Fish'),
(15, 'HYPO_CHILD', 'Pedia Hypo Chic', 3, 'Pedia Hypo Chic'),
(16, 'HYPO_CHILD', 'Pedia Hypo Lequmes', 4, 'Pedia Hypo Lequmes'),
(17, 'HYPO_CHILD', 'Pedia Hypo Eggs', 5, 'Pedia Hypo Eggs'),
(18, 'HYPO_CHILD', 'Pedia Hypo Dairy', 6, 'Pedia Hypo Dairy'),
(19, 'HYPO_CHILD', 'Pedia Hypo Gluten & Cereals', 7, 'Pedia Hypo Gluten & Cereals'),
(20, 'HYPO_CHILD', 'Pedia Hypo Veg', 8, 'Pedia Hypo Veg'),
(21, 'HYPO_CHILD', 'Pedia Hypo Fruits', 9, 'Pedia Hypo Fruits'),
(22, 'HYPO_CHILD', 'Pedia Hypo Soups', 10, 'Pedia Hypo Soups'),
(23, 'HYPO_CHILD', 'Pedia Hypo drinks', 11, 'Pedia Hypo drinks'),
(24, 'HYPO_CHILD', 'Pedia Hypo Oils & Fats', 12, 'Pedia Hypo Oils & Fats'),
(25, 'HYPO_CHILD', 'Pedia Hypo Nuts', 13, 'Pedia Hypo Nuts'),
(26, 'HYPO_CHILD', 'Pedia Hypo Sweet', 14, 'Pedia Hypo Sweet'),
(27, 'HYPO_CHILD', 'Pedia Hypo- Other', 15, 'Pedia Hypo- Other'),
(28, 'OTHER_CHILD', 'CHILD-Other', 1, 'CHILD-Other');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `child_diet_options`
--
ALTER TABLE `child_diet_options`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `child_diet_options`
--
ALTER TABLE `child_diet_options`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
