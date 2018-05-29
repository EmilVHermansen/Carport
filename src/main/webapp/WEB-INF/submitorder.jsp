<%-- 
    Document   : submitorder
    Created on : 26-Apr-2018, 10:23:55
    Author     : emilv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% int width = 240;
   if (request.getParameter("width") != null) 
        width = Integer.parseInt(request.getParameter("width")); %>
<% int length = 240;
    if (request.getParameter("length") != null) 
        length = Integer.parseInt(request.getParameter("length")); %>
<% String inclination = "Fladt tag";
    if (request.getParameter("inclination") != null) 
        inclination = request.getParameter("inclination"); %>
<% int angle = 0;
    if (request.getParameter("angle") != null) 
        angle = Integer.parseInt(request.getParameter("angle")); %>
<% String roofMaterial = "Ingen";
    if (request.getParameter("roofMaterial") != null) 
        roofMaterial = request.getParameter("roofMaterial"); %>
<% String shed = "noShed";
    if (request.getParameter("shed") != null) 
        shed = request.getParameter("shed"); %>
<% String shedDanish = "";
    if (shed.equals("noShed"))
        shedDanish = "Uden skur";
    else
        shedDanish = "Med skur";%>
<% int shedWidth = 0;
    if (request.getParameter("shedWidth") != null) 
        shedWidth = Integer.parseInt(request.getParameter("shedWidth")); %>
<% int shedLength = 0;
    if (request.getParameter("shedLength") != null) 
        shedLength = Integer.parseInt(request.getParameter("shedLength")); %>
<% String name = "";
    if (request.getParameter("name") != null) 
        name = request.getParameter("name"); %>
<% String address = "";
    if (request.getParameter("address") != null) 
        address = request.getParameter("address"); %>
<% String zipcode = "";
    if (request.getParameter("zipcode") != null) 
        zipcode = request.getParameter("zipcode"); %>
<% String phoneNumber = "";
    if (request.getParameter("phoneNumber") != null) 
        phoneNumber = request.getParameter("phoneNumber"); %>
<% String email = "";
    if (request.getParameter("email") != null) 
        email = request.getParameter("email"); %>
<% String comment = "";
    if (request.getParameter("comment") != null) 
            comment = request.getParameter("comment"); %>
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
                        <input type="hidden" name="command" value="submitorder">
                        Bredde:<br>
                        <select name="width">
                            <option value="<%out.print(width); %>" selected hidden><%out.print(width); %>cm</option>
                            <% for (int i = 240; i < 750; i += 30)
                                {
                            %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><%
                                } %>
                        </select>
                        <br>



                        Længde:<br>
                        <select name="length">
                            <option value="<%out.print(length); %>" selected hidden><%out.print(length); %>cm</option>
                            <% for (int j = 240; j < 780; j += 30)
                                {
                            %>  <option value=<% out.print(j); %>><% out.print(j);%>cm</option><%
                                } %>
                        </select>
                        <br>


                        Med rejsning eller fladt tag:<br>
                        <select name="inclination">
                            <option value="<%out.print(inclination); %>" selected hidden><%out.print(inclination); %></option>
                            <option value="Fladt tag">Fladt tag</option>
                            <option value="Med rejsning">Med rejsning</option>
                        </select>
                        <br>
                        Vinkel:<br>
                        <select name="angle">
                            <option value="<%out.print(angle); %>" selected hidden><%out.print(angle); %>°</option>
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
                            <option value="<%out.print(roofMaterial); %>" selected hidden><%out.print(roofMaterial); %></option>
                            <option value="ingen">Ingen</option>
                            <option value="betontagsten">Betontagsten</option>
                            <option value="eternittag b6">Eternittag B6</option>
                        </select>
                        <br>
                        Med skur:<br>
                        <select name="shed">
                            <option value="<%out.print(shed); %>" selected hidden><%out.print(shedDanish); %></option>
                            <option value="noShed">Uden Skur</option>
                            <option value="shed">Med Skur</option>
                        </select>
                        <br>
                        Skur bredde:<br>
                        <select name="shedWidth">
                            <option value="<%out.print(shedWidth); %>" selected hidden><%out.print(shedWidth); %>cm</option>
                            <% for (int i = 0; i < 720; i += 30)
                                {
                                    if (i == 0 || i >= 210)
                            %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><%
                                } %>
                        </select>

                        <br>
                        Skur længde:<br>
                        <select name="shedLength">
                            <option value="<%out.print(shedLength); %>" selected hidden><%out.print(shedLength); %>cm</option>
                            <% for (int i = 0; i < 690; i += 30)
                                {
                                     if (i == 0 || i >= 150)
                            %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><%
                                }%>
                        </select>
                        <br>
                        Navn:<br>
                        <input type="text" name="name" value="<%out.print(name); %>" required>
                        <br>
                        Adresse:<br>
                        <input type="text" name="address" value="<%out.print(address); %>" required>
                        <br>
                        Postnr. og by:<br>
                        <input type="text" name="zipcode" value="<%out.print(zipcode); %>" required>
                        <br>
                        Telefon:<br>
                        <input type="text" name="phoneNumber" value="<%out.print(phoneNumber); %>" required>
                        <br>
                        Email:<br>
                        <input type="text" name="email" value="<%out.print(email); %>" required>
                        <br>
                        Evt. bemærkninger:<br>
                        <input type="text" name="comment" value="<%out.print(comment); %>">
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
