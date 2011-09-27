package com.ez.server.model.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ez.server.model.Branding;
import com.ez.server.utils.AwsFacade;

public class BrandingPersistence {

	public void putBrand(Branding brand) throws IOException{
		// check if fields are nonempty
		// put in map
		
		
		Map<String,String> brandMap = new HashMap<String,String>();
		
		if(brand.getUnit_ID() != null)
			brandMap.put(AwsFacade.Key.BRANDING_UNIT_ID, brand.getUnit_ID());
		if(brand.getBackground_color() != null)
			brandMap.put(AwsFacade.Key.BRANDING_BACK_COLOR, brand.getBackground_color());
		if(brand.getForeground_color() != null)
			brandMap.put(AwsFacade.Key.BRANDING_FRONT_COLOR, brand.getForeground_color());
		if(brand.getHeader_color() != null)
			brandMap.put(AwsFacade.Key.BRANDING_HEAD_COLOR, brand.getHeader_color());
		if(brand.getHeader_size() != 0)
			brandMap.put(AwsFacade.Key.BRANDING_HEAD_SIZE, Integer.toString(brand.getHeader_size()));
		if(brand.getHeader_font() != null)
			brandMap.put(AwsFacade.Key.BRANDING_HEAD_FONT, brand.getHeader_font());
		if(brand.getText_color() != null)
			brandMap.put(AwsFacade.Key.BRANDING_TEXT_COLOR, brand.getText_color());
		if(brand.getText_size() != 0)
			brandMap.put(AwsFacade.Key.BRANDING_TEXT_SIZE, Integer.toString(brand.getText_size()));
		if(brand.getText_font() != null)
			brandMap.put(AwsFacade.Key.BRANDING_TEXT_FONT, brand.getText_font());
			
		// post map to database
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.BRANDING, brand.getUnit_ID(), brandMap);
	}
}
