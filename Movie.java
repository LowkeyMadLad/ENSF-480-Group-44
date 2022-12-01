import java.util.Date;
import java.util.ArrayList;

public class Movie {
    public Theatre theatre;
    
    public String name;

    public Date announcemenDate;

    private ArrayList<Showtime> showtimes;

    public Movie(String name, Date announcementDate, Theatre theatre){
        showtimes = new ArrayList<Showtime>();
        this.name = name;
        this.announcemenDate = announcementDate;
        this.theatre = theatre;
    }

    public Showtime getShowtime(int index){
        return showtimes.get(index);
    }
    public int getSize(){
        return showtimes.size();
    }

    public Showtime getShowtime(Date date){
        for(int i = 0; i < showtimes.size(); i++){
            if(showtimes.get(i).time.compareTo(date) == 0){
                return showtimes.get(i);
            }
        }
        return null;
    }

    public void addShowtime(Showtime st){
        showtimes.add(st);
    }
    public void addShowtime(Date dt){
        showtimes.add(new Showtime(dt, this));
    }
}
