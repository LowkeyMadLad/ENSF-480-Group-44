import java.sql.SQLException;
import javax.swing.*;

public class User {
    private TheatreStrategy searchStrat;

    public User(){
        
    }

    public void setStrategy(TheatreStrategy strat){
        searchStrat = strat;
    }

    public String[] performSearch(JPanel panel) throws DBConnectException, SQLException{
        return searchStrat.search(panel);
    }

    // public void removeTicket(String ticketNo) throws TicketErrorException{
    //     for (Ticket ticket : tickets) {
    //         if (ticket.getTicketNum().equals(ticketNo)) {
    //             tickets.remove(ticket);
    //             return;
    //         }
    //     }
    //     throw new TicketErrorException();
    // }

    
}