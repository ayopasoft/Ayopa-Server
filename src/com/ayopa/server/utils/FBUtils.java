package com.ayopa.server.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import sun.misc.BASE64Decoder;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.AuctionDTO;
import com.ayopa.server.model.Buyer;
import com.ayopa.server.model.persistence.BuyerPersistence;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class FBUtils {
	
	public static final String FACEBOOK_API_KEY = "120882414650116";
	public static final String FACEBOOK_APPLICATION_SECRET = "17ce975710ce3ac5670fa17d5e70fef3";
	public static final String FACEBOOK_AUTH_REDIRECT_URL = "http://beta.ayopasoft.com/AyopaServer/get-facebook-id-auth.action";
	public static final String FACEBOOK_CANCEL_URL = "http://beta.ayopasoft.com/AyopaServer/get-permissions.action";
	//public static final String FACEBOOK_NEXT_URL = "http://beta.ayopasoft.com/AyopaServer/get-registration.action";
	public static final String FACEBOOK_NEXT_URL = "http://beta.ayopasoft.com/AyopaServer/process-permissions.action";
	
	
	public static final String DEV_APPID = "186996844658023";
	public static final String DEV_APPURI = "http://localhost:8080/AyopaServer/get-facebook-id-auth.action";
	public static final String DEV_APPSECRET = "4db64bc60336d88d8547dcfb059cd7b6";
	
	public static JSONObject parseSignedRequest(String signedReq){
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
	
	
	public static void postAuctionToFacebook(String auctionID, String buyerID) throws IOException{
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		BuyerPersistence bp = new BuyerPersistence();
		Buyer buyer = new Buyer();
		Auction auction = new Auction();
		AuctionDTO auctionDTO = new AuctionDTO();
		
		buyer = bp.getBuyer(buyerID);
		
		auction = auction.getAuction(auctionID);
		auctionDTO = AuctionDTO.auctionToAuctionDTO(auction);
		
		FacebookClient facebookClient;
		facebookClient = new DefaultFacebookClient(buyer.getBuyer_access_token());
		
		String link = auction.getProduct_url();
		String picture = auction.getProduct_image();
		String message = "Great group buy with Ayopa. If " + auctionDTO.getHighest_quant() + " people buy this, we can get it for " + nf.format(auctionDTO.getLowest_price()) + ". This opportunity ends in " + auctionDTO.getTime_days() + " days, " + auctionDTO.getTime_hours() + " hours.";
		String name = auction.getProduct_title();
		String description = auction.getProduct_description();
		
		facebookClient.publish("me/feed", FacebookType.class,
			    Parameter.with("message", message), Parameter.with("link", link), 
			    Parameter.with("picture", picture), Parameter.with("name", name),
			    Parameter.with("description", description),
			    Parameter.with("caption", "Group buy and save!"));
	}
	
	public static String getLoginURL() {
        return "https://graph.facebook.com/oauth/authorize?" +
        		"client_id=" + FBUtils.FACEBOOK_API_KEY + 
                "&redirect_uri=" + FBUtils.FACEBOOK_AUTH_REDIRECT_URL;
    }
	
	public static String getLoginURL(String jsoncallback) throws UnsupportedEncodingException{
    	return "https://graph.facebook.com/oauth/authorize?" +
		"client_id=" + FBUtils.FACEBOOK_API_KEY + 
        "&redirect_uri=" + URLEncoder.encode(FBUtils.FACEBOOK_AUTH_REDIRECT_URL + "?jsoncallback="+jsoncallback,"UTF-8");
    }
	
	public static String getTestLoginURL() throws UnsupportedEncodingException{
    	return "https://graph.facebook.com/oauth/authorize?" +
		"client_id=" + FBUtils.DEV_APPID + 
        "&redirect_uri=" + FBUtils.DEV_APPURI;
    }
	
	public static String getTestLoginURL(String jsoncallback) throws UnsupportedEncodingException{
    	return "https://graph.facebook.com/oauth/authorize?" +
		"client_id=" + FBUtils.DEV_APPID + 
        "&redirect_uri=" + URLEncoder.encode(FBUtils.DEV_APPURI + "?jsoncallback="+jsoncallback,"UTF-8");
    }

    public static String getAuthURL(String authCode) {
        return "https://graph.facebook.com/oauth/access_token?" +
        		"client_id=" + FBUtils.FACEBOOK_API_KEY +
        		"&redirect_uri=" + FBUtils.FACEBOOK_AUTH_REDIRECT_URL+
        		"&client_secret="+FBUtils.FACEBOOK_APPLICATION_SECRET+"&code="+authCode;
    }

    public static String getAuthURL(String jsoncallback, String code) throws UnsupportedEncodingException{
    	return "https://graph.facebook.com/oauth/access_token?" +
		"client_id=" + FBUtils.FACEBOOK_API_KEY+
		"&redirect_uri=" + URLEncoder.encode(FBUtils.FACEBOOK_AUTH_REDIRECT_URL + "?jsoncallback="+jsoncallback,"UTF-8") +
		"&client_secret="+FBUtils.FACEBOOK_APPLICATION_SECRET+"&code="+code;
    }
	
    public static String getTestAuthURL(String authCode) {
        return "https://graph.facebook.com/oauth/access_token?" +
        		"client_id=" + FBUtils.DEV_APPID +
        		"&redirect_uri=" + FBUtils.DEV_APPURI+
        		"&client_secret="+FBUtils.DEV_APPSECRET+"&code="+authCode;
    }
    
    public static String getTestAuthURL(String jsoncallback, String code) throws UnsupportedEncodingException{
    	return "https://graph.facebook.com/oauth/access_token?" +
		"client_id=" + FBUtils.DEV_APPID+
		"&redirect_uri=" + URLEncoder.encode(FBUtils.DEV_APPURI + "?jsoncallback="+jsoncallback,"UTF-8") +
		"&client_secret="+FBUtils.DEV_APPSECRET+"&code="+code;
    }
    
    
    public static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.reset();
        digest.update(text.toString().getBytes());
        byte[] hash = digest.digest();
        StringBuffer buf = new StringBuffer();
 
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
        if (hex.length() == 1)
                buf.append('0');
            buf.append(hex);
        }
 
        return buf.toString();
    }
    
    public static Map<String,String> parseFBCookie (String fbCookie) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	
    	Map<String, String> map = new HashMap<String,String>();
    	
    	fbCookie = URLDecoder.decode(fbCookie,"UTF-8");
		
		String[] stringArgs = fbCookie.trim().split("&");
		
		String md5Hash = "";
	    String sig = "";
		
		for (String s : stringArgs) {
			String key = s.split("=")[0];
			String value = s.split("=")[1];
			
			if(key.equals("uid"))
				map.put("uid", value);
			
			if (!key.equals("sig")) {
				  md5Hash = md5Hash + key + '=' + value;
			} else {
				     sig = value;
			}
		}
		
		md5Hash = FBUtils.MD5(md5Hash + FBUtils.FACEBOOK_APPLICATION_SECRET);
		
		if (!md5Hash.equals(sig)){
			map.clear();
		}
		
		return map;
    }

    
}
