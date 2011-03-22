package com.ayopa.server.results;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class StringResult extends StrutsResultSupport {

	private static final long serialVersionUID = 1L;
	
	private String charset = "utf-8";
	private String property;
	private String value;
	private String contentType = "text/plain";


	@Override
	protected void doExecute(String finalLocation, ActionInvocation invocation)
			throws Exception {
		if (value == null) {
			value = (String)invocation.getStack().findValue(conditionalParse(property, invocation));
		}
		if (value == null) {
			throw new IllegalArgumentException("No string available in value stack named '" + property + "'");
		}

		byte[] b = value.getBytes(charset);

		HttpServletResponse res = (HttpServletResponse) invocation.getInvocationContext().get(HTTP_RESPONSE);

		res.setContentType(contentType + "; charset=" + charset);
		res.setContentLength(b.length);
		OutputStream out  = res.getOutputStream();
		try {
			out.write(b);
			out.flush();
		} finally {
			out.close();	
		}
	}


	public String getCharset() {
		return charset;
	}


	public void setCharset(String charset) {
		this.charset = charset;
	}


	public String getProperty() {
		return property;
	}


	public void setProperty(String property) {
		this.property = property;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
