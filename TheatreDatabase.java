import java.util.ArrayList;
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

    private ArrayList<Theatre> theatres;

    public Theatre getTheatre(int index){
        return theatres.get(index);
    }
    public int getSize(){
        return theatres.size();
    }

    public Theatre getTheatre(String theatre){
        for(int i = 0; i < theatres.size(); i++){
            if(theatres.get(i).name.equals(theatre)){
                return theatres.get(i);
            }
        }
        return null;
    }

    public void addTheatre(Theatre theatre){
        theatres.add(theatre);
    }
    public void addTheatre(String theatre){
        theatres.add(new Theatre(theatre));
    }

    private TheatreDatabase(){
        theatres = new ArrayList<Theatre>();
    }

    // get the instance of the database singleton
    public static TheatreDatabase getDB(){
        if(database == null){
            database = new TheatreDatabase();
        }
        return database;
    }
}
