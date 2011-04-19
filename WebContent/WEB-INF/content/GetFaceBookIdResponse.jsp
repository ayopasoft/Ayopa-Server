<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redirected From Facebook</title>
<s:head />
</head>
<body>
<h1>Redirected From Facebook</h1>

<s:actionerror />

<table border="1">
<tr><th>Key</th><th>Value</th></tr>
<tr><th align="right">error</th><td><s:property value="error"/></td></tr>
<tr><th align="right">errorReason</th><td><s:property value="errorReason"/></td></tr>
<tr><th align="right">errorDescription</th><td><s:property value="errorDescription"/></td></tr>
<tr><th align="right">code</th><td><s:property value="code"/></td></tr>
<tr><th align="right">accessToken</th><td><s:property value="accessToken"/></td></tr>
</table>

</body>
</html>
