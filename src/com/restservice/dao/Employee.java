package com.restservice.dao;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
	private int Id;
	private String firstName;
	private String lastName;
	private String mail;
	private int departmentId;
	private String job;
	
	public Employee() {}
	
	public Employee(int id, String firstName, String lastName, String mail, int department, String job) {
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.departmentId = department;
		this.job = job;
	}
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the department
	 */
	public int getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartmentId(int department) {
		this.departmentId = department;
	}
	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

}
