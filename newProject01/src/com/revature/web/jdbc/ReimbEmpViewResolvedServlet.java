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
 * Servlet implementation class ReimbEmpViewResolvedServlet
 */
@WebServlet("/ReimbEmpViewResolvedServlet")
public class ReimbEmpViewResolvedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReimbursementDbUtil reimbursementDbUtil;

	@Resource(name = "jdbc/project1")
	private DataSource dataSource;

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
	 * @see HttpServlet#HttpServlet()
	 */
	public ReimbEmpViewResolvedServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");

			if (theCommand == null) {
				theCommand = "LIST";
			}
			switch (theCommand) {

			case "LIST":
				listPending(request, response);
				break;
			// Room to expand servlet functionality later
			default:
				listPending(request, response);
			}
		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void listPending(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = getUserFromCookie(request, response);

		// get employees from db util
		List<Reimbursement> reimbursements = reimbursementDbUtil.getReimbursements(user);

		// add students to the request
		request.setAttribute("EPENDING_LIST", reimbursements);

		// send to JSP page (the view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employee/list-eresolved.jsp");
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
