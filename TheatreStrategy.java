import java.sql.SQLException;

public interface TheatreStrategy {
    public Ticket search() throws DBConnectException, SQLException;
}
