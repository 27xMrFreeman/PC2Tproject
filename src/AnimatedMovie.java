import java.util.Scanner;
import java.util.HashMap;
import java.util.List;




public class AnimatedMovie extends Movie{
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

    void editMovie(Person P, String name, HashMap<String, Movie> Movies) {
        String new_name = name;
        boolean flag = true;
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
}
    
