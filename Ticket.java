import java.sql.*;
import java.util.*;

public class Ticket {
    private String movie;
    private String theatre;
    private Timestamp showtime;
    private String seat;

    public Ticket(String theatre, String movie, Timestamp showtime, String seat){
        this.theatre = theatre;
        this.movie = movie;
        this.showtime = showtime;
        this.seat = seat;
    }

    public int getTicketNum() throws SQLException, DBConnectException {
        return TheatreDatabase.getDB().getTicketID(theatre, movie, showtime, seat);
    }
    public String getMovie() {
        return this.movie;
    }
    public String getTheatre() {
        return this.theatre;
    }
    public Timestamp getShowtime() {
        return this.showtime;
    }
    public String getSeat() {
        return this.seat;
    }

    @Override
    public String toString(){
        String t = "";
        t += "Seat " + seat + "\nTo watch " + movie + "\nAt " + theatre + "\nOn " + showtime;
        return t;
    }

}
