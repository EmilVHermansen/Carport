
<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User user = (User) session.getAttribute("user");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order your house</title>
    </head>
    <body>
        <h1>Hi, <% out.println(user.getEmail());%> </h1>
        <p>What dimensions do you want for your legohouse?</p>
        <form name="orderpage" action="FrontController" method="POST">
            <input type="hidden" name="command" value="orderpage">
            <p> Enter width and height. Measurement is in dots</p>
            <input type="number" min="8" max="5000" name="length" placeholder="Insert length">
            <input type="number" min="8" max="5000" name="width" placeholder="Insert width">
            <input type="number" min="6" max="2500" name="height" placeholder="Insert height">
            <br><input type="submit" value="Continue">
        </form>
        <p> Or if you would like to see your previous orders, <a href="FrontController?command=orderhistory">press here</a></p>

    </body>
</html>
