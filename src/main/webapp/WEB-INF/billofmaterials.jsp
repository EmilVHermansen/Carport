<%-- 
    Document   : billofmaterials
    Created on : 02-05-2018, 15:07:52
    Author     : adams
--%>

<%@page import="FunctionLayer.LineItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<LineItem> lineItems = (List<LineItem>) session.getAttribute("lineitems"); %>
<%@include file="header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Stykliste</title>
    </head>
    <body>
        <div class="title-container">
            <div class="title-content">
                <h1>Stykliste</h1>
            </div>
        </div>
        <table class="table table-hover">
            <thead class="thead-dark">
            <th>Beskrivelse</th>
            <th>Længde</th>
            <th>Antal</th>
            <th>Enhed</th>
            <th>Brugsbeskrivelse</th>
        </thead>
        <tbody>
            <%for (LineItem lineItem : lineItems)
                { %>
            <tr>
                <td><% out.print(lineItem.getName()); %></td>
                <td><% if (lineItem.getLength() != 0)
                    {
                        out.print(lineItem.getLength());
                    } else
                    {
                        out.print("");
                    } %></td>
                <td><% out.print(lineItem.getQty()); %></td>
                <td><% out.print(lineItem.getUnit()); %></td>
                <td><% out.print(lineItem.getDescriptionUse()); %></td>
            </tr>
            <%};%>
        </tbody>
    </table>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <div class="bill-but-container">
        <div class="div-but-content">
            <form name="login" action="FrontController" method="POST">
                <input type="hidden" name="command" value="returntoorders">
                <% //TODO change value to employee in session when loggin in %>
                <input type="submit" name="submit" value="Tilbage til ordre">
            </form>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
