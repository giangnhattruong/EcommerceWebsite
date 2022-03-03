package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import myutils.StringUtils;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = StringUtils.getString(request.getParameter("action"));
		String page = route(action, request);
		
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null)
			response.sendRedirect(request.getContextPath() + "/admin");
		else
			request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String formAction = request.getParameter("formAction");
		
		switch (formAction) {
		case "login":
			doLogin(request, response);
			break;
		case "resetPassword":
			doResetPassword(request, response);
			break;
		}
	}

	private String route(String action, HttpServletRequest request) {
		Map<String, String> routes = new HashMap<>();
		routes.put("", "/jsp/login.jsp");
		routes.put("forgotPassword", "/jsp/forgotPassword.jsp");

		String page = StringUtils.getString(routes.get(action), "/jsp/error.jsp");
		
		return page;
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//get list of accounts from application
		List<Account> accounts = getAccounts();
		
		String username = StringUtils.getString(request.getParameter("username"));
		String password = StringUtils.getString(request.getParameter("password"));
		String rememberMe = StringUtils.getString(request.getParameter("rememberMe"));

		if (validateAdmin(username, password) || 
				Account.doesUserExist(username, password, accounts)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			if (rememberMe.equals("on"))
				session.setMaxInactiveInterval(604800);
			else
				session.setMaxInactiveInterval(1800);

			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			request.setAttribute("message", "Invalid email or password.");
			request.setAttribute("username", username);
			
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
	}
	
	private void doResetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//get list of accounts from application
		List<Account> accounts = getAccounts();
		
		String username = StringUtils.getString(request.getParameter("username")).toLowerCase();
		String password = StringUtils.getString(request.getParameter("password1"));
		String passwordConfirm = StringUtils.getString(request.getParameter("password2"));
		String page = null;
		
		//check if password confirm is matched with password
		if (!password.equals(passwordConfirm)) {
			request.setAttribute("message", "Password not matched.");
			request.getRequestDispatcher("/jsp/forgotPassword.jsp").forward(request, response);
			return;
		}
		
		//check if account exists
		Account foundUser = Account.findUser(username, accounts);
		if (foundUser == null) {
			request.setAttribute("message", "User does not exist.");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/jsp/forgotPassword.jsp").forward(request, response);
			return;
		}
		
		//validate user with new password and set new password
		String validateMessage = Account.validateNewUser(username, password);
		if (validateMessage.equals("Success")) {
			foundUser.setPassword(password);
			request.setAttribute("message", "Please login with new password.");
			page = "/jsp/login.jsp";
		} else {
			request.setAttribute("message", validateMessage);
			request.setAttribute("username", username);
			page = "/jsp/forgotPassword.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	private List<Account> getAccounts() {
		ServletContext application = getServletContext();
		List<Account> accounts = (ArrayList<Account>) application.getAttribute("accounts");
		if (accounts == null)
			accounts = new ArrayList<>();
		return accounts;
	}

	private boolean validateAdmin(String username, String password) {
		ServletContext application = getServletContext();
		String adminUser = application.getInitParameter("adminUser");
		String adminPass = application.getInitParameter("password");

		return username.equalsIgnoreCase(adminUser) && password.equals(adminPass);
	}

}
