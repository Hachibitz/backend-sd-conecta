CREATE TABLE Doctor (
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  email varchar(45) UNIQUE,
  `password` varchar(45),
  `name` varchar(45),
  surname varchar(45),
  mobile_phone varchar(45),
  CRM varchar(45),
  authorization_status varchar(45),
  FOREIGN KEY (CRM) REFERENCES CRM(id)
);
