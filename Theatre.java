import java.util.ArrayList;
import java.util.Date;

public class Theatre {
    public String name;

    private ArrayList<Movie> movies;

    public Theatre(String name){
        movies = new ArrayList<Movie>();
        this.name = name;
    }

    public Movie getMovie(int index){
        return movies.get(index);
    }

    public Movie getMovie(String movie){
        for(int i = 0; i < movies.size(); i++){
            if(movies.get(i).name.equals(movie)){
                return movies.get(i);
            }
        }
        return null;
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public void addMovie(String name, Date dt){
        movies.add(new Movie(name, dt));
    }
}
