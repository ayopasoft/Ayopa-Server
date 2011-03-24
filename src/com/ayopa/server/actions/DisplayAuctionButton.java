package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="DisplayAuctionButton.jsp" ),
})
public class DisplayAuctionButton extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auctionID;

	
	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}
	
	private float currentPrice;
	private Integer time_seconds;
	private Integer time_minutes;
	private Integer time_hours;
	private Integer time_days;
	private Integer highest_quant;
	private Integer current_quant;
	private Integer next_quant;
	private Integer start_quant;
	private float next_price;
	private float lowest_price;
	private float start_price;
	
	
	
	public Integer getTime_seconds() {
		return time_seconds;
	}


	public Integer getTime_minutes() {
		return time_minutes;
	}


	public Integer getTime_hours() {
		return time_hours;
	}


	public Integer getTime_days() {
		return time_days;
	}


	public Integer getHighest_quant() {
		return highest_quant;
	}


	public Integer getCurrent_quant() {
		return current_quant;
	}


	public Integer getNext_quant() {
		return next_quant;
	}


	public Integer getStart_quant() {
		return start_quant;
	}


	public float getNext_price() {
		return next_price;
	}


	public float getLowest_price() {
		return lowest_price;
	}


	public float getStart_price() {
		return start_price;
	}


	public float getCurrentPrice () {
		return currentPrice;
	}


	@Override
	public String execute() throws Exception {
		
		//detects pricing conflict
		
		currentPrice = 800;
		start_price = 900;
		next_price = 700;
		lowest_price = 650;
		highest_quant = 91;
		start_quant = 20;
		current_quant = 55;
		next_quant = 61;
		time_days = 2;
		time_hours = 14;
		time_minutes = 48;
		time_seconds = 38;
		
		return Action.SUCCESS;
	}
	
}
