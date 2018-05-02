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

    int width = 6000;
    int length = 7800;
    int shedLength = 5155;
    double shedWidth = 1831.5;
    int rafterPos = 0;
    int rafterDistance = 550;

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teknisk Tegning</title>
    </head>
    <body>
        <h1> Teknisk tegning af carport for <% out.print(user.getEmail());%></h1>


        <!-- SVG drawing of carport for the customer -->
        <SVG width="<% out.print(length / 10); %>" height="<% out.print(width / 10); %>" 
             viewBox="0 0 <% out.print(length); %> <% out.print(width); %> ">

        <rect x="0" y="0" width="<% out.print(length); %>" height="<% out.print(width); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!--Stolper-->
        <rect x="1077.5" y="327.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="4077.5" y="327.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <rect x="1077.5" y="5582.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="4077.5" y="5582.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!--Rem-->
        <rect x="0" y="<% out.print(width - 395);%>" width="<% out.print(length); %>" height="45"
              style="stroke: black; fill: white; stroke-width: 10; "/>
        <rect x="0" y="350" width="<% out.print(length); %>" height="45"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!--Løsholte skur-->
        <!-- lodrette -->
        <rect x="<% out.print(length - rafterDistance - shedWidth + 28); %>" y="427.5" width="70" height="<% out.print(shedLength); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(length - 395); %>" y="427.5" width="70" height="<% out.print(shedLength); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!-- vandrette -->
        <rect x="5545" y="327.5" width="<% out.print(shedWidth - 200); %>" height="70"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="5545" y="5612.5" width="<% out.print(shedWidth - 200); %>" height="70"
              style="stroke: black; fill: white; stroke-width: 10;"/>



        <!--Spær-->
        <% for (int i = 0; i < length; i += rafterDistance)
            {
                //Todo fix this hardcoded mess

                if (rafterPos >= 7700)
                {
                    rafterPos = 7755;
                }
        %> <rect x=" <% out.print(rafterPos); %>" y="0" width="45" height="<% out.print(width); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <%
                rafterPos += rafterDistance;
            }
        %>



        <!--De 550mm afstand mellem spær er ikke korrekt, så den resterende længde er tilføjet på afstanden mellem sidste og næstsidste spær-->

        <!--Krydsbånd-->
        <line stroke-dasharray="50, 50"              x1="572.5" y1="395" x2="5495" y2="5562.5"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <line stroke-dasharray="50, 50"              x1="552.5" y1="415" x2="5475" y2="5582.5"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <line stroke-dasharray="50, 50"              x1="552.5" y1="5562.5" x2="5475" y2="445"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <line stroke-dasharray="50, 50"              x1="572.5" y1="5582.5" x2="5495" y2="465"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!--Stolpe skur-->
        <rect x="5445" y="327.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="7377.5" y="327.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <rect x="5445" y="2950" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="7377.5" y="2950" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/> 

        <rect x="5445" y="5582.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="7377.5" y="5582.5" width="100" height="100"
              style="stroke: black; fill: white; stroke-width: 10;"/>
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
