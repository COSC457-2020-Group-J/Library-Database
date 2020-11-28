SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO BOOK(Book_name, Author_number, Pub_number, Date_published)
VALUES('My First Book', 1, 1, '2020-01-01');
INSERT INTO BOOK(Book_name, Author_number, Pub_number, Date_published)
VALUES('The Second Book', 3, 2, '2020-02-15');
INSERT INTO BOOK(Book_name, Author_number, Pub_number, Date_published)
VALUES('Book the Third', 4, 3, '2020-03-03');
INSERT INTO BOOK(Book_name, Author_number, Pub_number, Date_published)
VALUES('4th Book', 7, 4, '2020-11-01');
INSERT INTO BOOK(Book_name, Author_number, Pub_number, Date_published)
VALUES('Book 5', 9, 5, '2020-09-23');

INSERT INTO PUBLISHER(Pub_name, City, Country, Telephone)
VALUES('Book House', 'Baltimore', 'United States', 4105551212);
INSERT INTO PUBLISHER(Pub_name, City, Country, Telephone)
VALUES('Towson Books', 'Towson', 'United States', 4105551234);
INSERT INTO PUBLISHER(Pub_name, City, Country, Telephone)
VALUES('Publishers', 'Annapolis', 'United States', 4105559876);
INSERT INTO PUBLISHER(Pub_name, City, Country, Telephone)
VALUES('Bookkeepers', 'Towson', 'United States', 4105554273);
INSERT INTO PUBLISHER(Pub_name, City, Country, Telephone)
VALUES('Books and Stuff', 'Baltimore', 'United States', 4105556237);

INSERT INTO AUTHOR(Person_number)
VALUES(1);
INSERT INTO AUTHOR(Person_number)
VALUES(3);
INSERT INTO AUTHOR(Person_number)
VALUES(5);
INSERT INTO AUTHOR(Person_number)
VALUES(7);
INSERT INTO AUTHOR(Person_number)
VALUES(9);

INSERT INTO BORROWER(Person_number, Book_number, Date_borrowed, Date_due)
VALUES(2, 1, '2020-2-15', '2020-3-15');
INSERT INTO BORROWER(Person_number, Book_number, Date_borrowed, Date_due)
VALUES(4, 5, '2020-3-06', '2020-4-06');
INSERT INTO BORROWER(Person_number, Book_number, Date_borrowed, Date_due)
VALUES(6, 3, '2020-5-01', '2020-6-01');
INSERT INTO BORROWER(Person_number, Book_number, Date_borrowed, Date_due)
VALUES(8, 4, '2020-6-09', '2020-7-09');
INSERT INTO BORROWER(Person_number, Book_number, Date_borrowed, Date_due)
VALUES(10, 2, '2020-8-17', '2020-9-17');

INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('John', 'Smith', 'Towson', 'United States');
INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('Dan', 'Daniels', 'Baltimore', 'United States');
INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('Mike', 'Michaels', 'Towson', 'United States');
INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('Mark', 'Smith', 'Towson', 'United States');
INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('John', 'Cena', 'Towson', 'United States');
INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('Mark', 'Robert', 'Baltimore', 'United States');
INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('Luke', 'Skywalker', 'Towson', 'United States');
INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('Mark', 'Twain', 'Towson', 'United States');
INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('Welven', 'Great', 'Towson', 'United States');
INSERT INTO PERSON(First_name, Last_name, City, Country)
VALUES('Bobby', 'Shmurda', 'Towson', 'United States');

INSERT INTO BRANCH(City, Country, Telephone)
VALUES('Ocean City', 'United States', 4435551212);
INSERT INTO BRANCH(City, Country, Telephone)
VALUES('Towson', 'United States', 4435554321);
INSERT INTO BRANCH(City, Country, Telephone)
VALUES('Baltimore', 'United States', 4435551928);

INSERT INTO LIBRARIAN(Person_number, Branch_number, Wage)
VALUES(1, 1, 15);
INSERT INTO LIBRARIAN(Person_number, Branch_number, Wage)
VALUES(3, 2, 20);
INSERT INTO LIBRARIAN(Person_number, Branch_number, Wage)
VALUES(5, 3, 25);

INSERT INTO JOURNAL(Author_number, News_number, Journal_name)
VALUES(1, 1, 'My First Journal');
INSERT INTO JOURNAL(Author_number, News_number, Journal_name)
VALUES(7, 2, 'Journalism');

INSERT INTO ARTICLE(Author_number, News_number, Article_name)
VALUES(1, 1, 'My First Article');
INSERT INTO ARTICLE(Author_number, News_number, Article_name)
VALUES(8, 3, 'New Books Available');

INSERT INTO NEWSLETTER(Date_published)
VALUES('2020-4-20');
INSERT INTO NEWSLETTER(Date_published)
VALUES('2020-5-20');
INSERT INTO NEWSLETTER(Date_published)
VALUES('2020-6-20');
INSERT INTO NEWSLETTER(Date_published)
VALUES('2020-7-20');
INSERT INTO NEWSLETTER(Date_published)
VALUES('2020-8-20');

INSERT INTO USER(Username, Password)
VALUES('admin', 'password');

SET FOREIGN_KEY_CHECKS = 1;