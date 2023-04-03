import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.io.Serializable;
import java.util.Set;
import java.util.Map;
public abstract class Movie implements Serializable {
    String name, director, scoreComment;
    int releaseDate, score;
    HashMap<String, Movie> Movies = new HashMap<>();

    public Movie(){
        this.name = "";
        this.director = "";
        this.scoreComment = "";
        this.releaseDate = 0;
        this.score = 0;

    }
    public Movie(String name, String director, String scoreComment, int releaseDate, int score) {
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

    public void setScore(int score) {
        this.score = score;
    }

    public void setScoreComment(String scoreComment) {
        this.scoreComment = scoreComment;
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
    public int getScore() {
        return score;
    }
    public String getScoreComment() {
        return scoreComment;
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
}



