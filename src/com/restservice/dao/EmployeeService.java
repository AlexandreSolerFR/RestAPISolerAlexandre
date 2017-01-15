package com.restservice.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/EmployeeService")
public class EmployeeService {

	EmployeeDao employeeDao = new EmployeeDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";

	@GET
	@Path("/employees")
	@Produces(MediaType.APPLICATION_XML)
	public List<Employee> getEmployees() {
		return employeeDao.getAllEmployees();
	}

	@GET
	@Path("/employees/{employeeid}")
	@Produces(MediaType.APPLICATION_XML)
	public Employee getEmployee(@PathParam("employeeid") int employeeid){
		return employeeDao.getEmployee(employeeid);
	}
	
	@GET
	@Path("/employees/department/{departmentid}")
	@Produces(MediaType.APPLICATION_XML)
	public List<Employee> getEmployeeByDepartment(@PathParam("departmentid") int departmentid){
		return employeeDao.getAllEmployeesByDepartment(departmentid);
	}
	
	@GET
	@Path("/employees/department/name/{departmentName}")
	@Produces(MediaType.APPLICATION_XML)
	public List<Employee> getEmployeeByDepartmentName(@PathParam("departmentName") String departmentname){
		return employeeDao.getAllEmployeesByDepartment(departmentname);
	}
	
	@GET
	@Path("/employees/custom/{fieldname}/{fieldvalue}")
	@Produces(MediaType.APPLICATION_XML)
	public List<Employee> getEmployeeByCustomFields(@PathParam("fieldname") String fieldname, @PathParam("fieldvalue") String fieldvalue){
		return employeeDao.getAllEmployeesByField(fieldname, fieldvalue);
	}

	@PUT
	@Path("/employees")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createEmployee(@FormParam("id") int id,
			@FormParam("firstname") String firstName,
			@FormParam("lastname") String lastName,
			@FormParam("mail") String mail,
			@FormParam("job") String job,
			@FormParam("departmentid") int depid,
			@Context HttpServletResponse servletResponse) throws IOException {
		Employee employee = new Employee(id, firstName, lastName, mail, depid, job);
		int result = employeeDao.createEmployee(employee);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@POST
	@Path("/employees")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateEmployee(@FormParam("id") int id,
			@FormParam("firstname") String firstName,
			@FormParam("lastname") String lastName,
			@FormParam("mail") String mail,
			@FormParam("job") String job,
			@FormParam("departmentid") int depid,
			@Context HttpServletResponse servletResponse) throws IOException {
		Employee employee = new Employee(id, firstName, lastName, mail, depid, job);
		int result = employeeDao.updateEmployee(employee);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@PATCH
	@Path("/employees")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String patchEmployee(
			@FormParam("id") int id,
			@QueryParam("optional1") String firstName,
			@QueryParam("lastname") String lastName,
			@QueryParam("mail") String mail,
			@QueryParam("job") String job,
			@QueryParam("departmentid") int depid,
			@Context HttpServletResponse servletResponse) throws IOException {
		int result = employeeDao.patchEmployee(id, firstName, lastName, mail, job, depid);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}


	@DELETE
	@Path("/employees/{employeeid}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteEmployee(@PathParam("employeeid") int employeeid){
		int result = employeeDao.deleteEmployee(employeeid);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@OPTIONS
	@Path("/employees")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations(){
		return "<operations>GET, PUT, POST, PATCH, DELETE</operations>";
	}


}
