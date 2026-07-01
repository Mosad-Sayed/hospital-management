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
-- Table structure for table `normal_diet_option`
--

CREATE TABLE `normal_diet_option` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `normal_diet_option`
--

INSERT INTO `normal_diet_option` (`id`, `active`, `label`, `value`, `category_id`) VALUES
(1, b'1', 'Regular Diet (Normal Diet)', 'Regular Diet (Normal Diet)', 1),
(2, b'1', 'Hypo Meat (N)', 'Hypo Meat (N)', 2),
(3, b'1', 'Hypo Fish (N)', 'Hypo Fish (N)', 2),
(4, b'1', 'Hypo Chic (N)', 'Hypo Chic (N)', 2),
(5, b'1', 'Hypo Lequmes (N)', 'Hypo Lequmes (N)', 2),
(6, b'1', 'Hypo Eggs (N)', 'Hypo Eggs (N)', 2),
(7, b'1', 'Hypo Dairy (N)', 'Hypo Dairy (N)', 2),
(8, b'1', 'Hypo Gluten & Cereals (N)', 'Hypo Gluten & Cereals (N)', 2),
(9, b'1', 'Hypo Veg (N)', 'Hypo Veg (N)', 2),
(10, b'1', 'Hypo Fruits (N)', 'Hypo Fruits (N)', 2),
(11, b'1', 'Hypo Soups (N)', 'Hypo Soups (N)', 2),
(12, b'1', 'Hypo Drinks (N)', 'Hypo Drinks (N)', 2),
(13, b'1', 'Hypo Oils & Fats (N)', 'Hypo Oils & Fats (N)', 2),
(14, b'1', 'Hypo Nuts (N)', 'Hypo Nuts (N)', 2),
(15, b'1', 'Hypo Sweet (N)', 'Hypo Sweet (N)', 2),
(16, b'1', 'Hypo Other (N)', 'Hypo Other (N)', 2),
(17, b'1', 'Normal-Other', 'Normal-Other', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `normal_diet_option`
--
ALTER TABLE `normal_diet_option`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm9uh796i7s39b2k2wkdd5329x` (`category_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `normal_diet_option`
--
ALTER TABLE `normal_diet_option`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `normal_diet_option`
--
ALTER TABLE `normal_diet_option`
  ADD CONSTRAINT `FKm9uh796i7s39b2k2wkdd5329x` FOREIGN KEY (`category_id`) REFERENCES `normal_diet_category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
