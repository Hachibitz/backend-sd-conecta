CREATE TABLE Doctor (
  id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  email varchar(45) UNIQUE,
  password varchar(45),
  name varchar(45),
  surname varchar(45),
  mobile_phone varchar(45),
  CRM varchar(45),
  authorization_status varchar(45)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
