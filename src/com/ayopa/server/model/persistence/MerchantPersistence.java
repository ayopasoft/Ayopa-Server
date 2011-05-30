package com.ayopa.server.model.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ayopa.server.model.Merchant;
import com.ayopa.server.utils.AwsFacade;

public class MerchantPersistence {

	
	public String putMerchant (Merchant merchant) throws IOException {
		if (merchant.getMerchant_id() == null || merchant.getMerchant_id().length() == 0)
			merchant.setMerchant_id(UUID.randomUUID().toString());		
		
		Map<String, String> map = new HashMap<String, String>();
		
		if (merchant.getMerchant_id() != null)
			map.put(AwsFacade.Key.MERCHANT_ID,merchant.getMerchant_id());
		if (merchant.getMerchant_name() != null)
			map.put(AwsFacade.Key.MERCHANT_NAME, merchant.getMerchant_name());
		if (merchant.getMerchant_username() != null)
			map.put(AwsFacade.Key.MERCHANT_USERNAME, merchant.getMerchant_username());
		if (merchant.getMerchant_password() != null)
			map.put(AwsFacade.Key.MERCHANT_PASSWORD, merchant.getMerchant_password());
		if (merchant.getMerchant_address1() != null)
			map.put(AwsFacade.Key.MERCHANT_ADDRESS1, merchant.getMerchant_address1());
		if (merchant.getMerchant_address2() != null)
			map.put(AwsFacade.Key.MERCHANT_ADDRESS2, merchant.getMerchant_address2());
		if (merchant.getMerchant_city() != null)
			map.put(AwsFacade.Key.MERCHANT_CITY, merchant.getMerchant_city());
		if (merchant.getMerchant_state() != null)
			map.put(AwsFacade.Key.MERCHANT_STATE, merchant.getMerchant_state());
		if (merchant.getMerchant_postalcode() != null)
			map.put(AwsFacade.Key.MERCHANT_POSTALCODE, merchant.getMerchant_postalcode());
		if (merchant.getMerchant_country() != null)
			map.put(AwsFacade.Key.MERCHANT_COUNTRY, merchant.getMerchant_country());
		if (merchant.getMerchant_contact_name() != null)
			map.put(AwsFacade.Key.MERCHANT_CONTACT, merchant.getMerchant_contact_name());
		if (merchant.getMerchant_email() != null)
			map.put(AwsFacade.Key.MERCHANT_EMAIL, merchant.getMerchant_email());
		if (merchant.getMerchant_website() != null)
			map.put(AwsFacade.Key.MERCHANT_WEBSITE, merchant.getMerchant_website());
		if (merchant.getMerchant_fb_page_id() != null)
			map.put(AwsFacade.Key.MERCHANT_FB_PAGE, merchant.getMerchant_fb_page_id());
		if (merchant.getMerchant_ayopa_fb_stream() == true || merchant.getMerchant_ayopa_fb_stream() == false)
			map.put(AwsFacade.Key.MERCHANT_AYOPA_FB, Boolean.toString(merchant.getMerchant_ayopa_fb_stream()));
		if (merchant.getMerchant_commission() >= 0)
			map.put(AwsFacade.Key.MERCHANT_COMMISSION, Double.toString(merchant.getMerchant_commission()));
		if (merchant.getMerchant_paypal() != null)
			map.put(AwsFacade.Key.MERCHANT_PAYPAL, merchant.getMerchant_paypal());
		
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.MERCHANT, merchant.getMerchant_id(), map);
		
		return merchant.getMerchant_id();
		
	}
	
	public Merchant getMerchant (String merchant_id) throws IOException {
		AwsFacade aws = AwsFacade.getInstance();
		
		Map<String,String> map = aws.getRow(AwsFacade.Table.MERCHANT, merchant_id);
		
		Merchant merchant = mapToMerchant(map);
		
		return merchant;
	}
	
	public Merchant mapToMerchant(Map<String, String> map) {
		Merchant merchant = new Merchant ();
		
		if (map.containsKey(AwsFacade.Key.MERCHANT_ID))
			merchant.setMerchant_id(map.get(AwsFacade.Key.MERCHANT_ID));
		if (map.containsKey(AwsFacade.Key.MERCHANT_NAME))
			merchant.setMerchant_name(map.get(AwsFacade.Key.MERCHANT_NAME));
		if (map.containsKey(AwsFacade.Key.MERCHANT_USERNAME))
			merchant.setMerchant_username(map.get(AwsFacade.Key.MERCHANT_USERNAME));
		if (map.containsKey(AwsFacade.Key.MERCHANT_PASSWORD))
			merchant.setMerchant_password(map.get(AwsFacade.Key.MERCHANT_PASSWORD));
		if (map.containsKey(AwsFacade.Key.MERCHANT_ADDRESS1))
			merchant.setMerchant_address1(map.get(AwsFacade.Key.MERCHANT_ADDRESS1));
		if (map.containsKey(AwsFacade.Key.MERCHANT_ADDRESS2))
			merchant.setMerchant_address2(map.get(AwsFacade.Key.MERCHANT_ADDRESS2));
		if (map.containsKey(AwsFacade.Key.MERCHANT_CITY))
			merchant.setMerchant_city(map.get(AwsFacade.Key.MERCHANT_CITY));
		if (map.containsKey(AwsFacade.Key.MERCHANT_STATE))
			merchant.setMerchant_state(map.get(AwsFacade.Key.MERCHANT_STATE));
		if (map.containsKey(AwsFacade.Key.MERCHANT_POSTALCODE))
			merchant.setMerchant_postalcode(map.get(AwsFacade.Key.MERCHANT_POSTALCODE));
		if (map.containsKey(AwsFacade.Key.MERCHANT_COUNTRY))
			merchant.setMerchant_country(map.get(AwsFacade.Key.MERCHANT_COUNTRY));
		if (map.containsKey(AwsFacade.Key.MERCHANT_CONTACT))
			merchant.setMerchant_contact_name(map.get(AwsFacade.Key.MERCHANT_CONTACT));
		if (map.containsKey(AwsFacade.Key.MERCHANT_EMAIL))
			merchant.setMerchant_email(map.get(AwsFacade.Key.MERCHANT_EMAIL));
		if (map.containsKey(AwsFacade.Key.MERCHANT_WEBSITE))
			merchant.setMerchant_website(map.get(AwsFacade.Key.MERCHANT_WEBSITE));
		if (map.containsKey(AwsFacade.Key.MERCHANT_FB_PAGE))
			merchant.setMerchant_fb_page_id(map.get(AwsFacade.Key.MERCHANT_FB_PAGE));
		if (map.containsKey(AwsFacade.Key.MERCHANT_AYOPA_FB))
			merchant.setMerchant_ayopa_fb_stream(Boolean.parseBoolean(map.get(AwsFacade.Key.MERCHANT_AYOPA_FB)));
		if (map.containsKey(AwsFacade.Key.MERCHANT_COMMISSION))
			merchant.setMerchant_commission(Double.parseDouble(map.get(AwsFacade.Key.MERCHANT_COMMISSION)));
		if (map.containsKey(AwsFacade.Key.MERCHANT_PAYPAL))
			merchant.setMerchant_paypal(map.get(AwsFacade.Key.MERCHANT_PAYPAL));
		
		return merchant;
	}
	
	
}
