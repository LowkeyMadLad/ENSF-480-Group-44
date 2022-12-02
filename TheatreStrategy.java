import java.sql.SQLException;

public interface TheatreStrategy {
    public void search() throws DBConnectException, SQLException;
}
