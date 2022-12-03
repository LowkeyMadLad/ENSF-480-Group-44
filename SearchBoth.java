import java.sql.*;
import java.util.*;

/**
 * This Strategy is for when a user knows the theatre and movie they want. 
 * They will enter both and if such movie is available at such theatre then
 * they will be presented with showtimes and may choose a seat. If the movie
 * is not playing at their theatre, the program will simply inform the user. 
 * After picking a showtime and seat, the function will then create and
 * return a Ticket. 
 */
public class SearchBoth implements TheatreStrategy {

    @Override
    public Ticket search() throws DBConnectException, SQLException{
        TheatreDatabase db = TheatreDatabase.getDB();

        System.out.println("Please enter the movie you wish to see: ");
        // take input
        Scanner scanner = new Scanner(System.in);  
        System.out.println("Enter your choice:");
        String movie = scanner.nextLine();

        System.out.println("Please enter the Theatre you wish to see it at: ");
        // take input
        System.out.println("Enter your choice:");
        String theatre = scanner.nextLine();

        // check if exists
        // arbitrary?
        // just convert to gui lol


        // display showtimes
        System.out.println("When would you like to watch the movie?");
        ArrayList<Timestamp> showtimeList = db.getShowtimeList(theatre, movie);
        for(int i = 0; i < showtimeList.size(); i++){
            System.out.print(Integer.toString(i+1) + " - ");
            System.out.println(showtimeList.get(i));
        }
        // take input
        System.out.println("Enter your choice:");
        String stchoice = scanner.nextLine(); 
        Timestamp dt = null;
        // verify validity of input
        try {
            Integer.parseInt(stchoice);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Entry! Exiting... ");
            System.exit(1);
        }
        if(Integer.parseInt(stchoice) < 0 || Integer.parseInt(stchoice) > showtimeList.size()){
            System.out.println("Invalid Entry! Exiting... ");
            System.exit(1);
        }else{
            dt = showtimeList.get(Integer.parseInt(stchoice)-1);
        }
        // show available seats
        Showtime st = new Showtime(theatre, movie, dt);
        ArrayList<String> seats = st.organizeSeats();
        int counter = 0;
        ArrayList<String> takenSeats = new ArrayList<String>();
        for(String seatNo : seats){
            if(st.isAvailable(seatNo)){
                System.out.print(seatNo + "\t");
            }else{
                System.out.print("\t");
                takenSeats.add(seatNo);
            }
            counter++;
            if(counter == 8){
                System.out.println();
                counter = 0;
            }
        }
        // take input
        System.out.println("Enter your choice:");
        String seatchoice = scanner.nextLine(); 
        String seat = seatchoice.toUpperCase();
        // verify validity of input
        if(takenSeats.contains(seat)){
            System.out.println("That seat is taken! Exiting... ");
            System.exit(1);
        } else if(!seats.contains(seat)){
            System.out.println("Invalid Entry! Exiting... ");
            System.exit(1);
        } 
        // at this point, the search is done. it just creates a Ticket
        // and sends in the required variables, and the rest of the program
        // will do its thing. search has done what it needed to. 
        scanner.close();
        return (new Ticket(theatre, movie, dt, seat));
    }
    
}
