import java.sql.SQLException;
import javax.swing.*;

public interface TheatreStrategy {
    public String[] search(JPanel panel) throws DBConnectException, SQLException;
}
