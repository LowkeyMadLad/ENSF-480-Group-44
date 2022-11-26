public class Seat {
    private String row;
    private int number;

    public Seat(String row, int num){
        this.row = row;
        this.number = num;
    }

    @Override
    public String toString(){
        String temp = row + number;
        return temp;
    }
}
