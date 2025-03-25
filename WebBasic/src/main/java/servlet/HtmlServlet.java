package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// @WebServlet의 url이 겹치면 안된다
// html 코드가 길어지면 ?? 자바 코드안에 html이 존재하므로 복잡, 가독성, 유지보수 등이 어렵다.
// 프론트 쪽 비즈니스 로직이 변경되면 백엔드가 수정 ???
// html과 java를 분리 !!! JSP 탄생 배경
@WebServlet("/html")
public class HtmlServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
   	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request는 클라이언트의 요청에 대한 정보, response는 응답에 대한
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Get Request");
		response.getWriter().append("<html><body><h1>Get Request</h1></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post Request");
		response.getWriter().append("<html><body><h1>Post Request</h1></body></html>");

	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Put Request");
		response.getWriter().append("<html><body><h1>Put Request</h1></body></html>");

	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Delete Request");
		response.getWriter().append("<html><body><h1>Delete Request</h1></body></html>");

	}
}
