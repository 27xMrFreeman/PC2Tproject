import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;
import java.io.*;
import java.io.Serializable;

public class AnimatedMovie extends Movie {
    int suggestedAge;
    String animators [];
    Scanner sc = new Scanner(System.in);

    public AnimatedMovie(String name, String director, String animators[], String scoreComment, int releaseDate, int score, int suggestedAge){
        super(name, director, scoreComment, releaseDate, score);
        this.suggestedAge = suggestedAge;
        this.animators = animators;
    }
    public AnimatedMovie(){
        this.name = "";
        this.director = "";
        this.scoreComment = "";
        this.releaseDate = 0;
        this.score = 0;
        //this.animators= animators;
    }

    public void setSuggestedAge(int suggestedAge) {
        this.suggestedAge = suggestedAge;
    }
    public void setAnimators(String [] animators){
        this.animators = animators;
    }
    public int getSuggestedAge() {
        return suggestedAge;
    }
    public String getComment() {
        return scoreComment;
    }
    public String[] getAnimatorsOrActors() {
        return animators;
    }
    void addMovie() {
        int i = 0;
        System.out.println("Zadejte jmeno filmu");
            name = sc.nextLine();
        System.out.println("Zadejte jmeno rezisera");
            director = sc.nextLine();
        System.out.println("Zadejte rok vydani");
            releaseDate = sc.nextInt();
            sc.nextLine();
        System.out.println("Zadejte seznam animatoru, oddelene carkou a mezerou"); 
            String buffer = sc.nextLine();
            animators = buffer.split(", ");
            System.out.println(Arrays.toString(animators));
        System.out.println("Zadejte doporuceny vek ");
            suggestedAge = sc.nextInt();
        System.out.println("Zadejte bodove hodnoceni (1-10) ");
            score = sc.nextInt();
            sc.nextLine();
        System.out.println("Prejete si pridat komentar k hodnoceni? (y/n) ");
        String ans = sc.nextLine();
        switch(ans) { 
            case "y":
                System.out.println("Napiste komentar");
                scoreComment = sc.nextLine();
                break;
            case "n":
                break;
        }
        Movies.put(name, new AnimatedMovie(name, director, animators, scoreComment, releaseDate, score, suggestedAge));
    }

    void editMovie() {
        System.out.println("Zadejte jmeno filmu na upravu: ");
        String name = sc.nextLine();
        String new_name = name;
        boolean flag = true;
        int i = 0;
        while (flag == true) {
        System.out.println("Zadejte co si prejete upravit:  1: Nazev 2: Reziser 3: Rok vydani 4: Seznam animatoru 5: Doporuceny vek 6: Konec editace");
        int ans;
        //sc.next();
        switch (ans = sc.nextInt()) {
            case 1:
                System.out.println("Zadejte nove jmeno filmu: ");
                new_name = sc.next();
                Movies.remove(name);
                Movies.put(new_name, new AnimatedMovie(new_name, director, animators, scoreComment, releaseDate, score, suggestedAge));
                break;
            case 2:
                System.out.println("Zadejte nove jmeno rezisera: ");
                director = sc.next();
                Movies.get(new_name).setDirector(director);
                break;
            case 3:
                System.out.println("Zadejte novy rok vydani: ");
                releaseDate = sc.nextInt();
                Movies.get(new_name).setReleaseDate(releaseDate);
                break;
            case 4:
                System.out.println("Zadejte novy seznam animatoru: ");
                String buffer = sc.nextLine();
                animators = buffer.split(", ");
                (Movies.get(new_name)).setAnimators(animators);
                break;
            case 5:
                System.out.println("Zadejte novy doporuceny vek: ");
                suggestedAge = sc.nextInt();
                (Movies.get(new_name)).setSuggestedAge(suggestedAge);
                break;
            case 6:
                flag = false;
                break;
        }
        }
    }
    void deleteMovie () {
        System.out.println("Zadejte jmeno filmu ktery chcete smazat: ");
    }
    void printMovie(){
        System.out.println("Zadejte jmeno filmu pro vypsani: ");
        String name = sc.nextLine();
        System.out.println("Jmeno: " + Movies.get(name).getName() + "\nReziser: " +  Movies.get(name).getDirector());
        System.out.println("Vydano: " + Movies.get(name).getReleaseDate() + "\nAnimatori: " + Arrays.toString(((AnimatedMovie)Movies.get(name)).getAnimatorsOrActors())); // mozna se zbavit loopem hranatych zavorek
        System.out.println("Doporuceny vek: " + ((AnimatedMovie) Movies.get(name)).getSuggestedAge() + "\nHodnoceni: " + Movies.get(name).getScore());
        System.out.println("Komentar: " + Movies.get(name).getScoreComment());
    }
    void saveMovie(AnimatedMovie M){
        System.out.println("Zadejte jmeno filmu pro vypsani: ");
        String name = sc.next();
        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(M);
        fos.close();
    }
}
