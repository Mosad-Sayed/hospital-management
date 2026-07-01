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
-- Table structure for table `diet_options`
--

CREATE TABLE `diet_options` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pdf_link` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `diet_options`
--

INSERT INTO `diet_options` (`id`, `category`, `name`, `pdf_link`, `value`, `label`, `type`) VALUES
(23, '', '', NULL, 'High Calorie & High CHON Diet', 'High Calorie & High CHON Diet', 'SPECIAL'),
(24, '', '', NULL, 'Renal Pre-ESRD Diet', 'Renal Pre-ESRD Diet', 'SPECIAL'),
(25, '', '', NULL, 'Renal Post-ESRD Diet', 'Renal Post-ESRD Diet', 'SPECIAL'),
(26, '', '', NULL, 'Low Fat Diet', 'Low Fat Diet', 'SPECIAL'),
(27, '', '', NULL, 'Fat Free (Low Cholesterol) Diet', 'Fat Free (Low Cholesterol) Diet', 'SPECIAL'),
(28, '', '', NULL, 'Low salt Diet (1200mg)', 'Low salt Diet (1200mg)', 'SPECIAL'),
(29, '', '', NULL, 'Salt Free Diet (500mg)', 'Salt Free Diet (500mg)', 'SPECIAL'),
(30, '', '', NULL, 'Diabetic Diet (IDDM)', 'Diabetic Diet (IDDM)', 'SPECIAL'),
(31, '', '', NULL, 'Diabetic Diet (NIDDM)', 'Diabetic Diet (NIDDM)', 'SPECIAL'),
(32, '', '', NULL, 'High Fiber Diet', 'High Fiber Diet', 'SPECIAL'),
(33, '', '', NULL, 'Soft Diet', 'Soft Diet', 'SPECIAL'),
(34, '', '', NULL, 'Puree (Bland) Diet', 'Puree (Bland) Diet', 'SPECIAL'),
(35, '', '', NULL, 'Full Liquid Diet', 'Full Liquid Diet', 'SPECIAL'),
(36, '', '', NULL, 'Clear Liquid Diet', 'Clear Liquid Diet', 'SPECIAL'),
(37, '', '', NULL, 'Cold Liquid Diet', 'Cold Liquid Diet', 'SPECIAL'),
(38, '', '', NULL, 'Hypo Meat (SP)', 'Hypo Meat (SP)', 'SPECIAL_HYPO'),
(39, '', '', NULL, 'Hypo Fish (SP)', 'Hypo Fish (SP)', 'SPECIAL_HYPO'),
(40, '', '', NULL, 'Hypo Chic (SP)', 'Hypo Chic (SP)', 'SPECIAL_HYPO'),
(41, '', '', NULL, 'Hypo Lequmes (SP)', 'Hypo Lequmes (SP)', 'SPECIAL_HYPO'),
(42, '', '', NULL, 'Hypo Eggs (SP)', 'Hypo Eggs (SP)', 'SPECIAL_HYPO'),
(43, '', '', NULL, 'Hypo Dairy (SP)', 'Hypo Dairy (SP)', 'SPECIAL_HYPO'),
(44, '', '', NULL, 'Hypo Gluten & Cereals (SP)', 'Hypo Gluten & Cereals (SP)', 'SPECIAL_HYPO'),
(45, '', '', NULL, 'Hypo Veg (SP)', 'Hypo Veg (SP)', 'SPECIAL_HYPO'),
(46, '', '', NULL, 'Hypo Fruits (SP)', 'Hypo Fruits (SP)', 'SPECIAL_HYPO'),
(47, '', '', NULL, 'Hypo Soups (SP)', 'Hypo Soups (SP)', 'SPECIAL_HYPO'),
(48, '', '', NULL, 'Hypo drinks (SP)', 'Hypo drinks (SP)', 'SPECIAL_HYPO'),
(49, '', '', NULL, 'Hypo Oils & Fats (SP)', 'Hypo Oils & Fats (SP)', 'SPECIAL_HYPO'),
(50, '', '', NULL, 'Hypo Nuts  (SP)', 'Hypo Nuts  (SP)', 'SPECIAL_HYPO'),
(51, '', '', NULL, 'Hypo Sweet (SP)', 'Hypo Sweet (SP)', 'SPECIAL_HYPO'),
(52, '', '', NULL, 'Hypo- Other (SP)', 'Hypo- Other (SP)', 'SPECIAL_HYPO'),
(53, '', '', NULL, 'SPEICAL-Other', 'SPEICAL-Other', 'OTHER_SPECIAL'),
(54, '', '', NULL, 'OSF (Standard 1.0)', 'OSF (Standard 1.0)', 'OTHER_OSF'),
(55, '', '', NULL, 'OSF (Concentrated1.5)', 'OSF (Concentrated1.5)', 'OTHER_OSF'),
(56, '', '', NULL, 'OSF (DIABETIC)', 'OSF (DIABETIC)', 'OTHER_OSF'),
(57, '', '', NULL, 'OSF (HEPATIC)', 'OSF (HEPATIC)', 'OTHER_OSF'),
(58, '', '', NULL, 'OSF (RENAL PRE)', 'OSF (RENAL PRE)', 'OTHER_OSF'),
(59, '', '', NULL, 'OSF (RENAL POST)', 'OSF (RENAL POST)', 'OTHER_OSF'),
(60, '', '', NULL, 'OSF (HIGH PRO)', 'OSF (HIGH PRO)', 'OTHER_OSF'),
(61, '', '', NULL, 'OSF (RESPIRATORY)', 'OSF (RESPIRATORY)', 'OTHER_OSF'),
(62, '', '', NULL, 'OSF (CLEAR ONS)', 'OSF (CLEAR ONS)', 'OTHER_OSF'),
(63, '', '', NULL, '+ Proten Powder', '+ Proten Powder', 'OTHER_OSF'),
(64, '', '', NULL, '+ Fiber Powder', '+ Fiber Powder', 'OTHER_OSF'),
(65, '', '', NULL, '+ Water', '+ Water', 'OTHER_OSF'),
(66, '', '', NULL, '+ Bag Pump', '+ Bag Pump', 'OTHER_OSF'),
(67, '', '', NULL, 'OSF-Other', 'OSF-Other', 'OTHER_OSF'),
(68, '', '', NULL, 'NPO', 'NPO', 'OTHER_GENERAL'),
(69, '', '', NULL, 'Water Only+MEDS', 'Water Only+MEDS', 'OTHER_GENERAL'),
(70, '', '', NULL, 'Water Only', 'Water Only', 'OTHER_GENERAL'),
(71, '', '', NULL, 'Out On Pass', 'Out On Pass', 'OTHER_GENERAL'),
(72, '', '', NULL, 'Breastfeeding', 'Breastfeeding', 'OTHER_GENERAL'),
(73, '', '', NULL, 'Other', 'Other', 'OTHER_GENERAL');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `diet_options`
--
ALTER TABLE `diet_options`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `diet_options`
--
ALTER TABLE `diet_options`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
