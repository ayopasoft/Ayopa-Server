package com.ayopa.server.model;

public class AuctionDTO {
	private String title;
	private String link;
	private String image;
	private double start_price;
	private double current_price;
	private double next_price;
	private double lowest_price;
	private int start_quant;
	private int current_quant;
	private int next_quant;
	private int highest_quant;
	private double purchase_price;
	private int time_seconds;
	private int time_minutes;
	private int time_hours;
	private int time_days;
	

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public double getStart_price() {
		return start_price;
	}
	public double getCurrent_price() {
		return current_price;
	}
	public double getNext_price() {
		return next_price;
	}
	public double getLowest_price() {
		return lowest_price;
	}
	public double getPurchase_price() {
		return purchase_price;
	}
	public void setStart_price(double start_price) {
		this.start_price = start_price;
	}
	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}
	public void setNext_price(double next_price) {
		this.next_price = next_price;
	}
	public void setLowest_price(double lowest_price) {
		this.lowest_price = lowest_price;
	}
	public void setPurchase_price(double purchase_price) {
		this.purchase_price = purchase_price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getStart_quant() {
		return start_quant;
	}
	public void setStart_quant(int start_quant) {
		this.start_quant = start_quant;
	}
	public int getCurrent_quant() {
		return current_quant;
	}
	public void setCurrent_quant(int current_quant) {
		this.current_quant = current_quant;
	}
	public int getNext_quant() {
		return next_quant;
	}
	public void setNext_quant(int next_quant) {
		this.next_quant = next_quant;
	}
	public int getHighest_quant() {
		return highest_quant;
	}
	public void setHighest_quant(int highest_quant) {
		this.highest_quant = highest_quant;
	}
	
	public int getTime_seconds() {
		return time_seconds;
	}
	public void setTime_seconds(int time_seconds) {
		this.time_seconds = time_seconds;
	}
	public int getTime_minutes() {
		return time_minutes;
	}
	public void setTime_minutes(int time_minutes) {
		this.time_minutes = time_minutes;
	}
	public int getTime_hours() {
		return time_hours;
	}
	public void setTime_hours(int time_hours) {
		this.time_hours = time_hours;
	}
	public int getTime_days() {
		return time_days;
	}
	public void setTime_days(int time_days) {
		this.time_days = time_days;
	}
	
	
	
	
	
}
