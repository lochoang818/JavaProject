-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2023 at 04:30 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `food`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `cate_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `restaurant` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`cate_id`, `name`, `restaurant`) VALUES
(1, 'Chicken', 1),
(2, 'Hamburger', 1),
(3, 'Beef', 2);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `food_id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `unit_quantity` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `res_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`food_id`, `image`, `name`, `price`, `unit_quantity`, `category_id`, `res_id`) VALUES
(1, 'https://www.indianhealthyrecipes.com/wp-content/uploads/2014/11/kfc-chicken-recipe.jpg', 'Chicken c', 123, 1, 1, 1),
(2, 'https://www.indianhealthyrecipes.com/wp-content/uploads/2014/11/kfc-chicken-recipe.jpg', 'Chicken A', 11, 1, 1, 1),
(5, 'https://www.indianhealthyrecipes.com/wp-content/uploads/2014/11/kfc-chicken-recipe.jpg', 'Kobe', 322, 1, 2, 1),
(6, 'https://www.indianhealthyrecipes.com/wp-content/uploads/2014/11/kfc-chicken-recipe.jpg', 'gà jolibee', 123, 1, 1, 1),
(7, 'https://www.indianhealthyrecipes.com/wp-content/uploads/2014/11/kfc-chicken-recipe.jpg', 'Gà kfc', 116, 1, 1, 1),
(9, 'https://www.indianhealthyrecipes.com/wp-content/uploads/2014/11/kfc-chicken-recipe.jpg', 'Burger bò', 20, 1, 2, 1),
(10, 'https://www.indianhealthyrecipes.com/wp-content/uploads/2014/11/kfc-chicken-recipe.jpg', 'Burger cua', 20, 1, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `food_order`
--

CREATE TABLE `food_order` (
  `food_id` int(11) NOT NULL,
  `order_id` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` double NOT NULL,
  `restaurant_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `res_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`res_id`, `address`, `logo_url`, `name`) VALUES
(1, '/images/logo/logo1.png', 'Gà okok', '15/7 Cộng Hòa'),
(2, '/images/logo/logo1.png', 'LOtte', '54 Cầu giấy'),
(3, '/images/logo/logo1.png', 'Ltp Food', '12 Quang Trung'),
(4, '/images/logo/logo4.jpg', 'Hello Chicken', '111 Hoa Hồng'),
(5, '/images/logo/logo4.jpg', 'Gà 1', '111 HTT'),
(6, '/images/logo/logo4.jpg', 'Gà 2', '29 CMT8'),
(7, '/images/logo/logo4.jpg', ' Gà 3', '5 Quang Trung');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_seq`
--

CREATE TABLE `restaurant_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `restaurant_seq`
--

INSERT INTO `restaurant_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `verification_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cate_id`),
  ADD KEY `FK7ytcb37fwgr7d9w7gb3mmmhcb` (`restaurant`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`food_id`),
  ADD KEY `FKkomdx99dhk2cveaxugl2lws2u` (`category_id`),
  ADD KEY `FK9qgsdxqg9h0ab7ugehlceco58` (`res_id`);

--
-- Indexes for table `food_order`
--
ALTER TABLE `food_order`
  ADD PRIMARY KEY (`food_id`,`order_id`),
  ADD KEY `FKmqe79vw8rsaiabqygkyhea9nl` (`order_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FKi7hgjxhw21nei3xgpe4nnpenh` (`restaurant_id`),
  ADD KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`res_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `cate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `food_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK7ytcb37fwgr7d9w7gb3mmmhcb` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`res_id`);

--
-- Constraints for table `food`
--
ALTER TABLE `food`
  ADD CONSTRAINT `FK9qgsdxqg9h0ab7ugehlceco58` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`res_id`),
  ADD CONSTRAINT `FKkomdx99dhk2cveaxugl2lws2u` FOREIGN KEY (`category_id`) REFERENCES `category` (`cate_id`);

--
-- Constraints for table `food_order`
--
ALTER TABLE `food_order`
  ADD CONSTRAINT `FKmqe79vw8rsaiabqygkyhea9nl` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `FKq0bn99dwrnw8vlwcvvaj1dcma` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKi7hgjxhw21nei3xgpe4nnpenh` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`res_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
