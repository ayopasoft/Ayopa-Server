<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html> 

<head>
<link href="http://ayopa-resources.s3.amazonaws.com/css/page.css" rel="stylesheet" type="text/css">

</head>
<body>

<div id="fb-root"></div>

<script type="text/javascript">             

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
			
			FB.Canvas.setAutoResize();

			
			             
				};             
			(function() {                
				 var e = document.createElement('script');                 
				 e.type = 'text/javascript';                 
				 e.src = document.location.protocol +                     
				 '//connect.facebook.net/en_US/all.js';                 
				 e.async = true;                 
				 document.getElementById('fb-root').appendChild(e);             
				 }());               
			    
			//stream publish method    
			
			function inviteFriends(){
				var fbml = '<center><fb:request-form action="http://apps.facebook.com/ayopa_auctions/consumer/" target="_top" method="POST" invite="true" type="Product Suggestion" content="DESCRIPTION <fb:req-choice url=\'http://www.ayopadev.com/product/HJS-TV1.html\' label=\'Buy with Ayopa\'"> <fb:multi-friend-selector target="_top" cols="3" rows="5" showborder="false" actiontext="Invite more friends to buy this item with Ayopa" /> </fb:request-form></center>';  
					FB.ui({     
					method: 'fbml.dialog',     
					fbml: fbml,     
					size: {width:200,height:480}, 
					width:300,height:500 
				}); 
			}
			         
			
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
				 	{ text: 'What\'s Ayopa?', href: 'http://www.ayopa.com' }
				      
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
							<a href="<s:url action='consumer-canvas'/>">
								<li class="ayopa_myAuctions">
									<div class="ayopa_menu_item_left"></div>
									<div class="ayopa_menu_item_middle"></div>
									<div class="ayopa_menu_item_right"></div>
								</li>
							</a>
							<a href="<s:url action='consumer-auctions'/>">
								<li class="ayopa_ayopaAuctions">
									<div class="ayopa_menu_item_left"></div>
									<div class="ayopa_menu_item_middle"></div>
									<div class="ayopa_menu_item_right"></div>
								</li>
							</a>
							<a href="<s:url action='consumer-rebate'/>">
								<li class="ayopa_rebate">
									<div class="ayopa_menu_item_left"></div>
									<div class="ayopa_menu_item_middle"></div>
									<div class="ayopa_menu_item_right"></div>
								</li>
							</a>
							<a href="<s:url action='consumer-info'/>">
								<li class="ayopa_info active">
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
						<p style="margin-top:0; padding-top:10px;"><b>Who is Ayopa?<br>
			</b>Ayopa Software is a Startup company founded by a group of successful entrepreneurs with over 40 years of experience building web based companies.  Our application is a group buying platform that allows online business to launch group buying events from the product detail pages and enables consumers to get bigger discounts as more like minded buyers participate. You can contact us at <a href="mailto:info@ayopasoft.com">info@ayopasoft.com</a> (we do read these emails).<b><br>
				<br>
					Why should I use Ayopa?<br>
			</b>You should only use Ayopa if you are interested in getting volume pricing for a product by sharing your purchase of that product with your social network.<b><br>
				<br>
			</b></p>
		<p><strong>How does it Work?</strong></p>
		<p><strong>1. Shop like you normally do</strong><br>
			Let's say you are shopping for a new LCD TV and see our Group Buy &amp; Save button on the product page of the site that sells the TV. Simply click Group Buy &amp; Save and you will proceed to the checkout page as normal. When you submit the order, Ayopa will ask for your permission to register with Ayopa using your facebook credentials.  <b></b></p>
		
		<p><b>2. That's It?<br>
			</b>Yes, it is that easy. By giving permission to Ayopa to register yourself, you are permitting your participation in the group buy to be published to your facebook feed. Because Ayopa retailers believe you can help generate more immediate buyers, they are offering you special volume pricing. As more buyers join, the price drops and we rebate you the difference via PayPal.<br>
			<br>
			<strong>3. Track Savings Owed to You<br>
			</strong>Create your account on our site and install our facebook app. Now watch the price drop and track the savings owed to you.  We send you the difference between what you paid and the final price.</p>
			<p><b>Rebates</b>
			<br>
				We send rebates out every other Friday via paypal - not a bad way to start the weekend. There is no need to have a paypal account prior to using ayopa.
			</p>
		<p><b>How do I find Ayopa Auctions?<br>
			</b>The best way to find ayopa auctions is to use our facebook application and then click AyopaAuctions tab, which will list every current deal.  In addition, while you shop, simply keep an eye out for our 'Group Buy and Save button' on product detail pages.  We are a small company and you are using a Beta version of our product, so be patient as we continue to add merchants and send us any feedback at <a href="mailto:info@ayopasoft.com">info@ayopasoft.com</a></p>
		<b>For more information visit us at <a title="ayopasoft.com" href="http://ayopasoft.com" target="_blank">http://ayopasoft.com</a></b><br>
		<br>
						

							


							<!-- ************* START OF SIDE NAVIGATION  *************** -->
							<div class="ayopa_side_nav_container">
							<!-- 	<ul class="ayopa_side_nav">
									<a href=""><li><div class="ayopa_side_nav_left"></div>Clothing & Accessories<div class="ayopa_side_nav_right"></div></li></a>
									<a href=""><li><div class="ayopa_side_nav_left"></div>Baby & Toddlers<div class="ayopa_side_nav_right"></div></li></a>
									<a href=""><li class="active"><div class="ayopa_side_nav_left"></div>Electronics<div class="ayopa_side_nav_right"></div></li></a>
									<a href=""><li><div class="ayopa_side_nav_left"></div>Furniture<div class="ayopa_side_nav_right"></div></li></a>
									<a href=""><li><div class="ayopa_side_nav_left"></div>Hardware<div class="ayopa_side_nav_right"></div></li></a>
									<a href=""><li><div class="ayopa_side_nav_left"></div>Home & Garden<div class="ayopa_side_nav_right"></div></li></a>
									<a href=""><li><div class="ayopa_side_nav_left"></div>Office Supplies<div class="ayopa_side_nav_right"></div></li></a>
									<a href=""><li><div class="ayopa_side_nav_left"></div>Software<div class="ayopa_side_nav_right"></div></li></a>
									<a href=""><li><div class="ayopa_side_nav_left"></div>Sporting & Outdoors<div class="ayopa_side_nav_right"></div></li></a>
									<a href=""><li><div class="ayopa_side_nav_left"></div>Toys & Games<div class="ayopa_side_nav_right"></div></li></a>
									
								</ul> -->
							</div>
							<!-- ************* END OF SIDE NAVIGATION  *************** -->
							<div class="size_right"></div>
						</div>
						
						
						
						<!-- ************ END MAIN CONTENT  ******************* -->
						<div class="ayopa_content_bottom">
						</div>
						<div style="float:right">
							<a href="http://www.ayopasoft.com/privacy.html" target="_blank">Privacy Policy</a>
						</div>
					</div>


</body></html>