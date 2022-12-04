import java.sql.SQLException;

public interface TheatreStrategy {
    public String[] search(String choice) throws DBConnectException, SQLException;
}
