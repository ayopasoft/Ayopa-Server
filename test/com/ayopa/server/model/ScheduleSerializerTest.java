package com.ayopa.server.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ScheduleSerializerTest {

	@Test
	public void testToJson() {
		
		String strReturn = "";
		List<ScheduleItem> list = new ArrayList<ScheduleItem>();
		strReturn = ScheduleSerializer.toJson(list);
		
		//empty list
		assertEquals("Empty list not serialized properly", "[]", strReturn);
		
		ScheduleItem item = new ScheduleItem();
		item.setDis(5.00);
		item.setMin(20);
		item.setMax(25);
		item.setAdd(5);
		list.add(item);
		
		
		strReturn = ScheduleSerializer.toJson(list);
//		JSONArray jsonArray = (JSONArray) new JSONArray( strReturn );  
//		JsonObject obj = new JsonObject(strReturn);
		
		
		assertEquals("1 Element list not serialized properly", "[{\"min\":20,\"max\":25,\"dis\":5,\"add\":5}]", strReturn);
		
		ScheduleItem item2 = new ScheduleItem();
		item2.setDis(6.00);
		item2.setMin(30);
		item2.setMax(35);
		item2.setAdd(3);
		list.add(item2);
		
		ScheduleItem item3 = new ScheduleItem();
		item3.setDis(7.90);
		item3.setMin(40);
		item3.setMax(45);
		item3.setAdd(4);
		list.add(item3);
		
		strReturn = ScheduleSerializer.toJson(list);
		
		assertEquals("Multiple element list not serialized properly", "[{\"min\":20,\"max\":25,\"dis\":5,\"add\":5},{\"min\":30,\"max\":35,\"dis\":6,\"add\":3},{\"min\":40,\"max\":45,\"dis\":7.9,\"add\":4}]", strReturn);
		
		
	}

	@Test
	public void testToSchedule() {
		
		String strInput = "[]";
		List<ScheduleItem> list = new ArrayList<ScheduleItem>();
		
		
		list = ScheduleSerializer.toSchedule(strInput);

		assertEquals("Empty list not serialized properly", 0, list.size());
		
		strInput = "[{\"min\":20,\"max\":25,\"dis\":5,\"add\":5}]";
		
		list.clear();
		list = ScheduleSerializer.toSchedule(strInput);
		
		assertEquals("1 Element list not serialized properly", 1, list.size());
		assertEquals("1 Element getAdd test failed", 5, list.get(0).getAdd());
		assertEquals("1 Element getMax test failed", 25, list.get(0).getMax());
		assertEquals("1 Element getDis test failed", 5, list.get(0).getDis(), 0);
		assertEquals("1 Element getMin test failed", 20, list.get(0).getMin());
		
		strInput = "[{\"min\":20,\"max\":25,\"dis\":5,\"add\":5},{\"min\":30,\"max\":35,\"dis\":6,\"add\":3},{\"min\":40,\"max\":45,\"dis\":7.9,\"add\":4}]";
		
		list.clear();
		list = ScheduleSerializer.toSchedule(strInput);
		
		assertEquals("Multiple element list not serialized properly", 3, list.size());
		
		
		assertEquals("List Element getAdd test failed", 5, list.get(0).getAdd());
		assertEquals("List Element getMax test failed", 35, list.get(1).getMax());
		assertEquals("List Element getDis test failed", 7.9, list.get(2).getDis(), 0.005);
		assertEquals("List Element getMin test failed", 20, list.get(0).getMin());
		assertEquals("List Element getDis Integer test failed", 6, list.get(1).getDis(), 0);
	}

}
