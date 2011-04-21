<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ayopa Server</title>
<s:head />
</head>
<body>
      <div id="fb-root"></div>
<script type="text/javascript">             

	window.fbAsyncInit = function() {                 
		FB.init({appId: '120882414650116', domain: 'happyjacksoftware.com', status: true, cookie: true, xfbml: true});                   
		/* All the events registered */                
			FB.Event.subscribe('auth.login', function(response) {                     
			// do something with response                     
			login();                 
			});                 
			
			FB.Event.subscribe('auth.logout', function(response) {                     
			// do something with response                     
			logout();                 
			});                   
			
			FB.Canvas.setAutoResize();
			
			FB.provide("UIServer.Methods",
   { 'permissions.request' : { size : {width: 575, height: 300}, 
                    url: 'connect/uiserver.php',
                transform : FB.UIServer.genericTransform }
        } );


			FB.api('/me', function(user) {
				if (user != null) {
					
				}
			});
			
			FB.getLoginStatus(function(response) {                     
				if (response.session) {                         
				// logged in and connected user, someone you know                         
				login(); 
				   
				//parent.window.location.hash = user.id                 
				} 
			    else
			    {
			    
			    	//FB.ui({method: "permissions.request", "perms": 'email,offline_access'} , callBack);

			    	//self.location.href="http://www.ayopadev.com/ayopa/register.php";
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
			    	if (window.focus) {newwindow.focus()} 
			    	
					//$("a.fancybox").trigger("click");
			    }           
				});             
				};             
			(function() {                
				 var e = document.createElement('script');                 
				 e.type = 'text/javascript';                 
				 e.src = document.location.protocol +                     
				 '//connect.facebook.net/en_US/all.js';                 
				 e.async = true;                 
				 document.getElementById('fb-root').appendChild(e);             
				 }());               
			function login(){                 
				
				FB.api('/me', function(response) {                     
					var newwindow;
			    	var  screenX    = typeof window.screenX != 'undefined' ? window.screenX : window.screenLeft,                  
			    	screenY    = typeof window.screenY != 'undefined' ? window.screenY : window.screenTop,                  
			    	outerWidth = typeof window.outerWidth != 'undefined' ? window.outerWidth : document.body.clientWidth,                  
			    	outerHeight = typeof window.outerHeight != 'undefined' ? window.outerHeight : (document.body.clientHeight - 22), 
			    	width    = 500,                  
			    	height   = 200,                  
			    	left     = parseInt(screenX + ((outerWidth - width) / 2), 10),                  
			    	top      = parseInt(screenY + ((outerHeight - height) / 2.5), 10),                  
			    	features = ( 'width=' + width + ',height=' + height + ',left=' + left + ',top=' + top );               
			    	newwindow=window.open('http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/post-to-facebook','Post To Facebook',features);                
			    	        
				});             
				}             
			
			function logout(){                 
				document.getElementById('login').style.display = "none";             
			}               
  
			      
</script>
</body>
</html>
