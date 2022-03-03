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

/**
 * Servlet implementation class Controller
 */
@WebServlet("/shop")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Account> accounts = new ArrayList<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = getString(request.getParameter("action"));
		String page = route(action, request);

		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String formAction = getString(request.getParameter("formAction"));

		switch (formAction) {
		case "login":
			doLogin(request, response);
			break;
		case "register":
			doRegister(request, response);
			break;
		case "resetPassword":
			doResetPassword(request, response);
			break;
		}
	}

	private String route(String action, HttpServletRequest request) {
		Map<String, String> routes = new HashMap<>();
		routes.put("", "index.jsp");
		routes.put("logout", "index.jsp");
		routes.put("products", "/jsp/products.jsp");
		routes.put("aboutus", "/jsp/aboutus.jsp");
		routes.put("login", "/jsp/login.jsp");
		routes.put("register", "/jsp/register.jsp");
		routes.put("forgotPassword", "/jsp/forgotPassword.jsp");

		checkForLogout(request);

		String page = getString(routes.get(action), "/jsp/error.jsp");
		HttpSession session = request.getSession();

		if ((action.equals("login") || action.equals("register")) && session.getAttribute("username") != null)
			page = "index.jsp";

		return page;
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = getString(request.getParameter("username"));
		String password = getString(request.getParameter("password"));
		String rememberMe = getString(request.getParameter("rememberMe"));

		String page = null;

		if (validateAdmin(username, password) || validateUser(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			if (rememberMe.equals("on"))
				session.setMaxInactiveInterval(604800);
			else
				session.setMaxInactiveInterval(1800);

			page = "index.jsp";
		} else {
			request.setAttribute("message", "Invalid email or password.");
			request.setAttribute("username", username);
			page = "/jsp/login.jsp";
		}

		request.getRequestDispatcher(page).forward(request, response);
	}
	
	private void doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = getString(request.getParameter("username")).toLowerCase();
		String password = getString(request.getParameter("password1"));
		String passwordConfirm = getString(request.getParameter("password2"));
		String page = null;
		
		if (!password.equals(passwordConfirm)) {
			request.setAttribute("message", "Password not matched.");
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
			return;
		}
		
		String validateMessage = validateNewUser(username, password);
		
		if (validateMessage.equals("Success")) {
			Account account = new Account(username, password);
			accounts.add(account);
			
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			page = "index.jsp";
		} else {
			request.setAttribute("message", validateMessage);
			request.setAttribute("username", username);
			page = "/jsp/register.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}
	
	private void doResetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = getString(request.getParameter("username")).toLowerCase();
		String password = getString(request.getParameter("password1"));
		String passwordConfirm = getString(request.getParameter("password2"));
		String page = null;
		
		if (!password.equals(passwordConfirm)) {
			request.setAttribute("message", "Password not matched.");
			request.getRequestDispatcher("/jsp/forgotPassword.jsp").forward(request, response);
			return;
		}
		
		Account foundAccount = accounts.stream()
				.filter(a -> a.getUsername().equals(username)).findAny().orElse(null);
		
		if (foundAccount == null) {
			request.setAttribute("message", "No account was found.");
			request.getRequestDispatcher("/jsp/forgotPassword.jsp").forward(request, response);
			return;
		}
		
		String validateMessage = validateNewUser(username, password);
		
		if (validateMessage.equals("Success")) {
			foundAccount.setPassword(password);
			request.setAttribute("message", "Please login with new password.");
			page = "/jsp/login.jsp";
		} else {
			request.setAttribute("message", validateMessage);
			request.setAttribute("username", username);
			page = "/jsp/forgotPassword.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	private void checkForLogout(HttpServletRequest request) {
		if (request.getParameter("action") != null && request.getParameter("action").equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
		}
	}

	private boolean validateAdmin(String username, String password) {
		ServletContext application = getServletContext();
		String adminUser = application.getInitParameter("adminUser");
		String adminPass = application.getInitParameter("password");

		return username.equalsIgnoreCase(adminUser) && password.equals(adminPass);
	}
	
	private boolean validateUser(String username, String password) {
		Account foundAccount = accounts.stream()
				.filter(a -> a.getUsername().equals(username) &&
						a.getPassword().equals(password))
				.findAny()
				.orElse(null);
		
		return foundAccount != null;
	}
	
	private String validateNewUser(String username, String password) {
		String emailRegex = "\\w+@\\w+\\.\\w+";
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,10}$";
		
		if (!username.matches(emailRegex))
			return "Invalid email.";
		else if (!password.matches(passwordRegex))
			return "Password must contain 6-10 characters, at least 1 special character, 1 uppercase character, 1 lowercase character and 1 digit.";
	
		return "Success";
	}

	private String getString(String input, String defaultString) {
		return input == null ? defaultString : input;
	}

	private String getString(String input) {
		return input == null ? "" : input;
	}

}
