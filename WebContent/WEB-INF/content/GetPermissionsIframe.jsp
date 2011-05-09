<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
	<script src="http://ayopa-resources.s3.amazonaws.com/js/jquery.fancybox-1.3.4/fancybox/jquery.fancybox-1.3.4.js"></script>
	<script src="http://ayopa-resources.s3.amazonaws.com/js/jquery.fancybox-1.3.4/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link href="http://ayopa-resources.s3.amazonaws.com/js/jquery.fancybox-1.3.4/fancybox/jquery.fancybox-1.3.4.css" type="text/css" rel="stylesheet">
<title>Ayopa Server</title>
<script type="text/javascript">

function newWindow()
{
					var newwindow;
			    	var  screenX    = typeof window.screenX != 'undefined' ? window.screenX : window.screenLeft,                  
			    	screenY    = typeof window.screenY != 'undefined' ? window.screenY : window.screenTop,                  
			    	outerWidth = typeof window.outerWidth != 'undefined' ? window.outerWidth : document.body.clientWidth,                  
			    	outerHeight = typeof window.outerHeight != 'undefined' ? window.outerHeight : (document.body.clientHeight - 22), 
			    	width    = 885,                  
			    	height   = 575,                  
			    	left     = parseInt(screenX + ((outerWidth - width) / 2), 10),                  
			    	top      = parseInt(screenY + ((outerHeight - height) / 2.5), 10),                  
			    	features = ( 'width=' + width + ',height=' + height + ',left=' + left + ',top=' + top );               
			    	newwindow=window.open('http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/get-permissions','Register through Facebook',features); 
			    	$('#hiddenLink', window.parent.document).fancybox.close(); 
}

</script>
<link href="http://ayopa-resources.s3.amazonaws.com/css/page.css" rel="stylesheet" type="text/css">
<s:head />
</head>
<body>
<h2>Group Buy &amp; Save</h2>
<p>In order to participate in the group buy, you will have to register for an ayopa account through your facebook account.</p>
<a href="#" onClick="newWindow()">Click Here to Continue</a>
</body>
</html>
