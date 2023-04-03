import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;
import java.io.*;
import java.io.Serializable;
import java.util.Set;
public class LiveActionMovie extends Movie{
    String actors [];
    Scanner sc = new Scanner(System.in);

    public LiveActionMovie(String name, String director, String scoreComment, int releaseDate, int score, int suggestedAge){
        super(name, director, scoreComment, releaseDate, score);
    }
   
}
