USE TESTDB;

Drop Table AccountInfo;
CREATE TABLE AccountInfo (
    userName VARCHAR(50) PRIMARY KEY,
    passWord VARCHAR(max) NOT NULL,
    email VARCHAR(50) NOT NULL,
	phoneNo VARCHAR(50) NOT NULL,
    name VARCHAR(100),
    age VARCHAR(20),
	addressLine1 VARCHAR(100),
    addressLine2 VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    zipCode VARCHAR(20),
    country VARCHAR(50),
	created_date datetime NOT NULL
);