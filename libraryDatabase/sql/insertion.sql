SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO BOOK(Book_name, Author_number, Pub_number, Date_published)
VALUES('My First Book', 1, 1, '2020-01-01');

INSERT INTO PUBLISHER(Pub_name, City, Country, Telephone)
VALUES('Book House', 'Baltimore', 'United States', 4105551212);

INSERT INTO AUTHOR(Person_number)
VALUES(1);

INSERT INTO BORROWER(Person_number, Book_number, Date_borrowed, Date_due)
VALUES(1, 1, '2020-2-15', '2020-3-15');

INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('John', 'Smith', 'Towson', 'United States');

INSERT INTO BRANCH(City, Country, Telephone)
VALUES('Ocean City', 'United States', 4435551212);

INSERT INTO LIBRARIAN(Person_number, Branch_number, Wage)
VALUES(1, 1, 15);

INSERT INTO JOURNAL(Author_number, News_number, Journal_name)
VALUES(1, 1, 'My First Journal');

INSERT INTO ARTICLE(Author_number, News_number, Article_name)
VALUES(1, 1, 'My First Article');

INSERT INTO NEWSLETTER(Date_published)
VALUES('2020-4-20');

INSERT INTO USER(Username, Password)
VALUES('admin', 'password');

SET FOREIGN_KEY_CHECKS = 1;