<%-- 
    Document   : orderhistory
    Created on : 26-Mar-2018, 21:18:28
    Author     : emilv
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Order> orders = (ArrayList<Order>) session.getAttribute("orders"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Order history</h1>
        <div>
             <%for (Order order : orders) {
                    out.println(order);%>
            <br> <br>
            <%};%>
        </div>
    </body>
</html>
