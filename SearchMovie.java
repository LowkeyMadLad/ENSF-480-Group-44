import java.util.*;
import java.sql.*;

/**
 * This Strategy is for when a user wants to watch a specific movie. 
 * They will enter a movie, and the program will list every theatre
 * that has said movie. Then the user can choose the theatre, and 
 * the showtimes for their desired movie at that theatre.
 * Upon choosing a seat at a showtime, the function will
 * create and return a Ticket.
 */
public class SearchMovie implements TheatreStrategy{

    @Override
    public Ticket search() throws DBConnectException, SQLException{
        TheatreDatabase db = TheatreDatabase.getDB();

        System.out.println("Please enter the movie you wish to see: ");
        // take input
        Scanner scanner = new Scanner(System.in);  
        System.out.println("Enter your choice:");
        String movie = scanner.nextLine(); 
        // attempt to find the movie
        ArrayList<String> theatreList = db.findMovieTheatres(movie);
        if(theatreList.isEmpty()){
            System.out.println("Sorry, that movie is not available anywhere! Exiting... ");
            System.exit(1);
        }
        // display theatres
        String theatre = "";
        if(theatreList.size() == 1){
            theatre = theatreList.get(0);
            System.out.println(movie + " is only available at " + theatre);
            System.out.println("Proceed? (Y/N)");
            // take input
            String tchoice = scanner.nextLine(); 
            // verify validity of input
            switch (tchoice.toUpperCase()) {
                case "Y":
                    System.out.println("Understood. Proceeding... ");
                    break;
                case "N":
                    System.out.println("Understood. Exiting... ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Entry! Exiting... ");
                    System.exit(1);
                    break;
            }
        } else{
            for(int i = 0; i < theatreList.size(); i++){
                System.out.print(Integer.toString(i+1) + " - ");
                System.out.println(theatreList.get(i));
            }
            // take input
            System.out.println("Enter your choice:");
            String tchoice = scanner.nextLine(); 
            // verify validity of input
            try {
                Integer.parseInt(tchoice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Entry! Exiting... ");
                System.exit(1);
            }
            if(Integer.parseInt(tchoice) < 0 || Integer.parseInt(tchoice) > theatreList.size()){
                System.out.println("Invalid Entry! Exiting... ");
                System.exit(1);
            }else{
                theatre = theatreList.get(Integer.parseInt(tchoice)-1);
            }
        }


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
