package com.revature.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ReimbEmpViewPendServlet
 */
@WebServlet("/ReimbEmpViewPendServlet")
public class ReimbEmpViewPendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReimbursementDbUtil reimbursementDbUtil;

	@Resource(name = "jdbc/project1")
	private DataSource dataSource;

	/*
	 * public ReimbEmpViewPendServlet() { super(); // TODO Auto-generated
	 * constructor stub }
	 */

	@Override
	public void init() throws ServletException {
		super.init();

		// Create our employee db util and pass in the conn pool / datasource
		try {
			reimbursementDbUtil = new ReimbursementDbUtil(dataSource);
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

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if command missing, list employees
			if (theCommand == null) {
				theCommand = "LIST";
			}

			// route to the appropriate method
			switch (theCommand) {

			case "LIST":
				listPending(request, response);
				break;

			case "ADD":
				addRequest(request, response);
				break;
			default:
				listPending(request, response);

			} // end switch

		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void addRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read employee info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String comment = request.getParameter("comment");
		String amount = request.getParameter("amount");
		String status = "pending";
		int empId = Integer.parseInt(getUserFromCookie(request, response));

		// int amount = 1; // TODO find a way to parse to int, or convert databse column
		// to VARCHAR and ints to Strings.

		// create a new employee object
		Reimbursement theReimbursement = new Reimbursement(firstName, lastName, comment, amount, status, empId);

		// add the employee to the database
		reimbursementDbUtil.addReimbursement(theReimbursement);

		// send back to the main page (the employee list)
		listPending(request, response);

	}

	private void listPending(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = getUserFromCookie(request, response);

		// get employees from db util
		List<Reimbursement> reimbursements = reimbursementDbUtil.getReimbursements(user);

		// add employees to the request
		request.setAttribute("EPENDING_LIST", reimbursements);

		// send to JSP page (the view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employee/list-epending.jsp");
		dispatcher.forward(request, response);
	}

	private String getUserFromCookie(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Retrieve cookie for UserName. Cookies are retrieved from server on request
		// object!
		Cookie[] theCookies = request.getCookies();
		String user = "";

		if (theCookies != null) {
			for (Cookie temp : theCookies) {
				if ("loggedIn".equals(temp.getName())) {
					System.out.println("***" + temp.getValue() + "***");
					user = temp.getValue();
				}
			}
		}
		return user;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
