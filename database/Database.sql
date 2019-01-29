DROP TABLE GameUser;

CREATE TABLE GameUser(
	username VARCHAR(30) NOT NULL,
	password VARCHAR(16) NOT NULL,
	score INT DEFAULT 0
);

ALTER TABLE GameUser 
	ADD CONSTRAINT GameUser_Usernaname_Pk PRIMARY KEY (username);