package com.ez.server.actions;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ez.server.model.Branding;
import com.opensymphony.xwork2.Action;


@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})
public class GetBranding {

	private String jsoncallback;
	private String jsonReturn;
	
	
	public String getJsoncallback() {
		return jsoncallback;
	}

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}

	public String getJsonReturn() {
		return jsonReturn;
	}

	public void setJsonReturn(String jsonReturn) {
		this.jsonReturn = jsonReturn;
	}
	
	// Get one institution
	public String getAnInstitute(String id) throws Exception{
		
		String jsonString = "";
		JSONArray jsonArray = null;
		
		try {
			
			List<java.util.Map<String, String>> list  = Branding.getBrand(id);
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		JSONObject json = new JSONObject();
		json.put("One Institution", jsonArray);
		jsonReturn = json.toString();
		
		return Action.SUCCESS;
		
	}
}
