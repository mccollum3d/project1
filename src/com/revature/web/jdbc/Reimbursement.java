package com.revature.web.jdbc;

// Reimbursement Bean
public class Reimbursement {
	private int id;
	private String firstName;
	private String lastName;
	private String comment;
	private String amount;
	private String status;
	private int empId;
	
	
	
	public Reimbursement(int id, String firstName, String lastName, String comment, String amount, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.comment = comment;
		this.amount = amount;
		this.status = status;
	}
	
	public Reimbursement(String firstName, String lastName, String comment, String amount, String status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.comment = comment;
		this.amount = amount;
		this.status = status;
	}
	
	
	
	public Reimbursement(int id, String firstName, String lastName, String comment, String amount, String status, int empId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.comment = comment;
		this.amount = amount;
		this.status = status;
		this.empId = empId;
		//System.out.println("Reimbursement Object Successfully Created! empId FK = " + empId);
	}

	public Reimbursement(String firstName, String lastName, String comment, String amount, String status, int empId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.comment = comment;
		this.amount = amount;
		this.status = status;
		this.empId = empId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	// FOR DEBUGGING
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", comment="
				+ comment + ", amount=" + amount + ", status=" + status + ", empId=" + empId + "]";
	}
	
	
	
	
	
	
}
