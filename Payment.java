

public class Payment {
    private float amount;
    private PaymentCard card;

    public Payment(float amount, PaymentCard card){
        this.amount = amount;
        this.card = card;
    }
    
    private void validate(){
        // do we just pretend it works lmfao
    }
}
