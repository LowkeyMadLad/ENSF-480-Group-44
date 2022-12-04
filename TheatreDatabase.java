import java.util.ArrayList;
// using sql date
// import java.util.HashMap;
import java.sql.*;

/* danny's note:
 * i am trying to remove the need for classes
 * as it ruins the point of using the database instead
 * of all that java heap memory. i dont really know
 * how databases work all too well, so there will be
 * a lot of pseudocode. as well, i am expecting that
 * the database will be something like the following:
 * - a table of theatres with a name, and a table of movies
 * - movies will have a name, announcement date, and a table of showtimes
 * - showtimes will have a date, and a table of seats
 * - seats will have an availability boolean
 * 
 * alternatively, if this is too much, we can have a simple
 * table with (Theatre, Movie, Announcement Date, Showtime)
 * this basic version, however, will make searching weird
 * and unintuitive (i assume so)
 */

public class TheatreDatabase {
    // singleton instance
    private static TheatreDatabase database = null;

    // database stuff
    private final String DBURL = "jdbc:mysql://localhost/MOVIE_DATABASE";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf";
    private Connection dbConnect;

    // converting db entries into objects
    // private ArrayList<Showtime> allShowtimes;
    // private HashMap<String, Date> announcementDates;

    private TheatreDatabase() throws DBConnectException, SQLException{
        // allShowtimes = new ArrayList<Showtime>();
        // announcementDates = new HashMap<String, Date>();
        // refreshDatabase(); 
        // validateDB();
    }

    /**
     * @return Void
     * @throws DBConnectException
     * @throws SQLException
     * Removes Showtimes from database that have already passed
     */
    // public void validateDB() throws DBConnectException, SQLException{
    //     ArrayList<Integer> idRemove = new ArrayList<Integer>();
    
    //     initializeConnection();
    //     String query = "SELECT MovieTime FROM MOVIE_INFORMATION";
    //     PreparedStatement myStmt = dbConnect.prepareStatement(query);
    //     ResultSet results = myStmt.executeQuery();

    //     String query2 = "SELECT FROM MovieReleaseDate WHERE MovieName = ?";
    //     PreparedStatement myStmt2 = dbConnect.prepareStatement(query2);

    //     String query3 = "DROP FROM MovieReleaseDate WHERE MovieName = ?";
    //     PreparedStatement myStmt3 = dbConnect.prepareStatement(query3);

    //     while(results.next()){
    //         Timestamp movieShowtime = results.getTimestamp("MovieTime");
    //         Timestamp now = new Timestamp(System.currentTimeMillis());
    //         String movie = results.getString("MovieName");
    //         myStmt2.setString(1, movie);
    //         ResultSet r2 = myStmt2.executeQuery();
    //         Timestamp releaseDateTime = r2.getTimestamp("ReleaseDate");
    //         if(movieShowtime.compareTo(now) < 0){
    //             idRemove.add(results.getInt("MovieID"));
    //         } else if (movieShowtime.compareTo(releaseDateTime)< 0) { // if movie showtime is before the release date
    //             idRemove.add(results.getInt("MovieID"));
    //         }

    //         if(releaseDateTime.compareTo(now) < 0){ // releasedate is before now
    //             myStmt3.setString(1, movie);
    //             int n = myStmt3.executeUpdate();
    //             if(n < 1){
    //                 throw new SQLException("Announcement date doesn't exist in DB");
    //             }
    //         }
    //         r2.close();
    //     }

    //     for (Integer integer : idRemove) {
    //         query = "DROP FROM MOVIE_INFORMATION WHERE MovieID = ?";
    //         myStmt = dbConnect.prepareStatement(query);
    //         myStmt.setInt(1, integer);
    //         int n = myStmt.executeUpdate();
    //         if(n < 1){
    //             throw new SQLException("Movie item doesn't exist in DB. Cannot Delete");
    //         }
    //     }
    //     myStmt.close();
    //     results.close();
    //     myStmt2.close();
    //     myStmt3.close();
    //     dbConnect.close();
    // }


    /**
     * @return ArrayList<String> of every theatre on the database.
     * @throws DBConnectException
     * @throws SQLException
     */
    public ArrayList<String> getTheatreList() throws DBConnectException, SQLException{
        ArrayList<String> list = new ArrayList<String>();
        //validateDB();
        initializeConnection();
        String query = "SELECT DISTINCT MovieTheatre FROM MOVIE_INFORMATION";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        ResultSet results = myStmt.executeQuery();

        while(results.next()){
            list.add(results.getString("MovieTheatre"));
        }

        myStmt.close();
        results.close();
        dbConnect.close();

        return list;
    }


    public ArrayList<String> getMovieList() throws DBConnectException, SQLException{
        ArrayList<String> list = new ArrayList<String>();
        //validateDB();
        initializeConnection();
        String query = "SELECT DISTINCT MovieName FROM MOVIE_INFORMATION";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        ResultSet results = myStmt.executeQuery();

        while(results.next()){
            list.add(results.getString("MovieName"));
        }

        myStmt.close();
        results.close();
        dbConnect.close();

        return list;
    }
    /**
     * @param movie
     * @return ArrayList<String> of every theatre that has the given movie. 
     * @throws DBConnectException
     * @throws SQLException
     */
    public ArrayList<String> findMovieTheatres(String movie) throws DBConnectException, SQLException{
        ArrayList<String> list = new ArrayList<String>();

        initializeConnection();
        String query = "SELECT DISTINCT MovieTheatre FROM MOVIE_INFORMATION WHERE MovieName = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, movie);
        ResultSet results = myStmt.executeQuery();
        
        while(results.next()){
            list.add(results.getString("MovieTheatre"));
        }

        myStmt.close();
        results.close();
        dbConnect.close();

        return list;
    }

    /**
     * @param theatre
     * @return ArrayList<String> of every movie at a given theatre.
     * @throws DBConnectException
     * @throws SQLException
     */
    public ArrayList<String> getMovieList(String theatre) throws DBConnectException, SQLException{
        ArrayList<String> list = new ArrayList<String>();
        //validateDB();
        initializeConnection();
        String query = "SELECT DISTINCT MovieName FROM MOVIE_INFORMATION WHERE MovieTheatre = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, theatre);
        ResultSet results = myStmt.executeQuery();
        
        while(results.next()){
            list.add(results.getString("MovieName"));
        }

        myStmt.close();
        results.close();
        dbConnect.close();

        return list;
    }

    public ArrayList<String> getSeats(String movie, String theatre, Timestamp time) throws DBConnectException, SQLException{
        ArrayList<String> list = new ArrayList<String>();
        initializeConnection();
        String query = "SELECT DISTINCT Seat FROM MovieTickets WHERE MovieName = ? AND MovieTheatre = ? AND MovieTime = ? ";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, movie);
        myStmt.setString(2, theatre);
        myStmt.setTimestamp(3, time);
        ResultSet results = myStmt.executeQuery();
        
        while(results.next()){
            list.add(results.getString("Seat"));
        }

        myStmt.close();
        results.close();
        dbConnect.close();

        return list;
    }

    public void insertTicket(Ticket ticket, String name) throws SQLException, DBConnectException
    {
        initializeConnection();
        System.out.println("in Loop");
        String query = "INSERT IGNORE INTO MovieTickets (MovieTheatre, MovieName, MovieTime, Seat, FullName) VALUES (?,?,?,?,?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, ticket.getTheatre());
        myStmt.setString(2, ticket.getMovie());
        myStmt.setTimestamp(3, ticket.getShowtime());
        myStmt.setString(4, ticket.getSeat());
        myStmt.setString(5, name);
        
        myStmt.executeUpdate();

        int rowCount = myStmt.executeUpdate();
        if(rowCount == 0){
            throw new SQLException("No rows were changed.");
        }
        
        myStmt.close();

    }

    public void cancelTicket(String ticketID, String name) throws SQLException, DBConnectException
    {
        initializeConnection();
        System.out.println("in Loop");

        String query = "DELETE FROM movietickets WHERE TicketID = ? AND FullName = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, ticketID);
        myStmt.setString(2, name);

        
        myStmt.executeUpdate();

        int rowCount = myStmt.executeUpdate();
        if(rowCount == 0){
            throw new SQLException("No rows were changed.");
        }
        
        myStmt.close();
    }
    /**
     * @param theatre
     * @param movie
     * @return ArrayList<Timestamp> of every showtime for a given movie at a given theatre. 
     * @throws DBConnectException
     * @throws SQLException
     */
    public ArrayList<Timestamp> getShowtimeList(String theatre, String movie) throws DBConnectException, SQLException{
        ArrayList<Timestamp> list = new ArrayList<Timestamp>();
        //validateDB();
        initializeConnection();
        String query = "SELECT MovieTime FROM MOVIE_INFORMATION WHERE MovieTheatre = ? AND MovieName = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, theatre);
        myStmt.setString(2, movie);
        ResultSet results = myStmt.executeQuery();
        
        while(results.next()){
            list.add(results.getTimestamp("MovieTime"));
        }

        myStmt.close();
        results.close();
        dbConnect.close();

        return list;
    }

    /**
     * @param movie
     * @return Timestamp of when a given movie is being publicly announced. 
     * @throws DBConnectException
     * @throws SQLException
     */
    public Timestamp getAnnouncementDate(String movie) throws DBConnectException, SQLException{
        initializeConnection();
        String query = "SELECT ReleaseDate FROM MovieReleaseDate WHERE MovieName = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, movie);
        ResultSet results = myStmt.executeQuery();

        Timestamp dt = results.getTimestamp("ReleaseDate");

        myStmt.close();
        results.close();
        dbConnect.close();

        return dt;
    }

    /**
     * Getter for the TheatreDatabase Singleton instance.
     * @return TheatreDatabase single instance.
     * @throws DBConnectException
     * @throws SQLException
     */
    public static TheatreDatabase getDB() throws DBConnectException, SQLException{
        
        if(database == null){
            database = new TheatreDatabase();
        }
        return database;
    }

    public Connection getConnection(){
        return dbConnect;
    }

    // database connections
    /* obsolete?
    public void refreshDatabase() throws DBConnectExcept{
        initializeConnection();
        allShowtimes.clear();
        Showtime st;
        try {
            // query information to the sql database
            String query = "SELECT * FROM MOVIE_INFORMATION";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            ResultSet results = myStmt.executeQuery();
            while (results.next()) {
                try {
                    st = new Showtime(results.getString("MovieTheatre"), 
                        results.getString("MovieName"), results.getDate("MovieTime"));
                } catch (IllegalArgumentException e) {
                    st = null;
                }
                if (st != null) {
                    allShowtimes.add(st);
                }
            }
            query = "SELECT * FROM MovieReleaseDate";
            myStmt = dbConnect.prepareStatement(query);
            results = myStmt.executeQuery();
            while (results.next()) {
                announcementDates.put(results.getString("MovieName"), 
                                    results.getDate("ReleaseDate"));
            }
            // closes
            myStmt.close();
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            System.err.println("Unexcpected SQL exception thrown in refreshDatabaseItems in DatabaseItems. Read the SQL error code for more details");
            e.printStackTrace();
        }
        allShowtimes.trimToSize();
    }
    */

    public void initializeConnection() throws DBConnectException{
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBConnectException("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }

        if (dbConnect == null) {
            throw new DBConnectException("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }
    }
}
