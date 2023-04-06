import java.util.ArrayList;
import java.util.List;

public class Person {
    private List<String> madeMovies = new ArrayList<String>();
    private int movieCount = 0;
    enum PersonType{Animator, Actor};
    private PersonType personType;

    public Person(String movie, PersonType personType) {
        this.madeMovies.add(movie);
        this.personType = personType;
        this.movieCount = movieCount+1;
    }
    public Person(PersonType personType) {
        this.personType = personType;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }
    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
    public void addMovieToPerson(String movieName) {
        this.madeMovies.add(movieName);
        this.movieCount = movieCount + 1;
    }

    public List<String> getMadeMovies() {
        return this.madeMovies;
    }
    public int getMovieCount() {
        return movieCount;
    }
    
}
