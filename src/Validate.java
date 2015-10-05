
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Validate
 */
@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String pass = request.getParameter("age");

		out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>KBC</title>");
		out.println("</head>");
		if (request.getSession(false) != null) {
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
				System.out.println("statement created");
				// executing query AND obtain result set
				String query = "SELECT * FROM registration WHERE login='"
						+ name + "'";
				ResultSet rws = st.executeQuery(query);
				boolean empty = true;
				int attempt=0;
				while (rws.next()) {
					empty = false;
					if(rws.getString("password").equals(pass)){
						//go ahead
					}else{
						//go back
						
						RequestDispatcher rd=request.getRequestDispatcher("login");
						if (request.getAttribute("attempt")==null){
							attempt=1;
							request.setAttribute("attempt", attempt);
						}else{
							attempt=(int) request.getAttribute("attempt");
							attempt=attempt+1;
							request.setAttribute("attempt", attempt);
						}
						
						rd.forward(request, response);
					}
				}
				if (empty) {
					out.println("No such user please enter correct combo");
					RequestDispatcher rd=request.getRequestDispatcher("login");
					
					if (request.getAttribute("attempt")==null){
						attempt=1;
						request.setAttribute("attempt", attempt);
					}else{
						attempt=(int) request.getAttribute("attempt");
						attempt=attempt+1;
						request.setAttribute("attempt", attempt);
					}
					
					rd.forward(request, response);
				}

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			RequestDispatcher rd=request.getRequestDispatcher("login");
			String msg="chodu hun kya chal login kar";
             request.setAttribute("message", msg);
			rd.forward(request, response);
		}
	}
}