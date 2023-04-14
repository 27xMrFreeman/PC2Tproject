import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class dbConn {
    String url = "jdbc:mysql://localhost:3306";
    String username = "user";
    String password = "password";
    String query;
//ResultSet resultSet = statement.executeQuery(query); na pozdeji
    public void saveMovieToDB(Movie M) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (Exception e) {
        e.printStackTrace();
    }
    try {
        query = "CREATE DATABASE IF NOT EXISTS projekt";
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement statement = conn.createStatement();
        statement.executeQuery(query);
        query = "CREATE TABLE IF NOT EXISTS movies (ID int NOT NULL, name varchar(255), director varchar(255), releaseDate int, PRIMARY KEY(ID))";
        statement.executeQuery(query);
        query = "INSERT INTO movies ()";
        
    } catch (SQLException e) {
        e.printStackTrace();
    }  
    

    }

        
}
