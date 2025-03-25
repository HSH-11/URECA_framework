package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/friend")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String interest = request.getParameter("interest");

		System.out.println("친구 신청");
		System.out.println("이름: " + name);
		System.out.println("관심 분야: " + interest);

		request.setAttribute("name", name);
		request.setAttribute("interest", interest);

		// JSP로 포워딩
		request.getRequestDispatcher("result.jsp").forward(request, response);

	}
}
