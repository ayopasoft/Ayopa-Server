package com.ayopa.server.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ayopa.server.model.persistence.AuctionPersistence;
import com.ayopa.server.model.persistence.CategoryPersistence;
import com.ayopa.server.utils.AwsFacade;

public class Category {
	private static Log log = LogFactory.getLog(AuctionPersistence.class);
	
	private String category_name;

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	public String putCategory(String category) throws IOException {
		CategoryPersistence cp = new CategoryPersistence();
		String output = cp.putCategory(category);
		return output;
	}
	
	public static List<Map<String,String>> getCategories() throws IOException {
		
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.CATEGORY 
		+ "` where `" + AwsFacade.Key.CATEGORY_NAME + "` like '%'"
		+ " order by `" + AwsFacade.Key.CATEGORY_NAME + "`";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
}
