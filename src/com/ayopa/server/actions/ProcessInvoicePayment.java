package com.ayopa.server.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.Invoice;
import com.ayopa.server.utils.PaypalUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="ProcessRegistration.jsp" ),

})
public class ProcessInvoicePayment extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	
	private String item_name;
	private String item_number;
	private String invoice;
	private String payment_status;
	private String mc_gross;
	private String mc_currency;
	private String receiver_email;
	private String txn_id;
	private String txn_type;
	private HttpServletRequest request;
	
	
	public void setServletRequest(HttpServletRequest request){
	    this.request = request;
	  }

	  public HttpServletRequest getServletRequest(){
	    return request;
	  }

	public String getTxn_type() {
		return txn_type;
	}

	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_number() {
		return item_number;
	}

	public void setItem_number(String item_number) {
		this.item_number = item_number;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getMc_gross() {
		return mc_gross;
	}


	public void setMc_gross(String mc_gross) {
		this.mc_gross = mc_gross;
	}

	public String getMc_currency() {
		return mc_currency;
	}


	public void setMc_currency(String mc_currency) {
		this.mc_currency = mc_currency;
	}


	public String getReceiver_email() {
		return receiver_email;
	}


	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}


	public String getTxn_id() {
		return txn_id;
	}


	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}


	@Override
	public String execute() throws Exception {
		
		Enumeration en = request.getParameterNames();
		String str = "cmd=_notify-validate";
		while(en.hasMoreElements()){
		String paramName = (String)en.nextElement();
		String paramValue = request.getParameter(paramName);
		str = str + "&" + paramName + "=" + URLEncoder.encode(paramValue,"UTF-8");
		}
		

		// post back to PayPal system to validate
		// NOTE: change http: to https: in the following URL to verify using SSL (for increased security).
		// using HTTPS requires either Java 1.4 or greater, or Java Secure Socket Extension (JSSE)
		// and configured for older versions.
		//URL u = new URL("https://www.paypal.com/cgi-bin/webscr");
		
		URL u = new URL(PaypalUtils.PAYPAL_URL);
		URLConnection uc = u.openConnection();
		uc.setDoOutput(true);
		uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		PrintWriter pw = new PrintWriter(uc.getOutputStream());
		pw.println(str);
		pw.close();

		BufferedReader in = new BufferedReader(
		new InputStreamReader(uc.getInputStream()));
		String res = in.readLine();
		in.close();

		Invoice inv = new Invoice();
		
		
		//check notification validation
		if(res.equals("VERIFIED")) {
			if (payment_status.equals("Completed"))
			{
				if (txn_type.equals("web_accept")) //Pay Now Button
				{
					if (receiver_email.equals(PaypalUtils.PAYPAL_EMAIL))
					{
						
						//try {
							inv = inv.getInvoice(invoice);
							if (Double.parseDouble(mc_gross) == inv.getInvoice_total() && mc_currency.equals("USD"))
							{
								Date now = Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime();
								inv.setInvoice_paid(Boolean.TRUE);
								inv.setInvoice_pd_date(now);
								inv.putInvoice(inv);
								Auction.clearAuctions(inv.getAuction_info());
							}
						//} catch (Exception e) {
							
						//	e.printStackTrace();
						//}
						
					}
					else
					{
						
					}
				}
				else
				{
					
				}
			}
			else if (txn_type.equals("masspay")) //mass payment
			{
				
			}
		}
		else if(res.equals("INVALID")) {
		// log for investigation
		}
		else {
		// error
		}

		
		
		return Action.SUCCESS;
	}

	



	


	
}
