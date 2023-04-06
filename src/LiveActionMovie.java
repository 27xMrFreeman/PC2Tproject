import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;
import java.io.*;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
public class LiveActionMovie extends Movie implements Serializable{
    String actors [];
    transient Scanner sc = new Scanner(System.in);

    public LiveActionMovie(String name, String director, String [] actors, int releaseDate){
        super(name, director, releaseDate);
        this.actors = actors;
    }
    public LiveActionMovie(){
        this.name = "";
        this.director = "";
        this.releaseDate = 0;
    }
    public String[] getAnimatorsOrActors() {
        return actors;
    }
    void addMovie(Person P) {
        int i = 0;
        System.out.println("Zadejte jmeno filmu");
            name = sc.nextLine();
        System.out.println("Zadejte jmeno rezisera");
            director = sc.nextLine();
        System.out.println("Zadejte rok vydani");
            releaseDate = sc.nextInt();
            sc.nextLine();
        System.out.println("Zadejte seznam hercu, oddelene carkou a mezerou"); 
            String buffer = sc.nextLine();
            actors = buffer.split(", ");
            System.out.println(Arrays.toString(actors));
        Movies.put(name, new LiveActionMovie(name, director, actors, releaseDate));
        for (String personName : actors) {
            if(P.personMap.containsKey(personName)){
                P.personMap.get(personName).addMovieToPerson(name);
            }
            else {
            P.personMap.put(personName, new Person(personName, name, Person.PersonType.Actor));
            }
        }
    }

    void editMovie(Person P) {
        System.out.println("Zadejte jmeno filmu na upravu: ");
        String name = sc.nextLine();
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
                Movies.put(new_name, new LiveActionMovie(new_name, director, actors, releaseDate));
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
                actors = buffer.split(", ");
                ((AnimatedMovie) Movies.get(new_name)).setAnimators(actors);
                break;
            case 6:
                flag = false;
                sc.nextLine();
                break;
        }
        }
    }

    void printMovie(String name){
        // try catch chybi - NullPointerException
        System.out.println("Jmeno: " + Movies.get(name).getName() + "\nReziser: " +  Movies.get(name).getDirector());
        System.out.println("Vydano: " + Movies.get(name).getReleaseDate() + "\nHerci: " + Arrays.toString(((LiveActionMovie)Movies.get(name)).getAnimatorsOrActors()));
        System.out.println("Hodnoceni: " + Movies.get(name).getScoreList());
        System.out.println("Komentar: " + Movies.get(name).getScoreCommentList());
    }

    void saveMovie(){
        System.out.println("Zadejte jmeno filmu pro ulozeni: ");
        String name = sc.nextLine();
        try {
        FileOutputStream fos = new FileOutputStream(name+ ".data");
        ObjectOutputStream oos;
        oos = new ObjectOutputStream(fos);
        oos.writeObject(((LiveActionMovie)Movies.get(name)));
        fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMovie() throws IOException, ClassNotFoundException{
        System.out.println("Zadejte jmeno filmu pro nacteni: ");
        String name = sc.nextLine();
        FileInputStream fis =new FileInputStream(name + ".data");
        ObjectInputStream ois =new ObjectInputStream(fis);
        LiveActionMovie M = (LiveActionMovie)ois.readObject();
        fis.close();
        Movies.put(name, M);
    }
    void printAnimatorOrActor(){
        System.out.println("Zadejte jmeno animatora pro vypsani: ");
        String name = sc.nextLine(); 
    }
    public void addScore() {
        System.out.println("Jmeno filmu kam chcete pridat hodnoceni: 1-5 ");
        String name = sc.nextLine();
        System.out.println("Zadejte bodove hodnoceni: ");
        Movies.get(name).score.add(sc.nextInt());
        sc.nextLine();
        System.out.println("Prejete si zadat komentar?: y/n");
        switch(sc.nextLine()){
            case "y":
                System.out.println("Zadejte komentar: ");
                Movies.get(name).scoreComment.add(sc.nextLine());
                break;
            case "n":
                Movies.get(name).scoreComment.add("-");
                break;
        }
    }
}
