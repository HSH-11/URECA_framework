package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// get
// <input type = "text" name = "searchWord">

@WebServlet("/param")
public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 검색어
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchWord = request.getParameter("searchWord");
		response.getWriter().append("<html><body><h1>Your searchWord: " + searchWord + "</h1></body></html>");

	}

	// 로그인
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		response.getWriter().append("<html><body><h1>Your username: " + username + "</h1></body></html>");

		String password = request.getParameter("password");
		System.out.println("Password: " + password);
	}

}
