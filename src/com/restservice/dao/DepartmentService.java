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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/DepartmentService")
public class DepartmentService {
	
	DepartmentDao depDao = new DepartmentDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	
	@GET
	@Path("/department")
	@Produces(MediaType.APPLICATION_XML)
	public List<Department> getDepartments() {
		return depDao.getAllDepartments();
	}
	

	   @GET
	   @Path("/department/{departmentid}")
	   @Produces(MediaType.APPLICATION_XML)
	   public Department getDepartment (@PathParam("departmentid") int departmentid){
	      return depDao.getDepartment(departmentid);
	   }

	   @PUT
	   @Path("/department")
	   @Produces(MediaType.APPLICATION_XML)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public String createDepartment(@FormParam("id") int id,
	      @FormParam("name") String name,
	      @Context HttpServletResponse servletResponse) throws IOException{
	      Department dep = new Department(id, name);
	      int result = depDao.createDepartment(dep);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }

	   @POST
	   @Path("/department")
	   @Produces(MediaType.APPLICATION_XML)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public String updateDepartment(@FormParam("id") int id,
	      @FormParam("name") String name,
	      @Context HttpServletResponse servletResponse) throws IOException{
	      Department dep = new Department(id, name);
	      int result = depDao.updateDepartment(dep);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }

	   @DELETE
	   @Path("/department/{departmentid}")
	   @Produces(MediaType.APPLICATION_XML)
	   public String deleteDepartment(@PathParam("departmentid") int departmentid){
	      int result = depDao.deleteDepartment(departmentid);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }

	   @OPTIONS
	   @Path("/department")
	   @Produces(MediaType.APPLICATION_XML)
	   public String getSupportedOperations(){
	      return "<operations>GET, PUT, POST, DELETE</operations>";
	   }
	

}
