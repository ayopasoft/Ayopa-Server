package com.ayopa.server.utils;
/*
 * Copyright 2005, 2008 PayPal, Inc. All Rights Reserved.
 *
 * MassPay NVP example; last modified 08MAY23. 
 *
 * Pay one or more recipients.  
 */

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;
/**
 * PayPal Java SDK sample code
 */
public class MassPay 
{
	public static String massPayCode(String emailSub,String receiverType,String[] receiverEmail,
							String[] uniqueId,String[] amount,String[] note)
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
			for(int i=0,j=0;i<receiverEmail.length; i++)
			{
				String recreceiverEmail=receiverEmail[i];
				if(recreceiverEmail != null && recreceiverEmail.length()!= 0)
				{
					encoder.add("L_EMAIL"+j,receiverEmail[i]);
					encoder.add("L_Amt"+j,amount[i]);
					encoder.add("L_UNIQUEID"+j,uniqueId[i]);
					encoder.add("L_NOTE"+j,note[i]);	
					j++;
				}								
			}
			
		// Execute the API operation and obtain the response.
		String NVPRequest = encoder.encode(); 
		String NVPResponse = (String) caller.call(NVPRequest);
		decoder.decode(NVPResponse);
		
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return decoder.get("ACK") + decoder.get("L_LONGMESSAGE") + decoder.get("L_ERRORCODE"); 
	}
}
