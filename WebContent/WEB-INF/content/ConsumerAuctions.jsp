<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html> 

<head>
<link href="http://ayopa-resources.s3.amazonaws.com/css/page.css" rel="stylesheet" type="text/css">

</head>
<body>


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
							<a href="http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/privacy-policy" target="_blank">Privacy Policy</a>
						</div>
					</div>


</body></html>