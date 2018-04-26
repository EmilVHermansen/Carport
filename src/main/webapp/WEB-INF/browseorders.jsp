<%-- 
    Document   : orderhistory
    Created on : 26-Mar-2018, 21:18:28
    Author     : emilv
--%>

<%@page import="FunctionLayer.LogicFacade"%>
<%@page import="DBAccess.DataAccessObject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Browse Orders</title>
    </head>
    <body>
        <form name="customerInfo" action="FrontController" method="POST">
            <p>Enter order id or customer email.</p>
            <input type="hidden" name="command" value="customerInfo">
            <input type="text" name ="orderid" placeholder="Email">
            <input type="submit" name="submit" value="submit">
        </form>

        <form name="updateStatus" action="FrontController" method="post">
            <input type="hidden" name="command" value="updatestatus">
            <p>Enter order id</p>
            <input type="text" name ="orderid" placeholder="Order id">
            <select name="status" value="Opdater Status">
                <option name="Behandler">Behandler</option>
                <option name="Behandlet">behandlet</option>
                <option name="Afsendt">Afsendt</option>
                <option name="Annulleret">Annulleret</option>
            </select>

        </form>


        <h1>Order history</h1>
        <div>
            <%for (Order order : orders) {
                     out.println(order);%>
            <br> <br>
            <%};%>
        </div>
    </body>
</html>
