package com.ayopa.server.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ayopa.server.model.persistence.AuctionPersistence;
import com.ayopa.server.model.persistence.MerchantPersistence;
import com.ayopa.server.utils.AwsFacade;

public class Merchant {
	private static Log log = LogFactory.getLog(AuctionPersistence.class);
	
	public static class Key
	{
		public static final String MERCHANT_ID = "merchant_id";
		public static final String MERCHANT_USERNAME = "merchant_username";
		public static final String MERCHANT_PASSWORD = "merchant_password";
		public static final String MERCHANT_NAME = "merchant_name";
		public static final String MERCHANT_ADDRESS1 = "merchant_address1";
		public static final String MERCHANT_ADDRESS2 = "merchant_address2";
		public static final String MERCHANT_CITY= "merchant_city";
		public static final String MERCHANT_STATE = "merchant_state";
		public static final String MERCHANT_COUNTRY = "merchant_country";
		public static final String MERCHANT_POSTALCODE = "merchant_postalcode";
		public static final String MERCHANT_CONTACT = "merchant_contact_name";
		public static final String MERCHANT_EMAIL = "merchant_email";
		public static final String MERCHANT_WEBSITE = "merchant_website";
		public static final String MERCHANT_FB_PAGE = "merchant_fb_page_id";
		public static final String MERCHANT_AYOPA_FB = "merchant_ayopa_fb_stream";
	}
	
	private String merchant_id;
	private String merchant_username;
	private String merchant_password;
	private String merchant_name;
	private String merchant_address1;
	private String merchant_address2;
	private String merchant_city;
	private String merchant_state;
	private String merchant_country;
	private String merchant_postalcode;
	private String merchant_contact_name;
	private String merchant_email;
	private String merchant_website;
	private String merchant_fb_page_id;
	private boolean merchant_ayopa_fb_stream;
	
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getMerchant_username() {
		return merchant_username;
	}
	public void setMerchant_username(String merchant_username) {
		this.merchant_username = merchant_username;
	}
	public String getMerchant_password() {
		return merchant_password;
	}
	public void setMerchant_password(String merchant_password) {
		this.merchant_password = merchant_password;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public String getMerchant_address1() {
		return merchant_address1;
	}
	public void setMerchant_address1(String merchant_address1) {
		this.merchant_address1 = merchant_address1;
	}
	public String getMerchant_address2() {
		return merchant_address2;
	}
	public void setMerchant_address2(String merchant_address2) {
		this.merchant_address2 = merchant_address2;
	}
	public String getMerchant_city() {
		return merchant_city;
	}
	public void setMerchant_city(String merchant_city) {
		this.merchant_city = merchant_city;
	}
	public String getMerchant_state() {
		return merchant_state;
	}
	public void setMerchant_state(String merchant_state) {
		this.merchant_state = merchant_state;
	}
	public String getMerchant_country() {
		return merchant_country;
	}
	public void setMerchant_country(String merchant_country) {
		this.merchant_country = merchant_country;
	}
	public String getMerchant_postalcode() {
		return merchant_postalcode;
	}
	public void setMerchant_postalcode(String merchant_postalcode) {
		this.merchant_postalcode = merchant_postalcode;
	}
	public String getMerchant_contact_name() {
		return merchant_contact_name;
	}
	public void setMerchant_contact_name(String merchant_contact_name) {
		this.merchant_contact_name = merchant_contact_name;
	}
	public String getMerchant_email() {
		return merchant_email;
	}
	public void setMerchant_email(String merchant_email) {
		this.merchant_email = merchant_email;
	}
	public String getMerchant_website() {
		return merchant_website;
	}
	public void setMerchant_website(String merchant_website) {
		this.merchant_website = merchant_website;
	}
	public String getMerchant_fb_page_id() {
		return merchant_fb_page_id;
	}
	public void setMerchant_fb_page_id(String merchant_fb_page_id) {
		this.merchant_fb_page_id = merchant_fb_page_id;
	}
	public boolean getMerchant_ayopa_fb_stream() {
		return merchant_ayopa_fb_stream;
	}
	public void setMerchant_ayopa_fb_stream(boolean merchant_ayopa_fb_stream) {
		this.merchant_ayopa_fb_stream = merchant_ayopa_fb_stream;
	}
	
	public String putMerchant(String merchantDef) throws IOException {
		Merchant merchant = new Merchant();
		MerchantPersistence awsMerchant = new MerchantPersistence();
			
		merchant = merchant.jsonToMerchant(merchantDef);
		String merchantReturn = awsMerchant.putMerchant(merchant);
		
		Map<String,String> mapReturn = new HashMap<String, String> ();
		mapReturn.put("merchant_id", merchantReturn);
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( mapReturn );
		
		return jsonObject.toString();
	}
	
	public Merchant jsonToMerchant(String json)
	{
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( json ); 
		
		Merchant merchant = new Merchant();
		
		if (json.length() != 0 && json != null){
			
			
			JSONObject jsonMerchant = (JSONObject) jsonObject.get("merchant");
			merchant.setMerchant_id(jsonMerchant.getString(Merchant.Key.MERCHANT_ID));
			merchant.setMerchant_name(jsonMerchant.getString(Merchant.Key.MERCHANT_NAME));
			merchant.setMerchant_username(jsonMerchant.getString(Merchant.Key.MERCHANT_USERNAME));
			merchant.setMerchant_password(jsonMerchant.getString(Merchant.Key.MERCHANT_PASSWORD));
			merchant.setMerchant_address1(jsonMerchant.getString(Merchant.Key.MERCHANT_ADDRESS1));
			merchant.setMerchant_address2(jsonMerchant.getString(Merchant.Key.MERCHANT_ADDRESS2));
			merchant.setMerchant_city(jsonMerchant.getString(Merchant.Key.MERCHANT_CITY));
			merchant.setMerchant_state(jsonMerchant.getString(Merchant.Key.MERCHANT_STATE));
			merchant.setMerchant_postalcode(jsonMerchant.getString(Merchant.Key.MERCHANT_POSTALCODE));
			merchant.setMerchant_country(jsonMerchant.getString(Merchant.Key.MERCHANT_COUNTRY));
			merchant.setMerchant_contact_name(jsonMerchant.getString(Merchant.Key.MERCHANT_CONTACT));
			merchant.setMerchant_email(jsonMerchant.getString(Merchant.Key.MERCHANT_EMAIL));
			merchant.setMerchant_website(jsonMerchant.getString(Merchant.Key.MERCHANT_WEBSITE));
			merchant.setMerchant_fb_page_id(jsonMerchant.getString(Merchant.Key.MERCHANT_FB_PAGE));
			merchant.setMerchant_ayopa_fb_stream(Boolean.parseBoolean(jsonMerchant.getString(Merchant.Key.MERCHANT_AYOPA_FB)));
			
		}
		return merchant;
	}
	
	public String merchantToJson(Merchant merchant){
		Map<String,Object> merchantMap = new HashMap<String, Object> ();
		Map<String, Map<String,Object>> merchant_container = new HashMap<String, Map<String,Object>>();
		
		merchantMap.put(Merchant.Key.MERCHANT_ID, merchant.getMerchant_id());
		merchantMap.put(Merchant.Key.MERCHANT_NAME, merchant.getMerchant_name());
		merchantMap.put(Merchant.Key.MERCHANT_USERNAME, merchant.getMerchant_username());
		merchantMap.put(Merchant.Key.MERCHANT_PASSWORD, merchant.getMerchant_password());
		merchantMap.put(Merchant.Key.MERCHANT_ADDRESS1, merchant.getMerchant_address1());
		merchantMap.put(Merchant.Key.MERCHANT_ADDRESS2, merchant.getMerchant_address2());
		merchantMap.put(Merchant.Key.MERCHANT_CITY, merchant.getMerchant_city());
		merchantMap.put(Merchant.Key.MERCHANT_STATE, merchant.getMerchant_state());
		merchantMap.put(Merchant.Key.MERCHANT_POSTALCODE, merchant.getMerchant_postalcode());
		merchantMap.put(Merchant.Key.MERCHANT_COUNTRY, merchant.getMerchant_country());
		merchantMap.put(Merchant.Key.MERCHANT_CONTACT, merchant.getMerchant_contact_name());
		merchantMap.put(Merchant.Key.MERCHANT_EMAIL, merchant.getMerchant_email());
		merchantMap.put(Merchant.Key.MERCHANT_WEBSITE, merchant.getMerchant_website());
		merchantMap.put(Merchant.Key.MERCHANT_FB_PAGE, merchant.getMerchant_fb_page_id());
		merchantMap.put(Merchant.Key.MERCHANT_AYOPA_FB, Boolean.toString(merchant.getMerchant_ayopa_fb_stream()));
		
		merchant_container.put("merchant",merchantMap);
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( merchant_container );
		
		return jsonObject.toString();
	}
	
	public Merchant authenticate_login(String username, String password) throws IOException
	{
		Merchant merchant = new Merchant();
		
		MerchantPersistence mp = new MerchantPersistence();
		
		AwsFacade aws = AwsFacade.getInstance();
	
		String query = "select * from `" + AwsFacade.Table.MERCHANT + "` where `" 
		+ AwsFacade.Key.MERCHANT_USERNAME + "` = '" + username + "' and `" 
		+ AwsFacade.Key.MERCHANT_PASSWORD + "` = '" + password + "'"; 
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		
		if (results.size() == 0) {
			//return empty merchant;
		}
		else {
			if (results.size() > 1){
				log.info("Merchant authenticate login returned more than 1 result");
			}
			
			merchant = mp.mapToMerchant(results.get(0));
			
		}
		
			return merchant;
	}
	
	public static String getMerchantFBPage(String merchant_id) throws IOException{
		
		String fb_page = "";
		AwsFacade aws = AwsFacade.getInstance();
	
		String query = "select * from `" + AwsFacade.Table.MERCHANT + "` where `" 
		+ AwsFacade.Key.MERCHANT_ID + "` = '" + merchant_id + "'"; 
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		
		if (results.size() == 0) {
			//return empty fb_page;
		}
		else {
			if (results.size() > 1){
				log.info("Get by Merchant id returned more than 1 result");
			}
			
			fb_page = (results.get(0).get(AwsFacade.Key.MERCHANT_FB_PAGE));
			
		}
		
			return fb_page;
	}
	
}
