import java.util.*;

public class SearchTheatre implements TheatreStrategy{

    @Override
    public void search() {
        TheatreDatabase db = TheatreDatabase.getDB();
        System.out.println("Please choose a theatre: ");
        for(int i = 0; i < db.getSize(); i++){
            System.out.print(Integer.toString(i+1) + " - ");
            System.out.println(db.getTheatre(i).name);
        }
        Scanner scanner = new Scanner(System.in);  
        System.out.println("Enter your choice:");
        String tchoice = scanner.nextLine(); 
        Theatre theatre = null;
        if(Integer.parseInt(tchoice) < 0 || Integer.parseInt(tchoice) > db.getSize()){
            System.out.println("Invalid Entry! Exiting... ");
            System.exit(1);
        }else{
            theatre = db.getTheatre(Integer.parseInt(tchoice)-1);
        }
        System.out.println("You have chosen: " + theatre.name);
        System.out.println("Please choose a movie: ");
        for(int i = 0; i < theatre.getSize(); i++){
            System.out.print(Integer.toString(i+1) + " - ");
            System.out.println(theatre.getMovie(i).name);
        }
        System.out.println("Enter your choice:");
        String mchoice = scanner.nextLine(); 
        Movie movie = null;
        if(Integer.parseInt(mchoice) < 0 || Integer.parseInt(mchoice) > theatre.getSize()){
            System.out.println("Invalid Entry! Exiting... ");
            System.exit(1);
        }else{
            movie = theatre.getMovie(Integer.parseInt(mchoice)-1);
        }
        System.out.println("When would you like to watch the movie?");
        for(int i = 0; i < movie.getSize(); i++){
            System.out.print(Integer.toString(i+1) + " - ");
            System.out.println(movie.getShowtime(i).time.toString());
        }
        scanner.close();
    }
    
}
