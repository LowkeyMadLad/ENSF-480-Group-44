import java.util.*;

public class MockGUI {
    static TheatreDatabase db = TheatreDatabase.getDB();
    static User user = new User();
    public static void main(String[] args) {
        fillDatabase();
        Date testDate = new Date(System.currentTimeMillis());
        System.out.println(testDate.toString());
        System.out.println("Unix Time: " + System.currentTimeMillis());
        // Calendar testCalendar = Calendar.getInstance();
        System.out.println("How would you like to find a movie?\nT = Search by theatre\nM = Search by movie");
        Scanner scanner = new Scanner(System.in);  
        System.out.println("Enter your choice: ");
        String searchstrat = scanner.nextLine(); 
        // scanner.close();
        switch (searchstrat.toUpperCase()) {
            case "T":
                user.setStrategy(new SearchTheatre());
                break;
            case "M":
                user.setStrategy(new SearchMovie());
                break;
            default:
                System.out.println("Invalid Entry! Exiting...");
                System.exit(1);
                break;
        }
        user.performSearch();

        scanner.close();
    }

    public static void fillDatabase(){
        db.addTheatre("ZOO Lounge");
        Theatre zoo = db.getTheatre("ZOO Lounge");
        zoo.addShowtime("Schulich Ohms", new Date(1633327200L*1000), new Date(1669877222L*1000));
        zoo.addShowtime("Schulich Ohms", null, new Date(1669887222L*1000));
        zoo.addShowtime("Schulich Ohms", null, new Date(1669889928L*1000));
        zoo.addShowtime("Not Schulich Ohms", new Date(1628056800L*1000), new Date(1669988822L*1000));

        db.addTheatre("Landmark");
        Theatre lm = db.getTheatre("Landmark");
        // announcement a year ahead
        lm.addShowtime("Black Panther", new Date(1513321200L*1000), new Date(1701759600L*1000));
        lm.addShowtime("Ratatouille", new Date(1663048800L*1000), new Date(1664048800L*1000));
        // showtime a year ago
        lm.addShowtime("1984", new Date(1386399600L*1000), new Date(1638687600L*1000));

        // im not done but i have to go do something lol
    }
}
