package com.ayopa.server.model.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ayopa.server.utils.AwsFacade;

public class CategoryPersistence {

	
	public String putCategory (String category) throws IOException {
				
		Map<String, String> map = new HashMap<String, String>();
		map.put(AwsFacade.Key.CATEGORY_NAME,category);
				
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.CATEGORY, category, map);
		
		return category;
		
	}

	
}
