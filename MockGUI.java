import java.sql.*;
import java.util.*;

public class MockGUI {
    static TheatreDatabase db;
    static User user = new User();
    public static void main(String[] args) throws DBConnectException, SQLException {
        db = TheatreDatabase.getDB();
        // fillDatabase();
        Timestamp testDate = new Timestamp(System.currentTimeMillis());
        System.out.println(testDate.toString());
        System.out.println("Unix Time: " + System.currentTimeMillis());
        // Calendar testCalendar = Calendar.getInstance();
        System.out.println("How would you like to find a movie?\nT = Search by theatre\nM = Search by movie\nB = Search both");
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
            case "B":
                user.setStrategy(new SearchBoth());
            default:
                System.out.println("Invalid Entry! Exiting...");
                System.exit(1);
                break;
        }
        user.performSearch();

        scanner.close();
    }
}
