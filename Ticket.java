import java.util.Date;

public class Ticket {
    private String ticketNum; // T <Time/date><TheatreID><Seatnm>
    private String movie;
    private String theatre;
    private Seat seat;
    private Date showtime;

    public String getTicketNum() {
        return this.ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getMovie() {
        return this.movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getTheatre() {
        return this.theatre;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public Seat getSeat() {
        return this.seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Date getShowtime() {
        return this.showtime;
    }

    public void setShowtime(Date showtime) {
        this.showtime = showtime;
    }
    

    // @Override
    // public String toString(){

    // }

}
