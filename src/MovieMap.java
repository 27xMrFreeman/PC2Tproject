import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class MovieMap {
    public HashMap<String, Movie> Movies = new HashMap<>();

    public void addMovie(Movie M) {
        Movies.put(M.name, M);
    }

    public void editMovie(Person P) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte jmeno filmu na upravu: ");
        String name = sc.nextLine();
        String className = Movies.get(name).getClass().getName();
        boolean flag = true;
        int i = 0;
        while (flag == true) {
            switch (className) {
                case "LiveActionMovie":
                    Movie LA = new LiveActionMovie();
                    LA = Movies.get(name);
                    LA.editMovie(P, name, Movies);
                    break;
                case "AnimatedMovie":
                    Movie AN = new AnimatedMovie();
                    AN = Movies.get(name);
                    AN.editMovie(P, name, Movies);
                    break;
                default: System.out.println("Unexpected class type");
            }
        }
    }

    public void printMovie(String name) {
        String className = Movies.get(name).getClass().getName();
        switch (className) {
            case "AnimatedMovie":
                ((AnimatedMovie) Movies.get(name)).sortScore();
                System.out.println("Jmeno: " + ((AnimatedMovie) Movies.get(name)).getName() + "\nReziser: " +  ((AnimatedMovie) Movies.get(name)).getDirector());
                System.out.println("Vydano: " + ((AnimatedMovie) Movies.get(name)).getReleaseDate() + "\nAnimatori: " + Arrays.toString(((AnimatedMovie)Movies.get(name)).getAnimatorsOrActors()));
                System.out.println("Doporuceny vek: " + ((AnimatedMovie) Movies.get(name)).getSuggestedAge() + "\nHodnoceni: " + ((AnimatedMovie) Movies.get(name)).getScoreList());
                System.out.println("Komentar: " + ((AnimatedMovie) Movies.get(name)).getScoreCommentList());
                break;
            case "LiveActionMovie":
                System.out.println("Jmeno: " + ((LiveActionMovie) Movies.get(name)).getName() + "\nReziser: " +  ((LiveActionMovie) Movies.get(name)).getDirector());
                System.out.println("Vydano: " + ((LiveActionMovie) Movies.get(name)).getReleaseDate() + "\nHerci: " + Arrays.toString(((LiveActionMovie)Movies.get(name)).getAnimatorsOrActors()));
                System.out.println("Hodnoceni: " + ((LiveActionMovie) Movies.get(name)).getScoreList());
                System.out.println("Komentar: " + ((LiveActionMovie) Movies.get(name)).getScoreCommentList());
                break;
            default: System.out.println("Unexpected type in Movies hashmap");
        }
    }
    
    public void printAllMovies(){
        Set <String> names = Movies.keySet();
		    for(String name:names) {
                printMovie(name);
                System.out.println("\n");
        }
    }

    void deleteMovie () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte jmeno filmu ktery chcete smazat: ");
        String name = sc.nextLine();
        Movies.remove(name);
    }
}
