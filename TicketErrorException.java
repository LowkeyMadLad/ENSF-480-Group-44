


public class TicketErrorException extends Exception{
    public TicketErrorException(){
        super("Failed to add or remove a ticket from the user");
    }
}
