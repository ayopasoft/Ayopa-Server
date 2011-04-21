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
								<li class="ayopa_rebate active">
									<div class="ayopa_menu_item_left"></div>
									<div class="ayopa_menu_item_middle"></div>
									<div class="ayopa_menu_item_right"></div>
								</li>
							</a>
							<a href="<s:url action='consumer-info'/>">
								<li class="ayopa_info">
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
						
						
						<s:if test="session_expired">
							Your session has expired.  Please refresh your web browser.
						</s:if>

						<s:iterator value="auctions" >
						
							<!--  ************** START OF PURCHASED ITEM ****************-->
							<div class="ayopa_auction_tile">
								<div class="ayopa_auction_tile_top"></div>
								<div class="ayopa_auction_tile_middle">
									<!-- **** TITLE  *** -->
									<div class="ayopa_item_title"><s:property value="title" /></div>
									
									<s:if test="%{expired}">
										<img src="http://ayopa-resources.s3.amazonaws.com/images/rebate_ac.png">
									</s:if>
									<s:else>
										<img src="http://ayopa-resources.s3.amazonaws.com/images/rebate_aip.png">
									</s:else>
									
									<!-- **** PHOTO *** -->
									<div class="ayopa_item_photo"><a href="<s:property value="link"/>" target="_blank"><img width="100px" src="<s:property value="image"/>"></a></div>


									<!-- *** scale *** -->
									<div class="ayopa_sell_scale">
										<div class="ayopa_start">
											Start<br />
											<s:text name="format.graphcurr">
												<s:param name="value" value="start_price"/>
											</s:text>
										</div>
										<div class="ayopa_current">
											Currently<br />
											<s:text name="format.graphcurr">
												<s:param name="value" value="current_price"/>
											</s:text>
										</div>
										<div class="ayopa_next">
											Next<br />
											<s:text name="format.graphcurr">
												<s:param name="value" value="next_price"/>
											</s:text>
										</div>
										<div class="ayopa_lowest">
											Lowest<br />
											<s:text name="format.graphcurr">
												<s:param name="value" value="lowest_price"/>
											</s:text>
										</div>		
									</div>

									<!-- *** GRAPH  *** -->
									<div class="ayopa_graph">
										<div>
											<img src="http://ayopa-resources.s3.amazonaws.com/images/buyer_graph.png" />
										</div>
										<div class="ayopa_buyer_amount">
											<div class="ayopa_start"><s:property value="start_quant"/></div>
											<div class="ayopa_current"><s:property value="current_quant"/></div>
											<div class="ayopa_next"><s:property value="next_quant"/></div>
											<div class="ayopa_lowest"><s:property value="highest_quant"/></div>
										</div>
										<div class="ayopa_graph_label">Buyers</div>
									</div>

									<!-- *** PRICE AND BUY SECTION  ** -->
									<div class="ayopa_tile_right_content">
										<div class="ayopa_current_price">Current Price: 
											<s:text name="format.graphcurr">
												<s:param name="value" value="current_price"/>
											</s:text>
										</div>
										<div class="ayopa_you_bought">You bought this item for: 
											<s:text name="format.graphcurr">
												<s:param name="value" value="purchase_price"/>
											</s:text>
										</div>
									</div>
									
									<s:if test="%{!expired}">	
									<!-- *** TIME LEFT  *** -->
									<div class="ayopa_time_left">
										<div class="ayopa_time_left_labels">
											<div>Sec</div>	
											<div>Min</div>
											<div>Hrs</div>
											<div>Day</div>
										</div>
										<div class="ayopa_time_left_time">
											<div><s:property value="time_seconds"/></div>	<!--seconds-->
											<div><s:property value="time_minutes"/></div>	<!--minutes-->
											<div><s:property value="time_hours"/></div>	<!--hours-->
											<div><s:property value="time_days"/></div>	<!--Days-->
										</div>
									</div>
									</s:if>
									<div class="ayopa_merchant_name"><a href="<s:property value="merchant_url"/>" target="_blank"><s:property value="merchant_name"/></a></div>
									
									<div class="size_right"></div>
								</div>
								<div class="ayopa_auction_tile_bottom"></div>
							</div>
							<!--  ************** END OF PURCHASED ITEM ****************-->
						
						</s:iterator>
							


							<!-- ************* START OF SIDE NAVIGATION  *************** -->
							<div class="ayopa_side_nav_container">
								
							</div>
							<!-- ************* END OF SIDE NAVIGATION  *************** -->
							<div class="size_right"></div>
						</div>
						<!-- ************ END MAIN CONTENT  ******************* -->
						<div class="ayopa_content_bottom">
						</div>
						<div style="float:right">
							<a href="http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/privacy-policy" target="_blank">Privacy Policy</a>
						</div>
					</div>


</body></html>