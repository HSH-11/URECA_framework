package servlet;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.ProductDAO;
import dto.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String job = req.getRequestURI().substring(req.getContextPath().length());
		switch (job) {
		case "/product/list":
			list(req, resp);
			break;
		case "/product/insert":
			insert(req, resp);
			break;
		case "/product/update":
			update(req, resp);
			break;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDAO ProductDAO = new ProductDAO();
		List<ProductDTO> productList = ProductDAO.getAllProducts();

		Gson gson = new Gson();
		String jsonStr = gson.toJson(productList);

		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().write(jsonStr);
	}

	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		int stockQuantity = Integer.parseInt(req.getParameter("stock"));

		ProductDTO productDTO = new ProductDTO(name, price, stockQuantity);
		productDAO.insertProduct(productDTO);

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("result", "success");
		jsonObject.addProperty("productId", productDTO.getProductId());

		String jsonStr = gson.toJson(jsonObject);
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().write(jsonStr);
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();

		int productId = Integer.parseInt(req.getParameter("productId"));
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		int stock = Integer.parseInt(req.getParameter("stock"));

		ProductDTO productDTO = new ProductDTO(productId, name, price, stock);
		productDAO.updateProduct(productDTO);

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();

		String jsonStr = gson.toJson(jsonObject);
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().write(jsonStr);
	}

}
