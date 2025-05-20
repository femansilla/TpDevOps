CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product` varchar(100) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` decimal DEFAULT NULL,
  `updatedAt` date DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
);

INSERT INTO orders (product, quantity, price)  VALUES('kaySlow', 10, 10.50);
INSERT INTO orders (product, quantity, price)  VALUES('ghostNinja', 20, 20.50);
INSERT INTO orders (product, quantity, price)  VALUES('paul_pop', 30, 30.50);
