import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
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
    public void setActors(String [] actors) {
        this.actors = actors;
    }
    public String[] getAnimatorsOrActors() {
        return actors;
    }
    
    
    LiveActionMovie createMovie(Person P) {
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
        for (String personName : actors) {
            if(P.personMap.containsKey(personName)){
                P.personMap.get(personName).addMovieToPerson(name);
            }
            else {
            P.personMap.put(personName, new Person(personName, name, Person.PersonType.Actor));
            }
        }
        LiveActionMovie LA = new LiveActionMovie(name, director, actors, releaseDate);
        return LA;
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
                Movies.put(new_name, new LiveActionMovie(new_name, director, actors, releaseDate));
                break;
            case 2:
                System.out.println("Zadejte nove jmeno rezisera: ");
                director = sc.next();
                ((LiveActionMovie) Movies.get(new_name)).setDirector(director);
                break;
            case 3:
                System.out.println("Zadejte novy rok vydani: ");
                releaseDate = sc.nextInt();
                ((LiveActionMovie) Movies.get(new_name)).setReleaseDate(releaseDate);
                break;
            case 4:
                sc.nextLine();
                System.out.println("Zadejte novy seznam hercu: ");
                String buffer = sc.nextLine();
                actors = buffer.split(", ");
                ((LiveActionMovie) Movies.get(new_name)).setActors(actors);
                break;
            case 6:
                flag = false;
                sc.nextLine();
                break;
        }
        }
    }

// **IMPLEMENTOVANO V MOVIEMAP**
    // void printMovie(String name, HashMap Movies){
    //     // try catch chybi - NullPointerException
    //     System.out.println("Jmeno: " + Movies.get(name).getName() + "\nReziser: " +  Movies.get(name).getDirector());
    //     System.out.println("Vydano: " + Movies.get(name).getReleaseDate() + "\nHerci: " + Arrays.toString(((LiveActionMovie)Movies.get(name)).getAnimatorsOrActors()));
    //     System.out.println("Hodnoceni: " + Movies.get(name).getScoreList());
    //     System.out.println("Komentar: " + Movies.get(name).getScoreCommentList());
    // }
// **IMPLEMENTOVANO V MOVIEMAP**

    void saveMovie(){ //CHYBI DOIMPLEMENTOVAT
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

    public void loadMovie() throws IOException, ClassNotFoundException{ //CHYBI DOIMPLEMENTOVAT
        System.out.println("Zadejte jmeno filmu pro nacteni: ");
        String name = sc.nextLine();
        FileInputStream fis =new FileInputStream(name + ".data");
        ObjectInputStream ois =new ObjectInputStream(fis);
        LiveActionMovie M = (LiveActionMovie)ois.readObject();
        fis.close();
        Movies.put(name, M);
    }
    void printAnimatorOrActor(){  //CHYBI DOIMPLEMENTOVAT
        System.out.println("Zadejte jmeno animatora pro vypsani: ");
        String name = sc.nextLine(); 
    }
// **IMPLEMENTOVANO V MOVIEMAP**
    // public void addScore() {
        
    //     System.out.println("Zadejte bodove hodnoceni: ");
    //     Movies.get(name).score.add(sc.nextInt());
    //     sc.nextLine();
    //     System.out.println("Prejete si zadat komentar?: y/n");
    //     switch(sc.nextLine()){
    //         case "y":
    //             System.out.println("Zadejte komentar: ");
    //             Movies.get(name).scoreComment.add(sc.nextLine());
    //             break;
    //         case "n":
    //             Movies.get(name).scoreComment.add("-");
    //             break;
    //     }
    // }
}
