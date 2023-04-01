import java.util.Scanner;
public class LiveActionMovie extends Movie{
    String actors [];
    Scanner sc = new Scanner(System.in);
    public LiveActionMovie(String name, String director, String scoreComment, int releaseDate, int score, int suggestedAge){
        super(name, director, scoreComment, releaseDate, score);
    }
    @Override
    void addMovie() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMovie'");
    }
    @Override
    void editMovie() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editMovie'");
    }
    @Override
    void deleteMovie() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMovie'");
    }
    @Override
    void printMovie() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printMovie'");
    }
    
    
}
