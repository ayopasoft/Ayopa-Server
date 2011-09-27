package com.ez.server.model.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ez.server.model.Department;
import com.ez.server.utils.AwsFacade;

// not adding any users
public class DepartmentPersistence {

	
	public void putDepartment(Department dept) throws IOException{
		// check if id has been set. if not set it
		if(dept.getId() != null || dept.getId().length() == 0)
			dept.setId(UUID.randomUUID().toString());
		
		Map<String,String> deptMap = new HashMap<String,String>();
		Map<String,String> metaMap = new HashMap<String,String>();
		
		// check if fields are nonempty 
		// put in map
		if(dept.getId() != null)
			deptMap.put(AwsFacade.Key.DEPARTMENT_ID, dept.getId());
		if(dept.getParent_Id() != null)
			deptMap.put(AwsFacade.Key.DEPARTMENT_PARENT_ID, dept.getParent_Id());
		if(dept.getName() != null)
			deptMap.put(AwsFacade.Key.DEPARTMENT_NAME, dept.getName());
		if(dept.getUniqueID() != null)
			deptMap.put(AwsFacade.Key.DEPARTMENT_UNIQUE_ID, dept.getUniqueID());
		if(dept.getUrl() != null)
			deptMap.put(AwsFacade.Key.DEPARTMENT_URL, dept.getUrl());
		if(dept.getDescription() != null)
			deptMap.put(AwsFacade.Key.DEPARTMENT_DESCRIPTION, dept.getDescription());
		if(dept.getLogo() != null)
			deptMap.put(AwsFacade.Key.DEPARTMENT_LOGO, dept.getLogo());
		
		
		// post map to database
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.DEPARTMENT, dept.getId(), deptMap);
		
		
		// check if fields are nonempty
		// put in map
		if(dept.getId() != null)
			metaMap.put(AwsFacade.Key.METADATA_UNIT_ID, dept.getId());
		if(dept.getCountry() != null)
			metaMap.put(AwsFacade.Key.METADATA_COUNTRY, dept.getCountry());
		if(dept.getState() != null)
			metaMap.put(AwsFacade.Key.METADATA_STATE, dept.getState());
		if(dept.getCity() != null)
			metaMap.put(AwsFacade.Key.METADATA_CITY, dept.getCity());
		if(dept.getLoc_type() != null)
			metaMap.put(AwsFacade.Key.METADATA_LOC_TYPE, dept.getLoc_type());
		if(dept.getDiscipline_type() != null)
			metaMap.put(AwsFacade.Key.METADATA_UNIT_TYPE, dept.getDiscipline_type());
		if(dept.getEnroll() != 0)
			metaMap.put(AwsFacade.Key.METADATA_ENROLL, Integer.toString(dept.getEnroll()));
		
		// post map to database
		aws.putRow(AwsFacade.Table.METADATA, dept.getId(), metaMap);
		
		
	
		

	}
}
