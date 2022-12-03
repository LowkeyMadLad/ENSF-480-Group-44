import java.sql.*;

import com.mysql.cj.log.Log;

public class LoginDatabase {
    //private static LoginDatabase loginDatabase = null;

    private final String DBURL = "jdbc:mysql://localhost/MOVIE_DATABASE";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf";
    private Connection dbConnect;

    
    public void initializeConnection() throws DBConnectException{
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new DBConnectException("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }
        if (dbConnect == null) {
            throw new DBConnectException("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }
    }

    public boolean checkLoginInformation(String username, String password)
    {
        initializeConnection();
        
        String query = "SELECT Pass FROM LoginServer WHERE EXISTS (Username = ?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, username);
        ResultSet results = myStmt.executeQuery();

        while(results.next())
        {
            if(results.getString("Pass") == password)
            {
                return true;
            }
        }

        return false;
    }

    public RegisteredUser getLoginInformation(String username, String password)
    {
        initializeConnection();

        String query = "SELECT * FROM LoginServer WHERE EXISTS (Username = ?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, username);
        ResultSet results = myStmt.executeQuery();
        RegisteredUser registeredUser;

        while(results.next())
        {
            String name = results.getString("Name");
            String creditCardInfo = results.getString("CardNumber");
            String email = results.getString("Email");
            String address = results.getString("Address");
            int cvv = results.getString("CVV");

             registeredUser = new RegisteredUser(name, email, address, password, creditCardInfo, cvv);
        }

        return registeredUser;
    }

    public void signUp(String username, String name, String email, String address, String password, String cardNumber, int cvv)
    {
        initializeConnection();

        String query = "INSERT INTO LoginServer (Username, Pass, Name, Email, Address, CardNumber, CVV) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, username);
        myStmt.setString(2, password);
        myStmt.setString(3, name);
        myStmt.setString(4, email);
        myStmt.setString(5, address);
        myStmt.setString(6, cardNumber);
        myStmt.setString(7, cvv);
        
        myStmt.executeUpdate();

    }
}
