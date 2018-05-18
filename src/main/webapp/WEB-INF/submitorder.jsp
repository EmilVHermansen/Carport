<%-- 
    Document   : submitorder
    Created on : 26-Apr-2018, 10:23:55
    Author     : emilv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <%@include file="header.jsp" %>

    <body>
        <h1>Bestil din carport her</h1>

        <table>
            <tr>Vælg specifikationer på din carport
                <td>
                    <form name="submitorder" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="orderconfirmation">
                        <%--TODO custom exceptions--%>
                        Bredde i cm:<br>
                        <select name="width">
                            <% for (int i = 240; i < 750; i += 30)
                                {
                            %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><% 
                            } %>
                        </select>
                        <br>



                        Længde i cm:<br>
                        <select name="length">
                            <% for (int j = 240; j < 780; j += 30)
                                {
                            %>  <option value=<% out.print(j); %>><% out.print(j);%>cm</option><% 
                            } %>
                        </select>
                        <br>


                        Med rejsning eller fladt tag:<br>
                        <select name="inclination">
                            <option value="Med rejsning">Med rejsning</option>
                            <option value="Fladt tag">Flat tag</option>
                        </select>
                        <br>
                        Vinkel:<br>
                        <select name="angle">
                            <option value="0">0</option>
                            <option value="15">15</option>
                            <option value="20">20</option>
                            <option value="25">25</option>
                            <option value="30">30</option>
                            <option value="35">35</option>
                            <option value="30">30</option>
                            <option value="45">45</option>
                        </select>
                        <br>
                        Tag materiale (hvis der er valgt med rejsning):<br>
                        <select name="roofMaterial">
                            <option value="ingen">Ingen</option>
                            <option value="betontagsten">Betontagsten</option>
                            <option value="eternittag b6">Eternittag B6</option>
                        </select>
                        <br>
                        Med skur:<br>
                        <select name="shed">
                            <option value="shed">Med Skur</option>
                            <option value="noShed">Uden Skur</option>
                        </select>
                        <%--TODO custom exceptions--%>
                        <br>
                        Skur bredde i cm:<br>
                        <select name="shedWidth">
                            <% for (int i = 0; i < 720; i += 30)
                                {
                            %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><% 
                            } %>
                        </select>

                        <br>
                        Skur længde i cm:<br>
                        <select name="shedLength">
                            <% for (int i = 0; i < 690; i += 30)
                                {
                            %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><% 
                            } %>
                        </select>
                        <br>
                        Navn:<br>
                        <input type="text" name="name" required>
                        <br>
                        Adresse:<br>
                        <input type="text" name="address" required>
                        <br>
                        Postnr. og by:<br>
                        <input type="text" name="zipcode" required>
                        <br>
                        Telefon:<br>
                        <input type="text" name="phoneNumber" required>
                        <br>
                        Email:<br>
                        <input type="text" name="email" required>
                        <br>
                        Evt. bemærkninger:<br>
                        <input type="text" name="comment">
                        <br>
                        <input type="submit" value="Submit">
                    </form>
                </td>
            </tr>
        </table>

    </body>
</html>
