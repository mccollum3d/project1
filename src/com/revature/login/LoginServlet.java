package com.revature.login;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.revature.web.jdbc.Employee;
import com.revature.web.jdbc.EmployeeDbUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeDbUtil employeeDbUtil;

	@Resource(name = "jdbc/project1")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	// Overriding from GenericServlet parent class
	@Override
	public void init() throws ServletException {
		super.init();

		// Create our employee db util and pass in the conn pool / datasource
		try {
			employeeDbUtil = new EmployeeDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// List the employees ... in MVC fashion
			retrieveLogins(request, response);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	// index.html login button sends doPost request, which calls this, which checks
	// logins.
	private void retrieveLogins(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Boolean success = false;

		// get employees from db util
		List<Employee> employees = employeeDbUtil.getEmployees();

		// DEBUGGING BLOCK
		System.out.println("Running LoginServlet/RetrieveLogins()");
		System.out.println("request = " + request + " response = " + response);
		System.out.println("Submitted login " + login + " submitted password " + password);
		System.out.println("Session status boolean is " + request.getSession(false));
		// END DEBUGGING BLOCK

		for (int i = 0; i < employees.size(); i++) {

			String tempLogin = employees.get(i).getLoginName();
			String tempPassword = employees.get(i).getPassword();
			String tempId = "" + employees.get(i).getId();
			// DEBUGGING BLOCK
			System.out.println(employees.get(i).getLoginName());
			System.out.println(employees.get(i).getPassword());
			// END DEBUGGING BLOCK

			// Check login/password
			if (tempLogin.equals(login) && tempPassword.equals(password)) {
				System.out.println("Successful login!");

				Cookie loggedIn = new Cookie("loggedIn", tempId);
				loggedIn.setMaxAge(3600);
				response.addCookie(loggedIn);
				
				// VALIDATE/INVALIDATE SESSION
				HttpSession session = request.getSession(true);
				session.setAttribute("User", tempId);
				System.out.println("Session Attribute set: " + session.getAttribute("User"));
				System.out.println(request.getSession(false));

				// if employee match, redirect to empHomepage
				if (employees.get(i).getManager() == 0) {
					response.sendRedirect("/newProject01/employee/ehome.jsp");
					success = true;
					break;
				}

				// if manager match, redirect to mgrHomepage
				if (employees.get(i).getManager() == 1) {
					// RequestDispatcher dispatcher2 =
					// request.getRequestDispatcher("/manager/mhome.html");
					// dispatcher2.forward(request, response);
					// ServletResponse sr = response.sendRedirect		
					response.sendRedirect("/newProject01/manager/mhome.jsp");
					success = true;
					break;
				}

			} 

		}
		
		if (!success) {
			RequestDispatcher dispatcher3 = request.getRequestDispatcher("index.html");
			dispatcher3.forward(request, response);
			
			System.out.println("Login/Password combo does not match. No logins found.");
		}
		
	}

}
