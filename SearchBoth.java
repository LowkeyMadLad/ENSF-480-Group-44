import java.sql.*;

public class SearchBoth implements TheatreStrategy {

    @Override
    public Ticket search() throws DBConnectException, SQLException{
        // TODO Auto-generated method stub
        return (new Ticket(null, null, null, null));
    }
    
}