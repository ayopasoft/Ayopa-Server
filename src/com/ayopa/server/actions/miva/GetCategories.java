package com.ayopa.server.actions.miva;


import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

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
		
		category = "<category value=\"Baby & Toddlers\">";
		category += "<category value=\"Clothing & Accessories\">";
		category += "<category value=\"Electronics\">";
		category += "<category value=\"Furniture\">";
		category += "<category value=\"Hardware\">";
		category += "<category value=\"Home & Garden\">";
		category += "<category value=\"Office Supplies\">";
		category += "<category value=\"Sporting Goods & Outdoor\">";
		category += "<category value=\"Software\">";
		category += "<category value=\"Toys & Games\">";
		
		return Action.SUCCESS;
	}

	
}
