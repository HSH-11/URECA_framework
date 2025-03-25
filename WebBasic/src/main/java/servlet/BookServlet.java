package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 도메인 업무를 서블릿으로 처리
// 단순 crud를 위한 서블릿을 각각 만들지 않고, 업무별 세부 처리를 하나의 서블릿에서 처리하는 방식을 해보자
// 1. sub url로 구분
//	도서목록 : /book/list
//	도서상세 : /book/detail
//	도서등록 : /book/insert
//	도서 ....

@WebServlet("/book/*")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 여러 요청을 처리
	// sub url 방식
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println(request.getRequestURI());
//		System.out.println(request.getContextPath());
//
//		String job = request.getRequestURI().substring(request.getContextPath().length());
//		switch (job) {
//		case "/book/list":
//			list();
//			break;
//		case "/book/detail":
//			detail();
//			break;
//		case "/book/insert":
//			insert();
//			break;
//		}
//
//	}
	// job parameter 방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String job = request.getParameter("job");
		switch (job) {
		case "list":
			list();
			break;
		case "detail":
			detail();
			break;
		case "insert":
			insert();
			break;
		}
		
	}
	
	private void list() {System.out.println("list()");}
	private void detail() {System.out.println("detail()");}
	private void insert() {System.out.println("insert()");}

}
