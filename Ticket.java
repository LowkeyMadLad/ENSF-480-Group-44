import java.util.Date;

public class Ticket {
    private static long ticketID = 0; 

    private String movie;
    private String theatre;
    private Date showtime;

    public Ticket(String theatre, String movie, Date showtime){
        this.theatre = theatre;
        this.movie = movie;
        this.showtime = showtime;
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

    // @Override
    // public String toString(){

    // }

}
