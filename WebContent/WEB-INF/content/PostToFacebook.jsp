<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://ayopa-resources.s3.amazonaws.com/css/page.css" rel="stylesheet" type="text/css">
<title>Ayopa Server</title>
<s:head />
</head>
<body>
<div id="fb-root"></div>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId  : '120882414650116',
      status : true, // check login status
      cookie : true, // enable cookies to allow the server to access the session
      xfbml  : true  // parse XFBML
    });
  };

  (function() {
    var e = document.createElement('script');
    e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
    e.async = true;
    document.getElementById('fb-root').appendChild(e);
  }());
  
  function streamPublish(name, description, hrefTitle, hrefLink, image, userPrompt){                 
		FB.ui(                
		 {                     
		 	method: 'stream.publish',                     
		 	message: '',                     
		 	attachment: {
           
		 	name: userPrompt,                         
		 	caption: '',                         
		 	description: (description),                         
		 	href: hrefLink, 
		 	media: [
		    {
		        "type": "image", 
		        "src": image, 
		        "href": hrefLink
		    }
		    
		    ]
			
         
		 	},                     
		 	action_links: [                         
		 	{ text: 'What\'s Ayopa?', href: 'http://www.ayopasoft.com' }                   
		 	],                     
		 	user_prompt_message: userPrompt                 
		 	},                 
		 	function(response) {                   
		 	});               
		 	}   
		 	          
		 	function showStream(message, hrefTitle, hrefLink, image, title){                 
		 	FB.api('/me', function(response) {                     
		 	//console.log(response.id);                     
		 	streamPublish(response.name, message, hrefTitle, hrefLink, image, title);                 
		 	});             
		 	}  
</script>

<h2>Thank you for participating in this ayopa group buy</h2>
<p>When you complete your purchase, we will post your purchase to your Facebook wall. Below is an example of how these types of posts look. You may also re-post this or send to specific friends from the <a href="http://apps.facebook.com/ayopa_auctions" target="_blank">ayopa facebook app.</a></p>
	<img src="https://ayopa-resources.s3.amazonaws.com/images/FB_post_preview.png">							
</body>
</html>
