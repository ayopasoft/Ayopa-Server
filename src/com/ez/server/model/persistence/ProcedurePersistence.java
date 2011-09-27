package com.ez.server.model.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ez.server.model.Procedure;
import com.ez.server.utils.AwsFacade;

public class ProcedurePersistence {

	
	public void putProcedure(Procedure procedure) throws IOException{
		// check if id is set. if not set it
		if(procedure.getId() == null || procedure.getId().length() == 0)
			procedure.setId(UUID.randomUUID().toString());
		
		Map<String,String> map = new HashMap<String,String>();
		
		// check if fields are nonempty
		// put into map
		if(procedure.getId() != null)
			map.put(AwsFacade.Key.PROCEDURE_ID, procedure.getId());
		if(procedure.getSearch_ID() != null)
			map.put(AwsFacade.Key.PROCEDURE_SEARCH_ID, procedure.getSearch_ID());
		if(procedure.getName() != null)
			map.put(AwsFacade.Key.PROCEDURE_NAME, procedure.getName());
		if(procedure.getMin() > -1)
			map.put(AwsFacade.Key.PROCEDURE_MIN, Integer.toString(procedure.getMin()));
		if(procedure.getMax() > 0)
			map.put(AwsFacade.Key.PROCEDURE_MAX, Integer.toString(procedure.getMax()));
		if(procedure.getInstructions() != null)
			map.put(AwsFacade.Key.PROCEDURE_INSTRUCTIONS, procedure.getInstructions());
		if(procedure.isPdf() == true || procedure.isPdf() == false)
			map.put(AwsFacade.Key.PROCEDURE_PDF, Boolean.toString(procedure.isPdf()));
		if(procedure.isWord() == true || procedure.isWord() == false)
			map.put(AwsFacade.Key.PROCEDURE_WORD, Boolean.toString(procedure.isWord()));
		
		
		// post map to database
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.PROCEDURE, procedure.getId(), map);
		
	
		
	}
}
