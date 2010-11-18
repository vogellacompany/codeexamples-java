<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="de.vogella.gae.java.tendering.model.Order" %>
<%@ page import="de.vogella.gae.java.tendering.model.Dao" %>


<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
	<head>
		<title>My Tendering Overview</title>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
		  <meta charset="utf-8"> 
	</head>
	<body>
	
	<%

UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

String url = userService.createLoginURL(request.getRequestURI());
String urlLinktext = "Login";
List<Order> orders = new ArrayList<Order>();
            
if (user != null){
    url = userService.createLogoutURL(request.getRequestURI());
    urlLinktext = "Logout";
    orders = Dao.getOrders(user.getUserId());
    
}
    
%>
	<div style="width: 100%;">
		<div class="line"></div>
		<div class="topLine">
			<div style="float: left;" class="headline">Tendering View</div>
			<div style="float: right;"><a href="<%=url%>"><%=urlLinktext%></a> <%=(user==null? "" : user.getNickname())%></div>
		</div>
	</div>
	
<div style="clear: both;"/>	
You have a total number of <%= orders.size() %>  Tendering Requests.

<table>
  <tr>
      <th>Order number </th>
      <th>From Location </th>
      <th>To Location</th>
      <th>Quantity</th>
      <th>Price</th>
      <th>Accept</th>
      <th>Decline</th>
    </tr>

<% for (Order order : orders) {%>
<tr> 
<td>
<%=order.getNumber()%>
</td>
<td>
<%=order.getFromLocation()%>
</td>
<td>
<%=order.getToLocation()%>
</td>
<td>
<%=order.getQuantity()%>
</td>
<td>
<%=order.getPrice()%>
</td>
<td>
<a class="done" href="/accept?id=<%=order.getNumber()%>" >Accept</a>
</td>
<td>
<a class="done" href="/decline?id=<%=order.getNumber()%>" >Reject</a>
</td>
</tr> 
<%}
%>
</table>

</body>
</html>
