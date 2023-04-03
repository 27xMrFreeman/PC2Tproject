import java.util.Scanner;
import java.io.Serializable;

public class App implements Serializable {
    public static void main(String[] args) throws Exception {
        System.out.println();
        AnimatedMovie A = new AnimatedMovie();
        int ans;
        Boolean flag = true;
        Scanner sc = new Scanner(System.in);

        while(flag ==true){
        System.out.println("1: add, 2: edit, 3: print, 4: ulozit do souboru , 5: konec");
        ans = sc.nextInt();
        
        
        switch(ans){
            case 1:
                A.addMovie();
                break;
            case 2:
                A.editMovie();
                break;
            case 3:
                A.printMovie();
                break;
            case 4:                 // nefunguje
                A.saveMovie();
                break;
            case 5:
                flag = false;
                break;
            }
       
        }
    }
}
