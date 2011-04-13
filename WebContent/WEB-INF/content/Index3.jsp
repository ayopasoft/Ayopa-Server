<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ayopa Server</title>
<s:head />
</head>

<body>
<p>

The user is <s:property value="#session.user_id" default="NIL"/>.


<p>

The counter is <s:property value="#session.my_counter" default="NIL"/>.


<p>

<a href="<s:url action='index2'/>">Go again</a>.
</body>
</html>