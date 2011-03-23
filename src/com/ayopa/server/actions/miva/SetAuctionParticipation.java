package com.ayopa.server.actions.miva;


import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "success" } ),

})
public class SetAuctionParticipation extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private Long auction_id;
	private Long fb_id;
	private String success;
	

	public String getSuccess() {
		return success;
	}


	public void setAuction_id(Long auction_id) {
		this.auction_id = auction_id;
	}

	public void setFb_id(Long fb_id) {
		this.fb_id = fb_id;
	}


	@Override
	public String execute() throws Exception {
		
		success = "1";
		
		return Action.SUCCESS;
	}

	
}
