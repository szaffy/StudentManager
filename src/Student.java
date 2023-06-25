/*A class to store information about our Students*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private int rank;

    public Student(String firstName, String lastName, int rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public int getRank(){
        return this.rank;
    }
    //override toString method to display Student information
    public String toString() {
        return "Name: " + firstName + " " + lastName +
               "\nRank: " + rank;
    }

    public void addNewStudent(Student student){
        try {
            //connect to database 
            Connection connection = DatabaseConnector.getConnection();
            // Use the connection object for database operations
            String sql = "INSERT INTO Student (first_name, last_name, rank) VALUES(?,?,?)"; 
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getRank());
            statement.executeUpdate();
            System.out.println("New student added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
