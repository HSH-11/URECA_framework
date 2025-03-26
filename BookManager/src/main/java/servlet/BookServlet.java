package servlet;

import java.io.IOException;
import java.util.List;

import dao.BookDao;
import dto.BookDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/books/*")
public class BookServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String job  = req.getRequestURI().substring(req.getContextPath().length());
		switch(job) {
			case "/books/list" : list(req,resp); break;
			case "/books/detail" : detail(req,resp); break;
			case "/books/insert" : insert(req,resp); break;
			case "/books/update" : update(req,resp); break;
			case "/books/delete" : delete(req,resp); break;
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		List<BookDto> bookList = bookDao.listBook();
		System.out.println(bookList);
		req.setAttribute("bookList", bookList);
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
	}
	protected void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		// bookId parameter
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		BookDto bookDto = bookDao.detailBook(bookId);
		System.out.println(bookDto);
		req.setAttribute("bookDto", bookDto);
		req.getRequestDispatcher("/detailForm.jsp").forward(req, resp);
	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		String bookName = req.getParameter("bookName");
		String publisher = req.getParameter("publisher");
		int price = Integer.parseInt(req.getParameter("price"));
		
		BookDto bookDto = new BookDto(bookId, bookName, publisher, price);
		int ret = bookDao.insertBook(bookDto);
		
		System.out.println(ret);
		// if (ret ==  1) {}
		// else {} // 실패
		
		req.getRequestDispatcher("/insertResult.jsp").forward(req, resp);
	}
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		String bookName = req.getParameter("bookName");
		String publisher = req.getParameter("publisher");
		int price = Integer.parseInt(req.getParameter("price"));
		
		BookDto bookDto = new BookDto(bookId, bookName, publisher, price);
		int ret = bookDao.updateBook(bookDto);
		
		System.out.println(ret);
		// if (ret ==  1) {}
		// else {} // 실패
		
		req.getRequestDispatcher("/updateResult.jsp").forward(req, resp);
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		
		int ret = bookDao.deleteBook(bookId);
		
		System.out.println(ret);
		req.getRequestDispatcher("/deleteResult.jsp").forward(req, resp);
	}
}
