package servlet;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
		String job = req.getRequestURI().substring(req.getContextPath().length());
		switch (job) {
		case "/customer/list":
			list(req, resp);
			break;
//			case "/books/detail" : detail(req,resp); break;
		case "/customer/insert":
			insert(req, resp);
			break;
		case "/customer/update":
			update(req, resp);
			break;
			case "/customer/delete" : delete(req,resp); break;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CustomerDAO customerDao = new CustomerDAO();
		List<CustomerDTO> CustomerList = customerDao.getAllCustomers();
		System.out.println(CustomerList);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(CustomerList);
		resp.getWriter().write(jsonStr);
	}

//	protected void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		CustomerDAO CustomerDAO = new CustomerDAO();
//		// bookId parameter
//		int bookId = Integer.parseInt(req.getParameter("bookId"));
//		CustomerDTO CustomerDTO = CustomerDAO.detailBook(bookId);
//		System.out.println(CustomerDTO);
//		req.setAttribute("CustomerDTO", CustomerDTO);
//		req.getRequestDispatcher("/detailForm.jsp").forward(req, resp);
//	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CustomerDAO customerDAO = new CustomerDAO();
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");

		customerDAO.findOrCreateCustomer(name, email, phone, address);

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();

		String jsonStr = gson.toJson(jsonObject);
		System.out.println(jsonStr);
		resp.getWriter().write(jsonStr);

	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CustomerDAO CustomerDao = new CustomerDAO();
		int customerId = Integer.parseInt(req.getParameter("customerId"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");

		CustomerDTO customerDto = new CustomerDTO(customerId, name, email, phone, address);
		boolean result = CustomerDao.updateCustomer(customerDto);

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();

		if (result) {
			jsonObject.addProperty("result", "success");
		} else {
			jsonObject.addProperty("result", "fail");
		}

		String jsonStr = gson.toJson(jsonObject);
		System.out.println(jsonStr);
		resp.getWriter().write(jsonStr);

	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CustomerDAO CustomerDAO = new CustomerDAO();
		int CustomerId = Integer.parseInt(req.getParameter("customerId"));	
		boolean result =  CustomerDAO.deleteCustomer(CustomerId);
		
		Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        
        if (result) {
            jsonObject.addProperty("result", "success");
        } else {
            jsonObject.addProperty("result", "fail");
        }
        
        String jsonStr = gson.toJson(jsonObject);
        System.out.println(jsonStr);
        resp.getWriter().write(jsonStr); 
	}
}
