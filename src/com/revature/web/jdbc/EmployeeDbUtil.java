package com.revature.web.jdbc;

// CLEAN EXAMPLE OF A DATABASE UTILITY CLASS (helper class)
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployeeDbUtil {

	private DataSource dataSource; 
	
	public EmployeeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Employee> getEmployees() throws Exception {
		
		List<Employee> employees = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "SELECT * FROM employees ORDER BY id";
			
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
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		
		}

	} // end getEmployees function

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close();
				// puts the connection back in the pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	} //end close function
	
} // end class
