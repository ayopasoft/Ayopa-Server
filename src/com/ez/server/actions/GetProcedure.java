package com.ez.server.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ez.server.model.Procedure;
import com.ez.server.model.Search;
import com.opensymphony.xwork2.Action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})


public class GetProcedure {
	// return a JSON object containing a list
	// of all the procedure for a given search
	public JSONObject getAll(String id){
		JSONArray jsonArray = null;
		String jsonString = "";
		
		try {
			
			List<java.util.Map<String, String>> list  = Procedure.getAll(id);
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		JSONObject json = new JSONObject();
		json.put("All procedures for search", jsonArray);
		return json;
	}
	
	// returns a JSONObject containing a single procedure
	public JSONObject getOne(String id){
		JSONArray jsonArray = null;
		String jsonString = "";
		
		try {
			
			List<java.util.Map<String, String>> list  = Procedure.getOne(id);
			jsonArray = (JSONArray) JSONSerializer.toJSON( list ); 
			jsonString = jsonArray.toString();
		
		} catch (Exception e) {
			
			
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		JSONObject json = new JSONObject();
		json.put("One Procedure", jsonArray);
		return json;
	}
}
