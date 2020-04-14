
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

        boolean Active = true;
        System.out.println("Please enter a code to execcute");
        System.out.println("At any point press 911 for help");

        input = new Scanner(System.in);

        while (Active) {
        	   int i = (int) input.nextInt();
               switch (i) {
                   case 911:
                	   System.out.println("**** Press 0 to quit *****");
                	   System.out.println("**** Press 1 to show guests ***");
                	   System.out.println("**** Press 2 to increase the salaries of the SPA therapists whose rating is higher than 4 by $200 ***** ");
                	   break;
                   case 0:
                       System.out.println("See you next time");
                       Active = false;
                       System.exit(0);
                       break;
                   case 1:
                       SelectingGuess();
                       break;
                   case 2:
                	   UpdateSalary();
                	   break;
                   case 3:
                	   UpdateSparoomStatus();
                	   break;
                	   
                   
                   default:
                       System.out.println("Sorry we dont know this command");
                       break;
        	
        }

	}
}
	//quering a table
	private static void SelectingGuess() throws SQLException {
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
	            String querySQL = "SELECT * from guest";
	            // System.out.println(querySQL);
	            java.sql.ResultSet rs = statement.executeQuery(querySQL);
	            while (rs.next()) {
	                String fam = rs.getString(1);
	                String name = rs.getString(2);
	                String email = rs.getString(3);
	                System.out.print("  FAMILY NAME : " + fam);
	                System.out.print("  FIRST NAME:   " + name);
	                System.out.println("  EMAIL:      " + email);
	                System.out.println("********************************************************************************");
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
	
	//The query is aimed to increase SPA therapists' salary if he/she has a rating higher or equal to 4.

	
	private static void UpdateSalary() throws SQLException {
		int error =0;
		String state ="00000";
		
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			
		}catch(Exception cnfe) {
			System.out.println("We can not find the class");
			
		}
		con = DriverManager.getConnection(url, usernamestring, passwordstring);
		Statement stm = con.createStatement();
		
		try {
			String updateSQL = "SELECT employeeID, salary, rating FROM SPATherapist;\n" + 
					"UPDATE SPATherapist \n" + 
					"	SET salary = salary + 200\n" + 
					"	WHERE rating >= 4;\n" + 
					"SELECT employeeID, salary, rating FROM SPATherapist;";
			stm.executeUpdate(updateSQL);
			System.out.println("SALARIES UPDATED");
					
		}catch(SQLException e) {
			error = e.getErrorCode();
			state = e.getSQLState();
		}

	}
	
	// make some rooms status uncompleted 
		private static void UpdateSparoomStatus() throws SQLException {
			int error =0;
			String state ="00000";
			
			try {
				DriverManager.registerDriver(new org.postgresql.Driver());
				
			}catch(Exception cnfe) {
				System.out.println("We can not find the class");
				
			}
			con = DriverManager.getConnection(url, usernamestring, passwordstring);
			Statement stm = con.createStatement();
			
			try {
				String updateSQL = "UPDATE sparoom SET status = 'under construction' where roomnumber >17 ;\n" + 
						"";
				stm.executeUpdate(updateSQL);
				System.out.println("STATUS UPDATED");
						
			}catch(SQLException e) {
				error = e.getErrorCode();
				state = e.getSQLState();
	            System.out.println("Code: " + error + "  sqlState: " + state);

			}
			stm.close();
			con.close();
			
			
		}
        

}
