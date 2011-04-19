package com.ayopa.server.model;

import java.io.IOException;

import com.ayopa.server.model.persistence.BuyerPersistence;

public class Buyer {

	private String buyer_id;
	private String buyer_access_token;
	private String buyer_name;
	private String buyer_email;
	private String buyer_address1;
	private String buyer_address2;
	private String buyer_city;
	private String buyer_state;
	private String buyer_zip;
	private String buyer_country;
	
	
	public String getBuyer_address1() {
		return buyer_address1;
	}
	public void setBuyer_address1(String buyer_address1) {
		this.buyer_address1 = buyer_address1;
	}
	public String getBuyer_address2() {
		return buyer_address2;
	}
	public void setBuyer_address2(String buyer_address2) {
		this.buyer_address2 = buyer_address2;
	}
	public String getBuyer_city() {
		return buyer_city;
	}
	public void setBuyer_city(String buyer_city) {
		this.buyer_city = buyer_city;
	}
	public String getBuyer_state() {
		return buyer_state;
	}
	public void setBuyer_state(String buyer_state) {
		this.buyer_state = buyer_state;
	}
	public String getBuyer_zip() {
		return buyer_zip;
	}
	public void setBuyer_zip(String buyer_zip) {
		this.buyer_zip = buyer_zip;
	}
	public String getBuyer_country() {
		return buyer_country;
	}
	public void setBuyer_country(String buyer_country) {
		this.buyer_country = buyer_country;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getBuyer_access_token() {
		return buyer_access_token;
	}
	public void setBuyer_access_token(String buyer_access_token) {
		this.buyer_access_token = buyer_access_token;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	
	public static void addAddress(String buyerID, String address1, String address2, String city, String state, String zip, String country) throws IOException {
		Buyer buyer = new Buyer();
		BuyerPersistence bp = new BuyerPersistence();
		buyer = bp.getBuyer(buyerID);
		
		if (buyer.getBuyer_address1() == null || buyer.getBuyer_address1().equals("")){
			buyer.setBuyer_address1(address1);
			buyer.setBuyer_address2(address2);
			buyer.setBuyer_city(city);
			buyer.setBuyer_state(state);
			buyer.setBuyer_zip(zip);
			buyer.setBuyer_country(country);
			bp.putBuyer(buyer);
		}
		
	}
	
}
