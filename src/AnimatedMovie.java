import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.lang.*;
import java.io.*;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;




public class AnimatedMovie extends Movie implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    int suggestedAge;
    String animators [];
    transient Scanner sc = new Scanner(System.in);

    public AnimatedMovie(String name, String director, String animators[], int releaseDate, int suggestedAge){
        super(name, director, releaseDate);
        this.suggestedAge = suggestedAge;
        this.animators = animators;
    }
    public AnimatedMovie(){
        this.name = "";
        this.director = "";
        this.releaseDate = 0;
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
    public List<Integer> getScoreList() {
        return score;
    }
    public List<String> getCommentList() {
        return scoreComment;
    }
    public String[] getAnimatorsOrActors() {
        return animators;
    }
    AnimatedMovie createMovie(Person P) {
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
            System.out.println(Arrays.toString(animators)); //pouze pro debugging, smazat před odevzdáním
        System.out.println("Zadejte doporuceny vek ");
            suggestedAge = sc.nextInt();
            sc.nextLine();
        for (String personName : animators) {
            if(P.personMap.containsKey(personName)){
                P.addMovieToPerson(name,personName);
            }
            else {
            P.personMap.put(personName, new Person(personName, name, Person.PersonType.Animator));
            }
        }
        AnimatedMovie AN = new AnimatedMovie(name, director, animators, releaseDate, suggestedAge);
        return AN;
    }

    void editMovie(Person P, String name, HashMap Movies) {
        // System.out.println("Zadejte jmeno filmu na upravu: ");
        // String name = sc.nextLine();
        String new_name = name;

        boolean flag = true;
        int i = 0;
        while (flag == true) {
        System.out.println("Zadejte co si prejete upravit:  1: Nazev 2: Reziser 3: Rok vydani 4: Seznam animatoru 5: Doporuceny vek 6: Konec editace");
        int ans;
        switch (ans = sc.nextInt()) {
            case 1:
                System.out.println("Zadejte nove jmeno filmu: ");
                new_name = sc.next();
                Movies.remove(name);
                Movies.put(new_name, new AnimatedMovie(new_name, director, animators, releaseDate, suggestedAge));
                break;
            case 2:
                System.out.println("Zadejte nove jmeno rezisera: ");
                director = sc.next();
                ((AnimatedMovie) Movies.get(new_name)).setDirector(director);
                break;
            case 3:
                System.out.println("Zadejte novy rok vydani: ");
                releaseDate = sc.nextInt();
                ((AnimatedMovie) Movies.get(new_name)).setReleaseDate(releaseDate);
                break;
            case 4:
                System.out.println("Zadejte novy seznam animatoru: ");
                sc.nextLine();
                String buffer = sc.nextLine();
                animators = buffer.split(", ");
                ((AnimatedMovie) Movies.get(new_name)).setAnimators(animators);
                for (String personName : animators) {
                    if(P.personMap.containsKey(personName)){
                        P.personMap.get(personName).addMovieToPerson(new_name,personName);
                    }
                    else {
                    P.personMap.put(personName, new Person(personName, new_name, Person.PersonType.Animator));
                    }
                }
                break;
            case 5:
                System.out.println("Zadejte novy doporuceny vek: ");
                suggestedAge = sc.nextInt();
                ((AnimatedMovie) Movies.get(new_name)).setSuggestedAge(suggestedAge);
                break;
            case 6:
                flag = false;
                sc.nextLine();
                break;
        }
        }
    }
// **IMPLEMENTOVANO V MOVIEMAP**
    // void printMovie(String name){
    //     Movies.get(name).sortScore();
    //     // try catch chybi - NullPointerException
    //     System.out.println("Jmeno: " + Movies.get(name).getName() + "\nReziser: " +  Movies.get(name).getDirector());
    //     System.out.println("Vydano: " + Movies.get(name).getReleaseDate() + "\nAnimatori: " + Arrays.toString(((AnimatedMovie)Movies.get(name)).getAnimatorsOrActors()));
    //     System.out.println("Doporuceny vek: " + ((AnimatedMovie) Movies.get(name)).getSuggestedAge() + "\nHodnoceni: " + Movies.get(name).getScoreList());
    //     System.out.println("Komentar: " + Movies.get(name).getScoreCommentList());
    // }
 

    // void loadMovie(HashMap Movies) throws IOException, ClassNotFoundException{
    //     System.out.println("Zadejte jmeno filmu pro nacteni: ");
    //     String name = sc.nextLine();
    //     FileInputStream fis =new FileInputStream(name + ".data");
    //     ObjectInputStream ois =new ObjectInputStream(fis);
    //     AnimatedMovie M = (AnimatedMovie)ois.readObject();
    //     fis.close();
    //     Movies.put(name, M);
    // }

    void printAnimatorOrActor(){
        System.out.println("Zadejte jmeno animatora pro vypsani: ");
        String name = sc.nextLine(); 
    }

// **IMPLEMENTOVANO V MOVIEMAP**
    // public void addScore(HashMap Movies) {
    //     System.out.println("Jmeno filmu kam chcete pridat hodnoceni: ");
    //     String name = sc.nextLine();
    //     System.out.println("Zadejte bodove hodnoceni 1-10: ");
    //     ((AnimatedMovie) Movies.get(name)).score.add(sc.nextInt());
    //     sc.nextLine();
    //     System.out.println("Prejete si zadat komentar?: y/n");
    //     switch(sc.nextLine()){
    //         case "y":
    //             System.out.println("Zadejte komentar: ");
    //             ((AnimatedMovie) Movies.get(name)).scoreComment.add(sc.nextLine());
    //             break;
    //         case "n":
    //             ((AnimatedMovie) Movies.get(name)).scoreComment.add("-");
    //             break;
    //     }
    // }
}
    
