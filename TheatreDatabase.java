import java.util.ArrayList;

public class TheatreDatabase {
    // singleton instance
    private static TheatreDatabase database = null;

    private ArrayList<Theatre> theatres;

    public Theatre getTheatre(int index){
        return theatres.get(index);
    }

    public Theatre getTheatre(String theatre){
        for(int i = 0; i < theatres.size(); i++){
            if(theatres.get(i).name.equals(theatre)){
                return theatres.get(i);
            }
        }
        return null;
    }

    public void addTheatre(Theatre theatre){
        theatres.add(theatre);
    }
    public void addTheatre(String theatre){
        theatres.add(new Theatre(theatre));
    }

    private TheatreDatabase(){
        theatres = new ArrayList<Theatre>();
    }

    // get the instance of the database singleton
    public static TheatreDatabase getDB(){
        if(database == null){
            database = new TheatreDatabase();
        }
        return database;
    }
}
