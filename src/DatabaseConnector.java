import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    //setting up the database url
    private static final String DATABASE_URL = "jdbc:sqlite:./db/Wingtsun.db";

    //we connect to the database and return the url to it.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

     /*  public static void main(String[] args){
        Connection connection = DatabaseConnector.getConnection();

    } */
}
