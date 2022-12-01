import java.util.*;

public class Showtime {
    public Movie movie;

    public Date time;

    private HashMap<String, Boolean> seats;

    public Showtime(Date showtime, Movie movie){
        this.time = showtime;
        this.movie = movie;
        seats = new HashMap<String, Boolean>();
        setSeatArrangement();
    }
    private void setSeatArrangement(){
        // add seats to hash
        // depends on how we want seats to look like

        // for now gonna hard code it to be:
        // A1 A2 A3 A4 A5 A6 A7 A8
        // B1 B2 B3 B4 B5 B6 B7 B8
        // C1 C2 C3 C4 C5 C6 C7 C8
        // D1 D2 D4 D4 D5 D6 D7 D8

        for(int letter = 65; letter <= 68; letter++){
            for(int col = 1; col <= 8; col++){
                String id = "" + (char)letter + Integer.toString(col);
                seats.put(id, true);
            }
        }
    }

    public ArrayList<String> organizeSeats(){
        ArrayList<String> list = new ArrayList<String>();
        // sort
        for(Map.Entry<String, Boolean> e : seats.entrySet()){
            list.add(e.getKey());
        }
        Collections.sort(list);
        return list;
    }

    public boolean isAvailable(String seatNo){
        return seats.get(seatNo);
    }

    public void toggleAvailability(String seatNo){
        seats.replace(seatNo, !seats.get(seatNo));
    }

    public double availableSeatPercentage(){
        if(seats.size() == 0) return 0;
        double available = 0;

        for(Map.Entry<String, Boolean> e : seats.entrySet()){
            if(e.getValue()) available++;
        }

        return available / seats.size() * 100.0;
    }
}
