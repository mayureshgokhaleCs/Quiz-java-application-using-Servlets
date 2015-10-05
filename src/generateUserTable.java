import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


public class generateUserTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// step 1 loading of driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver loaded");
		//establish connection
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","mayuresh");
		
		System.out.println("success");
		//create statement
		Statement st=con.createStatement();
		System.out.println("statement created");
		
		String sql= "CREATE TABLE REGISTRATION " +
                " (login VARCHAR(255), " + 
                " password VARCHAR(255), " + 
                " PRIMARY KEY (login))"; 

  
  // String sql = "DROP TABLE REGISTRATION ";
   st.executeUpdate(sql);
   System.out.println("Created table in given database...");
	}
}
