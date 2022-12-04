import java.util.*;
import java.sql.*;

public class Admin extends RegisteredUser{

    public Admin(String name, String email, String address, String password, String cardNumber, int cvv)
            throws Exception {
        super(name, email, address, password, cardNumber, cvv);
        //TODO Auto-generated constructor stub
    }
    
    public void addShowtime(String theatre, String movie, Timestamp showtime){
        
    }
}
