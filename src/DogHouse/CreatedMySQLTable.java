package DogHouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;




public class CreatedMySQLTable {
	static {
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch(ClassNotFoundException ex) {
	      System.err.println("Unable to load MySQL Driver");
	    }
	  }

	public static void main(String[] args) throws SQLException {
		
		 String url = "jdbc:mysql://localhost/cs3220stu55?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
         String username = "cs3220stu55";
         String password = "zWpa0NxR";

			Connection conn = null;
			Statement stmte = null;
			 try{
				 
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.cj.jdbc.Driver");

			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(url, username, password);
			      System.out.println("Connected database successfully...");
			      
			      //STEP 4: Execute a query
			      System.out.println("Creating table in given database...");
			      stmte = conn.createStatement();
			      
			      String sql = "CREATE TABLE pet " +
			                   
			                   " (name VARCHAR(255), " + 
			                   " breed VARCHAR(255), " + 
			                   " gender VARCHAR(255)," +
			                   " furColor VARCHAR(30), " +
			                   " weight double," +
			                   " height double," +
			                   " birthday date," +
			                   " neutorSpay boolean,"+
			                   " microchipped boolean," +
			                   " userEmail VARCHAR(255)"+
			                   " )"; 

			      stmte.executeUpdate(sql);
			      System.out.println("Created table in given database...");
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmte!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			    	  se.printStackTrace();
			      }//end finally try
			   }//end try
	}

}
