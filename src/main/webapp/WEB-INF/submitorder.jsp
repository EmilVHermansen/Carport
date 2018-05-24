<%-- 
    Document   : submitorder
    Created on : 26-Apr-2018, 10:23:55
    Author     : emilv
--%>

<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% Order order = (Order) request.getAttribute("order");%>
<% String shedDanish = "";
    if (order != null)
{
        if (order.getShed().equals("noShed"))
            shedDanish = "Uden skur";
        else
            shedDanish = "Med skur";
}%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <%@include file="header.jsp" %>

    <body>
        <div class="title-container">
            <div class="title-content">
        <h1>Bestil din carport her</h1>
            </div>
        </div>
          <div class="error-container">
            <div class="error-content">
                <% String error = (String) request.getAttribute("error");
            if (error != null) {%>
                <h5 style="color:red"><%= error%></h5>
                <br>
                <% }
                %>
            </div>
          </div>
        <div class="bot-container">
            <div class ="bot-content">
        <table>
            <tr>Vælg specifikationer på din carport
                <td>
                    <form name="submitorder" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="orderconfirmation">
                        Bredde:<br>
                        <select name="width">
                            <% if (order != null) 
                            { %>
                                <option value="<% out.print(order.getWidth()); %>" selected hidden><%out.print(order.getWidth()); %>cm</option>) 
                            <% } %>
                            <% for (int i = 240; i < 750; i += 30)
                                {
                            %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><%
                                } %>
                        </select>
                        <br>



                        Længde:<br>
                        <select name="length">
                            <% if (order != null) 
                            { %>
                                <option value="<% out.print(order.getLength()); %>" selected hidden><%out.print(order.getLength()); %>cm</option>) 
                            <% } %>
                            <% for (int j = 240; j < 780; j += 30)
                                {
                            %>  <option value=<% out.print(j); %>><% out.print(j);%>cm</option><%
                                } %>
                        </select>
                        <br>


                        Med rejsning eller fladt tag:<br>
                        <select name="inclination">
                            <% if (order != null) 
                            { %>
                                <option value="<% out.print(order.getInclination()); %>" selected hidden><%out.print(order.getInclination()); %></option>) 
                            <% } %>
                            <option value="Fladt tag">Fladt tag</option>
                            <option value="Med rejsning">Med rejsning</option>
                        </select>
                        <br>
                        Vinkel:<br>
                        <select name="angle">
                            <% if (order != null) 
                            { %>
                                <option value="<% out.print(order.getAngle()); %>" selected hidden><%out.print(order.getAngle()); %>°</option>) 
                            <% } %>
                            <option value="0">0°</option>
                            <option value="15">15°</option>
                            <option value="20">20°</option>
                            <option value="25">25°</option>
                            <option value="30">30°</option>
                            <option value="35">35°</option>
                            <option value="30">30°</option>
                            <option value="45">45°</option>
                        </select>
                        <br>
                        Tag materiale (hvis der er valgt med rejsning):<br>
                        <select name="roofMaterial">
                            <% if (order != null) 
                            { %>
                                <option value="<% out.print(order.getRoofMaterial()); %>" selected hidden><%out.print(order.getRoofMaterial()); %></option>) 
                            <% } %>
                            <option value="ingen">Ingen</option>
                            <option value="betontagsten">Betontagsten</option>
                            <option value="eternittag b6">Eternittag B6</option>
                        </select>
                        <br>
                        Med skur:<br>
                        <select name="shed">
                            <% if (order != null) 
                            { %>
                                <option value="<% out.print(order.getShed()); %>" selected hidden><%out.print(shedDanish); %></option>) 
                            <% } %>
                            <option value="noShed">Uden Skur</option>
                            <option value="shed">Med Skur</option>
                        </select>
                        <br>
                        Skur bredde:<br>
                        <select name="shedWidth">
                            <% if (order != null) 
                            { %>
                                <option value="<% out.print(order.getShedWidth()); %>" selected hidden><%out.print(order.getShedWidth()); %>cm</option>) 
                            <% } %>
                            <% for (int i = 0; i < 720; i += 30)
                                {
                                    if (i == 0 || i >= 210)
                            %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><%
                                } %>
                        </select>

                        <br>
                        Skur længde:<br>
                        <select name="shedLength">
                            <% if (order != null) 
                            { %>
                                <option value="<% out.print(order.getShedLength()); %>" selected hidden><%out.print(order.getShedLength()); %>cm</option>) 
                            <% } %>
                            <% for (int i = 0; i < 690; i += 30)
                                {
                                     if (i == 0 || i >= 150)
                            %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><%
                                }%>
                        </select>
                        <br>
                        Navn:<br>
                        <input type="text" name="name" <% if (order != null) {%> value="<%out.print(order.getName()); %>" <%} %> required>
                        <br>
                        Adresse:<br>
                        <input type="text" name="address" <% if (order != null) {%> value="<%out.print(order.getAddress()); %>" <%} %> required>
                        <br>
                        Postnr. og by:<br>
                        <input type="text" name="zipcode" <% if (order != null) {%> value="<%out.print(order.getZipCode()); %>" <%} %> required>
                        <br>
                        Telefon:<br>
                        <input type="text" name="phoneNumber" <% if (order != null) {%> value="<%out.print(order.getPhoneNumber()); %>" <%} %> required>
                        <br>
                        Email:<br>
                        <input type="text" name="email" <% if (order != null) {%> value="<%out.print(order.getEmail()); %>" <%} %> required>
                        <br>
                        Evt. bemærkninger:<br>
                        <input type="text" name="comment" <% if (order != null) {%> value="<%out.print(order.getComment()); %>" <%} %>>
                        <br>
                        <input type="submit" value="Bekræft">
                    </form>
                </td>
            </tr>
        </table>
            </div>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
