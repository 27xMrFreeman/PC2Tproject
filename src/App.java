import java.util.Scanner;
import java.io.Serializable;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

public class App implements Serializable {
    public static void main(String[] args) throws Exception {
        System.out.println();
        AnimatedMovie A = new AnimatedMovie();
        int ans;
        Boolean flag = true;
        Scanner sc = new Scanner(System.in);

        while(flag ==true){
        System.out.println("1: add 2: edit 3: pridat hodnoceni 4: print 5: ulozit do souboru 6: smazat film 7: vypsat vsechny filmy 8: vypsat podle animatora 9: konec");
        ans = sc.nextInt();
        
        switch(ans){
            case 1:
                A.addMovie();
                break;
            case 2:
                A.editMovie();
                break;
            case 3:
                A.addScore();
                break;
            case 4:
                A.printMovie();
                break;
            case 5:                 // nefunguje
                A.saveMovie();
                break;
            case 6:
                A.deleteMovie();
                break;
            case 7:
                A.printAllMovies();
                break;
            case 8:
                A.printAnimatorOrActor();
                break;
            case 9:
                flag = false;
                break;
            }
       
        }
    }
}
