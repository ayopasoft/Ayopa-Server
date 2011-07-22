package com.ayopa.server.utils;

import java.util.List;

import org.apache.log4j.Logger;

import com.ayopa.server.actions.CreateAuction;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;

public class PaypalUtils {
	private static org.apache.log4j.Logger logger = Logger.getLogger(PaypalUtils.class);

	public static final String PAY_NOW_URL = "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=ALC6UKX9WQKPA";  //live
	//public static final String PAY_NOW_URL = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=Q9KPS9CZHJT3Y";  //sandbox
	public static final String PAYPAL_URL = "https://www.paypal.com/cgi-bin/webscr";  //live
	//public static final String PAYPAL_URL = "https://www.sandbox.paypal.com/cgi-bin/webscr";  //sandbox
	public static final String INVOICE_SUBJECT = "Ayopa Invoice";
	public static final String INVOICE_FROM = "info@ayopasoft.com";
	public static final String PAYPAL_ID = "";
	
	
	
	public static final String PAYPAL_EMAIL = "todd@ayopasoft.com";  //live
	//public static final String PAYPAL_EMAIL ="kari_1304368087_biz@happyjacksoftware.com"; //sandbox
	public static final String API_USERNAME = "todd_api1.ayopasoft.com"; //live
	//public static final String API_USERNAME = "kari_1304368087_biz_api1.happyjacksoftware.com"; //sandbox
	public static final String API_PASSWORD = "PUYFGW2Q36XR4DW7"; //live
	//public static final String API_PASSWORD = "1304368104";  //sandbox
	public static final String SIGNATURE = "AjuSD4cUJhLD7WfOQsd7ZAS6t3MxACDTOa5VcvCxfoX4.JY7XWZK4biO"; //live
	//public static final String SIGNATURE = "An5ns1Kso7MWUdW4ErQKJJJ4qi4-A1.LLMlJ2dXSX.m67x.o9U.71IFw"; //sandbox
	public static final String ENVIRONMENT = "live"; //live
	//public static final String ENVIRONMENT = "sandbox";  //sandbox
	
	
	public static String massPayCode(String emailSub,String receiverType, List<String> receiverEmail,
			List<String> uniqueId, List<String> amount, List<String> note)
	{
		NVPCallerServices caller = null;
		
		NVPEncoder encoder = new NVPEncoder();
		NVPDecoder decoder = new NVPDecoder();
		
		try
		{
			caller = new NVPCallerServices();
			APIProfile profile = ProfileFactory.createSignatureAPIProfile();
			/*
			WARNING: Do not embed plaintext credentials in your application code.
			Doing so is insecure and against best practices.
			Your API credentials must be handled securely. Please consider
			encrypting them for use in any production environment, and ensure
			that only authorized individuals may view or modify them.
			*/
			
			// Set up your API credentials, PayPal end point, API operation and version.
			profile.setAPIUsername(PaypalUtils.API_USERNAME);
			profile.setAPIPassword(PaypalUtils.API_PASSWORD);
			profile.setSignature(PaypalUtils.SIGNATURE);
			profile.setEnvironment(PaypalUtils.ENVIRONMENT);
			
			profile.setSubject("");
			caller.setAPIProfile(profile);
			encoder.add("VERSION", "51.0");		    
			encoder.add("METHOD","MassPay");
			
			// Add request-specific fields to the request string.
			encoder.add("EMAILSUBJECT",emailSub);
			encoder.add("RECEIVERTYPE",receiverType);
			for(int i=0,j=0;i<receiverEmail.size(); i++)
			{
			String recreceiverEmail=receiverEmail.get(i);
			if(recreceiverEmail != null && recreceiverEmail.length()!= 0)
			{
				encoder.add("L_EMAIL"+j,receiverEmail.get(i));
				encoder.add("L_Amt"+j,amount.get(i));
				encoder.add("L_UNIQUEID"+j,uniqueId.get(i));
				encoder.add("L_NOTE"+j,note.get(i));	
				j++;
			}								
			}
			
			// Execute the API operation and obtain the response.
			String NVPRequest = encoder.encode(); 
			String NVPResponse = (String) caller.call(NVPRequest);
			decoder.decode(NVPResponse);
			logger.info("Rebate Error: " + decoder.get("L_SHORTMESSAGE0") + ", " + decoder.get("L_LONGMESSAGE0") + ", " + decoder.get("L_ERRORCODE0"));
			
		
		}
		
		catch (Exception ex)
		{
			logger.error("Paypal MassPay Error: " + ex);
			ex.printStackTrace();
		}
			
		     return decoder.get("ACK"); 
		}
}
