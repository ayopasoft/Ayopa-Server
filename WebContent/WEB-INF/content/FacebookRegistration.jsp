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
		FB.init({appId: '120882414650116', domain: 'ayopasoft.com', status: true, cookie: true, xfbml: true});                   
		/* All the events registered */                			
			FB.Event.subscribe('auth.login', function(response) {                     
			// do something with response                     
			login();                 
			});                 
			
			FB.Event.subscribe('auth.logout', function(response) {                     
			// do something with response                     
			logout();                 
			});                   
			
			
			FB.getLoginStatus(function(response) {                     
				if (response.session) { 
					if (response.perms) {
						if (hasPermission(response, "publish_stream") && hasPermission(response, "email") && hasPermission(response, "offline_access") && "<s:property value="isUser"/>" == "true"){
							getLoggedIn();
						}
						else
						{
							getRegistration();
						}
					}
					else
					{
						getRegistration();
					}
					                  
				} 
			    else
			    {	
					getRegistration();
			    }           
				}, true);             
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
					getLoggedIn();
			    	
				});             
				}             
			
			     
			
			function getRegistration(){
				location.href = "http://beta.ayopasoft.com/AyopaServer/get-permissions-iframe";
				
			}
			
			function getLoggedIn(){
				location.href =  "http://beta.ayopasoft.com/AyopaServer/post-to-facebook";  
				
			}
			
			function hasPermission(response, perm) {
				var begin = response.perms.indexOf('extended":[') + 11;
				var end = response.perms.indexOf(']', begin);
				var perms = response.perms.substring(begin, end);
				return (perms.indexOf(perm) != -1);
			}
  
			      
</script>
</body>
</html>
