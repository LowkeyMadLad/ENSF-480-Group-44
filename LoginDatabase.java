import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

// import com.mysql.cj.log.Log;

public class LoginDatabase {
    private static LoginDatabase loginDatabase = null;

    private final String DBURL = "jdbc:mysql://localhost:3306/MOVIE_DATABASE";
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

    public boolean checkLoginInformation(String username, String password) throws DBConnectException, SQLException
    {
        initializeConnection();
        
        String query = "SELECT Pass FROM LoginServer WHERE Username = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, username);
        ResultSet results = myStmt.executeQuery();

        while(results.next())
        {
            if(results.getString("Pass").equals(password))
            {
                return true;
            }
        }

        return false;
    }

    public RegisteredUser getLoginInformation(String username, String password) throws DBConnectException, SQLException
    {
        initializeConnection();
        
        String query = "SELECT * FROM LoginServer WHERE Username = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, username);
        ResultSet results = myStmt.executeQuery();
        // RegisteredUser registeredUser;

        while(results.next())
        {
            String name = results.getString("FullName");
            String creditCardInfo = results.getString("CardNumber");
            String email = results.getString("Email");
            String address = results.getString("HomeAddress");
            int cvv =Integer.parseInt(results.getString("CVV"));
            System.out.println(name + " " + creditCardInfo + " " + email + " " + address + " " + cvv);
            try {
                RegisteredUser registeredUser = new RegisteredUser(name, email, address , password, creditCardInfo, cvv);
                return registeredUser;
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }

        }

        
       
        return null;
    }

    public void signUp(String username, String name, String email, String address, String password, String cardNumber, int cvv) throws DBConnectException, SQLException
    {
        initializeConnection();

        String query = "INSERT IGNORE INTO LoginServer (Username, Pass, FullName, Email, HomeAddress, CardNumber, CVV) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, username);
        myStmt.setString(2, password);
        myStmt.setString(3, name);
        myStmt.setString(4, email);
        myStmt.setString(5, address);
        myStmt.setString(6, cardNumber);
        myStmt.setInt(7, cvv);
        
        //myStmt.executeUpdate();

        int rowCount = myStmt.executeUpdate();
        if(rowCount == 0){
            throw new SQLException("No rows were changed.");
        }
        
        myStmt.close();

    }

    public static LoginDatabase getDB() throws DBConnectException{
        if(loginDatabase == null){
            loginDatabase = new LoginDatabase();
        }
        return loginDatabase;
    }

    /**
     * Deletes a registered user from the loginserver database, and all their tickets. 
     * @param username
     * @param fullname
     * @throws DBConnectException
     * @throws SQLException
     */
    public void deleteRU(String username, String fullname) throws DBConnectException, SQLException{
        initializeConnection();
        
        String query = "DELETE FROM LoginServer WHERE Username = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, username);
        int n = myStmt.executeUpdate();
        if (n < 1) {
            // this should never happen but if it does :eyes:
            throw new SQLException("Entry was not deleted or does not exist");
        }

        query = "DELETE FROM MovieTickets WHERE FullName = ?";
        myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, fullname);
        n = myStmt.executeUpdate();
        if (n < 1) {
            // this should never happen but if it does :eyes:
            throw new SQLException("Entry was not deleted or does not exist");
        }
        
        myStmt.close();
    }

    /**
     * Adds an admin manually. Cannot be the head admin.
     * @param user
     * @param pw
     * @throws DBConnectException
     * @throws SQLException
     */
    public void addAdmin(String user, String pw) throws DBConnectException, SQLException{
        initializeConnection();
        
        if(user.equals("admin")){
            String msg = "You cannot be the head admin!";
            JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "INSERT INTO Admins (Username, Pass) VALUES (?,?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, user);
        myStmt.setString(2, pw);
        int n = myStmt.executeUpdate();
        if (n < 1) {
            // this should never happen but if it does :eyes:
            throw new SQLException("Entry was not deleted or does not exist");
        }
        
        myStmt.close();
    }

    /**
     * Deletes an admin. Cannot delete the head admin.
     * @param user
     * @throws DBConnectException
     * @throws SQLException
     */
    public void deleteAdmin(String user) throws DBConnectException, SQLException{
        initializeConnection();

        if(user.equals("admin")){
            String msg = "You cannot delete the head admin!";
            JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String query = "DELETE FROM Admins WHERE Username = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, user);
        int n = myStmt.executeUpdate();
        if (n < 1) {
            // this should never happen but if it does :eyes:
            throw new SQLException("Entry was not deleted or does not exist");
        }
        
        myStmt.close();
    }

    /**
     * @return ArrayList<String> of all admins. 
     * @throws DBConnectException
     * @throws SQLException
     */
    public ArrayList<String> getAdminList() throws DBConnectException, SQLException{
        ArrayList<String> list = new ArrayList<String>();
        initializeConnection();
        String query = "SELECT Username FROM Admins";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        ResultSet results = myStmt.executeQuery();

        while(results.next()){
            list.add(results.getString("Username"));
        }

        myStmt.close();
        results.close();

        return list;
    }

    public static void main(String[] args) throws SQLException, DBConnectException{
        LoginDatabase db = new LoginDatabase();
        String k = "Hell0";
        db.initializeConnection();
        db.signUp(k, k, k, k, k, "453", 0123);
    }
}
