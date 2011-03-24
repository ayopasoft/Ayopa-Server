package com.ayopa.server.utils;

public class Auction {
	private String title;
	private String link;
	private String image;
	private Double start_price;
	private Double current_price;
	private Double next_price;
	private Double lowest_price;
	private Integer start_quant;
	private Integer current_quant;
	private Integer next_quant;
	private Integer highest_quant;
	private Double purchase_price;
	private Integer time_seconds;
	private Integer time_minutes;
	private Integer time_hours;
	private Integer time_days;
	

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Double getStart_price() {
		return start_price;
	}
	public Double getCurrent_price() {
		return current_price;
	}
	public Double getNext_price() {
		return next_price;
	}
	public Double getLowest_price() {
		return lowest_price;
	}
	public Double getPurchase_price() {
		return purchase_price;
	}
	public void setStart_price(Double start_price) {
		this.start_price = start_price;
	}
	public void setCurrent_price(Double current_price) {
		this.current_price = current_price;
	}
	public void setNext_price(Double next_price) {
		this.next_price = next_price;
	}
	public void setLowest_price(Double lowest_price) {
		this.lowest_price = lowest_price;
	}
	public void setPurchase_price(Double purchase_price) {
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
	
	public Integer getStart_quant() {
		return start_quant;
	}
	public void setStart_quant(Integer start_quant) {
		this.start_quant = start_quant;
	}
	public Integer getCurrent_quant() {
		return current_quant;
	}
	public void setCurrent_quant(Integer current_quant) {
		this.current_quant = current_quant;
	}
	public Integer getNext_quant() {
		return next_quant;
	}
	public void setNext_quant(Integer next_quant) {
		this.next_quant = next_quant;
	}
	public Integer getHighest_quant() {
		return highest_quant;
	}
	public void setHighest_quant(Integer highest_quant) {
		this.highest_quant = highest_quant;
	}
	
	public Integer getTime_seconds() {
		return time_seconds;
	}
	public void setTime_seconds(Integer time_seconds) {
		this.time_seconds = time_seconds;
	}
	public Integer getTime_minutes() {
		return time_minutes;
	}
	public void setTime_minutes(Integer time_minutes) {
		this.time_minutes = time_minutes;
	}
	public Integer getTime_hours() {
		return time_hours;
	}
	public void setTime_hours(Integer time_hours) {
		this.time_hours = time_hours;
	}
	public Integer getTime_days() {
		return time_days;
	}
	public void setTime_days(Integer time_days) {
		this.time_days = time_days;
	}
	
	
	
	
	
}
