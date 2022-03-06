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

import dao.AccountDao;
import dao.OrderDao;
import dao.ProductDao;
import model.Account;
import model.Order;
import model.Product;
import myutils.StringUtils;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/ShoppingDB")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("cart");
		
		if (cart == null) {
			checkOutPageForward(request, response);
			return;
		}

		String increaseID = request.getParameter("increaseID");
		String decreaseID = request.getParameter("decreaseID");
		String removeID = request.getParameter("removeID");
		int maxAmount = 9;
		
		// Check and increase number of product in cart
		if (increaseID != null) {
			try {
				Product product = ProductDao.getProduct(ds, increaseID);
				int oldAmount = cart.get(product);
				int newAmount = oldAmount + 1;
				
				// Notify if the number of product exceed the max product amount allowed
				if (newAmount > maxAmount) {
					String message = "You cannot add more than " + 
							maxAmount + " items" +
							(oldAmount != 0? " (" + oldAmount + " already in cart).": ".");
					checkOutPageForward(request, response, message);
					return;
				}
				
				cart.replace(product, newAmount);
				session.setAttribute("cart", cart);
				checkOutPageForward(request, response);
			} catch (SQLException e) {
				pageForward(request, response, 
						"Our server is temporary down, please try again later.", 
						"/jsp/checkOutCart.jsp");
				e.printStackTrace();
			}
			
			return;
		}
		
		// Check and decrease number of product in cart
		if (decreaseID != null) {
			try {
				Product product = ProductDao.getProduct(ds, decreaseID);
				int oldAmount = cart.get(product);
				int newAmount = oldAmount - 1;
				
				// If number of product < 1, remove it from cart
				if (newAmount < 1) {
					cart.remove(product);
					session.setAttribute("cart", cart);
					checkOutPageForward(request, response);
					return;
				}
				
				cart.replace(product, newAmount);
				session.setAttribute("cart", cart);
				checkOutPageForward(request, response);
			} catch (SQLException e) {
				pageForward(request, response, 
						"Our server is temporary down, please try again later.", 
						"/jsp/checkOutCart.jsp");
				e.printStackTrace();
			}
			
			return;
		}
		
		// Check and remove product from cart
		if (removeID != null) {
			try {
				Product product = ProductDao.getProduct(ds, removeID);
				cart.remove(product);
				session.setAttribute("cart", cart);
				checkOutPageForward(request, response);
			} catch (SQLException e) {
				pageForward(request, response, 
						"Our server is temporary down, please try again later.", 
						"/jsp/checkOutCart.jsp");
				e.printStackTrace();
			}
			
			return;
		}
		
		checkOutPageForward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String action = StringUtils.getString(request.getParameter("action"));
		
		switch (action) {
		case "addToCart":
			addToCart(request, response);
			break;
		case "checkout":
			checkOut(request, response);
			break;
		default:
			pageForward(request, response, "/jsp/error.jsp");
		}
	}

	private void checkOutPageForward(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkOutPageForward(request, response, null);
	}
	
	private void checkOutPageForward(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		// Check for account informations for setting attribute
		try {
			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("username");
			Account account = AccountDao.getAccount(ds, email);
			request.setAttribute("email", email);
			request.setAttribute("address", account.getAddress());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// After all forward to checkout page
			pageForward(request, response, message, "/jsp/checkOutCart.jsp");
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
		
		// Check if new product within limited amount is added to cart, then set cart
		if (!cart.containsKey(product) && amount <= maxAmount) {
			cart.put(product, amount);
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/product?id=" + id);
			return;
		}
		
		int oldAmount = cart.get(product) == null? 0: cart.get(product);
		int newAmount = oldAmount + amount;
		
		// Notify if the number of product exceed the max product amount allowed
		if (newAmount > maxAmount) {
			String message = "You cannot add more than " + 
					maxAmount + " items" +
					(oldAmount != 0? " (" + oldAmount + " already in cart).": ".");
			session.setAttribute("message", message);
			response.sendRedirect(request.getContextPath() + "/product?id=" + id);
			return;
		}
		
		// Set cart
		cart.replace(product, newAmount);
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath() + "/product?id=" + id);
	}

	private void checkOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		Order order = new Order(email, address);
		String message = order.validate(order);
		
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("cart");
		
		// Check and notify error if cart is empty
		if (cart == null || cart.size() == 0) {
			message = "There is no items for checking out.";
			checkOutPageForward(request, response, message);
			return;
		}
		
		if (!message.equals("Success")) {
			checkOutPageForward(request, response, message);
			return;
		}
		
		// Add orders and orders_details to datasource
		try {
			OrderDao.addOrder(ds, order, cart);
			session.removeAttribute("cart");
			pageForward(request, response, 
					"Checkout successful. Thank you very much!", 
					"/jsp/success.jsp");
		} catch (SQLException e) {
			message = "Our server is temporary down, please try again later.";
			checkOutPageForward(request, response, message);
			e.printStackTrace();
		}
	}

	private void pageForward(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		pageForward(request, response, null, page);
	}
	
	private void pageForward(HttpServletRequest request, HttpServletResponse response, String message, String page)
			throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
