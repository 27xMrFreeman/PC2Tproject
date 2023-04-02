public class App {
    public static void main(String[] args) throws Exception {
        System.out.println();
        AnimatedMovie A = new AnimatedMovie();

        A.addMovie();
        A.printMovie();
        A.editMovie();
        A.printMovie();
    }
}
