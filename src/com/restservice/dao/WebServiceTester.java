package com.restservice.dao;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class WebServiceTester {
	
	private Client client;
	   private String REST_SERVICE_URL = "http://localhost:8080/EmployeeRest/rest/EmployeeService/employees";
	   private static final String SUCCESS_RESULT="<result>success</result>";
	   private static final String PASS = "pass";
	   private static final String FAIL = "fail";

	   private void init(){
	      this.client = ClientBuilder.newClient();
	   }

	   public static void main(String[] args){
	      WebServiceTester tester = new WebServiceTester();
	      //initialize the tester
	      tester.init();
	      //test get all users Web Service Method
	      tester.testGetAllUsers();
	      //test get user Web Service Method 
	      tester.testGetUser();
	      //test update user Web Service Method
	      tester.testUpdateUser();
	      //test add user Web Service Method
	      tester.testAddUser();
	      //test delete user Web Service Method
	      tester.testDeleteUser();
	   }
	   //Test: Get list of all users
	   //Test: Check if list is not empty
	   private void testGetAllUsers(){
	      GenericType<List<Employee>> list = new GenericType<List<Employee>>() {};
	      List<Employee> users = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_XML)
	         .get(list);
	      String result = PASS;
	      if(users.isEmpty()){
	         result = FAIL;
	      }
	      System.out.println("Test case name: testGetAllUsers, Result: " + result );
	   }
	   //Test: Get User of id 1
	   //Test: Check if user is same as sample user
	   private void testGetUser(){
	      Employee sampleEmployee = new Employee();
	      sampleEmployee.setId(1);

	      Employee user = client
	         .target(REST_SERVICE_URL)
	         .path("/{employeeid}")
	         .resolveTemplate("employeeid", 1)
	         .request(MediaType.APPLICATION_XML)
	         .get(Employee.class);
	      String result = FAIL;
	      if(sampleEmployee != null && sampleEmployee.getId() == user.getId()){
	         result = PASS;
	      }
	      System.out.println("Test case name: testGetEmployee, Result: " + result );
	   }
	   //Test: Update User of id 1
	   //Test: Check if result is success XML.
	   private void testUpdateUser(){
	      Form form = new Form();
	      form.param("id", "1");
	      form.param("firstname", "coucou");
	      form.param("lastname", "test");
	      form.param("mail", "suresh@test.com");
	      form.param("job", "programmer");
	      form.param("departmentid", "2");

	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_XML)
	         .post(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            String.class);
	      String result = PASS;
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	      }

	      System.out.println("Test case name: testUpdateEmployee, Result: " + result );
	   }
	   //Test: Add User of id 2
	   //Test: Check if result is success XML.
	   private void testAddUser(){
	      Form form = new Form();
	      form.param("id", "2");
	      form.param("firstname", "toto");
	      form.param("lastname", "tata");
	      form.param("mail", "titi@test.com");
	      form.param("job", "programmerrrrr");
	      form.param("departmentid", "1");

	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_XML)
	         .put(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            String.class);
	   
	      String result = PASS;
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	      }

	      System.out.println("Test case name: testAddEmployee, Result: " + result );
	   }
	   //Test: Delete User of id 2
	   //Test: Check if result is success XML.
	   private void testDeleteUser(){
	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .path("/{employeeid}")
	         .resolveTemplate("employeeid", 2)
	         .request(MediaType.APPLICATION_XML)
	         .delete(String.class);

	      String result = PASS;
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	      }

	      System.out.println("Test case name: testDeleteEmployee, Result: " + result );
	   }

      
}