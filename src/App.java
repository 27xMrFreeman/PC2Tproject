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
        while(flag ==true){
        System.out.println("1: add 2: edit 3: hodnoceni 4: ulozit do souboru 5: nacist ze souboru 6: smazat film 7: vypsat vsechny filmy 8: vyhledat film 9: konec 10: vypsat filmy podle herce/animatora");
        ans = sc.nextInt(); 
        switch(ans){
            case 1:
                System.out.println("Zadejte typ filmu hrany/animovany");
                sc.nextLine();
                String type = sc.next();
                switch(type){
                    case "h":
                        try{M.addMovie(LA);} 
                        catch (NullPointerException npe) {
                        LA = new LiveActionMovie();
                        LA = LA.createMovie(P);
                        System.out.println("Vytvoreno nove A typu LiveActionMovie");
                        M.addMovie(LA);
                        }
                        break;
                    case "a":
                        try{M.addMovie(AN);} 
                        catch (NullPointerException npe) {
                        AN = new AnimatedMovie();
                        AN = AN.createMovie(P);
                        System.out.println("Vytvoreno nove A typu AnimatedMovie");
                        M.addMovie(AN);
                        }
                        break;
                    default: System.out.println("Invalid choice");
                }
                break;
            case 2:
                M.editMovie(P);
                break;
            case 8:
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
            case 3:
                //A.addScore();
                break;
            case 4:
                //M.saveMovie();
                break;
            case 5:
            // try {
            // String C = A.loadClass();
            // if(C == "AnimatedMovie"){
            //     System.out.println("1");
            //     A.loadMovie();
            //     A.printMovie("ad");
            // }
            // else{
                
            //     System.out.println("2");
            //     A.loadMovie();
            //     A.printMovie("fo");
                
            // }
            // }
            // catch (NullPointerException npe){
            //     try{
            //         System.out.println("3");
            //     A = new LiveActionMovie();
            //     A.loadMovie();
            //     ((LiveActionMovie)A).printMovie("fo");
                
            //     }
            //     catch (ClassCastException x) {
            //         System.out.println("4");
            //         A = new AnimatedMovie();
            //         A.loadMovie();
            //         System.out.println("4");
            //         ((AnimatedMovie)A).printMovie("ad");
            //         }
            // }

            //    finally{ break;}
            case 6:
                M.deleteMovie();
                break;
            case 7:
                M.printAllMovies();
                break;
            case 9:
                flag = false;
                break;
            case 10:
                P.getMadeMovies();
            }
       
        }
    }
}
