import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;
// using sql date
import java.sql.Date;

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
    private final String DBURL = "jdbc:mysql://localhost/??????????";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf"; 
    private Connection dbConnect;

    // converting db entries into objects
    private ArrayList<Showtime> allShowtimes;
    private HashMap<String, Date> announcementDates;

    private TheatreDatabase() throws Exception{
        allShowtimes = new ArrayList<Showtime>();
        refreshDatabase(); 
    }

    public ArrayList<String> getTheatreList(){
        ArrayList<String> list = new ArrayList<String>();

        return list;
    }
    public ArrayList<String> getMovieList(String theatre){
        ArrayList<String> list = new ArrayList<String>();

        return list;
    }

    // get the instance of the database singleton
    public static TheatreDatabase getDB() throws Exception{
        if(database == null){
            database = new TheatreDatabase();
        }
        return database;
    }

    // database connections
    public void refreshDatabase() throws Exception{
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

    public void initializeConnection() throws Exception{
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new Exception("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }
        if (dbConnect == null) {
            throw new Exception("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }
    }
}
