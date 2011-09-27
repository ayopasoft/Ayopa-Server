package com.ez.server.model.persistence;

import com.ez.server.model.Institution;
import com.ez.server.model.persistence.InstitutionPersistence;
import com.ez.server.utils.AwsFacade;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;




public class InstitutionPersistence {

	// Post a new Institution to the database
	
	public void putInstitution(Institution institution) throws IOException{
		// if the id has been set. if not set it
		if(institution.getID() == null || institution.getID().length() == 0){
			institution.setID(UUID.randomUUID().toString());
		}
		
		Map<String,String> instMap = new HashMap<String,String>();
		Map<String,String> metaMap = new HashMap<String,String>();
		Map<String,String> brandMap = new HashMap<String,String>();
		
		// check all fields contain values
		// and put in map
		if(institution.getID() != null)
			instMap.put(AwsFacade.Key.INSTITUTION_ID, institution.getID());
		if(institution.getName() != null)
			instMap.put(AwsFacade.Key.INSTITUTION_NAME, institution.getName());
		if(institution.getShortName() != null)
			instMap.put(AwsFacade.Key.INSTITUTION_SHORTNAME, institution.getShortName());
		if(institution.getUrl() != null)
			instMap.put(AwsFacade.Key.INSTITUTION_URL, institution.getUrl());
		if(institution.getDescription() != null)
			instMap.put(AwsFacade.Key.INSTITUTION_DESCRIPTION, institution.getDescription());
		if(institution.isAsk_Demographics() == true || institution.isAsk_Demographics() == false)
			instMap.put(AwsFacade.Key.INSTITUTION_ASK_DEMOGRAPHIC, Boolean.toString(institution.isAsk_Demographics()));
		if(institution.isHr_search() == true || institution.isHr_search() == false)
			instMap.put(AwsFacade.Key.INSTITUTION_HR_SEARCH, Boolean.toString(institution.isHr_search()));
		if(institution.isHr_review() == true || institution.isHr_review() == false)
			instMap.put(AwsFacade.Key.INSTITUTION_HR_REVIEW, Boolean.toString(institution.isHr_review()));
		if(institution.getInfo_urls().size() > 0){
			// create a string of info urls with '/' deimiter
			// out of list of info urls
			List<String> tmp = institution.getInfo_urls();
			String result = "";
			for(int i = 0; i < tmp.size(); i++)
				result = result + "/" + tmp.get(i);	
			instMap.put(AwsFacade.Key.INSTITUTION_INFO_URLS, result);
		}
		
		
		// put map in database
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.INSTITUTION, institution.getID(), instMap);
		
		// check if fields are non empty
		// put into map
		if(institution.getID() != null)
			metaMap.put(AwsFacade.Key.METADATA_UNIT_ID, institution.getID());
		if(institution.getCountry() != null)
			metaMap.put(AwsFacade.Key.METADATA_COUNTRY, institution.getCountry());
		if(institution.getState() != null)
			metaMap.put(AwsFacade.Key.METADATA_STATE, institution.getState());
		if(institution.getCity() != null)
			metaMap.put(AwsFacade.Key.METADATA_CITY, institution.getCity());
		if(institution.getLoc_type() != null)
			metaMap.put(AwsFacade.Key.METADATA_LOC_TYPE, institution.getLoc_type());
		if(institution.getInst_type() != null)
			metaMap.put(AwsFacade.Key.METADATA_UNIT_TYPE, institution.getInst_type());
		if(institution.getEnroll() != 0)
			metaMap.put(AwsFacade.Key.METADATA_ENROLL, Integer.toString(institution.getEnroll()));
		
		//post the map to the database
		aws.putRow(AwsFacade.Table.METADATA, institution.getID(), metaMap);
		
		
		// check if fields are nonempty
		// put in map
		if(institution.getID() != null)
			brandMap.put(AwsFacade.Key.INSTITUTION_ID, institution.getID());
		if(institution.getLogo() != null)
			brandMap.put(AwsFacade.Key.BRANDING_LOGO, institution.getLogo());
		if(institution.getBackground_color() != null)
			brandMap.put(AwsFacade.Key.BRANDING_BACK_COLOR, institution.getBackground_color());
		if(institution.getForeground_color() != null)
			brandMap.put(AwsFacade.Key.BRANDING_FRONT_COLOR, institution.getForeground_color());
		if(institution.getHeader_color() != null)
			brandMap.put(AwsFacade.Key.BRANDING_HEAD_COLOR, institution.getHeader_color());
		if(institution.getHeader_size() != 0)
			brandMap.put(AwsFacade.Key.BRANDING_HEAD_SIZE, Integer.toString(institution.getHeader_size()));
		if(institution.getHeader_font() != null)
			brandMap.put(AwsFacade.Key.BRANDING_HEAD_FONT, institution.getHeader_font());
		if(institution.getText_color() != null)
			brandMap.put(AwsFacade.Key.BRANDING_TEXT_COLOR, institution.getText_color());
		if(institution.getText_size() != 0)
			brandMap.put(AwsFacade.Key.BRANDING_TEXT_SIZE, Integer.toString(institution.getText_size()));
		if(institution.getText_font() != null)
			brandMap.put(AwsFacade.Key.BRANDING_TEXT_FONT, institution.getText_font());
		
		// post map to database
		aws.putRow(AwsFacade.Table.BRANDING, institution.getID(), brandMap);

	}
}
