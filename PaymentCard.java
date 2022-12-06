
/**
 * Stores information about a users payment card for it to be used later
 */
public class PaymentCard {
    private String cardNumber;
    private String cardOwner;
    private int cvv;

    /**
     * Public constructor for the PaymentCard class
     * @param cn - String Card Number (16 Characters)
     * @param co - String Name of Card Owner
     * @param cvv - Int (3 digits)
     * @throws IllegalArgumentException
     */
    public PaymentCard(String cn, String co, int cvv) throws IllegalArgumentException{
        //cardNumber = cn.replaceAll("\\s","");
        cardNumber = cn;
        cardOwner = co;
        this.cvv = cvv;
        //validate();
        validate();
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardOwner() {
        return this.cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public int getCvv() {
        return this.cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * Validates the user input a legal card number and cvv (Primative)
     * @throws IllegalArgumentException
     */
    private void validate() throws IllegalArgumentException{
        if(cardNumber.length() != 16 || Integer.toString(cvv).length() != 3){
            throw new IllegalArgumentException("Invalid Card");
        }
    }
    // private void validate() throws Exception{
    //     if(cardNumber.length() != 12 || Integer.toString(cvv).length() != 3){
    //         throw new Exception("Invalid Card");
    //     }
    // }

}