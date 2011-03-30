package com.ayopa.server.model;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.ayopa.server.utils.AeSimpleSHA1;

public class MerchantTest {

	@Test
	public void testJsonToMerchant() {
		String merchantDef = "{\"merchant\":{\"merchant_website\":\"http://www.happyjacksoftware.com\",\"merchant_name\":\"Happy Jack Software\",\"merchant_contact_name\":\"Mona Gamboa\",\"merchant_ayopa_fb_stream\":\"true\",\"merchant_city\":\"Laramie\",\"merchant_postalcode\":\"82070\",\"merchant_email\":\"info@happyjacksoftware.com\",\"merchant_fb_page_id\":\"10002223\",\"merchant_username\":\"happyjack\",\"merchant_address2\":\"\",\"merchant_address1\":\"72 Juniper Dr\",\"merchant_country\":\"US\",\"merchant_id\":\"1\",\"merchant_state\":\"WY\",\"merchant_password\":\"[B@5ece2187\"}}";
		Merchant merchant = new Merchant();
		merchant = merchant.jsonToMerchant(merchantDef);
		assertEquals("JsontoMerchant error:","1",merchant.getMerchant_id());
		assertEquals("JsontoMerchant state error:", "WY", merchant.getMerchant_state());
	
	}
	
	@Test
	public void testMerchantToJson() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Merchant merchant = new Merchant();
		merchant.setMerchant_id("1");
		merchant.setMerchant_name("Happy Jack Software");
		merchant.setMerchant_username("happyjack");
		
	
		merchant.setMerchant_password(AeSimpleSHA1.SHA1("password"));
		
		
		
		merchant.setMerchant_address1("72 Juniper Dr");
		merchant.setMerchant_address2("");
		merchant.setMerchant_city("Laramie");
		merchant.setMerchant_state("WY");
		merchant.setMerchant_postalcode("82070");
		merchant.setMerchant_country("US");
		merchant.setMerchant_contact_name("Mona Gamboa");
		merchant.setMerchant_email("info@happyjacksoftware.com");
		merchant.setMerchant_website("http://www.happyjacksoftware.com");
		merchant.setMerchant_ayopa_fb_stream(Boolean.TRUE);
		merchant.setMerchant_fb_page_id("10002223");
		
		String jsonString = merchant.merchantToJson(merchant);
		
		assertEquals("Merchant to JSON conversion failed:", "{\"merchant\":{\"merchant_website\":\"http://www.happyjacksoftware.com\",\"merchant_name\":\"Happy Jack Software\",\"merchant_contact_name\":\"Mona Gamboa\",\"merchant_ayopa_fb_stream\":\"true\",\"merchant_city\":\"Laramie\",\"merchant_postalcode\":\"82070\",\"merchant_email\":\"info@happyjacksoftware.com\",\"merchant_fb_page_id\":\"10002223\",\"merchant_username\":\"happyjack\",\"merchant_address2\":\"\",\"merchant_address1\":\"72 Juniper Dr\",\"merchant_country\":\"US\",\"merchant_id\":\"1\",\"merchant_state\":\"WY\",\"merchant_password\":\"5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8\"}}", jsonString);
		
	}

}
