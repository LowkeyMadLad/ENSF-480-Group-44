import java.util.ArrayList;

public class User {
    private ArrayList<Ticket> tickets;
    private TheatreStrategy searchStrat;

    public User(){
        this.tickets = new ArrayList<Ticket>();
    }

    public void setStrategy(TheatreStrategy strat){
        searchStrat = strat;
    }

    public void performSearch(){
        searchStrat.search();
    }

    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    public void addTicket(Ticket t) throws TicketErrorException {
        if(!(tickets.add(t))){
            throw new TicketErrorException();
        }
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