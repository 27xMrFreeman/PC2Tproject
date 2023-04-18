import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
public class LiveActionMovie extends Movie{
    private static final long serialVersionUID = 6529685098267757690L;
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
                P.addMovieToPerson(name,personName);
            }
            else {
            P.personMap.put(personName, new Person(personName, name, Person.PersonType.Actor));
            }
        }
        LiveActionMovie LA = new LiveActionMovie(name, director, actors, releaseDate);
        return LA;
    }

    void editMovie(Person P, String name, HashMap<String, Movie> Movies) {
        String new_name = name;
        boolean flag = true;
        while (flag == true) {
        System.out.println("Zadejte co si prejete upravit:  1: Nazev 2: Reziser 3: Rok vydani 4: Seznam hercu 6: Konec editace");
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

}
