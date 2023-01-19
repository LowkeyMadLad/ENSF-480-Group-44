This project is now being labelled as complete and is being archived as of Jan 11, 2023.

The task in this project was to create a Movie Theatre application which connected to a local database to store information about tickets and showtimes for a given Movie Theatre and Movie. Features of the program include:
- Searching for movies, theaters, and a combination of the two
- Searching for showtimes at a given movie theatre for a given movie
- Storing tickets based on user purchases (User purchases are simulated)
- Cancelling tickets
- Seat Selection
- Admin features
- User signup/login
- Registered User discounts

This is not an exhaustive list of all features.

To run this file, make sure you have the correct version of the Java SQL connector paired with your local machines SQL Database. Create the Movie database using the MovieDatabase.sql file. We personally used SQL Workbench.
The hard coded information for the database is:
DBURL = "jdbc:mysql://localhost:3306/MOVIE_DATABASE"
USERNAME = "student"
PASSWORD = "ensf"
Database security was not a requirement in this course. 
From there, you can run the GUI.jar file inside of the build-jar folder which should run the project. 
The program gui should run regardless of if it gains connection to the database or not. How ever functionality will be non existant. 
If running the GUI.jar file does not work properly, then compile the code manually, and run the GUI file to get the program running. 

We used connector version 8.0.31 for most users, how ever some members used the 8.0.23 version as well. 

Contributors to the project were:
- Kirtan Kakadiya (GUI)
- Kartik Sharma (Database and GUI)
- Danny Picazo (GUI and Database logic functions)
- Ryan Mailhiot (Database and Database logic functions)
