package com.ayopa.server.model;

public class Buyer {

	private String buyer_id;
	private String buyer_access_token;
	private String buyer_name;
	private String buyer_email;
	
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
	
	
	
}
