import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Playquiz
 */
@WebServlet(name = "Quiz", urlPatterns = { "/Quiz" })
public class Quiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Question> r = new ArrayList<Question>();

	@Override
	public void init() {

		Question q1 = new Question(1, "What is 2+3", "3", "2", "5", "3");
		Question q2 = new Question(2, "What is 2+5", "7", "2", "5", "1");
		Question q3 = new Question(3, "What is 2+8", "3", "10", "5", "2");
		Question q4 = new Question(4, "What is 2+1", "1", "3", "5", "2");
		Question q5 = new Question(5, "What is 2+6", "8", "2", "5", "1");
		r.add(q1);
	    r.add(q2);
	   r.add(q3);
	    r.add(q4);
		r.add(q5);

	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>KBC</title>");
			out.println("</head>");
			out.println("<body>");
			ServletContext sc = getServletContext();
			if (request.getParameter("age") != null) {
				String name = request.getParameter("name");
				sc.setAttribute("name", name);
				String getName = (String) sc.getAttribute("name");
				out.println("<h1> Welcome &nbsp" + getName
						+ "&nbsp to KBC</h1>");
				out.println("<div>");
				int startValue = 0;
				sc.setAttribute("qnumber", 0);

				Question q = r.get(startValue);
				out.println("<p >" + q.question + "?</p>");
				out.println("<form method=\"GET\" action=\"http://localhost:8080/kbc/Quiz\">");
				out.println("<input type=\"radio\" name=\"option\" value=\"1\">"
						+ q.option1 + "<br>");
				out.println("<input type=\"radio\" name=\"option\" value=\"2\">"
						+ q.option2 + "<br>");
				out.println("<input type=\"radio\" name=\"option\" value=\"3\">"
						+ q.option3 + "<br>");
				out.println("<input type=\"submit\" value=\"NEXT\"/>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			} else {
				String getName = (String) sc.getAttribute("name");
				out.println("<h1> Welcome &nbsp" + getName
						+ "&nbsp to KBC</h1>");
				out.println("<div>");
				int userResponse = Integer.parseInt(request
						.getParameter("option"));
				Integer questionNum = (Integer) sc.getAttribute("qnumber");
				// check answer
				String idealAnswer = r.get(questionNum).answer;
				if (userResponse == Integer.parseInt(idealAnswer)) {
					if (sc.getAttribute("score") == null) {
						sc.setAttribute("score", 1);
					
					} else {
						Integer scoree = (Integer) sc.getAttribute("score");
						sc.setAttribute("score", ++scoree);
						
					}
				}
				// check for last question if last then compute score
				if (questionNum < r.size() - 1) {
					questionNum = questionNum + 1;
					sc.setAttribute("qnumber", questionNum);
					
					Question q = r.get(questionNum);
					out.println("<p >" + q.question + "?</p>");
					out.println("<form method=\"GET\" action=\"http://localhost:8080/kbc/Quiz\">");
					out.println("<input type=\"radio\" name=\"option\" value=\"1\">"
							+ q.option1 + "<br>");
					out.println("<input type=\"radio\" name=\"option\" value=\"2\">"
							+ q.option2 + "<br>");
					out.println("<input type=\"radio\" name=\"option\" value=\"3\">"
							+ q.option3 + "<br>");
					out.println("<input type=\"submit\" value=\"NEXT\"/>");
				

				} else {
					sc.setAttribute("bankSize", r.size());
					RequestDispatcher rd=request.getRequestDispatcher("CalculateResult");
					rd.include(request, response);
					
				}
			}
			out.println("</form>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
