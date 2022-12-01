import java.util.*;

public class MockGUI {
    static TheatreDatabase db = TheatreDatabase.getDB();
    public static void main(String[] args) {
        fillDatabase();
    }

    public static void fillDatabase(){
        db.addTheatre("ZOO Lounge");
        db.getTheatre("ZOO Lounge").addMovie("Schulich Ohms", new Date(1633327200*1000));
        db.getTheatre("ZOO Lounge").addMovie("Not Schulich Ohms", new Date(1628056800*1000));

        db.addTheatre("Landmark");
        db.getTheatre("Landmark").addMovie("Black Panther", new Date(1513321200*1000));
        db.getTheatre("Landmark").addMovie("Ratatouille", new Date(1663048800*1000));
        db.getTheatre("Landmark").addMovie("1984", new Date(1386399600*1000));

        // im not done but i have to go do something lol
    }
}
