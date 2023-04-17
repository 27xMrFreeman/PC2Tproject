import java.util.Scanner;
import java.io.Serializable;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

public class App implements Serializable {
    public static void main(String[] args) throws Exception {
        System.out.println();
        int ans;
        Boolean flag = true;
        Scanner sc = new Scanner(System.in);
        Person P = new Person();
        Movie LA = null;
        Movie AN = null;
        MovieMap M = new MovieMap();
        dbConn.loadMovieFromDB(M, P);
        dbConn.deleteDatabase();
        while(flag ==true){
        System.out.println("1: add 2: edit 3: hodnoceni 4: ulozit do souboru 5: nacist ze souboru 6: smazat film 7: vypsat vsechny filmy 8: vyhledat film 9: konec 10: vypsat filmy podle herce/animatora 11: vypsat herce/animatory s vice filmy");
        ans = sc.nextInt(); 
        switch(ans){
            case 1: //funguje
                System.out.println("Zadejte typ filmu hrany/animovany");
                sc.nextLine();
                String type = sc.next();
                switch(type){
                    case "h":
                        LA = new LiveActionMovie();
                        LA = LA.createMovie(P);
                        M.addMovie(LA);
                        break;
                    case "a":
                        AN = new AnimatedMovie();
                        AN = AN.createMovie(P);
                        M.addMovie(AN);
                        break;
                    default: System.out.println("Invalid choice");
                }
                break;
            case 2: //funguje
                M.editMovie(P);
                break;
            case 8: //funguje
                System.out.println("Zadejte jmeno filmu pro vypsani: ");
                sc.nextLine();
                String name = sc.nextLine();
                try{
                M.printMovie(name);
                }
                catch (NullPointerException npe){
                    System.out.println("Movie doesn't exist");
                }
                break;
            case 3: //funguje
                M.addScore();
                break;
            case 4: //funguje
                M.saveMovie();
                break;
            case 5: //funguje
                M.loadMovie(P);
                break;
            case 6: //funguje
                M.deleteMovie();
                break;
            case 7: //funguje
                M.printAllMovies();
                break;
            case 9:
                dbConn.saveMovieToDB(M); //funguje ale neukladam herce/anim
                flag = false;
                break;
            case 10: //funguje
                P.getMadeMovies();
                sc.nextLine();
                break;
            case 11:
                P.printMoreMovies();
                break;
            }
       
        }
    }
}
