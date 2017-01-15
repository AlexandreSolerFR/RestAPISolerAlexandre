package com.restservice.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
	
	
	   public Employee getEmployee(int id) {
	      List<Employee> employees = getAllEmployees();

	      for(Employee employee: employees){
	         if(employee.getId() == id){
	            return employee;
	         }
	      }
	      return null;
	   }
	   
	   public List<Employee> getAllEmployeesByDepartment(String value) {
		   List<Employee> employees = getAllEmployees();
		   DepartmentDao dao = new DepartmentDao();
		   Department department = dao.getDepartment(value);
		   List<Employee> resultList = new ArrayList<Employee>();
		   if (department != null) {
			   for (Employee emp : employees) {
				   if (emp.getDepartmentId() == department.getId())
					   resultList.add(emp);
			   }
		   }
		   return resultList;
	   }
	   
	   public List<Employee> getAllEmployeesByDepartment(int value) {
		   List<Employee> employees = getAllEmployees();
		   DepartmentDao dao = new DepartmentDao();
		   Department department = dao.getDepartment(value);
		   List<Employee> resultList = new ArrayList<Employee>();
		   if (department != null) {
			   for (Employee emp : employees) {
				   if (emp.getDepartmentId() == department.getId())
					   resultList.add(emp);
			   }
		   }
		   return resultList;
	   }
	   
	   
	   public List<Employee> getAllEmployeesByField(String field, String value) {
		   List<Employee> employees = getAllEmployees();
		   List<Employee> resultList = new ArrayList<Employee>();
		   Class<Employee> employeeClass = Employee.class;
		   Method m;
		   try {
			   m = employeeClass.getMethod("get"+ field);
			   for (Employee emp : employees) {
				   String result = (String) m.invoke(emp); 
				   if (result.equals(value))
					   resultList.add(emp);
			   }
		   }
		   catch (Exception e) {
			  return null;
		   }
 		   return resultList;
	   }
	   
	   // Create Department if none exist yet.
	   
	   protected void checkDepartment(int depId) {
		   DepartmentDao depDao = new DepartmentDao();
		   if (depDao.getDepartment(depId) == null)
			   depDao.createDepartment(new Department(depId, "Undefined Department"));
	   }
	   
	   public int createEmployee(Employee employee){
		  Employee emp = getEmployee(employee.getId());
	      if(emp == null){
	    	 List<Employee> employees = getAllEmployees();
	         employees.add(employee);
	         checkDepartment(employee.getDepartmentId());
	         saveEmployeeList(employees);
	         return 1;
	      }
	      return updateEmployee(employee);
	   }

	   public int updateEmployee(Employee employee){
	      List<Employee> employees = getAllEmployees();

	      for(Employee emp: employees){
	         if(emp.getId() == employee.getId()){
	            int index = employees.indexOf(emp);			
	            employees.set(index, employee);
	            checkDepartment(employee.getDepartmentId());
	            saveEmployeeList(employees);
	            return 1;
	         }
	      }
	      return 0;
	   }

	   public int deleteEmployee(int id){
		   List<Employee> employees = getAllEmployees();

		      for(Employee emp: employees){
		         if(emp.getId() == id){
		            int index = employees.indexOf(emp);			
		            employees.remove(index);
		            saveEmployeeList(employees);
		            return 1;
		         }
		      }		
		      return 0;
	   }
	
	public List<Employee> getAllEmployees() {
	      List<Employee> employeeList = VirtualDatabase.INSTANCE.getEmployees();
	      return employeeList;
	   }

	   private void saveEmployeeList(List<Employee> employeeList) {
		   VirtualDatabase.INSTANCE.setEmployees(employeeList);
	   }

	public int patchEmployee(int id, String firstName, String lastName, String mail, String job, int depid) {
		Employee emp = getEmployee(id);
		if (emp != null) {
			if (firstName != null) {
				emp.setFirstName(firstName);
			}
			if (lastName != null) {
				emp.setLastName(lastName);
			}
			if (mail != null) {
				emp.setMail(mail);
			}
			if (job != null) {
				emp.setJob(job);
			}
			if (depid > 0) {
				emp.setDepartmentId(depid);
			}
			updateEmployee(emp);
			return 1;
		}
		return 0;
	}   

}
