

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class SpaDAO {
	
	private static final String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
    private static final String usernamestring = "cs421g41";
    private static final String passwordstring = "COMP421GROUP41";
    private static Connection con;
    
    
    //DAO Constructor
    public SpaDAO() throws SQLException{
    	
    	try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (Exception cnfe) {
            System.out.println("Class not found");
        }
    	
    	//Connect to the database
    	con = DriverManager.getConnection(url, usernamestring, passwordstring);
    	System.out.println("DB connection sucessful to: " + url);
    	
    }
    
    
    //Function1
    private static void selectGuest() throws SQLException{
    	
    	int sqlCode = 0; 
        String sqlState = "00000";
        String SQLquery = "SELECT * FROM guest";
        
    	Statement myStmt = null;
    	ResultSet myRs = null;

    	//Execute the query
        try {
        	
        	myStmt = con.createStatement();
        	myRs = myStmt.executeQuery(SQLquery);

            while (myRs.next()) {
                String fam = myRs.getString(1);
                String name = myRs.getString(2);
                String email = myRs.getString(3);
                //SHOW ON THE GUI
                System.out.println("SELECTGUEST()");
                
                
            }
              
        } catch (SQLException e) {
        	
            sqlCode = e.getErrorCode(); 
            sqlState = e.getSQLState(); 
            
        }finally {
        	
        	myStmt.close();
        	myRs.close();
            con.close();
        	
        }
    }
    
    
    //Function2
    private static void UpdateSalary() throws SQLException{ 
    	
    	int error =0;
		String state ="00000";
		String SQLquery = "UPDATE SPATherapist \n" + 
						"	SET salary = salary + 200\n" + 
						"	WHERE rating >= 4;";
        
    	Statement myStmt = null;
    	ResultSet myRs = null;
    	
    	//Execute the query
        try {
        	
        	myStmt = con.createStatement();
        	myStmt.executeUpdate(SQLquery);
        	
              
        } catch (SQLException e) {
        	
        	error = e.getErrorCode();
			state = e.getSQLState();
			System.out.println("WE ARE VERY SORRY AN ERROR HAPPENED TRY AGAIN"); 
            
        }finally {
        	
        	myStmt.close();
            con.close();
        	
        }
    }
    
    
    
    //Functoin3
//    private static void updateSpaRoomStatus() throws SQLException{
//    	
//    	int error =0;
//		String state ="00000";
//		String SQLquery = "UPDATE sparoom SET status = 'under construction' where roomnumber = "
//					+	ChosenLocation+";";
//
//		Statement myStmt = null;
//		ResultSet myRs = null;
//		
//		//Execute the query
//        try {
//        	
//        	myStmt = con.createStatement();
//        	myStmt.executeUpdate(SQLquery);
//        	
//              
//        } catch (SQLException e) {
//        	
//        	error = e.getErrorCode();
//			state = e.getSQLState();
//			System.out.println("WE ARE VERY SORRY AN ERROR HAPPENED TRY AGAIN"); 
//            
//        }finally {
//        	
//        	myStmt.close();
//            con.close();
//        	
//        }
//		
//		
//		
//    }
    
    
    //Function4
    
    
    //Functino5
    
    
    
    
    
    
    
    //MAIN
    public static void main(String[] args) throws SQLException{
    	
    	SpaDAO myDAO = new SpaDAO();
    	
    	
    }
    
    

}
