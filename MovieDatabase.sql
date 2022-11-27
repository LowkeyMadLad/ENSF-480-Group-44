
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
('LandMark Cinemas', 'AVATAR' , '	2022-12-01 12:00:00'),
('LandMark Cinemas', 'AVATAR' , '	2022-12-01 18:00:00'),
('LandMark Cinemas', 'AVATAR' , '	2022-12-02 12:00:00'),
('LandMark Cinemas', 'AVATAR' , '	2022-12-02 18:00:00'),
('LandMark Cinemas', 'Black Adam' , '	2022-12-01 12:00:00'),
('LandMark Cinemas', 'Black Adam' , '	2022-12-01 18:00:00'),
('LandMark Cinemas', 'Black Adam' , '	2022-12-02 12:00:00'),
('LandMark Cinemas', 'Black Adam' , '	2022-12-02 18:00:00'),

('ScotiaBank Theatre', 'AVATAR' , '	2022-12-01 12:00:00'),
('ScotiaBank Theatre', 'AVATAR' , '	2022-12-01 18:00:00'),
('ScotiaBank Theatre', 'AVATAR' , '	2022-12-02 12:00:00'),
('ScotiaBank Theatre', 'AVATAR' , '	2022-12-02 18:00:00'),
('ScotiaBank Theatre', 'Black Adam' , '	2022-12-01 12:00:00'),
('ScotiaBank Theatre', 'Black Adam' , '	2022-12-01 18:00:00'),
('ScotiaBank Theatre', 'Black Adam' , '	2022-12-02 12:00:00'),
('ScotiaBank Theatre', 'Black Adam' , '	2022-12-02 18:00:00');


DROP TABLE IF EXISTS LoginServer;
CREATE TABLE LoginServer (
	Username	varchar(100) NOT NULL,
    Pass		varchar(100) NOT NULL,
--     LoginName		varchar(200),
    
    primary key	(Username)
);

