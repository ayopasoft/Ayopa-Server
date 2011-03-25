package com.ayopa.server.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class ScheduleSerializer {

	public String toJson (List<ScheduleItem> schedule) {
		JSONArray jsonSched = (JSONArray) JSONSerializer.toJSON( schedule ); 
		return jsonSched.toString();
	}
	
	public List<ScheduleItem> toSchedule (String scheduleList){
		
		List<ScheduleItem> schedule =  new ArrayList<ScheduleItem>();
			
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON( scheduleList );  
		
		for (int i=0; i<jsonArray.size(); i++) {
			ScheduleItem item = new ScheduleItem();
			JSONObject obj = jsonArray.getJSONObject(i);
			
			double dis = obj.getDouble("dis");
			int min = obj.getInt("min");
			int max = obj.getInt("max");
			int add = obj.getInt("add");
			
			item.setDis(dis);
			item.setMin(min);
			item.setMax(max);
			item.setAdd(add);
			schedule.add(item);
		}

		
		return schedule;
		
	}
	
}
