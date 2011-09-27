package com.ez.server.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;


import com.ez.server.model.Search;
import com.opensymphony.xwork2.Action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})

public class GetSearch {
	// returns a JSONObject containing all searches
	public JSONObject getAll(){
		String jsonString = "";
		JSONArray jsonArray = null;
		
		try {
			
			List<java.util.Map<String, String>> list  = Search.getAll();
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		JSONObject json = new JSONObject();
		json.put("All Searches", jsonArray);
		return json; 
	}
	
	// returns a JSONObject containing one search
	public JSONObject getOne(String id){
		String jsonString = "";
		JSONArray jsonArray = null;
		
		try {
			
			List<java.util.Map<String, String>> list  = Search.getOne(id);
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		JSONObject json = new JSONObject();
		json.put("One Search", jsonArray);
		return json; 
	}
	
	// returns a JSONObject containing 
	// all the searches for a specific department
	public JSONObject getDeptSearch(String id){
		String jsonString = "";
		JSONArray jsonArray = null;
		
		try {
			
			List<java.util.Map<String, String>> list  = Search.getDeptSearches(id);
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		JSONObject json = new JSONObject();
		json.put("One Search", jsonArray);
		return json; 
	}
}
