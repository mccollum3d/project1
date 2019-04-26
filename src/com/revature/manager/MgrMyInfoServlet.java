package com.revature.manager;

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

import com.revature.web.jdbc.Employee;
import com.revature.web.jdbc.EmployeeDbUtil;

/**
 * Servlet implementation class mgrMyInfoServlet
 */
@WebServlet("/mgrMyInfoServlet")
public class MgrMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MgrMyInfoDbUtil mgrDbUtil;

	@Resource(name = "jdbc/project1")
	private DataSource dataSource;

	// Overriding from GenericServlet parent class
	@Override
	public void init() throws ServletException {
		super.init();

		// Create our employee db util and pass in the conn pool / datasource
		try {
			mgrDbUtil = new MgrMyInfoDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			if (theCommand == null) {
				theCommand = "LIST";
			}

			// route to the appropriate method
			switch (theCommand) {

			case "LIST":
				// List the employees ... in MVC fashion
				listMgrInfo(request, response);
				break;
			case "LOAD":
				loadMgrForUpdate(request, response);
				break;
			case "UPDATE":
				updateMgr(request, response);
				//dispatcher.forward doesn't send them "back" to the page, it needs the refresh to work.
				response.sendRedirect("/newProject01/mgrMyInfoServlet");
				break;
			default:
				listMgrInfo(request, response);

			} // end switch

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void updateMgr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Read employee info from form data
		String empId = request.getParameter("employeeId");
		int id = Integer.parseInt(empId);
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String loginName = request.getParameter("loginName");
	
		// Create a new employee object
		Employee theEmp = new Employee(id, firstName, lastName, loginName);
		
		// Perform update on Database
		mgrDbUtil.updateEmployee(theEmp);
		// Send them back to the "list employees" page
		
	}

	private void loadMgrForUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Read employee ID from form data
		String empId = request.getParameter("employeeId");
		
		// Get employee from dbutil
		Employee theEmp = mgrDbUtil.getEmployee(empId);
		
		// place employee in request attribute
		request.setAttribute("THE_EMPLOYEE", theEmp);
		
		// send to jsp page: update-manager-info.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/update-mgr-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listMgrInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		// get employees from db util, and pass in the current user from the cookie!
		List<Employee> employees = mgrDbUtil.getEmployees(user);

		// add employees to the request
		request.setAttribute("EMPLOYEE_LIST", employees);

		// send to JSP page (the view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/mgrInfo.jsp");
		dispatcher.forward(request, response);

		// response.sendRedirect("/newProject01/manager/list-employees.jsp");
	}

}
