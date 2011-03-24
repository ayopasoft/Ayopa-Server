<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ayopa Server</title>
<s:head />
	<LINK REL=stylesheet HREF="css/buy_with_ayopa.css" TYPE="text/css">
	<LINK REL=stylesheet HREF="css/page.css" TYPE="text/css">
</head>

<body>
<script>
window.fbAsyncInit = function() {                 
		FB.init({appId: '120882414650116', status: true, cookie: true, xfbml: true});                   
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
				// logged in and connected user, someone you know                         
				login();                     
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
					document.getElementById('login').style.display = "block";                    
					document.getElementById('login').innerHTML = response.name + " succsessfully logged in!";                 
				});             
				}             
			
			function logout(){                 
				document.getElementById('login').style.display = "none";             
			}               
			//stream publish method             
			
			function streamPublish(name, description, hrefTitle, hrefLink, userPrompt){                 
				FB.ui(                
				 {                     
				 	method: 'stream.publish',                     
				 	message: '',                     
				 	attachment: {                         
				 	name: name,                         
				 	caption: '',                         
				 	description: (description),                         
				 	href: hrefLink                     
				 	},                     
				 	action_links: [                         
				 	{ text: hrefTitle, href: hrefLink }                     
				 	],                     
				 	user_prompt_message: userPrompt                 
				 	},                 
				 	function(response) {                   
				 	});               
				 	}             
				 	function showStream(){                 
				 	FB.api('/me', function(response) {                     
				 	//console.log(response.id);                     
				 	streamPublish(response.name, 'Thinkdiff.net contains geeky stuff', 'hrefTitle', 'http://thinkdiff.net', "Share thinkdiff.net");                 
				 	});             
				 	}               
				 	function share(){                 
				 	var share = {                     
				 	method: 'stream.share',                     
				 	u: 'http://thinkdiff.net/'                
				 	};                   
				 	FB.ui(share, function(response) { console.log(response); });             
				 	}               
				 	function graphStreamPublish(){                 
				 	var body = 'Reading New Graph api & Javascript Base FBConnect Tutorial';                 
				 	FB.api('/me/feed', 'post', { message: body }, function(response) {                     
				 	if (!response || response.error) {                         
				 	alert('Error occured');                     
				 	} else {                         
				 	alert('Post ID: ' + response.id);                     
				 	}                 
				 	});             
				 	}               
				 	function fqlQuery(){                 
				 	FB.api('/me', function(response) {                      
				 	var query = FB.Data.query('select name, hometown_location, sex, pic_square from user where uid={0}', response.id);                      
				 	query.wait(function(rows) {                          
				 	document.getElementById('name').innerHTML =                          
				 	'Your name: ' + rows[0].name + "<br />" +                          
				 	'<img src="' + rows[0].pic_square + '" alt="" />' + "<br />";                      
				 	});                 
				 	});             
				 	}               
				 	function setStatus(){                 
				 	status1 = document.getElementById('status').value;                 
				 	FB.api(                   
				 	{                     
				 	method: 'status.set',                     
				 	status: status1                   
				 	},                   
				 	function(response) {                     
				 	if (response == 0){                         
				 	alert('Your facebook status not updated. Give Status Update Permission.');                     
				 	}                     
				 	else{                         
				 	alert('Your facebook status updated');                     
				 	}                   
				 	}                
				 	 );             
				 	 }         
				 	 </script>

  


			<!--  main_content restricts to 760px wide  -->
					<div class="main_content">
						<!--*********** BEGINNING OF MENU **************** -->
						<ul class="ayopa_menu">
							<a href="/ayopa">
								<li class="ayopa_info">
									<div class="ayopa_menu_item_left"></div>
									<div class="ayopa_menu_item_middle"></div>
									<div class="ayopa_menu_item_right"></div>
								</li>
							</a>
							
							<a href="/ayopa/auctions">
								<li class="ayopa_ayopaAuctions">
									<div class="ayopa_menu_item_left"></div>
									<div class="ayopa_menu_item_middle"></div>
									<div class="ayopa_menu_item_right"></div>
								</li>
							</a>
							
							
						</ul>
						<!--*********** END OF MENU **************** -->
						<div class="ayopa_content_top">
						</div>
						<!-- ***************MAIN CONTENT HOES IN THE AYOPA_CONTENT_MIDDLE DIV  *****************-->
						<div class="ayopa_content_middle">
							<LINK REL=stylesheet HREF="http://www.ayopadev.com/ayopa/css/page.css" TYPE="text/css">

							<div class="ayopa_splash_screen">
								<div class="ayopa_splash_header">
									 Make group buying really work for you
								</div>
								<div class="ayopa_splash_content">
									<ul>
										<li>Shop from our merchant partners on facebook or from their websites</li>
										<li>One click and your are in</li>
										<li>Watch prices fall</li>
										<li>Track your savings</li>
									</ul>
								</div>
								<div class="ayopa_splash_action">
									<a href="http://apps.facebook.com/ayopa_auctions/consumer"><img src="http://www.ayopadev.com/ayopa/images/ayopa_consumer-demo.png"></a>
				<a href="http://apps.facebook.com/ayopa_auctions/merchant"><img src="http://www.ayopadev.com/ayopa/images/ayopa_merchant-demo.png"></a>
									
								</div>
							</div>
							
							<!-- ************* START OF SIDE NAVIGATION  *************** -->
							
							<!-- ************* END OF SIDE NAVIGATION  *************** -->
							<div class="size_right"></div>
						</div>
						<!-- ************ END MAIN CONTENT  ******************* -->
						<div class="ayopa_content_bottom">
						</div>
					</div>



</body>
</html>