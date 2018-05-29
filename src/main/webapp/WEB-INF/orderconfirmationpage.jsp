
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordre bekræftelse</title>
    </head>
     <%@include file="header.jsp" %>
    <body>
        <h1>Ordre bekræftet</h1>
       ${order}
       <br>
       <h5>Du vil blive ringet op inden for 5 arbejdsdage omkring detaljerne af ordren</h5>
           <%@include file="footer.jsp" %>
    </body>
</html>
