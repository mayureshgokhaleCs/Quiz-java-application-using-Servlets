
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddData
 */
@WebServlet("/AddData")
public class AddData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=null;
		// step 1 loading of driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			// establish connection
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mydb", "root", "mayuresh");
			System.out.println("success");
			// create statement
			Statement st = con.createStatement();
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			
			String query = "INSERT INTO registration VALUES  ('"+name+"','"+pass+"')";
			
			
			try {
				out = response.getWriter();
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>KBC</title>");
				out.println("</head>");
				int rs = st.executeUpdate(query);
				if (rs == 1) {

					out.println("<body>");
					out.println("<h2> Added to database go to login page below</h2>");
					out.println("<a href=\"http://localhost:11296/kbc/Welcome.html\">Login Here</a>");
					out.println("</body>");
					out.println("</html>");
				}
				
				
			}finally{}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e){
			out.println("<h2>UserName already exists</h2>");
			out.println("<a href=\"http://localhost:11296/kbc/Register.html\">TryAgainHere</a>");
			out.println("</body>");
			out.println("</html>");
		}
   
	}
}
