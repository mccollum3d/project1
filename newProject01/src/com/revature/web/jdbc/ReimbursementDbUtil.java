package com.revature.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ReimbursementDbUtil {

	private DataSource dataSource;

	public ReimbursementDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Reimbursement> getReimbursements() throws Exception {

		List<Reimbursement> reimbursements = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "SELECT * FROM reimbursement ORDER BY id";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				// Retrieve data from result set row
				int id = myRs.getInt("ID");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String comment = myRs.getString("comment");
				String amount = myRs.getString("Amount");
				String status = myRs.getString("status");

				// Create new reimbursement object
				Reimbursement tempReimbursement = new Reimbursement(id, firstName, lastName, comment, amount, status);

				// Add it to the list of reimbursements
				reimbursements.add(tempReimbursement);
			}

			return reimbursements;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);

		}
	} // end getReimbursements function

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

	public void addReimbursement(Reimbursement theReimbursement) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		int empId = theReimbursement.getEmpId();
		System.out.println(empId + " <== The Foreign Key is");

		try {
			// create sql for insert
			myConn = dataSource.getConnection();

			String sql = "INSERT INTO reimbursement " + "(First_Name, Last_Name, Comment, Amount, Status, empId) "
					+ "values (?, ?, ?, ?, ?, " + empId + ")";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the reimbursement
			myStmt.setString(1, theReimbursement.getFirstName());
			myStmt.setString(2, theReimbursement.getLastName());
			myStmt.setString(3, theReimbursement.getComment());
			myStmt.setString(4, theReimbursement.getAmount());
			myStmt.setString(5, theReimbursement.getStatus());

			// execute sql insert
			myStmt.execute();

		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);

		}

	}

	public List<Reimbursement> getReimbursements(String user) throws Exception {

		List<Reimbursement> reimbursements = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "SELECT * FROM reimbursement WHERE empId = '" + user + "' ORDER BY id";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				// Retrieve data from result set row
				int id = myRs.getInt("ID");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String comment = myRs.getString("comment");
				String amount = myRs.getString("Amount");
				String status = myRs.getString("status");
				int empId = myRs.getInt("empId");

				// Create new reimbursement object
				Reimbursement tempReimbursement = new Reimbursement(id, firstName, lastName, comment, amount, status, empId);

				// Add it to the list of reimbursements
				reimbursements.add(tempReimbursement);
			}

			return reimbursements;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);

		}
	} // end getReimbursements function
	
	public void approveDeny(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// Call me from a servlet.
	// TODO logic to run an UPDATE statement on database.
		String theCommand = request.getParameter("command");
		String theId = request.getParameter("requestId");
		List<Reimbursement> reimbursements = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		int rowsAffected = 0;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "UPDATE reimbursement SET status = '" + theCommand + "' WHERE id = '" + theId + "'";
			System.out.println("Debug: " + sql);

			myStmt = myConn.createStatement();

			// execute query
			rowsAffected = myStmt.executeUpdate(sql);
			System.out.println("Rows Affected: " + rowsAffected);

			// redirect to view requests
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
	}

}
