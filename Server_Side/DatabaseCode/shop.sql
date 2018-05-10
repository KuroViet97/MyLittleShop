SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `product` (
  `Barcode` int(11) NOT NULL,
  `Productname` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `product` (`Barcode`, `Productname`) VALUES
(1, 'anime'),
(2, 'manga'),
(3, 'movie'),
(12, 'tv');

CREATE TABLE `shop` (
  `ShopID` int(11) NOT NULL,
  `ShopName` tinytext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `shop` (`ShopID`, `ShopName`) VALUES
(1, 'shop 1'),
(2, 'shop 2'),
(3, 'shop 3'),
(4, 'Shop4');

CREATE TABLE `transaction` (
  `ID` int(11) NOT NULL,
  `ShopID` int(11) NOT NULL,
  `Barcode` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT '2018-09-04 17:00:00',
  `STATUS` text COLLATE utf8_unicode_ci NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `transaction` (`ID`, `ShopID`, `Barcode`, `date`, `STATUS`, `Quantity`) VALUES
(1, 2, 3, '2008-11-09 08:45:21', 'IN', 10000),
(3, 2, 2, '2008-11-11 04:12:01', 'IN', 10000),
(4, 2, 1, '2008-11-11 04:12:01', 'IN', 200000),
(5, 1, 1, '2008-11-11 04:12:01', 'IN', 40000),
(6, 1, 2, '2008-11-11 04:12:01', 'IN', 30000),
(7, 1, 3, '2008-11-25 04:12:01', 'IN', 30000),
(8, 3, 1, '2008-10-29 07:56:59', 'IN', 4500),
(9, 3, 2, '2008-10-29 07:56:59', 'IN', 3700),
(10, 3, 3, '2008-10-29 07:56:59', 'IN', 4500),
(11, 1, 3, '2018-05-08 17:30:18', 'OUT', 1000),
(12, 3, 2, '2018-05-08 18:00:34', 'OUT', 100),
(13, 1, 1, '2018-05-08 18:15:59', 'OUT', 40),
(14, 1, 2, '2018-05-08 18:18:35', 'OUT', 42),
(15, 1, 1, '2018-05-08 18:18:35', 'OUT', 70),
(16, 3, 1, '2018-05-08 19:40:37', 'OUT', 7),
(17, 1, 1, '2018-05-09 07:51:00', 'IN', 15),
(18, 3, 2, '2018-05-09 09:26:01', 'IN', 30),
(19, 2, 2, '2018-05-09 14:04:27', 'IN', 43),
(20, 2, 2, '2018-05-09 14:49:18', 'IN', 7);


ALTER TABLE `product`
  ADD PRIMARY KEY (`Barcode`);

ALTER TABLE `shop`
  ADD PRIMARY KEY (`ShopID`);

ALTER TABLE `transaction`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ShopID` (`ShopID`),
  ADD KEY `Barcode` (`Barcode`);


ALTER TABLE `shop`
  MODIFY `ShopID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `transaction`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;


ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`ShopID`) REFERENCES `shop` (`ShopID`),
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`Barcode`) REFERENCES `product` (`Barcode`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
