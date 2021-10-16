CREATE TABLE users
(
  id 			UUID 		NOT NULL,
  user_name 	VARCHAR(25) NOT NULL,
  first_name 	VARCHAR(25) NOT NULL,
  last_name 	VARCHAR(25) NOT NULL,
  gender 		VARCHAR(10) NOT NULL,
  birthday 		DATE 		NOT NULL,
  email 		VARCHAR(50) NOT NULL,
  phone_number 	VARCHAR(9) 	NOT NULL,
  PRIMARY KEY (id)
);