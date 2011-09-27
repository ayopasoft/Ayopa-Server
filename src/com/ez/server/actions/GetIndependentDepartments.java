package com.ez.server.actions;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ez.server.model.IndependentDepartment;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@ParentPackage (value="application")

public class GetIndependentDepartments {

	public JSONObject getAll() throws IOException{
		String jsonString = "";
		JSONArray jsonArray = null;
		
		try {
			
			List<java.util.Map<String, String>> list  = IndependentDepartment.getAll();
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		JSONObject json = new JSONObject();
		json.put("All Independent Departments", jsonArray);
		return json;
	}
	
	public JSONObject getOne(String id) throws IOException{
		String jsonString = "";
		JSONArray jsonArray = null;
		
		try {
			
			List<java.util.Map<String, String>> list  = IndependentDepartment.getOne(id);
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		JSONObject json = new JSONObject();
		json.put("One Independent Department", jsonArray);
		return json;
	}
	
}
