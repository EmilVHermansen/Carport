
<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee home page</title>
    </head>
         <%@include file="header.jsp" %>

    <body>

        <h1>Hello <%=request.getParameter("email")%> </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.
        
        <% Order order = (Order) request.getAttribute("order");%>
        <h2>Update order status</h2>
        <form action="FrontController" name="updateorder" method="POST">
        <input type="hidden" name="command" value="Update Order">
        <input type="hidden" name="orderid" value="<%=order.getIdOrder()%>">
        <input type="submit"  value="Update order">
    </form>
    </body>
</html>
