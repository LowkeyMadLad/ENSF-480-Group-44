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

    private TheatreDatabase() throws DBConnectException{
        // allShowtimes = new ArrayList<Showtime>();
        // announcementDates = new HashMap<String, Date>();
        // refreshDatabase(); 
    }

    public ArrayList<String> getTheatreList() throws DBConnectException, SQLException{
        ArrayList<String> list = new ArrayList<String>();

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

    public ArrayList<String> getMovieList(String theatre) throws DBConnectException, SQLException{
        ArrayList<String> list = new ArrayList<String>();

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

    public Date getAnnouncementDate(String movie) throws DBConnectException, SQLException{
        initializeConnection();
        String query = "SELECT ReleaseDate FROM MovieReleaseDate WHERE MovieName = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, movie);
        ResultSet results = myStmt.executeQuery();

        Date dt = results.getDate("ReleaseDate");

        myStmt.close();
        results.close();
        dbConnect.close();

        return dt;
    }

    // get the instance of the database singleton
    public static TheatreDatabase getDB() throws DBConnectException{
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
            throw new DBConnectException("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }
        if (dbConnect == null) {
            throw new DBConnectException("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }
    }
}
