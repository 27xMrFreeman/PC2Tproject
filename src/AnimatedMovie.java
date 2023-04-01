import java.util.Scanner;

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
        //this.animators;
    }

    public void setSuggestedAge(int suggestedAge) {
        this.suggestedAge = suggestedAge;
    }
    public void setAnimators(String [] animators){
        this.animators = animators;
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
                buffer[i] = sc.nextLine();
                i++;
            }
            String[] animators = new String [i];
            for (int n=0; n<i; n++){
                animators[n] = buffer[n];
            }

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
        //AnimatedMovie anim = new AnimatedMovie(name, director, animators, scoreComment, releaseDate, score, suggestedAge);
        Movies.put(name, new AnimatedMovie(name, director, animators, scoreComment, releaseDate, score, suggestedAge));
    }

    void editMovie() {
        System.out.println("Zadejte jmeno filmu na upravu: \n");
        String name = sc.nextLine();
        boolean flag = true;
        while (flag == true) {
        System.out.println("Zadejte co si prejete upravit: \n 1: Nazev\n 2: Reziser\n 3: Rok vydani\n 4: Seznam animatoru\n 5: Doporuceny vek\n 6: Konec editace\n");
        int ans = sc.nextInt();
        //sc.next();
        switch (ans) {
            case 1:
                System.out.println("Zadejte nove jmeno filmu: \n");
                String new_name = sc.nextLine();
                Movies.get(name).setName(new_name);
                break;
            case 2:
                System.out.println("Zadejte nove jmeno rezisera: \n");
                director = sc.nextLine();
                Movies.get(name).setDirector(director);
                break;
            case 3:
                System.out.println("Zadejte novy rok vydani: \n");
                releaseDate = sc.nextInt();
                Movies.get(name).setReleaseDate(releaseDate);
                break;
            case 4:
                int i = 0;
                String buffer [] = new String [100];
                    while (sc.hasNext()){
                        buffer[i] = sc.nextLine();
                        i++;
                    }
                String[] animators = new String [i];
                    for (int n=0; n<i; n++){
                        animators[n] = buffer[n];
                    }
                ((AnimatedMovie) Movies.get(name)).setAnimators(animators);
                break;
            case 5:
                System.out.println("Zadejte nove jmeno: \n");
                suggestedAge = sc.nextInt();
                ((AnimatedMovie) Movies.get(name)).setSuggestedAge(suggestedAge);
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
        System.out.println(this.name + this.director + this.releaseDate);
    }
}
