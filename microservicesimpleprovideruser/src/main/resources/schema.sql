DROP TABLE IF EXISTS user;
CREATE TABLE user(
  id VARCHAR(20) PRIMARY KEY ,
  username VARCHAR(40),
  name VARCHAR(20),
  age INT(3),
  balance DECIMAL(10, 2)
);