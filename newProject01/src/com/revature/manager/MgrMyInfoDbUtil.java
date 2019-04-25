package com.revature.manager;

// Contains functionality to getEmployee by user login name.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;

import com.revature.web.jdbc.Employee;

public class MgrMyInfoDbUtil {
	private DataSource dataSource;

	public MgrMyInfoDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	// Managers are still stored in Employee objects
	// String user is passed in with the cookie value of the current user!
	// To get cookies, you need a request object to run request.getCookies!

	public List<Employee> getEmployees(String user) throws Exception {

		List<Employee> employees = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "SELECT * FROM employees WHERE id = '" + user + "' ORDER BY id";
			System.out.println(sql);

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("ID");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String loginName = myRs.getString("login_name");
				String password = myRs.getString("password");
				int manager = myRs.getInt("manager");

				// Create new employees object
				Employee tempEmployee = new Employee(id, firstName, lastName, loginName, password, manager);

				// Add it to the list of employees
				employees.add(tempEmployee);
			}

			return employees;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);

		}

	} // end getEmployees function

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
				// puts the connection back in the pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	} // end close function

	public Employee getEmployee(String empId) throws Exception {
		// int id = Integer.parseInt(empId);

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		Employee tempEmployee = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "SELECT * FROM employees WHERE id = '" + empId + "' ORDER BY id";
			System.out.println(sql);

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			if (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("ID");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String loginName = myRs.getString("login_name");
				String password = myRs.getString("password");
				int manager = myRs.getInt("manager");

				// Create new employees object
				tempEmployee = new Employee(id, firstName, lastName, loginName, password, manager);

			} else {
				throw new Exception("Could not find employee id: " + empId);
			}

			return tempEmployee;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);

		}

	}

	public void updateEmployee(Employee theEmp) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement			add the int here so the string constructor converts it to a String for us
			String sql = "UPDATE employees set first_name=?, last_name=?, login_name=? where id=" + theEmp.getId();
			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theEmp.getFirstName());
			myStmt.setString(2, theEmp.getLastName());
			myStmt.setString(3, theEmp.getLoginName());

			// execute SQL statement
			myStmt.execute();
			
		} finally {
			// Close JDBC connection
			close(myConn, myStmt, null);
		}
	}

} // end class
