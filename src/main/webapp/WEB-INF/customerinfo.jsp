<%-- 
    Document   : customerinfo
    Created on : 25-04-2018, 10:33:44
    Author     : adams
--%>

<%@page import="FunctionLayer.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Customer customer = (Customer) session.getAttribute("customer"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer info</title>
    </head>
    <body>
        <h1>Order customer info</h1>
        <% String error = (String) request.getAttribute("error");
                   if (error != null)
                   {%>
        <H2>Error!!</h2>
        <p><%= error%>
            <% }
            %>
            <% if (error == null)
            { %>
        <table>
            <tr>
                <th>
                    Navn
                </th>
                <th>
                    Adresse
                </th>
                <th>
                    Postnummer & By
                </th>
                <th>
                    Telefon
                </th>
                <th>
                    Email
                </th>
            </tr>
            <tr> 
                <td><% out.print(customer.getName()); %></td>
                <td><% out.print(customer.getAddress()); %></td>
                <td><% out.print(customer.getZipCity()); %></td>
                <td><% out.print(customer.getPhoneNo()); %></td>
                <td><% out.print(customer.getEmail());%></td>
            </tr>
        </table>
            <% } %>
        <form name="login" action="FrontController" method="POST">
        <p>Enter order id or customer email.</p>
        <input type="hidden" name="command" value="login">
        //TODO change value to employee in session when loggin in
        <input type="hidden" name="empnumber" value="a01">
        <input type="hidden" name="password" value="admin">
        <input type="submit" name="submit" value="submit">
        </form>
    </body>
</html>
