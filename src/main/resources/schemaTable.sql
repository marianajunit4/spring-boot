CREATE TABLE account(id INT PRIMARY KEY, balance DECIMAL NOT NULL);
CREATE TABLE user(id INT PRIMARY KEY, name VARCHAR(50) NOT NULL);
CREATE TABLE person(id INT PRIMARY KEY, name VARCHAR(50) NOT NULL, age INT);