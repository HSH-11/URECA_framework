package servlet;

import java.io.IOException;
import java.util.List;

import dao.CustomerDAO;
import dto.CustomerDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String job  = req.getRequestURI().substring(req.getContextPath().length());
		switch(job) {
			case "/customer/list" : list(req,resp); break;
//			case "/books/detail" : detail(req,resp); break;
//			case "/product/insert" : insert(req,resp); break;
			case "/customer/update" : update(req,resp); break;
//			case "/books/delete" : delete(req,resp); break;
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CustomerDAO customerDao = new CustomerDAO();
		List<CustomerDTO> CustomerList = customerDao.getAllCustomers();
		System.out.println(CustomerList);
		req.setAttribute("customerList", CustomerList);
		req.getRequestDispatcher("/CustomerList.jsp").forward(req, resp);
	}
//	protected void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ProductDAO ProductDAO = new ProductDAO();
//		// bookId parameter
//		int bookId = Integer.parseInt(req.getParameter("bookId"));
//		ProductDTO ProductDTO = ProductDAO.detailBook(bookId);
//		System.out.println(ProductDTO);
//		req.setAttribute("ProductDTO", ProductDTO);
//		req.getRequestDispatcher("/detailForm.jsp").forward(req, resp);
//	}
//	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ProductDAO ProductDAO = new ProductDAO();
//		String Name = req.getParameter("name");
//		double price = Double.parseDouble(req.getParameter("price"));
//		int stockQuantity = Integer.parseInt(req.getParameter("stock"));
//		
//		ProductDTO ProductDTO = new ProductDTO(Name, price, stockQuantity);
//		ProductDAO.insertProduct(ProductDTO);
//		
//		// 결과 확인 (생성된 ID 확인 가능)
//	    System.out.println("생성된 상품 ID: " + ProductDTO.getProductId());
//	    
//	    req.setAttribute("product", ProductDTO);
//		req.getRequestDispatcher("/insertResult.jsp").forward(req, resp);
//	}
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CustomerDAO CustomerDao = new CustomerDAO();
		int customerId = Integer.parseInt(req.getParameter("customerId"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		
		CustomerDTO customerDto = new CustomerDTO();
	    customerDto.setCustomerId(customerId); 
	    customerDto.setName(name);
	    customerDto.setEmail(email);
	    customerDto.setPhone(phone);
	    customerDto.setAddress(address);
	    
		boolean success = CustomerDao.updateCustomer(customerDto);
			
		if (success) {
	        resp.sendRedirect("/PhoneManager/customer/list");
	    }else {
	    	System.out.println("fail");
	    }
	}
//	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ProductDAO ProductDAO = new ProductDAO();
//		int bookId = Integer.parseInt(req.getParameter("bookId"));
//		
//		int ret = ProductDAO.deleteBook(bookId);
//		
//		System.out.println(ret);
//		req.getRequestDispatcher("/deleteResult.jsp").forward(req, resp);
//	}
}
