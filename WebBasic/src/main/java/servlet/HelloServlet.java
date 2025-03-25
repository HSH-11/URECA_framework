package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// HttpServlet 상속
// doGet() doPost() 재정의
// .... 규칙
// ????
// main() 실행 <= tomcat
// tomcat은 어떻게 우리가 작성한 코드를 실행할 수 있는가?
// Container vs Component <= 양쪽이 서로 미리 약속한 규칙이 존재
// 우리는 그 규칙대로 coding
// HttpServlet Service()가 get,post 등 method를 확인하고 난 뒤 HelloServlet의 doGet, doPost를 호출


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request는 클라이언트의 요청에 대한 정보, response는 응답에 대한
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Get Request");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post Request");
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Put Request");
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Delete Request");
	}

}
