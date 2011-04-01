package com.ayopa.server.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.CurrentAuction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "application")
@Results({ @Result(name = Action.SUCCESS, location = "DisplayAuctionButton.jsp"), })
public class DisplayAuctionButton extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auctionID;

	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}

	private double current_price;
	private long time_seconds;
	private long time_minutes;
	private long time_hours;
	private long time_days;
	private Integer highest_quant;
	private Integer current_quant;
	private Integer next_quant;
	private Integer start_quant;
	private double next_price;
	private double lowest_price;
	private double start_price;

	public long getTime_seconds() {
		return time_seconds;
	}

	public long getTime_minutes() {
		return time_minutes;
	}

	public long getTime_hours() {
		return time_hours;
	}

	public long getTime_days() {
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

	public double getNext_price() {
		return next_price;
	}

	public double getLowest_price() {
		return lowest_price;
	}

	public double getStart_price() {
		return start_price;
	}


	public double getCurrent_price() {
		return current_price;
	}

	@Override
	public String execute() throws Exception {

		// detects pricing conflict

		Auction auction = new Auction();
		CurrentAuction currAuction = new CurrentAuction();
		Map<String, Long> map = new HashMap<String, Long>();

		auction = auction.getAuction(auctionID);
		int quantity = currAuction.getCurrentQuantity(auctionID);
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, quantity);

		map = CurrentAuction.getAuctionTimeRemaining(auction);

		current_price = currAuction.getCurrent_price();
		start_price = auction.getAuction_startprice();
		next_price = currAuction.getNext_price();
		lowest_price = currAuction.getLowest_price();
		highest_quant = currAuction.getLowest_level();
		start_quant = auction.getAuction_schedule().get(0).getMin();
		current_quant = currAuction.getCurrent_level();
		next_quant = currAuction.getNext_level();
		time_days = map.get("days");
		time_hours = map.get("hours");
		time_minutes = map.get("minutes");
		time_seconds = map.get("seconds");

		return Action.SUCCESS;
	}

}
