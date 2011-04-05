package com.ayopa.server.utils;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class FBUtilsTest {

	@Test
	public void testParseSignedRequest() throws Exception {
		Map<String,String> request = FBUtils.ParseSignedRequest("gW2OR3CY3VSB8ZfipWDB3S8GMbaEcasQz5TZCKwYeJM.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImV4cGlyZXMiOjEzMDE4ODI0MDAsImlzc3VlZF9hdCI6MTMwMTg3Njc5Nywib2F1dGhfdG9rZW4iOiIxMjA4ODI0MTQ2NTAxMTZ8Mi44elRQNzVMZ2x3a0ljZHYwcUF1QmdnX18uMzYwMC4xMzAxODgyNDAwLTU1NjY1OTY5NXx6aXZGWGJMVGszWndubDY5UldVb0VmSzNSNUUiLCJyZWdpc3RyYXRpb24iOnsibmFtZSI6IkthcmkgWmltbWVybWFuIiwiZW1haWwiOiJrYXJpXHUwMDQwa3Jhemt1c3RvbXMuY29tIn0sInJlZ2lzdHJhdGlvbl9tZXRhZGF0YSI6eyJmaWVsZHMiOiJuYW1lLGVtYWlsIn0sInVzZXIiOnsiY291bnRyeSI6InVzIiwibG9jYWxlIjoiZW5fVVMifSwidXNlcl9pZCI6IjU1NjY1OTY5NSJ9");
		assertEquals("Signed request email not parsed correctly","kari@krazkustoms.com",request.get("email"));
		assertEquals("Signed request name not parsed correctly","Kari Zimmerman",request.get("name"));
		assertEquals("Signed request user id not parsed correctly","556659695",request.get("user_id"));
		
	}

}
