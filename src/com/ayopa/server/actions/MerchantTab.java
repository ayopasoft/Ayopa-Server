package com.ayopa.server.actions;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.AuctionDTO;
import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="MerchantTab.jsp" ),

})
public class MerchantTab extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String page_id;
	private String signed_request;
	private String admin;
	
	
	
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getSigned_request() {
		return signed_request;
	}

	public void setSigned_request(String signed_request) {
		this.signed_request = signed_request;
	}

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	private List<AuctionDTO> auctions;
	//define getters for data elements
	
	public List<AuctionDTO> getAuctions() {
		return auctions;
	}
	
	

	@Override
	public String execute() throws Exception {
		//get users current auctions
		//authenticate by FB ID
		
		Auction auction = new Auction();
		
		JSONObject jsonObject = new JSONObject();
		
		try {
			jsonObject = FBUtils.parseSignedRequest(signed_request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Signed request is blank: " + e);
		}
		
		if (jsonObject.containsKey("page")){
			JSONObject page = (JSONObject) jsonObject.get("page");
			page_id = page.getString("id");
			admin = page.getString("admin");
		}
		
		
		auctions = auction.getAuctionsForFBPage(page_id);
		
		
		return Action.SUCCESS;
	}



	
}
