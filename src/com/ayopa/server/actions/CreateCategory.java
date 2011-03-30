package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Category;
import com.ayopa.server.utils.JsonUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})
public class CreateCategory extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String categoryName;
	private String jsoncallback;
	private String jsonReturn;

	
	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = JsonUtils.sanitizeJsonpParam(jsoncallback);
	}
	
	
		
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getJsonReturn() {
		return jsonReturn;
	}

	@Override
	public String execute() throws Exception {
		
		Category category = new Category();
		
		String jsonString = category.putCategory(categoryName);
		
		if ( jsoncallback != null ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
	
	
	
}
