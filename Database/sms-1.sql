-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 29, 2017 at 03:57 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sms`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminlogin`
--

CREATE TABLE `adminlogin` (
  `id` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `contact_no` int(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `date_of_joining` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adminlogin`
--

INSERT INTO `adminlogin` (`id`, `password`, `name`, `contact_no`, `email`, `address`, `date_of_joining`) VALUES
('1234', '1234', '', 0, '', '', '0'),
('1122', '1122', 'abcd', 17, 'sa@', 'adjsd,.', '27/10/2017');

-- --------------------------------------------------------

--
-- Table structure for table `dail`
--

CREATE TABLE `dail` (
  `selldate` varchar(12) NOT NULL,
  `totalsell` int(15) NOT NULL,
  `totalvat` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dail`
--

INSERT INTO `dail` (`selldate`, `totalsell`, `totalvat`) VALUES
('27/10/2017', 300, 45),
('29/10/2017', 120, 18),
('29/10/2017', 510, 77);

-- --------------------------------------------------------

--
-- Table structure for table `productdetails`
--

CREATE TABLE `productdetails` (
  `productID` int(100) NOT NULL,
  `productName` varchar(100) NOT NULL,
  `unitPrice` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productdetails`
--

INSERT INTO `productdetails` (`productID`, `productName`, `unitPrice`, `quantity`) VALUES
(1001, 'Rice', '60.0', 40),
(1002, 'Soyabean Oil', '105.0', 48),
(1003, 'ACI Pure Salt', '40.0', 17),
(1004, 'Moshur Dal', '120.0', 40),
(1005, 'Red Onion (Indian)', '65.0', 40),
(1006, 'Potato (Regular)', '22.0', 20),
(1007, 'Garlic (Big)', '25.0', 20),
(1008, 'Green Chili', '150.0', 10);

-- --------------------------------------------------------

--
-- Table structure for table `userlogin`
--

CREATE TABLE `userlogin` (
  `id` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `contract_no` int(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `date_of_joining` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userlogin`
--

INSERT INTO `userlogin` (`id`, `password`, `name`, `contract_no`, `email`, `address`, `date_of_joining`) VALUES
('123456', '123456', 'abcdg', 1914, 'asadg@', 'acdf,.', '27/10/2017');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
