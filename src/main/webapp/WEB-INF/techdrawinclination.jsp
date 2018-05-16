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
    int displace = 800;
    int shedPos = (displace / 2) + 38 + 800 + 2750 + 1200;
    int shedLength = 2412;
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
             viewBox="0 0 <% out.print(length + (displace / 2)); %> <% out.print(width + displace); %> ">


        <!-- Small lines under edge plates -->
        <rect x="<% out.print(displace / 2); %>" y="0" width="75" height="625"
              style="stroke: black; fill: none; stroke-width: 10;"/>
        <rect x="<% out.print((displace / 2) + length); %>" y="0" width="75" height="625"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Top part of roof 
             x = displace + (roof plate width/2)
        -->
        <rect x="<% out.print((displace / 2) + 37.5); %>" y="50" width="<% out.print(length); %>" height="635"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Bottom part of roof -->
        <rect x="<% out.print((displace / 2) + 37.5); %>" y="685" width="<% out.print(length); %>" height="225"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Line that goes from start of edge plate 1 to end of edge plate 2 -->
        <rect x="<% out.print(displace / 2); %>" y="50" width="<% out.print(length + 75); %>" height="725"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Top of pole & shed-->
        <rect x="<% out.print((displace / 2) + 150); %>" y="910" width="<% out.print(length - 250); %>" height="150"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- First pole 
             x = displace + (roof plate width / 2) + 0.8 metres from start of "bottom part of roof"
        -->
        <rect x="<% out.print((displace / 2) + 37.5 + 800); %>" y="1060" width="<% out.print(97); %>" height="2005"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Second pole 
             x = -||- + 2.75 metres
        -->
        <rect x="<% out.print((displace / 2) + 37.5 + 800 + 2750); %>" y="1060" width="<% out.print(97); %>" height="2005"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!--
             Shed cladding
        -->
        <!-- far left shed cladding -->
        <rect x="<% out.print((displace / 2) + 20 + 800 + 2750 + 1200); %>" y="1060" width="<% out.print(20); %>" height="2005"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!-- far right shed cladding -->
        <rect x="<% out.print((displace / 2) + 37.5 + 800 + 2750 + 1200 + 2412.5); %>" y="910" width="<% out.print(20); %>" height="<% out.print(150 + 2005); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!-- Shed
             Starts at Second pole X + 1.2 metres
             width = X coordinates subtracted with each other
        --> 
        <rect x="<% out.print(shedPos); %>" y="1000" width="<% out.print(shedLength); %>" height="2065"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!-- Shed middle cladding -->
        <%
            int qty = 1;
            int cladderPos = shedPos;
            for (int i = cladderPos; i < cladderPos + shedLength - 50;)
            {

                if (qty % 2 == 0)
                {
        %>
        <rect x="<% out.print(i); %>" y="1000" width="<% out.print(50); %>" height="2065"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <%
            i += 50;
        } else
        {
        %>
        <rect x="<% out.print(i); %>" y="1000" width="<% out.print(75); %>" height="2065"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <%
                    i += 75;
                }
                qty++;
            }
        %>

        <!-- FOR loop to add all the rafters(?) in the roof
                
             for (int i = startOfRoof; i < endOfRoof ; i+= widthOfRafters)
        -->
        <%
            for (int i = (displace / 2) + 38; i < (displace / 2) + length + 38; i += 50)
            {
        %> 
        <rect x=" <% out.print(i); %>" y="150" width="50" height="<% out.print(535); %>"
              style="stroke: black; fill: white; stroke-width: 4;"/>
        <%

            }
        %>

        <!-- Roof edge plates ??-->
        <rect x="<% out.print(displace / 2); %>" y="0" width="75" height="600"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print((displace / 2) + length); %>" y="0" width="75" height="600"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <!-- Ground line -->

        <line x1="<% out.print((displace / 2) - 100); %>" y1="3065" x2="<% out.print(length + (displace / 2) + 100); %>" y2="3065"
              style="stroke:#000000; stroke-width:10" />



        <!-- Big arrow left
             
             x1 = distance between number dimensions and line
             y2 = pole height + Y-coordinate
        
        -->
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
    <line x1="50" y1="0" x2="50" y2="3065" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>

    <!-- Small arrow left 
         y1 = Top of pole & shed Y-coordinate
    -->

    <defs>
    <marker id="beginArrow" 
            markerWidth="9" markerHeight="9" 
            refX="50" refY="4" 
            orient="auto">
        <path d="M0,4 L8,0 L8,8 L0,4" style="fill: #000000s;" />
    </marker>
    <marker id="endArrow" 
            markerWidth="9" markerHeight="9" 
            refX="58" refY="4" 
            orient="auto">
        <path d="M0,0 L8,4 L0,8 L0,0" style="fill: #000000;" />
    </marker>
    </defs>

    <line x1="400" y1="910" x2="400" y2="3065" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>

    <!-- Small arrow bottom left -->
    <defs>
    <marker id="beginArrow" 
            markerWidth="9" markerHeight="9" 
            refX="50" refY="4" 
            orient="auto">
        <path d="M0,4 L8,0 L8,8 L0,4" style="fill: #000000s;" />
    </marker>
    <marker id="endArrow" 
            markerWidth="9" markerHeight="9" 
            refX="58" refY="4" 
            orient="auto">
        <path d="M0,0 L8,4 L0,8 L0,0" style="fill: #000000;" />
    </marker>
    </defs>
    <line x1="<% out.print(displace / 2); %>" y1="3365" x2="<% out.print((displace / 2) + 37.5 + 800); %>" y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>

    <!-- Arrow bottom, second from left -->
    <defs>
    <marker id="beginArrow" 
            markerWidth="9" markerHeight="9" 
            refX="50" refY="4" 
            orient="auto">
        <path d="M0,4 L8,0 L8,8 L0,4" style="fill: #000000s;" />
    </marker>
    <marker id="endArrow" 
            markerWidth="9" markerHeight="9" 
            refX="58" refY="4" 
            orient="auto">
        <path d="M0,0 L8,4 L0,8 L0,0" style="fill: #000000;" />
    </marker>
    </defs>
    <line x1="<% out.print(displace / 2); %>" y1="3365" x2="<% out.print((displace / 2) + 37.5 + 800 + 2750); %>" y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>

    <!-- Arrow bottom, from start of second pole, to start of shed-->
    <defs>
    <marker id="beginArrow" 
            markerWidth="9" markerHeight="9" 
            refX="50" refY="4" 
            orient="auto">
        <path d="M0,4 L8,0 L8,8 L0,4" style="fill: #000000s;" />
    </marker>
    <marker id="endArrow" 
            markerWidth="9" markerHeight="9" 
            refX="58" refY="4" 
            orient="auto">
        <path d="M0,0 L8,4 L0,8 L0,0" style="fill: #000000;" />
    </marker>
    </defs>
    <line x1="<% out.print(displace / 2); %>" y1="3365" x2="<% out.print(shedPos); %>" y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>

    <!-- Arrow bottom, from start of shed to end of shed-->
    <defs>
    <marker id="beginArrow" 
            markerWidth="9" markerHeight="9" 
            refX="50" refY="4" 
            orient="auto">
        <path d="M0,4 L8,0 L8,8 L0,4" style="fill: #000000s;" />
    </marker>
    <marker id="endArrow" 
            markerWidth="9" markerHeight="9" 
            refX="58" refY="4" 
            orient="auto">
        <path d="M0,0 L8,4 L0,8 L0,0" style="fill: #000000;" />
    </marker>
    </defs>
    <line x1="<% out.print(shedPos); %>" y1="3365" x2="<% out.print(shedPos + shedLength);%>" y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>

    <!-- Arrow bottom, LAST one-->
    <defs>
    <marker id="beginArrow" 
            markerWidth="9" markerHeight="9" 
            refX="50" refY="4" 
            orient="auto">
        <path d="M0,4 L8,0 L8,8 L0,4" style="fill: #000000s;" />
    </marker>

    </defs>
    <line x1="<% out.print((displace / 2) + length); %>" y1="3365" x2="<% out.print((displace / 2) + length + 300);%>" y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);"/>

    </svg>
</body>
</html>
