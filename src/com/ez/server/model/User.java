package com.ez.server.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ez.server.utils.AwsFacade;

public class User {

	
	private String id;
	private String unit_ID;
	private String first;
	private String last;
	private String role;
	
	public User(String id, String unit_ID, String first, String last,
			String role) {
		super();
		this.id = id;
		this.unit_ID = unit_ID;
		this.first = first;
		this.last = last;
		this.role = role;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnit_ID() {
		return unit_ID;
	}

	public void setUnit_ID(String unit_ID) {
		this.unit_ID = unit_ID;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	// Post a user to the database
	public void putUser(){
		
	}
	
	// Get all users from the database
	public static List<Map<String,String>> getAllUsr() throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.USER
		+ "` where `" + AwsFacade.Key.USER_LAST + "` like '%'"
		+ " order by `" + AwsFacade.Key.USER_LAST + "`";
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
	// Get all users for a specific unit
	public static List<Map<String,String>> getUnitUsr(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.USER
		+ "` where `" + AwsFacade.Key.USER_UNIT_ID + "` = " + id
		+ " order by '" + AwsFacade.Key.USER_LAST + "`";
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
	// Get a single user
	public static List<Map<String,String>> getOneUsr(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.USER
		+ "` where `" + AwsFacade.Key.USER_ID + "` = " + id;
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
}
