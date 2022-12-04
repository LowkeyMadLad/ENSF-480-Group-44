import java.sql.SQLException;

public class User {
    private TheatreStrategy searchStrat;

    public User(){
        
    }

    public void setStrategy(TheatreStrategy strat){
        searchStrat = strat;
    }

    /**
     * Performs a strategy pattern to either search movie or theatre. 
     * @param choice
     * @return A two-index string containing [String theatre, String movie]
     * @throws DBConnectException
     * @throws SQLException
     */
    public String[] performSearch(String choice) throws DBConnectException, SQLException{
        return searchStrat.search(choice);
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