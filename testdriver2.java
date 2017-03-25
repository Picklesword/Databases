package cs5530;


import java.lang.*;
import java.sql.*;
import java.io.*;

public class testdriver2 {

	/**
	 * @param args
	 */
	public static void displayMenu()
	{
		 System.out.println("        Welcome to the Temporary Housing System     ");
    	 System.out.println("1. Register a new user:");
    	 System.out.println("2. Login with username and password:");
    	 System.out.println("3. enter your own query:");
    	 System.out.println("4. exit:");
    	 System.out.println("pleasse enter your choice:");
	}
	
	public static void loggedIn(String login_name, Connector con)
	{
		 System.out.println("        Welcome " + login_name);
    	 System.out.println("1. Browse Temporary Housing:");
    	 System.out.println("2. Add a Temporary Housing");
    	 System.out.println("3. Edit Profile");
    	 System.out.println("4. Get Statistics");
    	 System.out.println("5. Return to last menu");
    	 System.out.println("pleasse enter your choice:");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Example for cs5530");
		Connector con=null;
		String choice;
        String new_name;
        String new_pw = "";
        String repeat_pw = "empty";
        String login_name = "";
        String login_pw = "";
        String sql=null;
        int c=0;
         try
		 {
			//remember to replace the password
			 	 con= new Connector();
	             System.out.println ("Database connection established");
	         
	             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	             
	             while(true)
	             {
	            	 displayMenu();
	            	 while ((choice = in.readLine()) == null && choice.length() == 0);
	            	 try{
	            		 c = Integer.parseInt(choice);
	            	 }catch (Exception e)
	            	 {
	            		 
	            		 continue;
	            	 }
	            	 if (c<1 | c>4)
	            		 continue;
	            	 if (c==1)
	            	 {
	            		 System.out.println("Please enter a new username:");
	            		 while ((new_name = in.readLine()) == null && new_name.length() == 0);
	            		 String repeat_pw2 = repeat_pw;
	            		 System.out.println("Please enter a password:");
	            		 while ((new_pw = in.readLine()) == null && new_pw.length() == 0);
	            		 sql = "INSERT INTO User_Data (loginName, password) VALUES (" + "\"" + new_name +  "\""+ ", " + "\"" + new_pw + "\"" + ")" ;
	            		 con.stmt.executeUpdate(sql);
	            		 System.out.println("Success! You can now login with your new username and password");
	            	 }
	            	 else if (c==2)
	            	 {
	            		 System.out.println("Please enter your username:");
	            		 while ((login_name = in.readLine()) == null && login_name.length() == 0);
	            		 System.out.println("Please enter your password:");
	            		 while ((login_pw = in.readLine()) == null && login_pw.length() == 0);
	            		 sql = "SELECT password FROM User_Data";  //WHERE loginName = \"" + login_name + "\"";
	            		 ResultSet rs=con.stmt.executeQuery(sql);
	            		 ResultSetMetaData rsmd = rs.getMetaData();
	            		 int numCols = rsmd.getColumnCount();
	            		 while (rs.next())
	            		 {
	            			 //System.out.print("cname:");
	            			 for (int i=1; i<=numCols;i++)
	            				 System.out.print(rs.getString(i)+"  ");
	            			 System.out.println("");
	            		 }
	            		 System.out.println("Verifying username/password");
	            		 loggedIn(login_name, con);
	            	 }
	            	 else if (c==3)
	            	 {	 
	            		 System.out.println("please enter your query below:");
	            		 while ((sql = in.readLine()) == null && sql.length() == 0)
	            			 System.out.println(sql);
	            		 ResultSet rs=con.stmt.executeQuery(sql);
	            		 ResultSetMetaData rsmd = rs.getMetaData();
	            		 int numCols = rsmd.getColumnCount();
	            		 while (rs.next())
	            		 {
	            			 //System.out.print("cname:");
	            			 for (int i=1; i<=numCols;i++)
	            				 System.out.print(rs.getString(i)+"  ");
	            			 System.out.println("");
	            		 }
	            		 System.out.println(" ");
	            		 rs.close();
	            	 }
	            	 else
	            	 {   
	            		 System.out.println("EoM");
	            		 con.stmt.close(); 
	        
	            		 break;
	            	 }
	             }
		 }
         catch (Exception e)
         {
        	 e.printStackTrace();
        	 System.err.println ("Either connection error or query execution error!");
         }
         finally
         {
        	 if (con != null)
        	 {
        		 try
        		 {
        			 con.closeConnection();
        			 System.out.println ("Database connection terminated");
        		 }
        	 
        		 catch (Exception e) { /* ignore close errors */ }
        	 }	 
         }
	}
}
