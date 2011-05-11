package com.ayopa.server.model;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;

import com.ayopa.server.model.persistence.InvoicePersistence;
import com.ayopa.server.model.persistence.MerchantPersistence;
import com.ayopa.server.utils.Mail;
import com.ayopa.server.utils.PaypalUtils;

public class Invoice {
	
	private String invoice_id;
	private String merchant_id;
	private List<Map<String, String>> auction_info;
	private double invoice_total;
	private Date invoice_date;
	private boolean invoice_paid;
	private Date invoice_pd_date;
	private int invoice_notice;
	
	
	
	public int getInvoice_notice() {
		return invoice_notice;
	}
	public void setInvoice_notice(int invoice_notice) {
		this.invoice_notice = invoice_notice;
	}
	public List<Map<String, String>> getAuction_info() {
		return auction_info;
	}
	public void setAuction_info(List<Map<String, String>> auction_info) {
		this.auction_info = auction_info;
	}
	
	public boolean getInvoice_paid() {
		return invoice_paid;
	}
	public void setInvoice_paid(boolean invoice_paid) {
		this.invoice_paid = invoice_paid;
	}
	public Date getInvoice_pd_date() {
		return invoice_pd_date;
	}
	public void setInvoice_pd_date(Date invoice_pd_date) {
		this.invoice_pd_date = invoice_pd_date;
	}
	public Date getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}
	public double getInvoice_total() {
		return invoice_total;
	}
	public void setInvoice_total(double invoice_total) {
		this.invoice_total = invoice_total;
	}
	
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	
  private static List<Invoice> getInvoicesForMerchants(List<Auction> auctions) throws IOException{
		
		String merchant_id = "0";
		List<Map<String, String>> inv_auctions = new ArrayList<Map<String,String>>();
		Invoice invoice = new Invoice();
		List<Invoice> invoices = new ArrayList<Invoice>();
		InvoicePersistence ip = new InvoicePersistence();
		
		Date now = Calendar.getInstance().getTime();
		
		double total = 0;
		
		for (int i=0; i < auctions.size(); i++)
		{
			Map<String,String> map_auctions = new HashMap<String,String>();
			Map<String,Object> map = new HashMap<String,Object>();
			CurrentAuction currAuction = new CurrentAuction();
			map = currAuction.getCurrentQuantity(auctions.get(i).getAuction_id());
			int quantity = (Integer) map.get("quantity");
			currAuction = CurrentAuction.getCurrentAuctionInfo(auctions.get(i), quantity);
			map = CurrentAuction.getCurrentAuctionRebate(auctions.get(i).getMerchant_id(), currAuction.getCurrent_price(), (Double) map.get("total"), quantity);
			
		
			if (auctions.get(i).getMerchant_id().equals(merchant_id)){
				//add to invoice total for same merchant
				total += (Double) map.get("auction_total");
				
			}
			else {
				
				//start new invoice
				invoice = new Invoice();
				inv_auctions = new ArrayList<Map<String,String>>();
				
				total = (Double) map.get("auction_total");		
			}
			
			map_auctions.put("auction_id", auctions.get(i).getAuction_id());
			map_auctions.put("product_name", auctions.get(i).getProduct_title());
			map_auctions.put("rebate_total", map.get("rebate_total").toString());
			map_auctions.put("commission_total", map.get("commission_total").toString());
			map_auctions.put("auction_total", map.get("auction_total").toString());
			
			inv_auctions.add(map_auctions);
			invoice.setAuction_info(inv_auctions);
			invoice.setMerchant_id(auctions.get(i).getMerchant_id());
			invoice.setInvoice_total(total);
			invoice.setInvoice_date(now);
			invoice.setInvoice_id(UUID.randomUUID().toString());
			invoice.setInvoice_notice(1);
			
			merchant_id = auctions.get(i).getMerchant_id();
			
			if ((i + 1) == auctions.size()){
				//last iteration - save invoice
				invoices.add(invoice);
				ip.putInvoice(invoice);
				
			}
			else
			{
				if (!merchant_id.equals(auctions.get(i + 1).getMerchant_id())){ //finished with merchant - save invoice
					invoices.add(invoice);
					ip.putInvoice(invoice);
				}
			}
		}

			return invoices;
	}
	
  
  public static void sendInvoices() throws IOException, MessagingException{
	 
	  NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
	  
	  List<Invoice> invoices = new ArrayList<Invoice>();
	  List<Auction> auctions = new ArrayList<Auction>();
	  Merchant merchant = new Merchant();
	  auctions = Auction.getAuctionsforInvoice();
		
	  invoices = getInvoicesForMerchants(auctions);
	  
	  for (int i = 0; i < invoices.size(); i++){
		  merchant = merchant.getMerchant(invoices.get(i).getMerchant_id());
		  String[] recipient;
		  recipient = new String[1];
		  recipient[0] = merchant.getMerchant_paypal();
		  String message = "Please pay for the following auctions:\n";
		  for (int j = 0; j < invoices.get(i).getAuction_info().size(); j++) {
			  message += "\n\nAuction: " + invoices.get(i).getAuction_info().get(j).get("auction_id");
			  message += "    Product: " + invoices.get(i).getAuction_info().get(j).get("product_name");
			  message += "    Rebate: " + currencyFormatter.format(Double.parseDouble(invoices.get(i).getAuction_info().get(j).get("rebate_total")));
			  message += "    Commission: " + currencyFormatter.format(Double.parseDouble(invoices.get(i).getAuction_info().get(j).get("commission_total")));
			  message += "    Total: " + currencyFormatter.format(Double.parseDouble(invoices.get(i).getAuction_info().get(j).get("auction_total")));
		  }
		   message += "\n\nClick this link to pay: " + PaypalUtils.PAY_NOW_URL + "&amount=" + Double.toString(invoices.get(i).getInvoice_total()) + "&item_name=" + "Ayopa%20Auctions" + "&invoice=" + invoices.get(i).getInvoice_id();
		  Mail.postMail(recipient, PaypalUtils.INVOICE_SUBJECT, message, PaypalUtils.INVOICE_FROM);
		  Auction.invoiceAuctions(invoices.get(i).getAuction_info());
	  }
	  
  }

  public Invoice getInvoice(String invoice_id) throws IOException {

		Invoice invoice = new Invoice();
		InvoicePersistence ip = new InvoicePersistence();
		
		invoice = ip.getInvoice(invoice_id);
		return invoice;
  }
  
  public void putInvoice (Invoice invoice) throws IOException
  {
	    InvoicePersistence ip = new InvoicePersistence();
			
		String invoiceReturn = ip.putInvoice(invoice);
  }
	
}
