<%-- 
    Document   : techdrawinclination
    Created on : 14-05-2018, 11:36:09
    Author     : adams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    int width = 3005;
    int length = 7300;
    int displace = 400;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carport with inclination</title>
    </head>
    <body>
        <h1>CaRpOrT!</h1>
        <SVG width="<% out.print(length / 10); %>" height="<% out.print(width / 10); %>" 
             viewBox="0 0 <% out.print(length + displace); %> <% out.print(width + displace); %> ">

        <!-- carport top  -->
        <rect x="<% out.print(displace + 75); %>" y="0" width="<% out.print(length - 75); %>" height="150"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(displace + 75); %>" y="500" width="<% out.print(length - 75); %>" height="150"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        
        <!-- Roof edge plates ??-->
        <rect x="<% out.print(displace); %>" y="0" width="75" height="600"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print(displace + length); %>" y="0" width="75" height="600"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!-- vertical arrow -->
        <defs>
    <marker id="beginArrow" 
            markerWidth="9" markerHeight="9" 
            refX="0" refY="4" 
            orient="auto">
        <path d="M0,4 L8,0 L8,8 L0,4" style="fill: #000000s;" />
    </marker>
    <marker id="endArrow" 
            markerWidth="9" markerHeight="9" 
            refX="8" refY="4" 
            orient="auto">
        <path d="M0,0 L8,4 L0,8 L0,0" style="fill: #000000;" />
    </marker>
    </defs>
    <line x1="<% out.print(displace - 50); %>" y1="<% out.print(displace / 2); %>" x2="<% out.print(displace - 50); %>" y2="<% out.print(width + (displace / 2));%>" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    </svg>
</body>
</html>
