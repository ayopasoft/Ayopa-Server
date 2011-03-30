package com.ayopa.server.actions.miva;


import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Category;
import com.ayopa.server.utils.AwsFacade;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "category" } ),

})
public class GetCategories extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String category;
	
	
	
	public String getCategory() {
		return category;
	}



	@Override
	public String execute() throws Exception {
		
		List<java.util.Map<String, String>> list  = Category.getCategories();
		
		category = "";
		
		for(int i=0; i < list.size(); i++){
			
			category += "<category value=\"" + list.get(i).get(AwsFacade.Key.CATEGORY_NAME) + "\">";
		}
		
		return Action.SUCCESS;
	}

	
}
