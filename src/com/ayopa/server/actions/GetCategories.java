package com.ayopa.server.actions;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Category;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})
public class GetCategories extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(GetCategories.class);
	
	private String category;
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

	public String getCategory() {
		return category;
	}

	@Override
	public String execute() throws Exception {
		
		
		String jsonString = "";
		
		try {
			
			List<java.util.Map<String, String>> list  = Category.getCategories();
			JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			logger.error("Problem getting categories: " + e.getMessage());
			//e.printStackTrace();
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		if ( jsoncallback != null && !jsoncallback.equals("") ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
	
	
	
}
