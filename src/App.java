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
        Movie A;
        Person P;
        while(flag ==true){
        System.out.println("1: add 2: edit 3: hodnoceni 4: ulozit do souboru 5: nacist ze souboru 6: smazat film 7: vypsat vsechny filmy 8: vyhledat film 9: konec");
        ans = sc.nextInt();
        switch(ans){
            case 1:
                System.out.println("Zadejte typ filmu hrany/animovany");
                String type = sc.nextLine();
                if (type =="hrany"){
                    LiveActionMovie A = new LiveActionMovie();
                    }
                else if (type == "animovany"){
                     AnimatedMovie A = new AnimatedMovie();
                    }
                else{
                    System.out.println("tento typ neexistuje");
                    }
                A.addMovie();
                break;
            case 2:
                A.editMovie();
                break;
            case 8:
                System.out.println("Zadejte jmeno filmu pro vypsani: ");
                sc.nextLine();
                String name = sc.nextLine();
                A.printMovie(name);
                break;
            case 3:
                A.addScore();
                break;
            case 4:
                A.saveMovie();
                break;
            case 5:
                A.loadMovie();
                break;
            case 6:
                A.deleteMovie();
                break;
            case 7:
                A.printAllMovies();
                break;
            case 9:
                flag = false;
                break;
            case 10:
                
            }
       
        }
    }
}
