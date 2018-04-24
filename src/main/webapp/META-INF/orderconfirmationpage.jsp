
<%@page import="FunctionLayer.Brick"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ArrayList<Brick> bricks = (ArrayList<Brick>) session.getAttribute("bricks"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your list of bricks</h1>
        <p><%for (Brick brick : bricks) {
                out.println(brick);%>
            <br>
            <%};%></p>
    </body>
</html>
