package com.restservice.dao;

import java.util.ArrayList;
import java.util.List;

public class VirtualDatabase {
	
	public final static VirtualDatabase INSTANCE = new VirtualDatabase();
	   
	protected List<Employee> employees;
	protected List<Department> departments;
	
	private VirtualDatabase() {
		employees = new ArrayList<Employee>();
		employees.add(new Employee(1, "alexandre", "soler", "asoler@mail.eu", 1, "god programmer"));
		departments = new ArrayList<Department>();
		departments.add(new Department(1, "sales"));
		departments.add(new Department(3, "test"));
		
	}
	
	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}
	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	/**
	 * @return the departments
	 */
	public List<Department> getDepartments() {
		return departments;
	}
	/**
	 * @param departments the departments to set
	 */
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
}
