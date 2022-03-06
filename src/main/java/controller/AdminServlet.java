package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import model.Account;
import myutils.StringUtils;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/ShoppingDB")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		
		HttpSession session = request.getSession();
		List<Account> accounts = new ArrayList<>();
		String action = StringUtils.getString(request.getParameter("action"));
		String page = route(action, request);
		
		if (session.getAttribute("username") == null || !session.getAttribute("username").equals("admin")) {
			response.sendRedirect(request.getContextPath() + "/shop");
		}
		else {
			try {
				accounts = AccountDao.getAccounts(ds);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				request.setAttribute("accounts", accounts);
				request.getRequestDispatcher(page).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
	}

	private String route(String action, HttpServletRequest request) {
		Map<String, String> routes = new HashMap<>();
		routes.put("", "/jsp/admin/dashboard.jsp");
		routes.put("staffMembers", "/jsp/admin/staffMembers.jsp");
		String page = StringUtils.getString(routes.get(action), "/jsp/error.jsp");
		return page;
	}

}
