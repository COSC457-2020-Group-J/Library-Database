SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS BOOK (
Book_number int NOT NULL AUTO_INCREMENT,
Book_name VARCHAR(255) NOT NULL,
Author_number int NOT NULL,
Pub_number int NOT NULL,
Date_published DATE NOT NULL,
PRIMARY KEY (Book_number),
FOREIGN KEY (Author_number) REFERENCES AUTHOR (Author_number),
FOREIGN KEY (Pub_number) REFERENCES PUBLISHER (Pub_number)
);

CREATE TABLE IF NOT EXISTS PUBLISHER (
Pub_number int NOT NULL AUTO_INCREMENT,
Pub_name VARCHAR(255) NOT NULL,
City VARCHAR(255) NOT NULL,
Country VARCHAR(255) NOT NULL,
Telephone VARCHAR(10) NOT NULL,
PRIMARY KEY (Pub_number)
);

CREATE TABLE IF NOT EXISTS AUTHOR (
Author_number int NOT NULL AUTO_INCREMENT,
Person_number int NOT NULL,
PRIMARY KEY (Author_number),
FOREIGN KEY (Person_number) REFERENCES PERSON (Person_number)
);

CREATE TABLE IF NOT EXISTS BORROWER (
Borrow_number int NOT NULL AUTO_INCREMENT,
Person_number int NOT NULL,
Book_number int NOT NULL,
Date_borrowed DATE NOT NULL,
Date_due DATE NOT NULL,
PRIMARY KEY (Borrow_number),
FOREIGN KEY (Person_number) REFERENCES PERSON (Person_number),
FOREIGN KEY (Book_number) REFERENCES BOOK (Book_number)
);

CREATE TABLE IF NOT EXISTS PERSON (
Person_number int NOT NULL AUTO_INCREMENT,
First_name VARCHAR(255) NOT NULL,
Last_name VARCHAR(255) NOT NULL,
City VARCHAR(255) NOT NULL,
Country VARCHAR(255) NOT NULL,
PRIMARY KEY (Person_number)
);

CREATE TABLE IF NOT EXISTS BRANCH (
Branch_number int NOT NULL AUTO_INCREMENT,
City VARCHAR(255) NOT NULL,
Country VARCHAR(255) NOT NULL,
Telephone VARCHAR(10) NOT NULL,
PRIMARY KEY (Branch_number)
);

CREATE TABLE IF NOT EXISTS LIBRARIAN (
Lib_number int NOT NULL AUTO_INCREMENT,
Person_number int NOT NULL,
Branch_number int NOT NULL,
Wage int NOT NULL,
PRIMARY KEY (Lib_number),
FOREIGN KEY (Person_number) REFERENCES PERSON (Person_number),
FOREIGN KEY (Branch_number) REFERENCES BRANCH (Branch_number)
);

CREATE TABLE IF NOT EXISTS JOURNAL (
Journal_number int NOT NULL AUTO_INCREMENT,
Author_number int NOT NULL,
News_number int NOT NULL,
Journal_name VARCHAR(255) NOT NULL,
PRIMARY KEY (Journal_number),
FOREIGN KEY (Author_number) REFERENCES AUTHOR (Author_number),
FOREIGN KEY (News_number) REFERENCES NEWSLETTER (News_number)
);

CREATE TABLE IF NOT EXISTS ARTICLE (
Article_number int NOT NULL AUTO_INCREMENT,
Author_number int NOT NULL,
News_number int NOT NULL,
Article_name VARCHAR(255) NOT NULL,
PRIMARY KEY (Article_number),
FOREIGN KEY (Author_number) REFERENCES AUTHOR (Author_number),
FOREIGN KEY (News_number) REFERENCES NEWSLETTER (News_number)
);

CREATE TABLE IF NOT EXISTS NEWSLETTER (
News_number int NOT NULL AUTO_INCREMENT,
Date_published DATE NOT NULL,
PRIMARY KEY (News_number)
);

CREATE TABLE IF NOT EXISTS USER (
Username VARCHAR(15) NOT NULL,
Password VARCHAR(60) NOT NULL,
PRIMARY KEY (Username)
);

SET FOREIGN_KEY_CHECKS = 1;