import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Person {
    HashMap<String, Person> personMap= new HashMap<>();
    private List<String> madeMovies = new ArrayList<String>();
    private int movieCount = 0;
    public enum PersonType{Animator, Actor};
    private PersonType personType;
    private String personName;

    public Person(String personName, String movie, PersonType personType) {
        this.personName = personName;
        this.madeMovies.add(movie);
        this.personType = personType;
        this.movieCount = movieCount+1;
    }
    public Person(PersonType personType) {
        this.personType = personType;
    }
    public Person() {}
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

    public void getMadeMovies() {
        System.out.println("Zadejte jmeno herce/animatora: ");
        Scanner sc = new Scanner(System.in);
        String personName = sc.nextLine();
        this.personMap.get(personName).madeMovies.forEach(System.out::println);
        //sc.close();
    }
    public int getMovieCount() {
        return movieCount;
    }

    public void addPerson(String personName, String movie, PersonType personType) {
        this.personMap.put(personName, new Person(personName, movie, personType));
    }
}
