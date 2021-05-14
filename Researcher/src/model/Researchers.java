package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Researchers {

	//private Integer appID;
	private String name;

	//A common method to connect to the DB 
		private Connection connect() {
			Connection con = null;
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 //Provide the correct details: DBServer/DBName, username, password 
				 con= DriverManager.getConnection("jdbc:mysql://localhost/gadget_badget_system?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

				//For testing          
				 System.out.print("Successfully connected");
				 	
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return con; 
		}
		
		public String readResearchers() {  
			String output = "";  
			
			try {  
				Connection con = connect();  
				if (con == null)  {   
					return "Error while connecting to the database for reading.";  
				} 

				// Prepare the html table to be displayed   
				output = "<table border='1'><tr><th>appID</th>"
						+"<th>name</th>"
						+ "<th>mobile</th><th>email</th>"
						+ "<th>NIC</th><th>Research type</th>"
						+ "<th>Date</th>"
						+ "<th>Update</th><th>Remove</th></tr>";


				  String query = "select * from researcher";   
				  Statement stmt = con.createStatement();   
				  ResultSet rs = stmt.executeQuery(query); 

				  // iterate through the rows in the result set   
				  while (rs.next())   {  

					  	String appID = Integer.toString(rs.getInt("appID"));
						String name = rs.getString("name");
						String mobile = Integer.toString(rs.getInt("mobile"));
						String email = rs.getString("email");
						String nic = rs.getString("nic");
						String type = rs.getString("type");
						String date = rs.getString("date");
						//String rfile = rs.getString("rfile");
					  // Add into the html table    

					  output += "<td><input id='hidAppIDUpdate' name='hidAppIDUpdate' type='hidden' value='" + appID + "'>" + appID + "</td>"; 
					 output += "<td>" + name + "</td>";
					  output += "<td>" + mobile + "</td>";
						output += "<td>" + email + "</td>";
						output += "<td>" + nic + "</td>";
						output += "<td>" + type + "</td>";
						output += "<td>" + date + "</td>";
						//output += "<td>" + rfile + "</td>"; 
					  
					// buttons     
					  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
							  //+ "<td><from method='post' action=researcher.jsp>"
					  		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-appID='"+ appID +"'>"+"</td></tr>";

					} 
				  
				  con.close(); 

				  // Complete the html table   
				  output += "</table>"; 
				}
				catch (Exception e) {  
					output = "Error while reading the researcher.";  
					System.err.println(e.getMessage()); 
				}

				return output;
			}
		
		//Insert researcher
		public String insertResearchers(String appID,String name, String mobile, String email, String nic, String type, String date) {
			String output = "";

			try {
				Connection con = connect();  

				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement   
				String query = " insert into researcher (`appID`,`name`,`mobile`,`email`,`nic`, `type`,`date`)"+" values (?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values 
				preparedStmt.setString(1, appID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, mobile);
				preparedStmt.setString(4, email);
				preparedStmt.setString(5,nic);
				preparedStmt.setString(6, type);
				preparedStmt.setString(7, date);
				//preparedStmt.setString(8, rfile);
				
				//execute the statement   
				preparedStmt.execute();   
				con.close(); 

				//Create JSON Object to show successful msg.
				String newResearchers = readResearchers();
				output = "{\"status\":\"success\", \"data\": \"" + newResearchers + "\"}";
			}
			catch (Exception e) {  
				//Create JSON Object to show Error msg.
				output = "{\"status\":\"error\", \"data\": \"Error while Inserting Researchers.\"}";   
				System.err.println(e.getMessage());  
			} 

			 return output; 
		}
		
		//Update researcher
		public String updateResearchers(String appID, String name, String mobile, String email, String nic, String type, String date )  {   
			String output = ""; 
		 
		  try   {   
			  Connection con = connect();
		 
			  if (con == null)    {
				  return "Error while connecting to the database for updating."; 
			  } 
		 
		   // create a prepared statement    
			   String query = "UPDATE researcher SET name=?,mobile=?,email=?,nic=?,type=?,date=?,rfile=?WHERE appID=?";
				 
		   PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		   // binding values  
		   preparedStmt.setInt(1, Integer.parseInt(appID));
		   preparedStmt.setString(2, name);
			preparedStmt.setInt(3,Integer.parseInt (mobile));
			preparedStmt.setString(4, email);
			preparedStmt.setString(5,nic);
			preparedStmt.setString(6, type);
			preparedStmt.setString(7, date);
			//preparedStmt.setString(7, rfile);
			
		   
		 
		   // execute the statement    
		   preparedStmt.execute();    
		   con.close(); 
		 
		   //create JSON object to show successful msg
		   String newResearchers = readResearchers();
		   output = "{\"status\":\"success\", \"data\": \"" + newResearchers + "\"}";
		   }   catch (Exception e)   {    
			   output = "{\"status\":\"error\", \"data\": \"Error while Updating Researchers Details.\"}";      
			   System.err.println(e.getMessage());   
		   } 
		 
		  return output;  
		  }
		
		public String deleteResearchers(String appID) {  
			String output = ""; 
		 
		 try  {   
			 Connection con = connect();
		 
		  if (con == null)   {    
			  return "Error while connecting to the database for deleting.";   
		  } 
		 
		  // create a prepared statement   
		  String query = "DELETE FROM researcher WHERE name=?"; 
		 
		  PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		  // binding values   
		  preparedStmt.setString(2, name);      
		  // execute the statement   
		  preparedStmt.execute();   
		  con.close(); 
		 
		  //create JSON Object
		  String newresearchers = readResearchers();
		  output = "{\"status\":\"success\", \"data\": \"" + newresearchers + "\"}";
		  }  catch (Exception e)  {  
			  //Create JSON object 
			  output = "{\"status\":\"error\", \"data\": \"Error while Deleting Researchers.\"}";
			  System.err.println(e.getMessage());  
			  
		 } 
		 
		 return output; 
		 }
}
