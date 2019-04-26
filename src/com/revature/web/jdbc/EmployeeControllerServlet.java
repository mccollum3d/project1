package com.revature.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeDbUtil employeeDbUtil;
	
	@Resource(name="jdbc/project1")
	private DataSource dataSource;
	
	// Overriding from GenericServlet parent class 
	@Override
	public void init() throws ServletException {
		super.init();
		
		// Create our employee db util and pass in the conn pool / datasource
		try {
			employeeDbUtil = new EmployeeDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// List the employees ... in MVC fashion
			listEmployees(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	
	}


	private void listEmployees(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		// get employees from db util
		List<Employee> employees = employeeDbUtil.getEmployees();
		
		// add employees to the request
		request.setAttribute("EMPLOYEE_LIST", employees);
		
		// send to JSP page (the view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manager/list-employees.jsp");
		dispatcher.forward(request, response);
		
		//response.sendRedirect("/newProject01/manager/list-employees.jsp");
	}

}
