package com.ayopa.server.actions;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Buyer;
import com.ayopa.server.model.persistence.BuyerPersistence;
import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="Canvas.jsp" ),
	@Result( name="consumerRedirect", type="redirect", location="consumer-canvas?buyer_id=${buyer_id}"),
	@Result( name="permsRedirect", type="redirect", location="${perms_url}")
	
})
public class Canvas extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String signed_request;
	private String buyer_id;
	private String perms_url;
	
	
	
	public String getPerms_url() {
		return perms_url;
	}


	public String getBuyer_id() {
		return buyer_id;
	}


	public void setSigned_request(String signed_request) {
		this.signed_request = signed_request;
	}


	@Override
	public String execute() throws Exception {
		//detect user type
		//redirect to consumer if consumer
		//redirect to merchant if merchant
		//otherwise display canvas page
	
		JSONObject jsonObject = new JSONObject();
		jsonObject = FBUtils.parseSignedRequest(signed_request);
		
		BuyerPersistence bp = new BuyerPersistence();
		Buyer buyer = new Buyer();
		
		try {
			buyer = bp.getBuyer(jsonObject.getString("user_id"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (buyer.getBuyer_id() != null)
		{
			buyer_id = buyer.getBuyer_id();
			Map<String, Object> session = ActionContext.getContext().getSession();   
		    session.put("buyer_id", buyer_id);
			return "consumerRedirect";
		}
		else if (jsonObject.containsKey("user_id"))
		{
			return Action.SUCCESS;
		}
		else
		{
			String cancel_url = "http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/";
			String next_url = "http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/";
			
			perms_url = "https://www.facebook.com/login.php?api_key=" + FBUtils.FACEBOOK_API_KEY + "&display=page&return_session=1&session_version=3&v=1.0&req_perms=publish_stream,email,offline_access,manage_pages&canvas=1&fbconnect=0&cancel_url=" + cancel_url + "&next=" + next_url;
			 
			return "permsRedirect";
		}
		
	}
}
