package com.revature.web.jdbc;

// CLEAN EXAMPLE OF A BEAN
public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;
	private String loginName;
	private String password;
	private int manager;
	
	// myConstructor
	public Employee(int id, String firstName, String lastName, String loginName, String password, int manager) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginName = loginName;
		this.password = password;
		this.manager = manager;
	}
	
	public Employee(String firstName, String lastName, String loginName, String password, int manager) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginName = loginName;
		this.password = password;
		this.manager = manager;
	}
	
	//used when updating the fields we want employees to be allowed to update (all but id, pw, manager)
	public Employee(int id, String firstName, String lastName, String loginName) {
		super();		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginName = loginName;
	}

	// GETTERS AND SETTERS
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	// FOR DEBUGGING USE
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", loginName=" + loginName
				+ ", password=" + password + ", manager=" + manager + "]";
	}
	
	
	
	
	
}
