package com.ez.server.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ez.server.utils.AwsFacade;

public class Branding {
	
	
	private String unit_ID;
	private String background_color;
	private String foreground_color;
	private String header_color;
	private int header_size;
	private String header_font;
	private String text_color;
	private int text_size;
	private String text_font;
	
	// constructor
	public Branding(String unit_ID, String background_color,
			String foreground_color, String header_color, int header_size,
			String header_font, String text_color, int text_size,
			String text_font) {
		super();
		this.unit_ID = unit_ID;
		this.background_color = background_color;
		this.foreground_color = foreground_color;
		this.header_color = header_color;
		this.header_size = header_size;
		this.header_font = header_font;
		this.text_color = text_color;
		this.text_size = text_size;
		this.text_font = text_font;
	}

	
	public String getUnit_ID() {
		return unit_ID;
	}

	
	public void setUnit_ID(String unit_ID) {
		this.unit_ID = unit_ID;
	}

	
	public String getBackground_color() {
		return background_color;
	}

	
	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	
	public String getForeground_color() {
		return foreground_color;
	}

	
	public void setForeground_color(String foreground_color) {
		this.foreground_color = foreground_color;
	}

	
	public String getHeader_color() {
		return header_color;
	}

	
	public void setHeader_color(String header_color) {
		this.header_color = header_color;
	}

	
	public int getHeader_size() {
		return header_size;
	}

	
	public void setHeader_size(int header_size) {
		this.header_size = header_size;
	}

	
	public String getHeader_font() {
		return header_font;
	}

	
	public void setHeader_font(String header_font) {
		this.header_font = header_font;
	}

	
	public String getText_color() {
		return text_color;
	}

	
	public void setText_color(String text_color) {
		this.text_color = text_color;
	}

	
	public int getText_size() {
		return text_size;
	}

	
	public void setText_size(int text_size) {
		this.text_size = text_size;
	}

	
	public String getText_font() {
		return text_font;
	}

	
	public void setText_font(String text_font) {
		this.text_font = text_font;
	}

	// Post branding info to database
	public void putBrand(){
		
	}
	
	// get the branding info for an institution
	public static List<Map<String,String>> getBrand(String id) throws IOException{
		AwsFacade aws = AwsFacade.getInstance();
		
		String query = "select * from `" + AwsFacade.Table.BRANDING
		+ "` where `" + AwsFacade.Key.BRANDING_INST_ID + "` = " + id;
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		return results;
		
	}
	
}
