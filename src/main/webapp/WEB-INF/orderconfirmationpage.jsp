
<%@page import="FunctionLayer.Brick"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Confirmation</title>
    </head>
     <%@include file="header.jsp" %>
    <body>
        <h1>Order confirmed</h1>
       ${order}
    </body>
</html>
