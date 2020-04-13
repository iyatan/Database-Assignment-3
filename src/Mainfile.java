
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class Mainfile {
	
	private static final String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
    private static final String usernamestring = "cs421g41";
    private static final String passwordstring = "COMP421GROUP41";
    private static Connection con;
    private static Scanner input;


	public static void main(String[] args) throws SQLException {

        boolean notDone = true;
        System.out.println("Please enter a number ");

        input = new Scanner(System.in);

        while (notDone) {
        	   int i = input.nextInt();
               switch (i) {
                   case 0:
                       System.out.println("We are sad to see you go :(");
                       notDone = false;
                       System.exit(0);
                       break;
                   case 1:
                       SelectingBooks();
                       break;
                   
                   default:
                       System.out.println("Unknown Selection");
                       break;
        	
        }

	}
}
	//quering a table
	private static void SelectingBooks() throws SQLException {
		 int sqlCode = 0; // Variable to hold SQLCODE
	        String sqlState = "00000"; // Variable to hold SQLSTATE

	        // Register the driver. You must register the driver before you can use it.
	        try {
	            DriverManager.registerDriver(new org.postgresql.Driver());
	        } catch (Exception cnfe) {
	            System.out.println("Class not found");
	        }

	        con = DriverManager.getConnection(url, usernamestring, passwordstring);
	        Statement statement = con.createStatement();
	        
	        // Querying a table
	        try {
	            String querySQL = "SELECT * from books";
	            // System.out.println(querySQL);
	            java.sql.ResultSet rs = statement.executeQuery(querySQL);
	            while (rs.next()) {
	                int id = rs.getInt(1);
	                String name = rs.getString(2);
	                System.out.print("book Id :  " + id);
	                System.out.println("  Book name:  " + name);
	            }
	            System.out.println("DONE");
	        } catch (SQLException e) {
	            sqlCode = e.getErrorCode(); // Get SQLCODE
	            sqlState = e.getSQLState(); // Get SQLSTATE
	            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
	        }

	        // Finally but importantly close the statement and connection
	        statement.close();
	        con.close();
	}
        

}
