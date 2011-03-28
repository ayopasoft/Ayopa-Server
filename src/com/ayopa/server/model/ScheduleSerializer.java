package com.ayopa.server.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class ScheduleSerializer {

	public static String toJson (List<ScheduleItem> schedule) {
		JSONArray jsonSched = (JSONArray) JSONSerializer.toJSON( schedule ); 
		return jsonSched.toString();
	}
	
	public static List<ScheduleItem> toSchedule (String scheduleList){
		
		List<ScheduleItem> schedule =  new ArrayList<ScheduleItem>();
			

		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON( scheduleList );  
		
		for (int i=0; i<jsonArray.size(); i++) {
			ScheduleItem item = new ScheduleItem();
			JSONObject obj = jsonArray.getJSONObject(i);
			
			item.setDis(obj.getDouble(Auction.Key.DISCOUNT));
			item.setMin(obj.getInt(Auction.Key.MINIMUM));
			item.setMax(obj.getInt(Auction.Key.MAXIMUM));
			item.setAdd(obj.getInt(Auction.Key.ADDITIONAL));
		
			schedule.add(item);
		}

		
		return schedule;
		
	}
	
}
