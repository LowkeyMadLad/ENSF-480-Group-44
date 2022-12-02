import java.sql.SQLException;

public class User {
    private TheatreStrategy searchStrat;

    public User(){
        
    }

    public void setStrategy(TheatreStrategy strat){
        searchStrat = strat;
    }

    public Ticket performSearch() throws DBConnectException, SQLException{
        return searchStrat.search();
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