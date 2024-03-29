DROP DATABASE IF EXISTS MOVIE_DATABASE;
CREATE DATABASE MOVIE_DATABASE; 
USE MOVIE_DATABASE;

DROP TABLE IF EXISTS MOVIE_INFORMATION;
CREATE TABLE MOVIE_INFORMATION (
	MovieID			int not null AUTO_INCREMENT,
	MovieTheatre	varchar(100),
    MovieName		varchar(200),
    MovieTime		datetime,
    
	primary key (MovieID)
);

INSERT INTO MOVIE_INFORMATION (MovieTheatre, MovieName, MovieTime)
VALUES
('LandMark Cinemas', 'AVATAR' , '2022-12-25 12:00:00'),
('LandMark Cinemas', 'AVATAR' , '2022-12-25 18:00:00'),
('LandMark Cinemas', 'AVATAR' , '2022-12-26 12:00:00'),
('LandMark Cinemas', 'AVATAR' , '2022-12-26 18:00:00'),
('LandMark Cinemas', 'Black Adam' , '2022-12-30 12:00:00'),
('LandMark Cinemas', 'Black Adam' , '2022-12-30 18:00:00'),
('LandMark Cinemas', 'Black Adam' , '2022-12-31 12:00:00'),
('LandMark Cinemas', 'Black Adam' , '2022-12-31 18:00:00'),
('LandMark Cinemas', 'Schulich Ohms' , '2025-12-30 12:00:00'),
('LandMark Cinemas', 'Schulich Ohms' , '2025-12-30 15:00:00'),
('LandMark Cinemas', 'Schulich Ohms' , '2025-12-30 18:00:00'),
('LandMark Cinemas', 'Schulich Ohms' , '2025-12-31 12:00:00'),
('LandMark Cinemas', 'Schulich Ohms' , '2025-12-31 15:00:00'),
('LandMark Cinemas', 'Schulich Ohms' , '2025-12-31 18:00:00'),
('LandMark Cinemas', 'Black Punther' , '2022-12-29 12:00:00'),
('LandMark Cinemas', 'Black Punther' , '2022-12-29 18:00:00'),
('LandMark Cinemas', 'Black Punther' , '2022-12-30 12:00:00'),
('LandMark Cinemas', 'Black Punther' , '2022-12-30 18:00:00'),

('Scotiabank Theatres', 'AVATAR' , '2022-12-16 12:00:00'),
('Scotiabank Theatres', 'AVATAR' , '2022-12-16 18:00:00'),
('Scotiabank Theatres', 'AVATAR' , '2022-12-17 12:00:00'),
('Scotiabank Theatres', 'AVATAR' , '2022-12-17 18:00:00'),
('Scotiabank Theatres', 'Black Adam' , '2022-12-24 12:00:00'),
('Scotiabank Theatres', 'Black Adam' , '2022-12-24 18:00:00'),
('Scotiabank Theatres', 'Black Adam' , '2022-12-25 12:00:00'),
('Scotiabank Theatres', 'Black Adam' , '2022-12-25 18:00:00'),
('Scotiabank Theatres', 'Black Punther' , '2022-12-28 12:00:00'),
('Scotiabank Theatres', 'Black Punther' , '2022-12-28 18:00:00'),
('Scotiabank Theatres', 'Black Punther' , '2022-12-29 12:00:00'),
('Scotiabank Theatres', 'Black Punther' , '2022-12-29 18:00:00'),
-- invalid showtime testing:
('Scotiabank Theatres', 'Ratatouille' , '2021-10-10 12:00:00'),
('Scotiabank Theatres', 'Ratatouille' , '2022-12-22 18:00:00'),
('Scotiabank Theatres', '1984' , '1984-01-06 18:00:00'),
('Scotiabank Theatres', '1984' , '1984-01-06 18:00:00');
-- ratatouille is going to have one showtime before its announcement date
-- but one valid showtime. the invalid showtime should be removed.
-- 1984 will have showtimes from the past, which should all be removed
-- since 1984 wont have any valid showtimes, the movie should not appear
-- nvm it can appear just no showtimes

DROP TABLE IF EXISTS LoginServer;
CREATE TABLE LoginServer (
	Username	    varchar(100) NOT NULL,
    Pass		    varchar(100) NOT NULL,
    FullName        varchar(200) NOT NULL,
    Email           varchar(200) NOT NULL,
    HomeAddress     varchar(200) NOT NULL,
    CardNumber      varchar(200) NOT NULL,
    CVV             int,
    AnnualFee       datetime,
--     LoginName		varchar(200),
    
    primary key	(Username)
);
INSERT INTO LoginServer (Username, Pass, FullName, Email, HomeAddress, CardNumber, CVV , AnnualFee)
VALUES
('test', 'pw', 'random scrandom', 'fbi@cia.gov', '12 sesame st', '1111222233334444', 888, "2022-11-30 12:00:00");

DROP TABLE IF EXISTS Admins;
CREATE TABLE Admins (
	AdminUsername	varchar(100) NOT NULL,
    AdminPass		varchar(100) NOT NULL,
    primary key	(AdminUsername)
);
-- admin is the head admin and cannot be deleted
INSERT INTO Admins (AdminUsername, AdminPass)
VALUES
('admin', 'pass');

DROP TABLE IF EXISTS MovieReleaseDate;
CREATE TABLE MovieReleaseDate (
    MovieName		varchar(200),
    ReleaseDate		datetime,
    
    primary key	(MovieName)
);

INSERT INTO MovieReleaseDate (MovieName, ReleaseDate)
VALUES
('AVATAR' , '2022-11-30 12:00:00'),
('Black Adam' , '2022-11-30 12:00:00'),
('Schulich Ohms' , '2025-01-01 12:00:00'),
('Black Punther' , '2022-11-30 12:00:00'),
('Ratatouille' , '2022-11-30 12:00:00'),
('1984' , '2022-11-30 12:00:00');

DROP TABLE IF EXISTS MovieTickets;
CREATE TABLE MovieTickets (
    TicketID        int not null AUTO_INCREMENT,
    MovieTheatre	varchar(100),
    MovieName		varchar(200),
    MovieTime		datetime,
    Seat            varchar(10),
    FullName        varchar(100),
    ConfirmationNumber  int,

    primary key	(TicketID)
);

INSERT INTO MovieTickets (MovieTheatre, MovieName, MovieTime, Seat, FullName)
VALUES
('LandMark Cinemas', 'AVATAR' , '2022-12-25 12:00:00', 'A7', 'Larry David'),
('LandMark Cinemas', 'AVATAR' , '2022-12-25 12:00:00', 'B3', 'Britney Spears');

DROP TABLE IF EXISTS MovieCredit;
CREATE TABLE MovieCredit (
    CreditID    int,
    Amount      float,
    

    primary key	(CreditID)
);


