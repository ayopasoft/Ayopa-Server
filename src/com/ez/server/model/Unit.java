package com.ez.server.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ez.server.utils.AwsFacade;

public class Unit {
	
	

	private String id;
	private String parentID;
	private String name;
	private String shortName;
	private String uniqueID;
	private String url;
	private String description;
	private boolean ask_Demographics;		// Ask confidential demographic info 
	private boolean hr_search;				// Require HR Approval for new searches
	private boolean hr_review;				// Require HR Approval to modify review stages
	
	private List<Map<String,String>> infoURLs;
	private List<User> users;
	
	// Metadata
	private String country;
	private String state;
	private String city;
	private String loc_type;
	private String unit_type;
	private int enroll;

	private String logo;
	
	// Constructor
	public Unit(String id, String parentID, String name, String shortName,
			String uniqueID, String url, String description,
			boolean ask_Demographics, boolean hr_search, boolean hr_review,
			List<Map<String, String>> infoURLs, List<User> users,
			String country, String state, String city, String loc_type,
			String unit_type, int enroll, String logo) {
		super();
		this.id = id;
		this.parentID = parentID;
		this.name = name;
		this.shortName = shortName;
		this.uniqueID = uniqueID;
		this.url = url;
		this.description = description;
		this.ask_Demographics = ask_Demographics;
		this.hr_search = hr_search;
		this.hr_review = hr_review;
		this.infoURLs = infoURLs;
		this.users = users;
		this.country = country;
		this.state = state;
		this.city = city;
		this.loc_type = loc_type;
		this.unit_type = unit_type;
		this.enroll = enroll;
		this.logo = logo;
	}
	
	
	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public String getParentID() {
		return parentID;
	}

	
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getShortName() {
		return shortName;
	}

	
	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	
	public boolean isAsk_Demographics() {
		return ask_Demographics;
	}

	
	public void setAsk_Demographics(boolean ask_Demographics) {
		this.ask_Demographics = ask_Demographics;
	}

	
	public boolean isHr_search() {
		return hr_search;
	}

	
	public void setHr_search(boolean hr_search) {
		this.hr_search = hr_search;
	}

	
	public boolean isHr_review() {
		return hr_review;
	}

	
	public void setHr_review(boolean hr_review) {
		this.hr_review = hr_review;
	}
	
	public List<Map<String, String>> getInfoURLs() {
		return infoURLs;
	}

	public void setInfoURLs(List<Map<String, String>> infoURLs) {
		this.infoURLs = infoURLs;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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

	
	public String getUnit_type() {
		return unit_type;
	}

	
	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
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
	
	// Post a Unit to the database
	public void putUnit(){
		
	}
	
	// Get all units from the database
	public static List<Map<String,String>> getAllUnits() throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.UNIT
		+ "` where `" + AwsFacade.Key.UNIT_NAME + "` like '%'"
		+ " order by `" + AwsFacade.Key.UNIT_NAME + "`";
		
		List<Map<String,String>> results = aws.selectRows(query);
		return results;
	}
	
	// Get one units from the database
	public static List<Map<String,String>> getOneUnit(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.UNIT
		+ "` where `" + AwsFacade.Key.UNIT_ID + "` = " + id;
		
		
		List<Map<String,String>> results = aws.selectRows(query);
		return results;
	}
	
	// Get sub-units of a certain unit from the database
	public static List<Map<String,String>> getSubUnits(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.UNIT
		+ "` where `" + AwsFacade.Key.UNIT_PARENT_ID + "` = " + id
		+ " order by `" + AwsFacade.Key.UNIT_NAME + "`";
		
		List<Map<String,String>> results = aws.selectRows(query);
		return results;	
	}



	
	
}
