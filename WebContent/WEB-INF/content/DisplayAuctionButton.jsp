<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ayopa Server</title>
<s:head />
	<LINK REL=stylesheet HREF="http://www.ayopadev.com/ayopa/css/buy_with_ayopa.css" TYPE="text/css">
	<LINK REL=stylesheet HREF="http://www.ayopadev.com/ayopa/css/page.css" TYPE="text/css">
</head>
<body>

<s:actionerror />


	<body bgcolor="#ffffff">
		<div class="main_buy_with_ayopa">
		
							<div class="buy_with_ayopa_data">
								<div class="buy_with_ayopa_buyer_count">
									<div class="buy_with_ayopa_current">
										<s:property value="current_quant"/>
									</div>

									<div class="buy_with_ayopa_next"><s:property value="next_quant"/></div>
									<div class="buy_with_ayopa_last"><s:property value="highest_quant"/></div>
								</div>
								<div>
									<s:text name="format.graphcurr">
											<s:param name="value" value="current_price"/>
										</s:text>
								</div>
								<div class="buy_with_ayopa_time_left">
									<div class="buy_with_ayopa_time_left_header">
										<div>Sec</div>
										<div>Min</div>

										<div>Hrs</div>
										<div>Day</div>
									</div>
									<div class="buy_with_ayopa_time_left_numbers">
										<div><s:property value="time_seconds"/></div>  <!--Seconds-->
										<div><s:property value="time_minutes"/></div>  <!--Minutes-->

										<div><s:property value="time_hours"/></div>  <!--Hours-->
										<div><s:property value="time_days"/></div>	<!--Days-->
									</div>
								</div>
							</div>	
		</div>

</body>
</html>
