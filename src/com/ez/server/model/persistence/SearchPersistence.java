package com.ez.server.model.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ez.server.model.Search;
import com.ez.server.utils.AwsFacade;

public class SearchPersistence {
	// upload a search record to the database
	// still needs to post the review stages list
	public void putSearch(Search srch) throws IOException{
		// check if the search has unique ID
		// if not assign one
		if(srch.getId() == null || srch.getId().length() == 0){
			srch.setId(UUID.randomUUID().toString());
		}
		
		Map<String,String> sMap = new HashMap<String,String>();
		Map<String,String> metaMap = new HashMap<String,String>();
		
		if(srch.getId() != null)
			sMap.put(AwsFacade.Key.SEARCH_ID, srch.getId());
		if(srch.getParent_ID() != null)
			sMap.put(AwsFacade.Key.SEARCH_PARENT_ID, srch.getParent_ID());
		if(srch.getPosition() != null)
			sMap.put(AwsFacade.Key.SEARCH_POSITION, srch.getPosition());
		if(srch.getRefMin() > -1)
			sMap.put(AwsFacade.Key.SEARCH_REF_MIN, Integer.toString(srch.getRefMin()));
		if(srch.getRefMax() > -1)
			sMap.put(AwsFacade.Key.SEARCH_REF_MAX, Integer.toString(srch.getRefMax()));
		if(srch.getMail() != null)
			sMap.put(AwsFacade.Key.SEARCH_MAIL, srch.getMail());
		if(srch.isPdf() == true || srch.isPdf() == false)
			sMap.put(AwsFacade.Key.SEARCH_PDF, Boolean.toString(srch.isPdf()));
		if(srch.isWord() == true || srch.isWord() == false)
			sMap.put(AwsFacade.Key.SEARCH_WORD, Boolean.toString(srch.isWord()));
		if(srch.getDescription() != null)
			sMap.put(AwsFacade.Key.SEARCH_DESCRIPTION, srch.getDescription());
		if(srch.isCitizenInfo() == true || srch.isCitizenInfo() == false)
			sMap.put(AwsFacade.Key.SEARCH_CITIZEN_INFO, Boolean.toString(srch.isCitizenInfo()));
		if(srch.isFound() == true || srch.isFound() == false)
			sMap.put(AwsFacade.Key.SEARCH_FOUND, Boolean.toString(srch.isFound()));
		if(srch.getAdPlaces() != null)
			sMap.put(AwsFacade.Key.SEARCH_AD_PLACES, srch.getAdPlaces());
		if(srch.isOffline() == true || srch.isOffline() == false)
			sMap.put(AwsFacade.Key.SEARCH_OFFLINE, Boolean.toString(srch.isOffline()));
		if(srch.isOnline() == true || srch.isOnline() == false)
			sMap.put(AwsFacade.Key.SEARCH_ONLINE, Boolean.toString(srch.isOnline()));
		if(srch.getReview().size() > 0){
			// Convert the list<String> to a String 
			// using '/' as a delimiter
			List<String> tmp = srch.getReview();
			String result = "";
			for(int i = 0; i < tmp.size(); i++){
				result = result + "/" + tmp.get(i);
			}
			sMap.put(AwsFacade.Key.SEARCH_REVIEW, result);
		}
		
			
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.SEARCH, srch.getId(), sMap);
		
		
		if(srch.getId() != null)
			metaMap.put(AwsFacade.Key.METADATA_UNIT_ID, srch.getId());
		if(srch.getCountry() != null)
			metaMap.put(AwsFacade.Key.METADATA_COUNTRY, srch.getCountry());
		if(srch.getState() != null)
			metaMap.put(AwsFacade.Key.METADATA_STATE, srch.getState());
		if(srch.getCity() != null)
			metaMap.put(AwsFacade.Key.METADATA_CITY, srch.getCity());
		if(srch.getLoc_type() != null)
			metaMap.put(AwsFacade.Key.METADATA_LOC_TYPE, srch.getLoc_type());
		if(srch.getPosition_type() != null)
			metaMap.put(AwsFacade.Key.METADATA_UNIT_TYPE, srch.getPosition_type());
		if(srch.isTenure() == true || srch.isTenure() == false)
			metaMap.put(AwsFacade.Key.METADATA_TENURE, Boolean.toString(srch.isTenure()));
		
		aws.putRow(AwsFacade.Table.METADATA, srch.getId(), metaMap);
		
		
	}
}
