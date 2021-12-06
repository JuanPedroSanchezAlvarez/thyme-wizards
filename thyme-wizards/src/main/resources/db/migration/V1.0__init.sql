CREATE TABLE users
(
  id 			UUID 		NOT NULL,
  version      	BIGINT  	NOT NULL,
  user_name 	VARCHAR(25) NOT NULL,
  first_name 	VARCHAR(25) NOT NULL,
  last_name 	VARCHAR(25) NOT NULL,
  gender 		VARCHAR(10) NOT NULL,
  birthday 		DATE 		NOT NULL,
  email 		VARCHAR(50) NOT NULL,
  phone_number 	VARCHAR(9) 	NOT NULL,
  password 		VARCHAR 	NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT UK_user_email UNIQUE (email);

CREATE TABLE user_roles
(
  user_id 		UUID 		NOT NULL,
  role 			VARCHAR 	NOT NULL
);

ALTER TABLE user_roles ADD CONSTRAINT FK_user_roles_to_user FOREIGN KEY (user_id) REFERENCES users;
