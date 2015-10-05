import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		if(request.getSession(false)== null){
		HttpSession s=request.getSession();
		System.out.println(s.getId());
		}
		
		
		try (PrintWriter out = response.getWriter()) {
			response.setContentType("text/html;charset=UTF-8");
			String msg=(String) request.getAttribute("message");
			if (msg!=null){
			out.println(request.getAttribute("message"));
			}
			Integer attempt=(Integer) request.getAttribute("attempt");
			if(attempt!=null){
				if(attempt<3){
			out.println("Please enter correct password and username --your attempt number :"+attempt);
			
				}else{
					
				}
			
			}
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>KBC</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>You get 5 questions you should atleast,get 4 correct to win the show<br>");
			out.println("All the best click Start to start the show</p>");
			out.println("<div>");

			out.println("<form method=\"POST\" action=\"Validate\">");
			out.println("<label for=\"name\" > LoginName :: </label>");
			out.println("<input type=\"text\" name=\"name\" size=\"20\"/>");
			out.println("<br>");
			out.println("<label for=\"age\" > Password :: </label>");
			out.println("<input type=\"password\" name=\"age\" size=\"20\"/>");
			out.println("<br>");
			out.println(" <input type=\"submit\" value=\"Go\"/>");
			out.println("</form>");
			out.println(" <a href=\"http://localhost:11296/kbc/Register.html\">Register Here</a>");

			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
