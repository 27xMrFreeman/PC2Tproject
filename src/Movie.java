import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.List;;
public abstract class Movie implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    transient public Scanner sc = new Scanner(System.in);
    String name, director;
    List<String> scoreComment = new ArrayList<String>();
    int releaseDate;
    List<Integer> score = new ArrayList<Integer>();
    // **IMPLEMENTOVANO V MOVIEMAP**//HashMap<String, Movie> Movies = new HashMap<>();

    public Movie(){
        this.name = "";
        this.director = "";
        this.releaseDate = 0;
        this.scoreComment = scoreComment;
        this.score = score;


    }
    public Movie(String name, String director, int releaseDate) {
        this.name = name;
        this.director = director;
        this.scoreComment = scoreComment;
        this.releaseDate = releaseDate;
        this.score = score;

    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setScore(int score, int scoreIndex) {
        this.score.set(scoreIndex, score);
    }

    public void setScoreComment(String scoreComment, int scoreIndex) {
        this.scoreComment.set(scoreIndex, scoreComment);
    }

    public String getName(){
        return name;
    }
    public String getDirector() {
        return director;
    }
    public int getReleaseDate() {
        return releaseDate;
    }
    public List<Integer> getScoreList() {
        return score;
    }
    public int getScore(int j) {
        return score.get(j);
    }
    public List<String> getScoreCommentList() {
        return scoreComment;
    }
    public String getScoreComment(int j) {
        return scoreComment.get(j);
    }
    
    // **IMPLEMENTOVANO V MOVIEMAP**// abstract void addScore(HashMap Movies);
    abstract Movie createMovie(Person P);
    abstract void editMovie(Person P, String name, HashMap Movies);
    // **IMPLEMENTOVANO V MOVIEMAP**// abstract void printMovie(String name);
    abstract void saveMovie(HashMap Movies);
    abstract void loadMovie(HashMap Movies) throws IOException, ClassNotFoundException, InvalidClassException;
    abstract void printAnimatorOrActor();
// **IMPLEMENTOVANO V MOVIEMAP**
    // void deleteMovie (HashMap Movies) {
    //     System.out.println("Zadejte jmeno filmu ktery chcete smazat: ");
    //     String name = sc.nextLine();
    //     Movies.remove(name);
    // }
    // void printAllMovies(HashMap Movies){
    //     Set <String> names = Movies.keySet();
	// 	    for(String name:names) {
    //             printMovie(name);
    //             System.out.println("\n");
    //     }
    // }
// **IMPLEMENTOVANO V MOVIEMAP**
    public void sortScore() {
        int listSize = this.getScoreList().size();
        for (int i = 0; i < listSize; i++) {
            for (int j = 0; j < listSize-1; j++) {
                int scoreBuffer0 = this.getScore(j);
                int scoreBuffer1 = this.getScore(j+1);
                String scoreCommentBuffer0 = this.getScoreComment(j);
                if(scoreBuffer0 < scoreBuffer1) {
                    this.getScoreList().set(j, scoreBuffer1);
                    this.getScoreList().set(j+1, scoreBuffer0);
                    this.getScoreCommentList().set(j, getScoreComment(j+1));
                    this.getScoreCommentList().set(j+1, scoreCommentBuffer0);
                }

            }
        }
    }
    public String loadClass() throws IOException, ClassNotFoundException{
        System.out.println("Zadejte jmeno filmu pro nacteni: ");
        String name = sc.nextLine();
        FileInputStream fis =new FileInputStream(name + ".data");
        ObjectInputStream ois =new ObjectInputStream(fis);
        Movie M = (Movie)ois.readObject();
        fis.close();
        return M.getClass().getName();
        }
        
    }
    
    




