
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
('LandMark Cinemas', 'AVATAR' , '2022-12-30 12:00:00'),
('LandMark Cinemas', 'AVATAR' , '2022-12-30 18:00:00'),
('LandMark Cinemas', 'AVATAR' , '2022-12-31 12:00:00'),
('LandMark Cinemas', 'AVATAR' , '2022-12-31 18:00:00'),
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
('LandMark Cinemas', 'Black Punther' , '2022-12-30 12:00:00'),
('LandMark Cinemas', 'Black Punther' , '2022-12-30 18:00:00'),
('LandMark Cinemas', 'Black Punther' , '2022-12-31 12:00:00'),
('LandMark Cinemas', 'Black Punther' , '2022-12-31 18:00:00'),

('Scotiabank Theatres', 'AVATAR' , '2022-12-28 12:00:00'),
('Scotiabank Theatres', 'AVATAR' , '2022-12-28 18:00:00'),
('Scotiabank Theatres', 'AVATAR' , '2022-12-29 12:00:00'),
('Scotiabank Theatres', 'AVATAR' , '2022-12-29 18:00:00'),
('Scotiabank Theatres', 'Black Adam' , '2022-12-28 12:00:00'),
('Scotiabank Theatres', 'Black Adam' , '2022-12-28 18:00:00'),
('Scotiabank Theatres', 'Black Adam' , '2022-12-29 12:00:00'),
('Scotiabank Theatres', 'Black Adam' , '2022-12-29 18:00:00'),
('Scotiabank Theatres', 'Black Punther' , '2022-12-28 12:00:00'),
('Scotiabank Theatres', 'Black Punther' , '2022-12-28 18:00:00'),
('Scotiabank Theatres', 'Black Punther' , '2022-12-29 12:00:00'),
('Scotiabank Theatres', 'Black Punther' , '2022-12-29 18:00:00');


DROP TABLE IF EXISTS LoginServer;
CREATE TABLE LoginServer (
	Username	varchar(100) NOT NULL,
    Pass		varchar(100) NOT NULL,
--     LoginName		varchar(200),
    
    primary key	(Username)
);

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
('Schulich Ohms' , '2025-1-1 12:00:00'),
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

    primary key	(TicketID)
);

INSERT INTO MovieTickets (MovieTheatre, MovieName, MovieTime, Seat, FullName)
VALUES
('LandMark Cinemas', 'AVATAR' , '2022-12-30 12:00:00', 'A7', 'Larry David'),
('LandMark Cinemas', 'AVATAR' , '2022-12-30 12:00:00', 'B3', 'Britney Spears');



