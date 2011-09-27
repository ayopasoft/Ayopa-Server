package com.ez.server.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ez.server.model.persistence.SearchPersistence;
import com.ez.server.utils.AwsFacade;

public class Search {
	
	
	// Search info
	private String id;
	private String parent_ID;
	private String position;
	private int refMin;
	private int refMax;
	private String mail;
	private boolean pdf;
	private boolean word;
	private String description;
	private boolean citizenInfo;		// request citizenship info from applicants
	private boolean found; 				// request where applicant found search
	private String adPlaces;			// where is the search advertised
	private boolean offline;
	private boolean online;
	
	private List<String> review;
	
	private List<Procedure> procedureSteps;
	
	private List<String> users;			// list of the users ids for this search
	
	
	// Metadata
	private String country;
	private String state;
	private String city;
	private String loc_type;
	private String position_type;
	private boolean tenure;
	
	
	// constructor
	public Search(String id, String parent_ID, String position, int refMin,
			int refMax, String mail, boolean pdf, boolean word,
			String description, boolean citizenInfo, boolean found,
			String adPlaces, boolean offline, boolean online,
			List<String> review, List<Procedure> procedureSteps, List<String> users,
			String country, String state, String city, String loc_type,
			String position_type, boolean tenure) {
		super();
		this.id = id;
		this.parent_ID = parent_ID;
		this.position = position;
		this.refMin = refMin;
		this.refMax = refMax;
		this.mail = mail;
		this.pdf = pdf;
		this.word = word;
		this.description = description;
		this.citizenInfo = citizenInfo;
		this.found = found;
		this.adPlaces = adPlaces;
		this.offline = offline;
		this.online = online;
		this.review = review;
		this.procedureSteps = procedureSteps;
		this.users = users;
		this.country = country;
		this.state = state;
		this.city = city;
		this.loc_type = loc_type;
		this.position_type = position_type;
		this.tenure = tenure;
	}


	
	
	public String getId() {
		return id;
	}


	
	public void setId(String id) {
		this.id = id;
	}


	
	public String getParent_ID() {
		return parent_ID;
	}


	
	public void setParent_ID(String parent_ID) {
		this.parent_ID = parent_ID;
	}


	
	public String getPosition() {
		return position;
	}


	
	public void setPosition(String position) {
		this.position = position;
	}


	
	public int getRefMin() {
		return refMin;
	}


	
	public void setRefMin(int refMin) {
		this.refMin = refMin;
	}


	
	public int getRefMax() {
		return refMax;
	}


	
	public void setRefMax(int refMax) {
		this.refMax = refMax;
	}


	
	public String getMail() {
		return mail;
	}


	
	public void setMail(String mail) {
		this.mail = mail;
	}


	
	public boolean isPdf() {
		return pdf;
	}


	
	public void setPdf(boolean pdf) {
		this.pdf = pdf;
	}


	
	public boolean isWord() {
		return word;
	}


	
	public void setWord(boolean word) {
		this.word = word;
	}


	
	public String getDescription() {
		return description;
	}


	
	public void setDescription(String description) {
		this.description = description;
	}


	
	public boolean isCitizenInfo() {
		return citizenInfo;
	}


	
	public void setCitizenInfo(boolean citizenInfo) {
		this.citizenInfo = citizenInfo;
	}


	
	public boolean isFound() {
		return found;
	}


	
	public void setFound(boolean found) {
		this.found = found;
	}


	
	public String getAdPlaces() {
		return adPlaces;
	}


	
	public void setAdPlaces(String adPlaces) {
		this.adPlaces = adPlaces;
	}


	
	public boolean isOffline() {
		return offline;
	}


	
	public void setOffline(boolean offline) {
		this.offline = offline;
	}


	
	public boolean isOnline() {
		return online;
	}


	
	public void setOnline(boolean online) {
		this.online = online;
	}


	
	public List<String> getReview() {
		return review;
	}


	
	public void setReview(List<String> review) {
		this.review = review;
	}


	
	public List<Procedure> getProcedureSteps() {
		return procedureSteps;
	}


	
	public void setProcedureSteps(List<Procedure> procedureSteps) {
		this.procedureSteps = procedureSteps;
	}
	
	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
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


	
	public String getPosition_type() {
		return position_type;
	}


	
	public void setPosition_type(String position_type) {
		this.position_type = position_type;
	}


	
	public boolean isTenure() {
		return tenure;
	}


	
	public void setTenure(boolean tenure) {
		this.tenure = tenure;
	}
	
	
	// Post a search to the Database
	public void putSearch() throws IOException{
		Search s = this;
		SearchPersistence sp = new SearchPersistence();
		sp.putSearch(s);
	}
	
	// Get all searches
	public static List<Map<String,String>> getAll() throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.SEARCH
		+ "` where `" + AwsFacade.Key.SEARCH_POSITION + "` like '%'"
		+ " order by `" + AwsFacade.Key.SEARCH_POSITION + "`";
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
	// Get one specific search
	public static List<Map<String,String>> getOne(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.SEARCH
		+ "` where `" + AwsFacade.Key.SEARCH_ID + "` = " + id;
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
	// get All searches for a certain department
	public static List<Map<String,String>> getDeptSearches(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.SEARCH
		+ "` where `" + AwsFacade.Key.SEARCH_PARENT_ID + "` = " + id
		+ " order by `" + AwsFacade.Key.SEARCH_POSITION + "`";
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
}
