


public class PaymentCard {
    private String cardNumber;
    private String cardOwner;
    private int cvv;

    public PaymentCard(String cn, String co, int cvv) throws Exception{
        //cardNumber = cn.replaceAll("\\s","");
        cardNumber = cn;
        cardOwner = co;
        this.cvv = cvv;
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

    private void validate() throws Exception{
        if(cardNumber.length() != 12 || Integer.toString(cvv).length() != 3){
            throw new Exception("Invalid Card");
        }
    }

}
