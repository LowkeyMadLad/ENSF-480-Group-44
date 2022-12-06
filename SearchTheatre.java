import java.util.*;
import java.sql.*;
import javax.swing.*;

/**
 * This Strategy is for when a user wants to browse the movies at
 * a theatre. They will choose the theatre they want to browse at, 
 * then will be presented with every movie available at that theatre. 
 * After choosing a movie, they may choose a showtime followed by a seat. 
 * The function will then create a Ticket and return it.
 */
public class SearchTheatre implements TheatreStrategy{

    /**
     * Strategy pattern for searching a movie when the Movie isn't specified
     * @param choice - String
     * @throws DBConnectException
     * @throws SQLException
     * @throws IllegalArgumentException
     */
    @Override
    public String[] search(String choice) throws DBConnectException, SQLException, IllegalArgumentException {
        TheatreDatabase db = TheatreDatabase.getDB();

        ArrayList<String> movieList = db.getMovieList(choice);
        String[] moviesToChoose = new String[movieList.size()];
        for(int i=0;i<movieList.size();i++)
        {
            moviesToChoose[i] = movieList.get(i);
        }
        
        String movie = (String) JOptionPane.showInputDialog(
            null, "Which movie would you like to watch?", "Available Movies at " + choice,
            JOptionPane.QUESTION_MESSAGE, null, moviesToChoose, moviesToChoose[0]);

        // // verify validity of input
        // if(movie.equals("") || movie == null){
        //     throw new IllegalArgumentException("No movie chosen.");
        // }

        String[] ret = new String[2];
        ret[0] = choice;
        ret[1] = movie;
        return ret;
    }

    /*
    @Override
    public Ticket search() throws DBConnectException, SQLException {
        TheatreDatabase db = TheatreDatabase.getDB();

        // display theatres
        System.out.println("Please choose a theatre: ");
        ArrayList<String> theatreList = db.getTheatreList();
        for(int i = 0; i < theatreList.size(); i++){
            System.out.print(Integer.toString(i+1) + " - ");
            System.out.println(theatreList.get(i));
        }
        // take input
        Scanner scanner = new Scanner(System.in);  
        System.out.println("Enter your choice:");
        String tchoice = scanner.nextLine(); 
        String theatre = "";
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
        System.out.println("You have chosen: " + theatre);
        // display movies
        System.out.println("Please choose a movie: ");
        ArrayList<String> movieList = db.getMovieList(theatre);
        for(int i = 0; i < movieList.size(); i++){
            System.out.print(Integer.toString(i+1) + " - ");
            System.out.println(movieList.get(i));
        }
        // take input
        System.out.println("Enter your choice:");
        String mchoice = scanner.nextLine(); 
        String movie = "";
        // verify validity of input
        try {
            Integer.parseInt(mchoice);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Entry! Exiting... ");
            System.exit(1);
        }
        if(Integer.parseInt(mchoice) < 0 || Integer.parseInt(mchoice) > movieList.size()){
            System.out.println("Invalid Entry! Exiting... ");
            System.exit(1);
        }else{
            movie = movieList.get(Integer.parseInt(mchoice)-1);
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
     */
    
}
