package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// servlet: 다양한 request에 대해 어떤 클래스의 메소드가 처리해야 하는지에 대한 제어
// job parameter를 request에서 구분해서 처리

@WebServlet("/mvc")
public class MVCServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// job parameter 값 확인
		String job = request.getParameter("job");

		// switch
		switch (job) {
		case "list":
			List<String> strList = getList();
			request.setAttribute("strList", strList);
			request.getRequestDispatcher("/mvc.jsp").forward(request, response);
			break;

		// redirection
		// 302 응답: redirection
		// 최초 redirection : 200 OK
		// 이후 html 변경 없이 redirection : 200 OK (from disk cache)
		// 이후 html 변경 없이 html 직접 요청 304 Not Modified 응답 : cache 이용하라는 것
		case "info":
			response.sendRedirect("/WebBasic/info.html");
			break;
		}
	}

	private List<String> getList() {
		List<String> strList = List.of("Java", "Servlet", "JSP"); // service, dao layer 통해 구한 결과로 가정
		return strList;
	}

}
