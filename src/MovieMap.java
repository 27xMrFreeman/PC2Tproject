import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class MovieMap extends Person {
    private static final long serialVersionUID = 6529685098267757690L;
    public HashMap<String, Movie> Movies = new HashMap<>();

    public void addMovie(Movie Mo) {
        Movie test = Movies.put(Mo.getName(), Mo);
    }

    public void editMovie(Person P) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte jmeno filmu na upravu: ");
        String name = sc.nextLine();
        String className = Movies.get(name).getClass().getName();
        switch (className) {
            case "LiveActionMovie":
                Movie LA = new LiveActionMovie();
                LA = Movies.get(name);
                LA.editMovie(P, name, Movies);
                break;
            case "AnimatedMovie":
                Movie AN = new AnimatedMovie();
                AN = Movies.get(name);
                AN.editMovie(P, name, Movies);
                break;
            default: System.out.println("Unexpected class type");
            }
    }


    public void printMovie(String name) {
        String className = Movies.get(name).getClass().getName();
        switch (className) {
            case "AnimatedMovie":
                ((AnimatedMovie) Movies.get(name)).sortScore();
                System.out.println("Jmeno: " + ((AnimatedMovie) Movies.get(name)).getName() + "\nReziser: " +  ((AnimatedMovie) Movies.get(name)).getDirector());
                System.out.println("Vydano: " + ((AnimatedMovie) Movies.get(name)).getReleaseDate() + "\nAnimatori: " + Arrays.toString(((AnimatedMovie)Movies.get(name)).getAnimatorsOrActors()));
                System.out.println("Doporuceny vek: " + ((AnimatedMovie) Movies.get(name)).getSuggestedAge() + "\n");
                System.out.println("Komentar: " + ((AnimatedMovie) Movies.get(name)).getScoreCommentList());
                break;
            case "LiveActionMovie":
                System.out.println("Jmeno: " + ((LiveActionMovie) Movies.get(name)).getName() + "\nReziser: " +  ((LiveActionMovie) Movies.get(name)).getDirector());
                System.out.println("Vydano: " + ((LiveActionMovie) Movies.get(name)).getReleaseDate() + "\nHerci: " + Arrays.toString(((LiveActionMovie)Movies.get(name)).getAnimatorsOrActors()));
                System.out.println("Hodnoceni: " + ((LiveActionMovie) Movies.get(name)).getScoreList());
                System.out.println("Komentar: " + ((LiveActionMovie) Movies.get(name)).getScoreCommentList());
                break;
            default: System.out.println("Unexpected type in Movies hashmap");
        }
    }

    public void printAllMovies(){
        Set <String> names = Movies.keySet();
		    for(String name:names) {
                printMovie(name);
                System.out.println("\n");
        }
    }

    public void deleteMovie () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte jmeno filmu ktery chcete smazat: ");
        String name = sc.nextLine();
        Movies.remove(name);
    }

    public void addScore() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Jmeno filmu kam chcete pridat hodnoceni: ");
        String name = sc.nextLine();
        String className = Movies.get(name).getClass().getName();
        switch (className) {
            case "AnimatedMovie":
                System.out.println("Zadejte bodove hodnoceni 1-10: ");
                int ans = sc.nextInt();
                if(10 < ans || ans < 1) {System.out.println("Invalid rating"); break;}
                ((AnimatedMovie) Movies.get(name)).score.add(ans);
                sc.nextLine();
                System.out.println("Prejete si zadat komentar?: y/n");
                switch(sc.nextLine()){
                    case "y":
                        System.out.println("Zadejte komentar: ");
                        ((AnimatedMovie) Movies.get(name)).scoreComment.add(sc.nextLine());
                        break;
                    case "n":
                        ((AnimatedMovie) Movies.get(name)).scoreComment.add("-");
                        break;
                }
                break;
            case "LiveActionMovie":
                System.out.println("Zadejte bodove hodnoceni 1-5: ");
                ans = sc.nextInt();
                if(5 < ans || ans < 1) {System.out.println("Invalid rating"); break;}
                Movies.get(name).score.add(ans);
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
                break;
            default: System.out.println("Unexpected type in Movies hashmap");
        }
    }

    void saveMovie(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte jmeno filmu pro ulozeni: ");
        String name = sc.nextLine();
        try {
            FileOutputStream fos = new FileOutputStream(name + ".data");
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(( Movies.get(name)));
            fos.close();
            }catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadMovie(Person P) throws IOException, ClassNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte jmeno filmu pro nacteni: ");
        String name = sc.nextLine();
        try{
            FileInputStream fis =new FileInputStream(name + ".data");
            ObjectInputStream ois =new ObjectInputStream(fis);
            AnimatedMovie M = (AnimatedMovie)ois.readObject();
            fis.close();
            Movies.put(name, M);
            String [] animators = (((AnimatedMovie)Movies.get(name)).getAnimatorsOrActors());
            int size = (((AnimatedMovie)Movies.get(name)).getAnimatorsOrActors()).length;
            for (int i =0; i<size;i++){
                if (!P.personMap.containsKey(animators[i])){
                    P.addPerson(animators[i], name, Person.PersonType.Animator);
                }
                else{
                    P.addMovieToPerson(name,animators[i]);
                }
            }
            
        }
        catch (ClassCastException e){
                FileInputStream fas =new FileInputStream(name + ".data");
                ObjectInputStream oas =new ObjectInputStream(fas);
                LiveActionMovie N = (LiveActionMovie)oas.readObject();
                fas.close();
                Movies.put(name, N);
                String [] actors = (((LiveActionMovie)Movies.get(name)).getAnimatorsOrActors());
            int size = (((LiveActionMovie)Movies.get(name)).getAnimatorsOrActors()).length;
                for (int i =0; i<size;i++){
                    if (!P.personMap.containsKey(actors[i])){
                        P.addPerson(actors[i], name, Person.PersonType.Actor);
                    }
                    else{
                        P.addMovieToPerson(name,actors[i]);
                    }
                }
        }
        
    }
}

