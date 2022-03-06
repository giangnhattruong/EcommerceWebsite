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
	}

	private void showProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = StringUtils.getString(request.getParameter("id"));
		
		// If no id from param, redirect home
		if (id.equals("")) {
			redirectHome(request, response);
			return;
		}
		
		// Check and get to product details page or show error page if product not found
		try {
			Product product = ProductDao.getProduct(ds, id);
			if (product == null)
				pageForward(request, response, "Product not found.", "/jsp/error.jsp");
			else 
				pageForward(request, response, product, "/jsp/productDetails.jsp");
				HttpSession session = request.getSession();
				session.removeAttribute("message");
		} catch (SQLException e) {
			pageForward(request, response, 
					"Our server is temporary down, please try again later.", "/jsp/error.jsp");
			e.printStackTrace();
		}
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
