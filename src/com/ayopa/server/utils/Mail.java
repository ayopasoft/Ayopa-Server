package com.ayopa.server.utils;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Mail {
	 

	 
	public static void postMail( String recipients[ ], String subject, String message , String from) throws MessagingException
	{
	    boolean debug = false;

	     //Set the host smtp address
	     Properties props = new Properties();
	     props.put("mail.smtp.host", "smtp.gmail.com");
	     props.put("mail.smtp.port", "465");
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.socketFactory.port", "465");
	     props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	     props.put("mail.smtp.socketFactory.fallback", "false");

	     
	    // create some properties and get the default Session
	     Session session = Session.getDefaultInstance(props,
	    		 new javax.mail.Authenticator() {

	    		 protected PasswordAuthentication getPasswordAuthentication() {
	    		 return new PasswordAuthentication("rebate@ayopasoft.com", "ayopa2011");
	    		 }
	    		 });
	     
	    session.setDebug(debug);

	    // create a message
	    Message msg = new MimeMessage(session);

	    // set the from and to address
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);

	    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
	    for (int i = 0; i < recipients.length; i++)
	    {
	        addressTo[i] = new InternetAddress(recipients[i]);
	    }
	    msg.setRecipients(Message.RecipientType.TO, addressTo);
	   

	    // Optional : You can also set your custom headers in the Email if you Want
	   // msg.addHeader("MyHeaderName", "myHeaderValue");

	    // Setting the Subject and Content Type
	    msg.setSubject(subject);
	    msg.setContent(message, "text/plain");
	    Transport.send(msg);
	}
}
