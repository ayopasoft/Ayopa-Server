package com.ez.server.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ez.server.model.persistence.DepartmentPersistence;
import com.ez.server.utils.AwsFacade;

public class Department {
	
	

	// Dept information
	private String id;
	private String parent_ID;
	private String name;
	private String uniqueID;
	private String url;
	private String description;
	
	// metadata
	private String country;
	private String state;
	private String city;
	private String loc_type;
	private String discipline_type;
	private int enroll;
	
	private String logo;
	
	// Department Users
	private List<String> users;
	
	
	// Constructor
	public Department(String id, String parent_ID, String name, String uniqueID, String url,
			String description, String country, String state, String city,
			String loc_type, String discipline_type, int enroll, String logo,
			List<String> users) {
		super();
		this.id = id;
		this.parent_ID = parent_ID;
		this.name = name;
		this.uniqueID = uniqueID;
		this.url = url;
		this.description = description;
		this.country = country;
		this.state = state;
		this.city = city;
		this.loc_type = loc_type;
		this.discipline_type = discipline_type;
		this.enroll = enroll;
		this.logo = logo;
		this.users = users;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getParent_Id() {
		return parent_ID;
	}

	public void setParent_Id(String parent_ID) {
		this.parent_ID = parent_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLoc_type() {
		return loc_type;
	}

	public void setLoc_type(String loc_type) {
		this.loc_type = loc_type;
	}

	public String getDiscipline_type() {
		return discipline_type;
	}

	public void setDiscipline_type(String discipline_type) {
		this.discipline_type = discipline_type;
	}

	public int getEnroll() {
		return enroll;
	}

	public void setEnroll(int enroll) {
		this.enroll = enroll;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	// post a department to the database
	public void putDepartment() throws IOException{
		Department dept = this;
		DepartmentPersistence dp = new DepartmentPersistence();
		dp.putDepartment(dept);
	}
	
	// get a single department
	public static List<Map<String,String>> getDepartment(String id) throws IOException{	
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.DEPARTMENT
		+ "` where `" + AwsFacade.Key.DEPARTMENT_ID + "` = " + id;
		
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	// get all departments
	public static List<Map<String,String>> getAllDepartments() throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.DEPARTMENT
		+ "` where `" + AwsFacade.Key.DEPARTMENT_NAME + "` like '%'"
		+ " order by `" + AwsFacade.Key.DEPARTMENT_NAME + "`";
		
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
	// get all departments with the specified parent
	public static List<Map<String,String>> getSubDept(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.DEPARTMENT
		+ "` where `" + AwsFacade.Key.DEPARTMENT_PARENT_ID + "` = " + id
		+ " order by `" + AwsFacade.Key.DEPARTMENT_NAME + "`";
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
}
