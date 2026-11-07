CREATE DATABASE  IF NOT EXISTS `ticketbox_db`;
USE `ticketbox_db`;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(128) DEFAULT NULL,
  `organizer_name` varchar(128) DEFAULT NULL,
  `ticket_price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
