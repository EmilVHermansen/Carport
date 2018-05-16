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
    
    //Used to find the amount of rafters needed
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


        <!-- Small lines under edge plates -->
        <rect x="<% out.print(displace); %>" y="0" width="75" height="625"
              style="stroke: black; fill: none; stroke-width: 10;"/>
        <rect x="<% out.print(displace + length); %>" y="0" width="75" height="625"
              style="stroke: black; fill: none; stroke-width: 10;"/>
        
        <!-- Top part of roof 
             x = displace + (roof plate width/2)
        -->
        <rect x="<% out.print(displace + 37.5); %>" y="50" width="<% out.print(length); %>" height="635"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Bottom part of roof -->
        <rect x="<% out.print(displace + 37.5); %>" y="685" width="<% out.print(length); %>" height="225"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Line that goes from start of edge plate 1 to end of edge plate 2 -->
        <rect x="<% out.print(displace); %>" y="50" width="<% out.print(length + 75); %>" height="725"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Top of pole & shed-->
        <rect x="<% out.print(displace + 150); %>" y="910" width="<% out.print(length - 250); %>" height="150"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- First pole 
             x = displace + (roof plate width / 2) + 0.8 metres from start of "bottom part of roof"
        -->
        <rect x="<% out.print(displace + 37.5 + 800); %>" y="1060" width="<% out.print(97); %>" height="1995"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Second pole 
             x = -||- + 2.75 metres
        -->
        <rect x="<% out.print(displace + 37.5 + 800 + 2750); %>" y="1060" width="<% out.print(97); %>" height="1995"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Shed
             Starts at Second pole X + 1.2 metres
             width = X coordinates subtracted with each other
        -->
        <rect x="<% out.print(displace + 37.5 + 800 + 2750 + 1200); %>" y="1000" width="<% out.print(2412.5); %>" height="2065"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        
                <%
            for (int i = displace + 38; i < displace + length + 38 ; i+= 50)
            {
        %> 
        <rect x=" <% out.print(i); %>" y="150" width="50" height="<% out.print(535); %>"
              style="stroke: black; fill: white; stroke-width: 4;"/>
        <%
             
            }
        %>

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
