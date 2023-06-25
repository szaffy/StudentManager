/*A class to store information about our Students*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;

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
    //a method to add new students to the students table in the database
    public static void addNewStudent(String first_name, String last_name, int rank){
        try {
            //connect to database 
            Connection connection = DatabaseConnector.getConnection();
            // Use the connection object for database operations
            String sql = "INSERT INTO Student (first_name, last_name, rank) VALUES(?,?,?)"; 
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setInt(3, rank);
            statement.executeUpdate();
            System.out.println("New student added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //a method to insert payment in the payment table in the database
    public static void addPayment(int student_id, int year, int month, String paymentStatus){
        if (!paymentStatus.equals("Payed") && !paymentStatus.equals("Not payed")){
            System.out.println("It is not a supported value");
            return;
        }
        int currentYear = Year.now().getValue();
        if (year < 2020 || year > currentYear) {
            System.out.println("Year needs to be between 2020 and current year");
            return;
        }
        try {
            Connection connection = DatabaseConnector.getConnection();
            //check if payment already exists
            String checkSql = ("SELECT * FROM payment WHERE student_id = ? AND year = ? AND month = ? ");
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setInt(1, student_id);
            checkStatement.setInt(2, year);
            checkStatement.setInt(3, month);
            ResultSet resultSet = checkStatement.executeQuery();
            if(resultSet.next()){
                System.out.println("A payment already exists for the specified year and month");
                return;
            }
            //insert date, time , payment status in the payment table 
            String sql = "INSERT INTO Payment (student_id, year, month, payment_status) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, student_id);
            statement.setInt(2, year);
            statement.setInt(3, month);
            statement.setString(4, paymentStatus);
            statement.executeUpdate();
            System.out.println("Payment has been added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
