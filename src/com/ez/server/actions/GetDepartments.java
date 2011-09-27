package com.ez.server.actions;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ez.server.model.Department;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@ParentPackage (value="application")

public class GetDepartments {

	// return a JSONObject containing information for all the departments
	public JSONObject getDepartments() throws IOException{
		String jsonString = "";
		JSONArray jsonArray = null;
		
		try {
			
			List<java.util.Map<String, String>> list  = Department.getAllDepartments();
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		JSONObject json = new JSONObject();
		json.put("All Departments", jsonArray);
		return json;
	}
	
	
	// returns a JSONObject containing information for one department
	public JSONObject getOneDepartment(String id) throws IOException{
		String jsonString = "";
		JSONArray jsonArray = null;
		
		try {
			
			List<java.util.Map<String, String>> list  = Department.getDepartment(id);
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		JSONObject json = new JSONObject();
		json.put("One Department", jsonArray);
		return json;
	}
	
	// returns a JSONObject containing information for one department
	public JSONObject getSubDept(String id) throws IOException{
		String jsonString = "";
		JSONArray jsonArray = null;
			
		try {
			List<java.util.Map<String, String>> list  = Department.getSubDept(id);
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
			
		} catch (Exception e) {
				
				
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
			
		JSONObject json = new JSONObject();
		json.put("One Department", jsonArray);
		return json;
	}
}
