import java.util.*;

/**
 * Registered user contains information that will be stored on the database and will allow a seamless transition between database and code
 */
public class RegisteredUser extends User{
    private String name; // Changing name does not make sense in this context
    private String email;
    private String address;
    private String password;
    private PaymentCard card;

    // tickets bought by an RU will be saved to their account
    private ArrayList<Ticket> tickets;

    /**
     * Public constructor for Registered User
     * @param name - String
     * @param email - String
     * @param address - String
     * @param password - String
     * @param cardNumber - String (16 characters)
     * @param cvv - Int (3 digits)
     * @throws IllegalArgumentException
     */
    public RegisteredUser(String name, String email, String address, String password, String cardNumber, int cvv) throws IllegalArgumentException {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.card = new PaymentCard(cardNumber, name , cvv);
        this.tickets = new ArrayList<Ticket>();
    }

    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    public void addTicket(Ticket t) throws TicketErrorException {
        if(!(tickets.add(t))){
            throw new TicketErrorException();
        }
    }

    
    public String getName(){
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PaymentCard getCard() {
        return this.card;
    }

    public void setCard(PaymentCard card) {
        this.card = card;
    }




}
