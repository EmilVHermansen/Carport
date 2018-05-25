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
        <div class="title-container">
            <div class="title-content">
                <h1>Order customer info</h1>
            </div>
        </div>
        <% String error = (String) request.getAttribute("error");
            if (error != null)
            {%>
        <H2>Error</h2>
        <p><%= error%>
            <% }
            %>
            <% if (error == null)
                { %>
        <div class="customer-container">
            <div class="customer-content">

                <span id="title">Navn:</span> <span id="customer-info"><% out.print(customer.getName()); %> </span> <br>
                <span id="title">Adresse: </span><span id="customer-info"><% out.print(customer.getAddress()); %> </span><br>
                <span id="title">Postnummer & By :</span><span id="customer-info"> <% out.print(customer.getZipCity()); %> </span><br>
                <span id="title">Telefon: </span><span id="customer-info"><% out.print(customer.getPhoneNo()); %> </span><br>
                <span id="title">Email: </span><span id="customer-info"><% out.print(customer.getEmail());%> </span>



            </div>
        </div>
        <div class="buttons-container">
            <div class="button1">
                <!-- button to access the bill of materials -->
                <form name="billofmaterials" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="billofmaterials">
                    <input type="submit" name="submit" value="Stykliste">
                </form>
            </div>
            <div class="button2">
                <!-- button to access the technical drawing -->
                <form name="tegning" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="tegning">
                    <input type="submit" name="tegning" value="Tegning">
                </form>
            </div>
            <div class="button3">

                <!-- button to go back to order history-->
                <form name="login" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="login">
                    <% //TODO change value to employee in session when loggin in %>
                    <input type="hidden" name="empnumber" value="a01">
                    <input type="hidden" name="password" value="admin">
                    <input type="submit" name="submit" value="Tilbage til ordrer">
                </form>

            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <%@include file="footer.jsp" %>
    </body>
</html>
