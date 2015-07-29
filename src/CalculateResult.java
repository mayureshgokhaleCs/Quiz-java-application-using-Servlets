

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculateResult
 */
@WebServlet(name="CalculateResult",urlPatterns={"/CalculateResult"})
public class CalculateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, 
            IOException 
{
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
try{
out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Servlet E2</title>"); 
out.println("</head>");
out.println("<body>");
ServletContext sc = getServletContext();

out.println("<h2> Your Result:</h2>");
Integer scoree = (Integer) sc.getAttribute("score");
Integer size=(Integer)sc.getAttribute("bankSize");
int passingScore= (int)(0.60*size);

if (scoree >= passingScore) {
	out.println("<h2> Congratulations!!!</h2>");
	double percentage = 100 * scoree/ size;
	out.println("<h3> YOU GOT&nbsp" + percentage + "!!</h3>");
	
	
} else {
	
	out.println("<h2> SORRY You Failed!!!</h2>");
	double percentage = 100 * scoree/ size;
	out.println("<h3> YOU GOT&nbsp" + percentage + "!!</h3>");

}
out.println("</body>");
out.println("</html>");
}
catch(Exception e){
System.out.println("Exception in Servlet CalculateResult");
}
}
}
