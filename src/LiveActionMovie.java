import java.util.Scanner;
import java.util.Arrays;
public class LiveActionMovie extends Movie{
    String actors [];
    Scanner sc = new Scanner(System.in);

    public LiveActionMovie(String name, String director, String scoreComment, int releaseDate, int score, int suggestedAge){
        super(name, director, scoreComment, releaseDate, score);
    }
   
}
