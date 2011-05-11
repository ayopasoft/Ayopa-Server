package com.ayopa.server.model.persistence;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ayopa.server.model.Invoice;
import com.ayopa.server.utils.AwsFacade;

public class InvoicePersistence {
	private static Log log = LogFactory.getLog(InvoicePersistence.class);
	
	public String putInvoice (Invoice invoice) throws IOException {
		if (invoice.getInvoice_id() == null || invoice.getInvoice_id().length() == 0)
			invoice.setInvoice_id(UUID.randomUUID().toString());		
		
		Map<String, String> map = new HashMap<String, String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"); 
		
		map.put(AwsFacade.Key.INVOICE_ID,invoice.getInvoice_id());
		
		if (invoice.getInvoice_date() != null)
			map.put(AwsFacade.Key.INVOICE_DATE, df.format(invoice.getInvoice_date()));
		if (invoice.getInvoice_paid() == true || invoice.getInvoice_paid() == false)
			map.put(AwsFacade.Key.INVOICE_PAID, Boolean.toString(invoice.getInvoice_paid()));
		if (invoice.getAuction_info() != null) {
			JSONArray jsonObject = (JSONArray) JSONSerializer.toJSON( invoice.getAuction_info() );
			map.put(AwsFacade.Key.INVOICE_AUCTIONS, jsonObject.toString());
		}
		if (invoice.getInvoice_pd_date() != null)
			map.put(AwsFacade.Key.INVOICE_PD_DATE, df.format(invoice.getInvoice_pd_date()));
		if (invoice.getInvoice_total() >= 0)
			map.put(AwsFacade.Key.INVOICE_TOTAL, Double.toString(invoice.getInvoice_total()));
		if (invoice.getMerchant_id() != null)
			map.put(AwsFacade.Key.MERCHANT_ID, invoice.getMerchant_id());
		if (invoice.getInvoice_notice() >= 0)
			map.put(AwsFacade.Key.INVOICE_NOTICE, Integer.toString(invoice.getInvoice_notice()));
		
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.INVOICE, invoice.getInvoice_id(), map);
		
		return invoice.getInvoice_id();
		
	}
	
	public Invoice getInvoice(String invoice_id) throws IOException {
		AwsFacade aws = AwsFacade.getInstance();
		
		Map<String,String> map = aws.getRow(AwsFacade.Table.INVOICE, invoice_id);
		
		Invoice invoice = mapToInvoice(map);
		
		return invoice;
	}
	
	public Invoice mapToInvoice(Map<String, String> map) {
		Invoice invoice = new Invoice();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"); 
		
		if (map.containsKey(AwsFacade.Key.INVOICE_ID)){
			invoice.setInvoice_id(map.get(AwsFacade.Key.INVOICE_ID));
		}
		
		if (map.containsKey(AwsFacade.Key.INVOICE_TOTAL)){
			invoice.setInvoice_total(Double.parseDouble(map.get(AwsFacade.Key.INVOICE_TOTAL)));
		}
		
		if (map.containsKey(AwsFacade.Key.INVOICE_PAID)){
			invoice.setInvoice_paid(Boolean.parseBoolean(map.get(AwsFacade.Key.INVOICE_PAID)));
		}
		if (map.containsKey(AwsFacade.Key.INVOICE_DATE)){
			try {
				invoice.setInvoice_date(df.parse(map.get(AwsFacade.Key.INVOICE_DATE)));
			} catch (ParseException e) {
				log.error("Invoice date is invalid:" + e);
			}
		}
		if (map.containsKey(AwsFacade.Key.INVOICE_PD_DATE)){
			try {
				invoice.setInvoice_pd_date(df.parse(map.get(AwsFacade.Key.INVOICE_PD_DATE)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("Invoice paid date is invalid:" + e);
			}
		}
		
		if (map.containsKey(AwsFacade.Key.MERCHANT_ID)){
			invoice.setMerchant_id(map.get(AwsFacade.Key.MERCHANT_ID));
		}
		
		if (map.containsKey(AwsFacade.Key.INVOICE_NOTICE)){
			invoice.setInvoice_notice(Integer.parseInt(map.get(AwsFacade.Key.MERCHANT_ID)));
		}
		
		if (map.containsKey(AwsFacade.Key.INVOICE_AUCTIONS)){
			JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON( map.get(AwsFacade.Key.INVOICE_AUCTIONS) );  
			List<Map<String,String>> list = new ArrayList<Map<String, String>>();
			
			for (int i=0; i<jsonArray.size(); i++) {
				Map<String, String> auctionMap = new HashMap<String,String>();
				
				JSONObject obj = jsonArray.getJSONObject(i);
				auctionMap.put("auction_id", obj.getString("auction_id"));
				auctionMap.put("product_name",obj.getString("product_name"));
				auctionMap.put("auction_total", obj.getString("auction_total"));
				auctionMap.put("commission_total", obj.getString("commission_total"));
				auctionMap.put("rebate_total", obj.getString("rebate_total"));
				
				list.add(auctionMap);
				
			}
			
			invoice.setAuction_info(list);
		}
			
			
		return invoice;
	}
	
	
}
