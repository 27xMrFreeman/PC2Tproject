import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.*;
public class dbConn{
    static String url = "jdbc:mysql://127.0.0.1:3306";
    static String username = "user";
    static String password = "password";
    static String query;
//ResultSet resultSet = statement.executeQuery(query); na pozdeji
    public static void saveMovieToDB(MovieMap M) {
    HashMap<String, Movie> Movies = M.Movies;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (Exception e) {
        e.printStackTrace();
    }
    try {
        int i = 0;
        query = "CREATE DATABASE IF NOT EXISTS test2";
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        query = "USE test2";
        stmt.execute(query);
        query = "CREATE TABLE IF NOT EXISTS testik (ID int NOT NULL, name varchar(255), director varchar(255), releaseDate int, suggestedAge int, score varchar(255), scoreComment varchar(1000), movieType varchar(255), PRIMARY KEY(ID))";
        stmt.execute(query);
        Set <String> keys = Movies.keySet();
        for (String key : keys) {
            Movie Mo = Movies.get(key);
            String name = Mo.getName();
            System.out.println(name);
            String director = Mo.getDirector();
            int releaseDate = Mo.getReleaseDate();
            List<Integer> score = Mo.getScoreList();
            int suggestedAge = 0;
            String movieType = null;
            try {
                suggestedAge = ((AnimatedMovie) Mo).getSuggestedAge();
                movieType = "A";
            }
            catch (Exception e) {
                suggestedAge = 0;
                movieType = "L";
            }
            String scoreStr = new String();
            String scoreCommStr = new String();
            for (int j = 0; j < score.size(); j++) {
                scoreStr += score.get(j) + ";"; 
            }
            List<String> scoreComment = Movies.get(key).getScoreCommentList();
            for (int j = 0; j < scoreComment.size(); j++) {
                scoreCommStr += scoreComment.get(j) + ";"; 
            }
            System.out.println(director);
            try {
                query = "SELECT ID FROM testik WHERE ID = (SELECT MAX(ID) FROM testik)";
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                i = rs.getInt("ID") +1;
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(i);
            }
            query = "INSERT INTO testik(ID, name, director, releaseDate, suggestedAge, score, scoreComment, movieType) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement prSt = conn.prepareStatement(query);
            prSt.setInt(1, i);
            prSt.setString(2, name);
            prSt.setString(3, director);
            prSt.setInt(4, releaseDate);
            prSt.setInt(5, suggestedAge);
            prSt.setString(6, scoreStr);
            prSt.setString(7, scoreCommStr);
            prSt.setString(8, movieType);
            prSt.executeUpdate();
            i++;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }  
    

    }

        
}
