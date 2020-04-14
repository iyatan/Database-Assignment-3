
import java.util.*;
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


	public static void main(String[] args) throws SQLException, ParseException {

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
                   case 4:
                	   MakeReservation();
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
		
		private static void MakeReservation() throws SQLException, ParseException{
			int error = 0; // Variable to hold SQLCODE
	           String state = "00000"; // Variable to hold SQLSTATE

	           // Register the driver. You must register the driver before you can use it.
	           try {
	               DriverManager.registerDriver(new org.postgresql.Driver());
	           } catch (Exception cnfe) {
	               System.out.println("Class not found");
	           }
			
	           con = DriverManager.getConnection(url, usernamestring, passwordstring);
	           Statement stm = con.createStatement();
	           // To make sure of the uniqueness of the ids we use a hashset
	           HashSet<Integer> sessionIds = new HashSet<Integer>();
	           try {
	        	   String query = "SELECT sessionid FROM reservation";
	        	   java.sql.ResultSet result = stm.executeQuery(query);
	        	   while(result.next()) {
	        		   int id = result.getInt(1);
	        		   sessionIds.add(id);
	        	   }
	        	   
	           }catch(SQLException e) {
	        	   error = e.getErrorCode();
	        	   state= e.getSQLState();
	        	   System.out.println("Error :" + error + "state"+state);
	        	    
	           }
	           
	           Random rdm = new Random();
	           int newId = (int) rdm.nextInt((9999999 - 1111111) + 1) + 1111111;
	           
	           if(sessionIds.contains(newId)) {
	        	   newId = (int) rdm.nextInt((9999999 - 1111111) + 1) + 1111111;
	           }
	           
	           String begTimeAvl ="0000-00-00 00:00:00";
	           
	           HashMap<Integer, String> availableTime = new HashMap<Integer, String>();
	           ArrayList<String> avTime = new ArrayList<>(Arrays.asList("2020-05-19 14:00:00", "2020-05-20 14:00:00", "2020-06-19 14:00:00","2020-12-19 10:00:00", "2020-12-12 10:00:00"));
			   int i=1;
			   while(i<=avTime.size()) {
				   availableTime.put(i, avTime.get(i-1));
				   i++;
			   }
			   System.out.println();
			   
			   System.out.println("Here is the list of avalable time to pick from and how to pick them");
			   for(Map.Entry<Integer, String> entry : availableTime.entrySet() ) {
				   System.out.println(" ** To pick " + entry.getValue() + "   Press " + entry.getKey());
			   }
			   System.out.println("You can press now: ");
			   int timeCode = input.nextInt();
			   
			   String chosenTime = (String)(availableTime.get(timeCode));
			   
			   System.out.println("Set the Number of Positions you want from 1 to 4: ");
			   
	           int chosenPosition = input.nextInt();
	           
	           try {
	     	      
	        	   String insertSQL = "INSERT INTO reservation VALUES ( "+newId+", '"+chosenTime+"', "+chosenPosition+") ";
//	        	   System.out.println(insertSQL);
	        	   stm.executeUpdate(insertSQL);
	        	   System.out.println("CONGRATS YOU HAVE MADE THE RESERVATION");


			} catch (SQLException e) {
				error = e.getErrorCode(); // Get SQLCODE
				state = e.getSQLState(); // Get SQLSTATE

				// Your code to handle errors comes here;
				// something more meaningful than a print would be good
				System.out.println("Code: " + error + "  sqlState: " + state);
			}
			   
			   
	           
	           
	           
		}
        

}
