package com.ayopa.server.utils;

import static org.junit.Assert.*;

import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

public class FBUtilsTest {

	@Test
	public void testParseSignedRequest() throws Exception {
		
		
		
		JSONObject request = FBUtils.parseSignedRequest("gW2OR3CY3VSB8ZfipWDB3S8GMbaEcasQz5TZCKwYeJM.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImV4cGlyZXMiOjEzMDE4ODI0MDAsImlzc3VlZF9hdCI6MTMwMTg3Njc5Nywib2F1dGhfdG9rZW4iOiIxMjA4ODI0MTQ2NTAxMTZ8Mi44elRQNzVMZ2x3a0ljZHYwcUF1QmdnX18uMzYwMC4xMzAxODgyNDAwLTU1NjY1OTY5NXx6aXZGWGJMVGszWndubDY5UldVb0VmSzNSNUUiLCJyZWdpc3RyYXRpb24iOnsibmFtZSI6IkthcmkgWmltbWVybWFuIiwiZW1haWwiOiJrYXJpXHUwMDQwa3Jhemt1c3RvbXMuY29tIn0sInJlZ2lzdHJhdGlvbl9tZXRhZGF0YSI6eyJmaWVsZHMiOiJuYW1lLGVtYWlsIn0sInVzZXIiOnsiY291bnRyeSI6InVzIiwibG9jYWxlIjoiZW5fVVMifSwidXNlcl9pZCI6IjU1NjY1OTY5NSJ9");
		JSONObject registration = (JSONObject) request.get("registration");
		
		assertEquals("Signed request email not parsed correctly","kari@krazkustoms.com",registration.get("email"));
		assertEquals("Signed request name not parsed correctly","Kari Zimmerman",registration.get("name"));
		assertEquals("Signed request user id not parsed correctly","556659695",request.get("user_id"));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject = FBUtils.parseSignedRequest("0XHsZHL2NzmvuVBV9Nq6yttt62EdO8BC7_EGT9cQqMo.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImV4cGlyZXMiOjAsImlzc3VlZF9hdCI6MTMwMjA1Nzk4Niwib2F1dGhfdG9rZW4iOiIxMjA4ODI0MTQ2NTAxMTZ8MzA5YWExNjM3MTg3MGFkMTAyODc4ZmRiLTU1NjY1OTY5NXw4VUhBZW40LTdNZHpCakhqM09fRHAtWVdpYW8iLCJwYWdlIjp7ImlkIjoiMTkyOTM1NDk3MzgzMzY4IiwibGlrZWQiOmZhbHNlLCJhZG1pbiI6dHJ1ZX0sInVzZXIiOnsiY291bnRyeSI6InVzIiwibG9jYWxlIjoiZW5fVVMiLCJhZ2UiOnsibWluIjoyMX19LCJ1c2VyX2lkIjoiNTU2NjU5Njk1In0=");
		
		JSONObject page = (JSONObject) jsonObject.get("page");
		System.out.println(page);
		
		assertEquals("Signed request page id incorrect","192935497383368",page.get("id"));
		assertEquals("Signed request admin id incorrect",Boolean.TRUE,page.get("admin"));
	}

}
