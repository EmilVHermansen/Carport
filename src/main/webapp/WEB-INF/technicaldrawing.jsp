<%-- 
    Document   : technicaldrawing
    Created on : May 2, 2018, 9:56:09 AM
    Author     : wtfak
--%>

<%@page import="FunctionLayer.Order"%>
<%@page import="FunctionLayer.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Customer user = (Customer) session.getAttribute("customer"); %>
<!DOCTYPE html>
<%
    int width = 7500;
    int length = 7800;
    int shedWidth = 2700;
    double shedLength = 3300;
    int rafterPos = 0;
    int farRightPoleX = length - 425;
    boolean shed = true;

    int qty = 1;
    qty += (length - 45) / 600;
    if ((length - 45) % 600 > 0)
    {
        qty++;
    }
    int holes = qty - 1;
    int rafterDistance = (length - 45) / holes;

    
    //Used to find the placement of the KRYDSBÅND if there is a shed.
    int RafterQty = 1;
    RafterQty += ((length - shedLength - 395) - 45) / 600;
    if (((length - shedLength - 395) - 45) % 600 > 0)
    {
        RafterQty++;
    }
    int holesWithoutShed = RafterQty - 2;

%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teknisk Tegning</title>
    </head>
    <body>
        <h1> Teknisk tegning af carport for <% out.print(user.getEmail());%></h1>

        <% //TODO delete this nephew
            out.print("Rafter distance: " + ((length - (45 * qty)) / holes) + " mm"); %> <br>



        <!-- SVG drawing of carport for the customer -->
        <SVG width="<% out.print(length / 10); %>" height="<% out.print(width / 10); %>" 
             viewBox="0 0 <% out.print(length); %> <% out.print(width); %> ">

        <!-- carport outline -->
        <rect x="0" y="0" width="<% out.print(length); %>" height="<% out.print(width); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <%
            if (shed)
            {

        %>
        <!--Løsholte skur-->
        <!-- lodrette -->
        <rect x="<% out.print(length - rafterDistance - shedLength + 85); %>" y="350" width="70" height="<% out.print(shedWidth); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(length - 395); %>" y="350" width="70" height="<% out.print(shedWidth); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!-- vandrette -->
        <rect x="<% out.print(length - rafterDistance - shedLength + 175); %>" y="327.5" width="<% out.print(shedLength); %>" height="70"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(length - rafterDistance - shedLength + 175); %>" y="<% out.print(shedWidth + 280);%>" width="<% out.print(shedLength); %>" height="70"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!--Stolpe skur-->
        <%
            //If the shed is 3m or wider than that, then an extra pole will be added in the middle
            if (shedWidth >= 3000)
            {
        %>

        <!-- 350 is the distance from the edge of the carport to the wallPlate, where the shed starts-->
        <rect x="<% out.print(length - rafterDistance - shedLength + 85); %>" y="<% out.print(shedWidth / 2 + 350); %>" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(length - 425); %>" y="<% out.print(shedWidth / 2 + 350); %>" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/> 
        <%
            }

        %>
        <rect x="<% out.print(length - rafterDistance - shedLength + 85); %>" y="<% out.print(shedWidth + 250); %>" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!-- Pole bottom right -->
        <rect x="<% out.print(length - 425); %>" y="<% out.print(shedWidth + 250); %>" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(length - rafterDistance - shedLength + 85); %>" y="<% out.print(width - 417.5); %>" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(length - rafterDistance - shedLength + 85); %>" y="327.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <% } %>







        <!--Stolper-->
        <!-- top left -->
        <%
            //3835 because the poles are not on the edge of the carport.
            //If the shed is smaller than 3835mm, then the 4 poles can be parallel
            if (length < 3835)
            {
        %>
        <rect x="317.5" y="327.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="317.5" y="<% out.print(width - 417.5); %>" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <% }
            //If there's still more space left 6m away from the far right pole
            //two extra poles will be added on the far right
            if (length > farRightPoleX - 6000)
            {
        %>
        <rect x="<% out.print(farRightPoleX - 6000); %>" y="327.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(farRightPoleX - 6000); %>" y="<% out.print(width - 417.5); %>" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <% } %>
        <%
            //If there's more than 3m between the far right poles and the left edge, a pole needs to be added 3 meters from it
        %>
        <rect x="<% out.print(farRightPoleX - 3000); %>" y="327.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(farRightPoleX - 3000); %>" y="<% out.print(width - 417.5); %>" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!-- top, far right pole -->
        <rect x="<% out.print(farRightPoleX); %>" y="327.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(farRightPoleX); %>" y="<% out.print(width - 417.5); %>" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!--Rem-->
        <rect x="0" y="<% out.print(width - 395);%>" width="<% out.print(length); %>" height="45"
              style="stroke: black; fill: white; stroke-width: 10; "/>
        <rect x="0" y="350" width="<% out.print(length); %>" height="45"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!--Spær-->
        <%
            for (int i = 0; i <= qty; i++)
            {
        %> 
        <rect x=" <% out.print(rafterPos); %>" y="0" width="45" height="<% out.print(width); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <%
                rafterPos += rafterDistance;
            }
        %>






        <!--De 550mm afstand mellem spær er ikke korrekt, så den resterende længde er tilføjet på afstanden mellem sidste og næstsidste spær-->

        <!--Krydsbånd-->


        <!-- if there's no shed present, the krydsbånd will range from the 
             start of the first rafter, to the start of the last rafter -->
        <% if (!shed)
            {
        %>
        <!-- TODO fix the X coordinates -->
        <!-- venstre top, til højre bund -->
        <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 75); %>" y1="395" x2="<% out.print(length - rafterDistance - 25); %>" y2="<% out.print(width - 395); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 50); %>" y1="415" x2="<% out.print(length - rafterDistance - 85); %>" y2="<% out.print(width - 395); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!-- højre top, til venstre bund -->
        <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 75); %>" y1="<% out.print(width - 395); %>" x2="<% out.print(length - rafterDistance - 25); %>" y2="395"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 50); %>" y1="<% out.print(width - 415); %>" x2="<% out.print(length - rafterDistance - 85); %>" y2="415"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <% }
        %>

        <% if (shed)
            {
        %>
        <!-- venstre top, til højre bund -->

        <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 75); %>" y1="395" x2="<% out.print(holesWithoutShed * rafterDistance); %>" y2="<% out.print(width - 395); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 50); %>" y1="415" x2="<% out.print(holesWithoutShed * rafterDistance - 50); %>" y2="<% out.print(width - 395); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!-- venstre bund, til højre top -->
        <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 75); %>" y1="<% out.print(width - 375); %>" x2="<% out.print(holesWithoutShed * rafterDistance); %>" y2="395"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 50); %>" y1="<% out.print(width - 395); %>" x2="<% out.print(holesWithoutShed * rafterDistance - 50); %>" y2="415"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <%
            }
        %>

        </SVG>

        <form name="login" action="FrontController" method="POST">
            <input type="hidden" name="command" value="login">
            <% //TODO change value to employee in session when loggin in %>
            <input type="hidden" name="empnumber" value="a01">
            <input type="hidden" name="password" value="admin">
            <input type="submit" name="submit" value="Tilbage til ordre">
        </form>

    </body>
</html>
