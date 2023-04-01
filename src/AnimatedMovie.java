import java.util.Scanner;

public class AnimatedMovie extends Movie {
    int suggestedAge;
    Scanner sc = new Scanner(System.in);

    public AnimatedMovie(String name, String director, String scoreComment, int releaseDate, int score, int suggestedAge){
        super(name, director, scoreComment, releaseDate, score);
        this.suggestedAge = suggestedAge;
    }

    public void setSuggestedAge(int suggestedAge) {
        this.suggestedAge = suggestedAge;
    }

    void addMovie() {
        System.out.println("Zadejte jmeno filmu \n");
        name = sc.nextLine();
        System.out.println("Zadejte jmeno rezisera \n");
        director = sc.nextLine();
        System.out.println("Zadejte rok vydani \n");
        releaseDate = sc.nextInt();
        // System.out.println("Zadejte seznam animatoru \n");
        
        System.out.println("Zadejte doporuceny vek \n");
        suggestedAge = sc.nextInt();
        System.out.println("Zadejte bodove hodnoceni (1-10) \n");
        score = sc.nextInt();
        System.out.println("Prejete si pridat komentar k hodnoceni? (y/n) \n");
        String i = sc.nextLine();
        switch(i) { 
            case "y":
                scoreComment = sc.next();
            case "n":
                break;
        }
        AnimatedMovie anim = new AnimatedMovie(name, director, scoreComment, releaseDate, score, suggestedAge);
    }

    void editMovie() {
        System.out.println("Zadejte jmeno filmu na upravu: \n");
        String name = sc.nextLine();
        boolean flag = true;
        while (flag == true) {
        System.out.println("Zadejte co si prejete upravit: \n 1: Nazev\n 2: Reziser\n 3: Rok vydani\n 4: Seznam animatoru\n 5: Doporuceny vek\n 6: Konec editace\n");
        int i = sc.nextInt();
        switch (i) {
            case 1:
                System.out.println("Zadejte nove jmeno filmu: \n");
                name = sc.nextLine();
                setName(name);
            case 2:
                System.out.println("Zadejte nove jmeno rezisera: \n");
                director = sc.nextLine();
                setDirector(director);
            case 3:
                System.out.println("Zadejte novy rok vydani: \n");
                releaseDate = sc.nextInt();
                setReleaseDate(releaseDate);
            case 4:
                // System.out.println("Zadejte novy seznam animatoru: \n");
                // name = sc.nextLine();
                // setName(name);
            case 5:
                System.out.println("Zadejte nove jmeno: \n");
                suggestedAge = sc.nextInt();
                setSuggestedAge(suggestedAge);
            case 6:
                flag = false;
        }
        }
    }

    void deleteMovie () {
        System.out.println("Zadejte jmeno filmu ktery chcete smazat: \n");
    }
}
