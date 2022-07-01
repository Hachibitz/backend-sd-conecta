CREATE TABLE `CRM` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  `crm` varchar(45) NOT NULL,
  `uf` varchar(45),
  `specialty` varchar(45),
  `userId` int(10),
  FOREIGN KEY (userId) REFERENCES Doctor(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;