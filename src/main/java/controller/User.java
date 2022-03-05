package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
 * Servlet implementation class User
 */
@WebServlet("/user")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/ShoppingDB")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public User() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String action = StringUtils.getString(request.getParameter("action"));
		String page = route(action, request);

		if (action.equals("logout")) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/shop");
			return;
		}

		if (session.getAttribute("username") == null)
			request.getRequestDispatcher(page).forward(request, response);
		else
			response.sendRedirect(request.getContextPath() + "/admin");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String formAction = request.getParameter("formAction");
		
		switch (formAction) {
		case "login":
			doLogin(request, response);
			break;
		case "resetPassword":
			doResetPassword(request, response);
			break;
		case "register":
			doRegister(request, response);
			break;
		}
	}

	private String route(String action, HttpServletRequest request) {
		Map<String, String> routes = new HashMap<>();
		routes.put("", "/jsp/login.jsp");
		routes.put("login", "/jsp/login.jsp");
		routes.put("forgotPassword", "/jsp/forgotPassword.jsp");
		routes.put("register", "/jsp/register.jsp");

		return StringUtils.getString(routes.get(action), "/jsp/error.jsp");
	}

	private boolean validateAdmin(String username, String password) {
		ServletContext application = getServletContext();
		String adminUser = application.getInitParameter("adminUser");
		String adminPass = application.getInitParameter("password");

		return username.equalsIgnoreCase(adminUser) && password.equals(adminPass);
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = StringUtils.getString(request.getParameter("username"));
		String password = StringUtils.getString(request.getParameter("password"));
		String rememberMe = StringUtils.getString(request.getParameter("rememberMe"));

		try {
			if (validateAdmin(username, password) || AccountDao.doesUserExist(ds, username, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);

				if (rememberMe.equals("on"))
					session.setMaxInactiveInterval(604800);
				else
					session.setMaxInactiveInterval(1800);

				response.sendRedirect(request.getContextPath() + "/admin");
			} else {
				formForward(request, response, username, 
						"Invalid email or password.", "/jsp/login.jsp");
			}
		} catch (SQLException | IOException | ServletException e) {
			formForward(request, response, username,
					"Our server is temporary down, please try again later.", "/user");
			e.printStackTrace();
		}
	}

	private void doResetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = StringUtils.getString(request.getParameter("username")).toLowerCase();
		String password = StringUtils.getString(request.getParameter("password1"));
		String passwordConfirm = StringUtils.getString(request.getParameter("password2"));

		try {
			Account foundAccount = AccountDao.findUser(ds, username);

			// check if user exists
			if (foundAccount == null) {
				formForward(request, response, username, 
						"User does not exist.", "/jsp/forgotPassword.jsp");
				return;
			}

			// check if password confirm is matched with password
			if (!password.equals(passwordConfirm)) {
				formForward(request, response, username, 
						"Password not matched.", "/jsp/forgotPassword.jsp");
				return;
			}
			
			// validate user with new password and set new password
			String validateMessage = Account.validateUser(password);
			if (validateMessage.equals("Success")) {
				foundAccount.setPassword(password);
				AccountDao.updateAccount(ds, foundAccount);
				formForward(request, response, username, 
						"Please login with new password.", "/jsp/login.jsp");
			} else {
				formForward(request, response, username, 
						validateMessage, "/jsp/forgotPassword.jsp");
			}
		} catch (SQLException e1) {
			formForward(request, response, username,
					"Our server is temporary down, please try again later.", "/user");
			e1.printStackTrace();
		}

	}

	private void doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = StringUtils.getString(request.getParameter("username")).toLowerCase();
		String password = StringUtils.getString(request.getParameter("password1"));
		String passwordConfirm = StringUtils.getString(request.getParameter("password2"));
		String name = StringUtils.getString(request.getParameter("name"));
		String address = StringUtils.getString(request.getParameter("address"));
		String phone = StringUtils.getString(request.getParameter("phone"));
		Account account = new Account(username, password, name, address, phone);

		try {
			// check if account exists
			Account foundAccount = AccountDao.findUser(ds, username);
			
			if (foundAccount != null) {
				formForward(request, response, account,
						"User already existed.", "/jsp/register.jsp");
				return;
			}
			
			// check if password confirm is matched with password
			if (!password.equals(passwordConfirm)) {
				formForward(request, response, account, 
						"Password not matched.", "/jsp/register.jsp");
				return;
			}

			// validate new user and set session
			String validateMessage = Account.validateUser(account);
			if (validateMessage.equals("Success")) {

				// Add account to database
				AccountDao.addAccount(ds, account);

				HttpSession session = request.getSession();
				session.setAttribute("username", username);

				response.sendRedirect(request.getContextPath() + "/admin");
			} else {
				formForward(request, response, account, 
						validateMessage, "/jsp/register.jsp");
			}
		} catch (SQLException e1) {
			formForward(request, response, account,
					"Our server is temporary down, please try again later.", "/user");
			e1.printStackTrace();
		}
	}

	private void formForward(HttpServletRequest request, HttpServletResponse response, String username,
			String validateMessage, String page) throws ServletException, IOException {
		request.setAttribute("message", validateMessage);
		request.setAttribute("username", username);
		request.getRequestDispatcher(page).forward(request, response);
	}

	private void formForward(HttpServletRequest request, HttpServletResponse response, Account account,
			String validateMessage, String page) throws ServletException, IOException {
		request.setAttribute("message", validateMessage);
		request.setAttribute("account", account);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
