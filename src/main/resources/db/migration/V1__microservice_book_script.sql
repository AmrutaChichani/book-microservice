CREATE TABLE IF NOT EXISTS `author`(
  `author_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `publisher` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `contact_no` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `active_flag` tinyint(1) DEFAULT '1',
  `category` varchar(255) DEFAULT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `publish_date` date DEFAULT NULL,
  `publisher_id` int DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `FK_book_publisher` (`publisher_id`),
  CONSTRAINT `FK_book_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
