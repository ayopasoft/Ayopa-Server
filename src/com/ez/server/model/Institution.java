package com.ez.server.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ez.server.model.persistence.InstitutionPersistence;
import com.ez.server.utils.AwsFacade;



public class Institution {
	
	public Institution(String id, String name, String shortName, String url,
			String description, boolean ask_Demographics, boolean hr_search,
			boolean hr_review, String country, String state, String city,
			String loc_type, String inst_type, int enroll, String logo,
			String background_color, String foreground_color,
			String header_color, int header_size, String header_font,
			String text_color, int text_size, String text_font,
			List<String> info_urls, List<Map<String, String>> users) {
		super();
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.url = url;
		this.description = description;
		this.ask_Demographics = ask_Demographics;
		this.hr_search = hr_search;
		this.hr_review = hr_review;
		this.country = country;
		this.state = state;
		this.city = city;
		this.loc_type = loc_type;
		this.inst_type = inst_type;
		this.enroll = enroll;
		this.logo = logo;
		this.background_color = background_color;
		this.foreground_color = foreground_color;
		this.header_color = header_color;
		this.header_size = header_size;
		this.header_font = header_font;
		this.text_color = text_color;
		this.text_size = text_size;
		this.text_font = text_font;
		this.info_urls = info_urls;
		this.users = users;
	}

	// General Info
	private String id;
	private String name;
	private String shortName;
	private String url;
	private String description;
	private boolean ask_Demographics;
	private boolean hr_search;
	private boolean hr_review;
	
	// Metadata
	private String country;
	private String state;
	private String city;
	private String loc_type;
	private String inst_type;
	private int enroll; 
	
	// Branding Info
	private String logo;
	private String background_color;
	private String foreground_color;
	private String header_color;
	private int header_size;
	private String header_font;
	private String text_color;
	private int text_size;
	private String text_font;
	
	// URL about the area
	private List<String> info_urls;
	
	// Institute Users
	private List<Map<String,String>> users;
	
	
	
	public String getID(){
		return id;
	}
	
	public void setID(String id){
		this.id = id;
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

	public String getInst_type() {
		return inst_type;
	}

	public void setInst_type(String inst_type) {
		this.inst_type = inst_type;
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

	public String getBackground_color() {
		return background_color;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	public String getForeground_color() {
		return foreground_color;
	}

	public void setForeground_color(String foreground_color) {
		this.foreground_color = foreground_color;
	}

	public String getHeader_color() {
		return header_color;
	}

	public void setHeader_color(String header_color) {
		this.header_color = header_color;
	}

	public int getHeader_size() {
		return header_size;
	}

	public void setHeader_size(int header_size) {
		this.header_size = header_size;
	}

	public String getHeader_font() {
		return header_font;
	}

	public void setHeader_font(String header_font) {
		this.header_font = header_font;
	}

	public String getText_color() {
		return text_color;
	}

	public void setText_color(String text_color) {
		this.text_color = text_color;
	}

	public int getText_size() {
		return text_size;
	}

	public void setText_size(int text_size) {
		this.text_size = text_size;
	}

	public String getText_font() {
		return text_font;
	}

	public void setText_font(String text_font) {
		this.text_font = text_font;
	}

	public List<String> getInfo_urls() {
		return info_urls;
	}

	public void setInfo_urls(List<String> info_urls) {
		this.info_urls = info_urls;
	}

	public List<Map<String, String>> getUsers() {
		return users;
	}

	public void setUsers(List<Map<String, String>> users) {
		this.users = users;
	}
	
	// Post Institution to the database
	public void putInstitution() throws IOException{
		Institution inst = this;
		InstitutionPersistence ip = new InstitutionPersistence();
		ip.putInstitution(inst);
	}
	
	// get all the institutions
	public static List<Map<String,String>> getInstitutions() throws IOException {
		
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.INSTITUTION
		+ "` where `" + AwsFacade.Key.INSTITUTION_NAME + "` like '%'"
		+ " order by `" + AwsFacade.Key.INSTITUTION_NAME + "`";
		
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
	// get a seingle institution
	public static List<Map<String,String>> getSingleInstitution(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.INSTITUTION
		+ "` where `" + AwsFacade.Key.INSTITUTION_ID + "` = " + id;
		
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
	
	

}
