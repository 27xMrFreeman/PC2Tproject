public abstract class Movie {
    String name, director, scoreComment;
    int releaseDate, score;

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

    //chybi hashmap hercu / animatoru

    abstract void addMovie();
    abstract void editMovie();
    abstract void deleteMovie();
}



