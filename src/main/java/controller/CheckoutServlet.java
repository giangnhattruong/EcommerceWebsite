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
import model.Account;
import model.Order;
import model.Product;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/ShoppingDB")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		routeToCheckOutPage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		checkOut(request, response);
	}

	private void routeToCheckOutPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("username");
		
		if (email == null) {
			pageForward(request, response, "/jsp/checkOutCart.jsp");
			return;
		}
		
		try {
			Account account = AccountDao.getAccount(ds, email);
			request.setAttribute("email", email);
			request.setAttribute("address", account.getAddress());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pageForward(request, response, "/jsp/checkOutCart.jsp");
		}
	}

	private void checkOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		Order order = new Order(email, address);
		String message = order.validate(order);
		
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("cart");
		
		if (cart == null) {
			message = "There is no items for checking out.";
			pageForward(request, response, message, "/jsp/checkOutCart.jsp");
			return;
			
		}
		
		if (!message.equals("Success")) {
			pageForward(request, response, message, "/jsp/checkOutCart.jsp");
			return;
		}
		
		try {
			OrderDao.addOrder(ds, order, cart);
			session.removeAttribute("cart");
			pageForward(request, response, 
					"Checkout successful. Thank you very much!", 
					"/jsp/success.jsp");
		} catch (SQLException e) {
			pageForward(request, response, 
					"Our server is temporary down, please try again later.", 
					"/jsp/checkOutCart.jsp");
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
