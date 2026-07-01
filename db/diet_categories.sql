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
-- Table structure for table `diet_categories`
--

CREATE TABLE `diet_categories` (
  `id` bigint(20) NOT NULL,
  `category_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `diet_name` varchar(255) DEFAULT NULL,
  `pdf_link` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `diet_categories`
--

INSERT INTO `diet_categories` (`id`, `category_type`, `description`, `diet_name`, `pdf_link`) VALUES
(1, 'normal', 'النظام الغذائي العادي', 'Regular Diet (Normal Diet)', NULL),
(2, 'hypo', 'نظام غذائي منخفض اللحوم', 'Hypo Meat (N)', NULL),
(3, 'hypo', 'نظام غذائي منخفض الأسماك', 'Hypo Fish (N)', NULL),
(4, 'hypo', 'نظام غذائي منخفض الدجاج', 'Hypo Chic (N)', NULL),
(5, 'hypo', 'نظام غذائي منخفض البقوليات', 'Hypo Lequmes (N)', NULL),
(6, 'hypo', 'نظام غذائي منخفض البيض', 'Hypo Eggs (N)', NULL),
(7, 'hypo', 'نظام غذائي منخفض الألبان', 'Hypo Dairy (N)', NULL),
(8, 'hypo', 'نظام غذائي منخفض الجلوتين والحبوب', 'Hypo Gluten & Cereals (N)', NULL),
(9, 'hypo', 'نظام غذائي منخفض الخضروات', 'Hypo Veg (N)', NULL),
(10, 'hypo', 'نظام غذائي منخفض الفواكه', 'Hypo Fruits (N)', NULL),
(11, 'hypo', 'نظام غذائي منخفض الشوربات', 'Hypo Soups (N)', NULL),
(12, 'hypo', 'نظام غذائي منخفض المشروبات', 'Hypo Drinks (N)', NULL),
(13, 'hypo', 'نظام غذائي منخفض الزيوت والدهون', 'Hypo Oils & Fats (N)', NULL),
(14, 'hypo', 'نظام غذائي منخفض المكسرات', 'Hypo Nuts (N)', NULL),
(15, 'hypo', 'نظام غذائي منخفض الحلويات', 'Hypo Sweet (N)', NULL),
(16, 'hypo', 'نظام غذائي منخفض آخر', 'Hypo Other (N)', NULL),
(17, 'other', 'نظام غذائي عادي آخر', 'Normal-Other', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `diet_categories`
--
ALTER TABLE `diet_categories`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `diet_categories`
--
ALTER TABLE `diet_categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
