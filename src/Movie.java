import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.List;;
public abstract class Movie implements Serializable {
    String name, director;
    List<String> scoreComment = new ArrayList<String>();
    int releaseDate;
    List<Integer> score = new ArrayList<Integer>();
    HashMap<String, Movie> Movies = new HashMap<>();

    public Movie(){
        this.name = "";
        this.director = "";
        this.releaseDate = 0;

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
    //abstract String[] getAnimatorsOrActors();
    //abstract int getSuggestedAge();
    
    abstract void addMovie();
    abstract void editMovie();
    abstract void deleteMovie();
    abstract void printMovie();
    abstract void printAllMovies();
    abstract void saveMovie();
    abstract void printAnimatorOrActor();
    abstract void setAnimators(String [] animators);
    abstract void setSuggestedAge(int suggestedAge);

    public void sortScore(String nameSort) {
        int listSize = Movies.get(nameSort).getScoreList().size();
        for (int i = 0; i < listSize; i++) {
            for (int j = 0; j < listSize-1; j++) {
                int scoreBuffer0 = Movies.get(name).getScore(j);
                int scoreBuffer1 = Movies.get(name).getScore(j+1);
                String scoreCommentBuffer0 = Movies.get(name).getScoreComment(j);
                if(scoreBuffer0 < scoreBuffer1) {
                    Movies.get(name).getScoreList().set(j, scoreBuffer1);
                    Movies.get(name).getScoreList().set(j+1, scoreBuffer0);
                    Movies.get(name).getScoreCommentList().set(j, getScoreComment(j));
                    Movies.get(name).getScoreCommentList().set(j+1, scoreCommentBuffer0);
                }

            }
        }

    }
    
}
    




