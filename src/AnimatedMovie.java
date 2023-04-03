import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;
import java.io.*;
import java.io.Serializable;

public class AnimatedMovie extends Movie implements serializable {
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
    public String[] getAnimators() {
        return animators;
    }
    public int getSuggestedAge() {
        return suggestedAge;
    }

    void addMovie() {
        int i = 0;
        String buffer [] = new String [100];
        System.out.println("Zadejte jmeno filmu \n");
        name = sc.nextLine();
        System.out.println("Zadejte jmeno rezisera \n");
        director = sc.nextLine();
        System.out.println("Zadejte rok vydani \n");
        releaseDate = sc.nextInt();
        System.out.println("Zadejte seznam animatoru, stop pro konec\n"); 

         
             while (!sc.hasNext("stop")){           //Lidl while loop na debug
                String animator = sc.next();
                buffer[i] = animator;
                i++;
            }
           animators = new String[i];
            System.arraycopy(buffer,0,animators,0,i);
            System.out.println(i);                        // jenom pro debug
            System.out.println(Arrays.toString(animators));  


        
        sc.next();
        System.out.println("Zadejte doporuceny vek \n");
        suggestedAge = sc.nextInt();
        System.out.println("Zadejte bodove hodnoceni (1-10) \n");
        score = sc.nextInt();
        System.out.println("Prejete si pridat komentar k hodnoceni? (y/n) \n");
        sc.next();
        String ans = sc.nextLine();
        switch(ans) { 
            case "y":
                scoreComment = sc.nextLine();
                break;
            case "n":
                break;
        }
        Movies.put(name, new AnimatedMovie(name, director, animators, scoreComment, releaseDate, score, suggestedAge));
    }

    void editMovie() {
        System.out.println("Zadejte jmeno filmu na upravu: \n");
        String name = sc.nextLine();
        String new_name = name;
        boolean flag = true;
        int i = 0;
        String buffer [] = new String [100];
        while (flag == true) {
        System.out.println("Zadejte co si prejete upravit: \n 1: Nazev\n 2: Reziser\n 3: Rok vydani\n 4: Seznam animatoru\n 5: Doporuceny vek\n 6: Konec editace\n");
        int ans;
        //sc.next();
        switch (ans = sc.nextInt()) {
            case 1:
                System.out.println("Zadejte nove jmeno filmu: \n");
                new_name = sc.next();
                Movies.remove(name);
                Movies.put(new_name, new AnimatedMovie(new_name, director, animators, scoreComment, releaseDate, score, suggestedAge));
                break;
            case 2:
                System.out.println("Zadejte nove jmeno rezisera: \n");
                director = sc.next();
                Movies.get(new_name).setDirector(director);
                break;
            case 3:
                System.out.println("Zadejte novy rok vydani: \n");
                releaseDate = sc.nextInt();
                Movies.get(new_name).setReleaseDate(releaseDate);
                break;
            case 4:
            System.out.println("Zadejte novy seznam animatoru: \n");
            while (!sc.hasNext("stop")){           //Lidl while loop na debug
                String animator = sc.next();
                buffer[i] = animator;
                i++;
            }
                animators = new String[i];
                System.arraycopy(buffer,0,animators,0,i);
                System.out.println(i);                        // jenom pro debug
                System.out.println(Arrays.toString(animators));  

                (Movies.get(new_name)).setAnimators(animators);
                break;
            case 5:
                System.out.println("Zadejte novy doporuceny vek: \n");
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
        System.out.println("Zadejte jmeno filmu ktery chcete smazat: \n");
    }
    void printMovie(){
        System.out.println("Zadejte jmeno filmu pro vypsani: \n");
        String key = sc.next();
        System.out.println(Movies.get(key).getName() + " " +  Movies.get(key).getDirector() + " " + Movies.get(key).getReleaseDate() + " " + Arrays.toString(animators) + " " + Movies.get(key).getSuggestedAge());
    }
    void saveMovie(){
        
    }
}
