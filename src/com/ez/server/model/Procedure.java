package com.ez.server.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ez.server.model.persistence.ProcedurePersistence;
import com.ez.server.utils.AwsFacade;

public class Procedure {

	private String id;
	private String search_ID;
	private String name;
	private int min;
	private int max;
	private String instructions;
	private boolean pdf;
	private boolean word;


	// constructor
	public Procedure(String id, String search_ID, String name, int min,
			int max, String instructions, boolean pdf, boolean word) {
		super();
		this.id = id;
		this.search_ID = search_ID;
		this.name = name;
		this.min = min;
		this.max = max;
		this.instructions = instructions;
		this.pdf = pdf;
		this.word = word;
	}


	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSearch_ID() {
		return search_ID;
	}


	public void setSearch_ID(String search_ID) {
		this.search_ID = search_ID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getMin() {
		return min;
	}


	public void setMin(int min) {
		this.min = min;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
	}


	public String getInstructions() {
		return instructions;
	}


	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}


	public boolean isPdf() {
		return pdf;
	}


	public void setPdf(boolean pdf) {
		this.pdf = pdf;
	}


	public boolean isWord() {
		return word;
	}


	public void setWord(boolean word) {
		this.word = word;
	}
	
	// post a procedure to the database
	public void putProcedure() throws IOException{
		Procedure pro = this;
		ProcedurePersistence pp = new ProcedurePersistence();
		pp.putProcedure(pro);
	}
	
	// Get all the procedure steps pertaining to a certain search
	public static List<Map<String,String>> getAll(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.PROCEDURE
		+ "` where `" + AwsFacade.Key.PROCEDURE_SEARCH_ID + "` = " + id
		+ " order by '" + AwsFacade.Key.PROCEDURE_NAME + "`";
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}
	
	// get a single procedure
	public static List<Map<String,String>> getOne(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.PROCEDURE
		+ "` where `" + AwsFacade.Key.PROCEDURE_ID + "` = " + id;
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
	}

}
