public class RegisteredUser extends User{
    private String name; // Changing name does not make sense in this context
    private String email;
    private String address;
    private String password;
    private PaymentCard card;

    public RegisteredUser(String name, String email, String address, String password, String cardNumber, int cvv) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.card = new PaymentCard(password, cardNumber, cvv);
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
