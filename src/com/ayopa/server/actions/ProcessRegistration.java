package com.ayopa.server.actions;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Buyer;
import com.ayopa.server.model.persistence.BuyerPersistence;
import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="ProcessRegistration.jsp" ),

})
public class ProcessRegistration extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String signed_request;
	//define getters for data elements
	
	public String getSigned_request() {
		return signed_request;
	}



	public void setSigned_request(String signed_request) {
		this.signed_request = signed_request;
	}


	@Override
	public String execute() throws Exception {
		
		Buyer buyer = new Buyer();
		BuyerPersistence bp = new BuyerPersistence();
		
		//Map<String,String> map = new HashMap<String,String>();
		//map = FBUtils.ParseRegistration(signed_request);
		
		JSONObject request = FBUtils.parseSignedRequest(signed_request);
		JSONObject registration = (JSONObject) request.get("registration");
			
		buyer.setBuyer_id(request.getString("user_id"));
		buyer.setBuyer_name(registration.getString("name"));
		buyer.setBuyer_email(registration.getString("email"));
		buyer.setBuyer_access_token(request.getString("oauth_token"));
		
		String buyer_id = bp.putBuyer(buyer);
		
		
		return Action.SUCCESS;
	}



	


	
}
