DROP TABLE IF EXISTS user;
CREATE TABLE user(
  id int PRIMARY KEY ,
  username VARCHAR(40),
  name VARCHAR(20),
  age INT(3),
  balance DECIMAL(10, 2)
);