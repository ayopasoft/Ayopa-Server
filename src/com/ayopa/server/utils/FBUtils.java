package com.ayopa.server.utils;

import java.io.IOException;
import java.text.NumberFormat;
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
	public static final String FACEBOOK_AUTH_REDIRECT_URL = "http://ayopa1dev.happyjacksoftware.com/AyopaServer/home";
	public static final String FACEBOOK_CANCEL_URL = "http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/get-permissions.action";
	public static final String FACEBOOK_NEXT_URL = "http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/get-registration.action";
	
	
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
	
	public static String getLoginRedirectURL() {
        return "https://graph.facebook.com/oauth/authorize?client_id=" +
            FBUtils.FACEBOOK_API_KEY + "&redirect_uri=" +
            FBUtils.FACEBOOK_AUTH_REDIRECT_URL;
    }

    public static String getAuthURL(String authCode) {
        return "https://graph.facebook.com/oauth/access_token?fbconnect=1&client_id=" +
            FBUtils.FACEBOOK_API_KEY+"&redirect_uri=" +
            FBUtils.FACEBOOK_AUTH_REDIRECT_URL+"&client_secret="+FBUtils.FACEBOOK_APPLICATION_SECRET+"&code="+authCode;
    }

	
}
