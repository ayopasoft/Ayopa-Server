<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ayopa Server</title>
<s:head />
</head>
<body>
<h1>Ayopa Server</h1>

<s:actionerror />

The input value was <s:property value="inputvalue"/>.

<s:form action="json">
	<s:textfield name="inputvalue" label="Input Value"/>
	<s:submit />
</s:form>

</body>
</html>
