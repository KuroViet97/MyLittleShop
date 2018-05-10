-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 10, 2018 lúc 04:11 PM
-- Phiên bản máy phục vụ: 10.1.31-MariaDB
-- Phiên bản PHP: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shop`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` tinytext COLLATE utf8_unicode_ci NOT NULL,
  `pass` tinytext COLLATE utf8_unicode_ci NOT NULL,
  `token` tinytext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `login`
--

INSERT INTO `login` (`id`, `username`, `pass`, `token`) VALUES
(1, 'employee', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', '5B2F8E27E2E5B4081C03CE70B288C87BD1263140CBD1BD9AE078123509B7CAFF'),
(2, 'manager', 'B3A8E0E1F9AB1BFE3A36F231F676F78BB30A519D2B21E6C530C0EEE8EBB4A5D0', '4682AC63F25BF51E92FC3B6E3033D29C4F6CCEE77AE5A6543E4AFF9698AA6B49');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `Barcode` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `Productname` text COLLATE utf8_unicode_ci NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`Barcode`, `Productname`, `price`) VALUES
('1', 'anime', 20),
('12', 'tv', 2000),
('1337a', 'gun', 50),
('1357a', 'spear', 40),
('137a', 'sex toy', 80),
('143', 'weed', 2),
('14t', 'katana', 5),
('17a', 'eye', 130),
('2', 'manga', 10),
('3', 'movie', 12),
('ASK-36', 'vn', 45),
('DEPZAI', 'drill', 123);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shop`
--

CREATE TABLE `shop` (
  `ShopID` int(11) NOT NULL,
  `ShopName` tinytext COLLATE utf8_unicode_ci NOT NULL,
  `employee` text COLLATE utf8_unicode_ci NOT NULL,
  `pass` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `shop`
--

INSERT INTO `shop` (`ShopID`, `ShopName`, `employee`, `pass`) VALUES
(1, 'shop 1', 'employee 1', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3'),
(2, 'shop 2', 'employee 2', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3'),
(3, 'shop 3', 'employee 3', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3'),
(4, 'Shop4', 'employee 4', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3'),
(5, 'Shop 5', 'employee 5', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3'),
(6, 'Shop 6', 'quan', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `transaction`
--

CREATE TABLE `transaction` (
  `ID` int(11) NOT NULL,
  `ShopID` int(11) NOT NULL,
  `Barcode` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT '2018-09-04 17:00:00',
  `STATUS` text COLLATE utf8_unicode_ci NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `transaction`
--

INSERT INTO `transaction` (`ID`, `ShopID`, `Barcode`, `date`, `STATUS`, `Quantity`) VALUES
(1, 2, '3', '2008-11-09 08:45:21', 'IN', 10000),
(3, 2, '2', '2008-11-11 04:12:01', 'IN', 10000),
(4, 2, '1', '2008-11-11 04:12:01', 'IN', 200000),
(5, 1, '1', '2008-11-11 04:12:01', 'IN', 40000),
(6, 1, '2', '2008-11-11 04:12:01', 'IN', 30000),
(7, 1, '3', '2008-11-25 04:12:01', 'IN', 30000),
(8, 3, '1', '2008-10-29 07:56:59', 'IN', 4500),
(9, 3, '2', '2008-10-29 07:56:59', 'IN', 3700),
(10, 3, '3', '2008-10-29 07:56:59', 'IN', 4500),
(11, 1, '3', '2018-05-08 17:30:18', 'OUT', 1000),
(12, 3, '2', '2018-05-08 18:00:34', 'OUT', 100),
(13, 1, '1', '2018-05-08 18:15:59', 'OUT', 40),
(14, 1, '2', '2018-05-08 18:18:35', 'OUT', 42),
(15, 1, '1', '2018-05-08 18:18:35', 'OUT', 70),
(16, 3, '1', '2018-05-08 19:40:37', 'OUT', 7),
(17, 1, '1', '2018-05-09 07:51:00', 'IN', 15),
(18, 3, '2', '2018-05-09 09:26:01', 'IN', 30),
(19, 2, '2', '2018-05-09 14:04:27', 'IN', 43),
(20, 2, '2', '2018-05-09 14:49:18', 'IN', 7),
(21, 3, '2', '2018-05-10 02:40:13', 'OUT', 20),
(22, 1, '1', '2018-05-10 02:50:35', 'IN', 7),
(23, 2, '2', '2018-05-10 02:51:02', 'IN', 50),
(24, 1, '2', '2018-05-10 02:51:34', 'IN', 2),
(25, 1, '2', '2018-05-10 03:02:23', 'IN', 40),
(26, 1, '1', '2018-05-10 03:07:38', 'IN', 8),
(27, 1, '2', '2018-05-10 04:14:11', 'OUT', 30),
(28, 1, '2', '2018-05-10 07:59:26', 'OUT', 13),
(29, 2, '12', '2018-05-10 08:00:18', 'IN', 2),
(30, 3, '2', '2018-05-10 08:07:31', 'OUT', 50),
(31, 1, '143', '2018-05-10 08:33:34', 'IN', 3000),
(32, 2, '1', '2018-05-10 09:00:42', 'IN', 12),
(33, 2, '12', '2018-05-10 09:01:25', 'IN', 18),
(34, 1, '17a', '2018-05-10 12:18:07', 'IN', 15),
(35, 1, 'ASK-36', '2018-05-10 12:20:00', 'IN', 144),
(36, 1, '17a', '2018-05-10 12:29:30', 'IN', 35),
(37, 1, '12', '2018-05-10 12:29:39', 'IN', 4),
(38, 1, '143', '2018-05-10 12:29:48', 'IN', 1),
(39, 1, '1337a', '2018-05-10 12:29:59', 'IN', 145),
(40, 1, '1357a', '2018-05-10 12:30:22', 'IN', 32),
(41, 1, '137a', '2018-05-10 12:30:56', 'IN', 14153),
(42, 1, '1', '2018-05-10 12:32:22', 'IN', 179),
(43, 1, 'ASK-36', '2018-05-10 13:29:33', 'IN', 15),
(44, 1, 'ASK-36', '2018-05-10 13:33:51', 'OUT', 12),
(45, 2, '2', '2018-05-10 13:35:06', 'OUT', 7),
(46, 6, '137a', '2018-05-10 13:38:17', 'IN', 150),
(47, 1, 'DEPZAI', '2018-05-10 13:39:07', 'IN', 7),
(48, 1, '2', '2018-05-10 14:10:14', 'OUT', 13);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`Barcode`);

--
-- Chỉ mục cho bảng `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`ShopID`);

--
-- Chỉ mục cho bảng `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ShopID` (`ShopID`),
  ADD KEY `Barcode` (`Barcode`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `shop`
--
ALTER TABLE `shop`
  MODIFY `ShopID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `transaction`
--
ALTER TABLE `transaction`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`ShopID`) REFERENCES `shop` (`ShopID`),
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`Barcode`) REFERENCES `product` (`Barcode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
