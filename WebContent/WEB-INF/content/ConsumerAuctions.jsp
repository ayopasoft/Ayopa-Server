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
			
			FB.Canvas.setAutoResize(50);
			//FB.Canvas.setSize({height:$('.main_content').css('height') + 'px'});
			
			
			             
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
			
			function streamPublish(name, description, hrefTitle, hrefLink, image, userPrompt){                 
				FB.ui(                
				 {                     
				 	method: 'stream.publish',                     
				 	message: '',                     
				 	attachment: {
                     
				 	name: hrefTitle,                         
				 	caption: userPrompt,                         
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
								<li class="ayopa_ayopaAuctions active">
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
						
						


						<s:iterator value="auctions" >
						
							<!--  ************** START OF PURCHASED ITEM ****************-->
							<div class="ayopa_auction_tile">
								<div class="ayopa_auction_tile_top"></div>
								<div class="ayopa_auction_tile_middle">
									<!-- **** TITLE  *** -->
									<div class="ayopa_item_title"><s:property value="title" /></div>

									<!-- **** PHOTO *** -->
									<div class="ayopa_item_photo"><a href="<s:property value="link"/>" target="_blank"><img width="100px" src="<s:property value="image"/>"></a></div>

									<s:if test="current_price >= price_conflict">
									
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
									</s:if>
										<s:else>
											<div class="ayopa_auction_tile_price_conflict">The current "Group Buy and Save" price of this item is too low to show, 
											but we can tell you it's no more than 
											<s:text name="format.currency">
												<s:param name="value" value="price_conflict"/>
											</s:text>
											</div>
										</s:else>
										
									<!-- *** PRICE AND BUY SECTION  ** -->
										
									<div class="ayopa_tile_right_content">
									  <s:if test="current_price >= price_conflict">
										<div class="ayopa_current_price">Current Price: 
											<s:text name="format.graphcurr">
												<s:param name="value" value="current_price"/>
											</s:text>
										</div>
									</s:if>	
											<div><a href="<s:property value="link"/>" target="_blank"><img src="http://ayopa-resources.s3.amazonaws.com/images/buy_now.png" /></a></div>
											<div><a href="" onclick="showStream('<s:property value="description" />', 'Buy <s:property value="title"/> at <s:property value="merchant_name"/>', '<s:property value="link"/>', '<s:property value="image"/>','A New GroupBuyNSave from <s:property value="merchant_name"/> is available. Check out this great deal.'); return false;">Post to Wall</a></div>
									</div>

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
									<div class="ayopa_merchant_name"><a href="<s:property value="merchant_url"/>" target="_blank"><s:property value="merchant_name"/></a></div>
									
									<div class="size_right"></div>
								</div>
								<div class="ayopa_auction_tile_bottom"></div>
							</div>
							<!--  ************** END OF PURCHASED ITEM ****************-->
						
						</s:iterator>
							


							<!-- ************* START OF SIDE NAVIGATION  *************** -->
							<div class="ayopa_side_nav_container">
							
							 	<ul class="ayopa_side_nav">
							 		<div class="ayopa_categories_header"></div>
							 		<a href="?category="><li <s:if test="category == ''"></s:if>><div class="ayopa_side_nav_left"></div>All<div class="ayopa_side_nav_right"></div></li></a>
							 	    <s:iterator value="categories" status="categoriesStatus">
							 	    <s:url id="url" action="consumer-auctions">
  										<s:param name="category">%{categories[#categoriesStatus.index]}</s:param>
									</s:url>
									
									<s:a href="%{url}"><li <s:if test="category == categories[#categoriesStatus.index]">class = "active"</s:if>><div class="ayopa_side_nav_left"></div><s:property /><div class="ayopa_side_nav_right"></div></li></s:a>
									</s:iterator>
								</ul> 
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