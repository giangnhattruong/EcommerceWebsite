package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import dao.ProductDao;
import model.Product;
import myutils.StringUtils;

/**
 * Servlet implementation class Product
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/ShoppingDB")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		showProduct(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		addToCart(request, response);
	}

	private void showProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = StringUtils.getString(request.getParameter("id"));
		showProduct(request, response, id);
	}
	
	private void showProduct(HttpServletRequest request, HttpServletResponse response, String id)
			throws IOException, ServletException {
		showProduct(request, response, id, "");
	}
	
	private void showProduct(HttpServletRequest request, HttpServletResponse response, String id, String message)
			throws IOException, ServletException {
		if (id.equals("")) {
			redirectHome(request, response);
			return;
		}
		
		try {
			Product product = ProductDao.getProduct(ds, id);
			
			if (product == null)
				pageForward(request, response, "Product not found.", "/jsp/error.jsp");
			else 
				request.setAttribute("message", message);
				pageForward(request, response, product, "/jsp/productDetails.jsp");
		} catch (SQLException e) {
			pageForward(request, response, 
					"Our server is temporary down, please try again later.", "/jsp/error.jsp");
			e.printStackTrace();
		}
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = request.getParameter("product_id");
		String name = request.getParameter("product_name");
		String description = request.getParameter("product_description");
		double price = Double.parseDouble(request.getParameter("product_price"));
		String type = request.getParameter("product_type");
		String brand = request.getParameter("product_brand");
		String imgURL = request.getParameter("product_imgURL");
		Product product = new Product(id, name, description, price, type, brand, imgURL);
		
		int amount = Integer.parseInt(request.getParameter("amount"));
		int maxAmount = 9;
		
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("cart");
		
		if (cart == null)
			cart = new HashMap<>();
		
		if (!cart.containsKey(product) && amount <= maxAmount) {
			cart.put(product, amount);
			session.setAttribute("cart", cart);
			showProduct(request, response, id);
			return;
		}
		
		int oldAmount = cart.get(product) == null? 0: cart.get(product);
		int newAmount = oldAmount + amount;
		
		if (newAmount > maxAmount) {
			String message = "You cannot add more than " + 
					maxAmount + " items" +
					(oldAmount != 0? " (" + oldAmount + " already in cart).": ".");
			showProduct(request, response, id, message);
			return;
		}
		
		cart.replace(product, newAmount);
		session.setAttribute("cart", cart);
		redirectHome(request, response);
	}

	private void pageForward(HttpServletRequest request, HttpServletResponse response, String message, String page)
			throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher(page).forward(request, response);
	}
	
	private void pageForward(HttpServletRequest request, HttpServletResponse response, Product product, String page)
			throws ServletException, IOException {
		request.setAttribute("product", product);
		request.getRequestDispatcher(page).forward(request, response);
	}

	private void redirectHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/shop");
	}

}
