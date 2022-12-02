import java.util.Date;

public class Ticket {
    private static long ticketID = 0; 

    private String movie;
    private String theatre;
    private Date showtime;
    private String seat;

    public Ticket(String theatre, String movie, Date showtime, String seat){
        this.theatre = theatre;
        this.movie = movie;
        this.showtime = showtime;
        this.seat = seat;
        ticketID++;
    }

    public long getTicketNum() {
        return Ticket.ticketID;
    }
    public String getMovie() {
        return this.movie;
    }
    public String getTheatre() {
        return this.theatre;
    }
    public Date getShowtime() {
        return this.showtime;
    }

    @Override
    public String toString(){
        String t = "";
        t += "Seat " + seat + "\nTo watch " + movie + "\nAt " + theatre + "\nOn " + showtime;
        return t;
    }

}
