package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null)
			response.sendRedirect(request.getContextPath() + "/admin");
		else
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRegister(request, response);
	}
	
	private void doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//get list of accounts from application
		List<Account> accounts = getAccounts();
		
		String username = StringUtils.getString(request.getParameter("username")).toLowerCase();
		String password = StringUtils.getString(request.getParameter("password1"));
		String passwordConfirm = StringUtils.getString(request.getParameter("password2"));
		
		//check if password confirm is matched with password
		if (!password.equals(passwordConfirm)) {
			request.setAttribute("message", "Password not matched.");
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
			return;
		}
		
		//check if account exists
		Account foundUser = Account.findUser(username, accounts);
		if (foundUser != null) {
			request.setAttribute("message", "User already existed.");
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
			return;
		}
		
		//validate new user and set session
		String validateMessage = Account.validateNewUser(username, password);
		if (validateMessage.equals("Success")) {
			Account account = new Account(username, password);
			
			//Add list of accounts to Servlet Context
			accounts.add(account);
			getServletContext().setAttribute("accounts", accounts);
			
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			request.setAttribute("message", validateMessage);
			request.setAttribute("username", username);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/register.jsp");
			dispatcher.forward(request, response);
		}
	}

	private List<Account> getAccounts() {
		ServletContext application = getServletContext();
		List<Account> accounts = (ArrayList<Account>) application.getAttribute("accounts");
		if (accounts == null) {
			accounts = new ArrayList<>();
			application.setAttribute("accounts", accounts);
		}
		return accounts;
	}

}
