<%-- 
    Document   : customerinfo
    Created on : 25-04-2018, 10:33:44
    Author     : adams
--%>

<%@page import="FunctionLayer.User"%>
<%@page import="FunctionLayer.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% Customer customer = (Customer) session.getAttribute("customer"); %>
<%@include file="header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kunde information</title>
    </head>
    <body>
        <h1>Order customer info</h1>
        <% String error = (String) request.getAttribute("error");
            if (error != null) {%>
        <H2>Error!!</h2>
        <p><%= error%>
            <% }
            %>
            <% if (error == null) { %>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th>Navn</th>
                    <th>Adresse</th>
                    <th>Postnummer & By</th>
                    <th>Telefon</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tr> 
                <td><% out.print(customer.getName()); %></td>
                <td><% out.print(customer.getAddress()); %></td>
                <td><% out.print(customer.getZipCity()); %></td>
                <td><% out.print(customer.getPhoneNo()); %></td>
                <td><% out.print(customer.getEmail());%></td>
            </tr>
        </table>
        <% }%>
        <!-- button to access the bill of materials -->
        <form name="billofmaterials" action="FrontController" method="POST">
            <input type="hidden" name="command" value="billofmaterials">
            <input type="submit" name="submit" value="Stykliste">
        </form>

        <!-- button to access the technical drawing -->
        <form name="tegning" action="FrontController" method="POST">
            <input type="hidden" name="command" value="tegning">
            <input type="submit" name="tegning" value="Tegning">
        </form>

        <!-- button to go back to order history-->
        <form name="login" action="FrontController" method="POST">
            <input type="hidden" name="command" value="login">
            <% //TODO change value to employee in session when loggin in %>
            <input type="hidden" name="empnumber" value="a01">
            <input type="hidden" name="password" value="admin">
            <input type="submit" name="submit" value="Tilbage til ordrer">
        </form>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
