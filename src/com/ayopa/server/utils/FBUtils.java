package com.ayopa.server.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.mail.internet.MimeUtility;

import sun.misc.BASE64Decoder;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class FBUtils {
	
	public static final String FACEBOOK_API_KEY = "120882414650116";
	public static final String FACEBOOK_APPLICATION_SECRET = "17ce975710ce3ac5670fa17d5e70fef3";
	
	
	public static byte[] base64_url_decode(byte[] b) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        InputStream b64is = MimeUtility.decode(bais, "base64");
        byte[] tmp = new byte[b.length];
        int n = b64is.read(tmp);
        byte[] res = new byte[n];
        System.arraycopy(tmp, 0, res, 0, n);
        return res;
     }  

	public static Map<String,String> ParseRegistration(String signed_request) throws Exception{
		/*String[] sig_parts = signed_request.split("\\.",2);
		//String sig = base64_url_decode(sig_parts[0].getBytes());
		//Replace special character in payload as indicated by FB
        String payload = sig_parts[1].replace("-", "+").replace("_", "/").trim();
		byte[] data = base64_url_decode(payload.getBytes());
		String value = new String(data);
		
		Map<String,String> map = new HashMap<String,String>();
	
		JSONObject request = (JSONObject) JSONSerializer.toJSON( value );*/
		
		
		StringTokenizer st = new StringTokenizer(signed_request, ".");   
        int count = 0;
        String payload = "";
        
        //Retrieve payload (Note: encoded signature is used for internal verification and it is optional)
        while (st.hasMoreTokens())
        {
            if(count == 1)
            {
                payload = st.nextToken();
                break;
            }
            else
                st.nextToken();           

            count++;
        }

        //Initialize Base64 decoder
        BASE64Decoder decoder = new BASE64Decoder();
       
        //Replace special character in payload as indicated by FB
        payload = payload.replace("-", "+").replace("_", "/").trim();
       
        //Decode payload
        try
        {
            byte[] decodedPayload = decoder.decodeBuffer(payload);
            payload = new String(decodedPayload, "UTF8");
        }
        catch (IOException e)
        {
            System.out.println("ERROR: Unable to perform Base64 Decode");
            
        }
        
        Map<String,String> map = new HashMap<String,String>();
		JSONObject request = (JSONObject) JSONSerializer.toJSON( payload );
		JSONObject registration = (JSONObject) request.get("registration");
		
		map.put("user_id", request.getString("user_id"));
		map.put("name", registration.getString("name"));
		map.put("email", registration.getString("email"));
		map.put("oauth_token", request.getString("oauth_token"));
		
		return map;
	}
	
	public static JSONObject ParseSignedRequest(String signed_request) throws Exception{
		String[] sig_parts = signed_request.split("\\.",2);
		//String sig = base64_url_decode(sig_parts[0].getBytes());
		//Replace special character in payload as indicated by FB
        String payload = sig_parts[1].replace("-", "+").replace("_", "/").trim();
		byte[] data = base64_url_decode(payload.getBytes());
		String value = new String(data);
	
		JSONObject request = (JSONObject) JSONSerializer.toJSON( value );
		
		return request;
	}
	
	public static String getAccessTokenFromCookieValue(String cookieValue,
			String attributeName) {
			          int startIndex = cookieValue.indexOf(attributeName);
			          if(startIndex == -1) return "";
			          int endIndex = cookieValue.indexOf("&", startIndex + 1);
			          if(endIndex == -1) endIndex = cookieValue.length();
			          String value = cookieValue.substring(startIndex +
			        		  attributeName.length() + 1, endIndex);
			          return value;

			} 
	
	public static JSONObject parseSignedRequest2(String signedReq){
		//The parameter contains encoded signature and payload separated by Ô.Õ
        StringTokenizer st = new StringTokenizer(signedReq, ".");   
        int count = 0;
        String payload = "";
        
        //Retrieve payload (Note: encoded signature is used for internal verification and it is optional)
        while (st.hasMoreTokens())
        {
            if(count == 1)
            {
                payload = st.nextToken();
                break;
            }
            else
                st.nextToken();           

            count++;
        }

        //Initialize Base64 decoder
        BASE64Decoder decoder = new BASE64Decoder();
       
        //Replace special character in payload as indicated by FB
        payload = payload.replace("-", "+").replace("_", "/").trim();
       
        //Decode payload
        try
        {
            byte[] decodedPayload = decoder.decodeBuffer(payload);
            payload = new String(decodedPayload, "UTF8");
        }
        catch (IOException e)
        {
            System.out.println("ERROR: Unable to perform Base64 Decode");
            
        }
        
 
		JSONObject request = (JSONObject) JSONSerializer.toJSON( payload );
		
		return request;
	}
	
	/*function parse_signed_request($signed_request, $secret) {
		  list($encoded_sig, $payload) = explode('.', $signed_request, 2); 

		  // decode the data
		  $sig = base64_url_decode($encoded_sig);
		  $data = json_decode(base64_url_decode($payload), true);

		  if (strtoupper($data['algorithm']) !== 'HMAC-SHA256') {
		    error_log('Unknown algorithm. Expected HMAC-SHA256');
		    return null;
		  }

		  // check sig
		  $expected_sig = hash_hmac('sha256', $payload, $secret, $raw = true);
		  if ($sig !== $expected_sig) {
		    error_log('Bad Signed JSON signature!');
		    return null;
		  }

		  return $data;
		}

		function base64_url_decode($input) {
		    return base64_decode(strtr($input, '-_', '+/'));
		}*/
	
}
