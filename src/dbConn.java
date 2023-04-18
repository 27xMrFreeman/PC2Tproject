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

    static void deleteDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try
        {
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        query = "DROP DATABASE test2";
        stmt.executeUpdate(query);
        }
        catch (SQLException e)
        {
            System.out.println("Unable to drop non-existant database");
        }
    }

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
                    if(score.get(j) == null) {scoreStr += -1 + ";";}
                    else scoreStr += score.get(j) + ";"; 
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
                int flag;
                String [] People = Mo.getAnimatorsOrActors();
                try {
                query = "SELECT * FROM testik WHERE name =" + name;
                stmt.execute(query);
                flag = 1;
                }
                catch (SQLException eq) {
                    flag = 0;
                }
                if (flag == 0) {
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
                    
                    
                    
                    PeopleExist(i, conn, People);
                    i++;
                }
                else {
                    query = "UPDATE testik SET director= ?, releaseDate= ?, suggestedAge= ?, score= ?, scoreComment= ? WHERE ID = ?";
                    PreparedStatement prSt = conn.prepareStatement(query);
                    prSt.setString(1, director);
                    prSt.setInt(2, releaseDate);
                    prSt.setInt(3, suggestedAge);
                    prSt.setString(4, scoreStr);
                    prSt.setString(5, scoreCommStr);
                    prSt.setInt(6, i);
                    prSt.executeUpdate();
                
                    PeopleExist(i, conn, People);
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }
        
    public static void PeopleExist(int i, Connection conn, String [] People ){
        int flag = 1;
        String name = null;
        try {
            Statement stmt = conn.createStatement();
            query = "CREATE TABLE IF NOT EXISTS people (ID int NOT NULL, name varchar(255), movies varchar(255), PRIMARY KEY(ID))";
            stmt.execute(query);
            for (int j = 0;j<People.length;j++){
                query = "SELECT name FROM people WHERE name = '" + People[j] + "'";
                System.out.println(People[j]);
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                try {rs.getString(1);}
                catch (SQLException e) {flag = 0;}
                
                // try { 
                //     stmt.execute(query);
                //     flag = 1;
                // } catch (SQLException eq){
                //     flag = 0;
                // }
                if (flag == 0){
                    System.out.println("rip0");
                    try {
                        query = "SELECT ID FROM people WHERE ID = (SELECT MAX(ID) FROM people)";
                        rs = stmt.executeQuery(query);
                        rs.next();
                        j = rs.getInt("ID") +1;
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println(i);
                    }
                    for (int k = j; k < People.length; k++) {
                        name = People[k];
                        query = "INSERT INTO people(ID, name, movies) VALUES (?,?,?)";
                        PreparedStatement prSt = conn.prepareStatement(query);
                        prSt.setInt(1, k);
                        prSt.setString(2, name);
                        prSt.setString(3, i + ";");
                        prSt.executeUpdate();
                    }
                } else {
                    System.out.println("rip1");
                    query = "SELECT ID FROM people WHERE name = '" + People[j] + "'";
                    rs = stmt.executeQuery(query);
                    rs.next();
                    int k = rs.getInt("ID");
                    query = "UPDATE people SET movies = CONCAT(movies, ?) WHERE ID = ?";
                    PreparedStatement prSt = conn.prepareStatement(query);
                    prSt.setString(1, i + ";");
                    prSt.setInt(2, k);
                    prSt.executeUpdate();
                } 
            }
        } catch(SQLException e){
            e.printStackTrace();    
        }
    }

    public static void loadMovieFromDB(MovieMap MM, Person P) {
        try {
            Person flag = null;
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            query = "USE test2";
            stmt.execute(query);
            query = "SELECT ID FROM testik WHERE ID = (SELECT MAX(ID) FROM testik)";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int maxID = rs.getInt("ID");
            //vsechno v loopu do max ID
            for (int i = 0; i < maxID+1; i++) {
                query = "SELECT * FROM testik WHERE ID = " + i;
                rs = stmt.executeQuery(query);
                rs.next();
                int ID = rs.getInt("ID");
                String name = rs.getString("name");
                String director = rs.getString("director");
                int releaseDate = rs.getInt("releaseDate");
                int suggestedAge = rs.getInt("suggestedAge");
                String scoreStr = rs.getString("score");
                String scoreCommStr = rs.getString("scoreComment");
                String movieType = rs.getString("movieType");

                query = "SELECT COUNT( * ) FROM people WHERE movies LIKE '%" + i + ";%'";
                rs = stmt.executeQuery(query);
                rs.next();
                int count = rs.getInt(1);

                query = "SELECT name FROM people WHERE movies LIKE '%" + i + ";%'";
                rs = stmt.executeQuery(query);
                rs.next();
                String people [] = new String[count];
                for (int j = 0; j < count; j++) {
                    people[j] = rs.getString(1);
                    rs.next();
                    flag = P.personMap.get(people[j]);
                    if (movieType == "L") {
                        if (flag != null) {P.addMovieToPerson(name, people[j]);}
                        else P.addPerson(people[j], name, Person.PersonType.Actor);
                    }
                    else {
                        if (flag != null) {P.addMovieToPerson(name, people[j]);}
                        else P.addPerson(people[j], name, Person.PersonType.Animator);
                    }
                    
                } 
                
                Movie M;
                if (movieType.equals("L")) {M = new LiveActionMovie(name, director, people, releaseDate);}
                else {M = new AnimatedMovie(name, director, people, releaseDate, suggestedAge);}
                
                int []score = Arrays.stream(scoreStr.split(";")).mapToInt(Integer::parseInt).toArray();
                String [] scoreComment = scoreCommStr.split(";");
                for (int j = 0; j < score.length; j++) {
                    if (score[j] != -1) {
                        M.setScore(score[j],j);
                        M.setScoreComment(scoreComment[j], j);
                    }
                }

                
                MM.addMovie(M);
            }
        } catch (SQLException e) {
            System.out.println("Unable to use or load non-existant database");
        }
        

    }
}
