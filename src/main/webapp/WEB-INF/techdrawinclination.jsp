<%-- 
    Document   : techdrawinclination
    Created on : 14-05-2018, 11:36:09
    Author     : adams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    int width = 3050;
    int length = 7800;
    String widthToString = Integer.toString(width);
    int displace = 800;
    int shedLength = 2412; //2412
    int xTopOfPoles = (displace / 2) + 150;
    //Set the far right pole to the edge of the top of the poles & shed
    int farRightPole = (xTopOfPoles + length - 250) - 97;
    int shedPos = farRightPole - shedLength + 97;
    
    int poleQty = 1;
    poleQty += ((xTopOfPoles + length - 250) - 1200 - 97) / 3097;
    if (((xTopOfPoles + length - 250) - 97 - 1200) % 3097 > 0)
    {
        poleQty++;
    }
    int holes = poleQty - 1;
    int poleDistance = ((xTopOfPoles + length - 250) - 1200 - 97) / holes;

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carport with inclination</title>
    </head>
    <body>
        <% out.print(poleDistance); %>
        <h1>CaRpOrT!</h1>
        <SVG width="<% out.print(length / 6); %>" height="<% out.print(width / 6); %>" 
             viewBox="0 0 <% out.print(length + displace); %> <% out.print(width + displace); %> ">


        <!-- Top part of roof 
             x = displace + (roof plate width/2)
        -->
        <rect x="<% out.print((displace / 2) + 38); %>" y="50" width="<% out.print(length); %>" height="635"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Bottom part of roof -->
        <rect x="<% out.print((displace / 2) + 38); %>" y="685" width="<% out.print(length); %>" height="225"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Line that goes from start of edge plate 1 to end of edge plate 2 -->
        <rect x="<% out.print(displace / 2); %>" y="50" width="<% out.print(length + 75); %>" height="725"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Top of pole & shed-->
        <rect x="<% out.print(xTopOfPoles); %>" y="910" width="<% out.print(length - 250); %>" height="150"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- first pole -->
        <% for (int i = 0; i <= poleQty; i++)
            {
                if (farRightPole < xTopOfPoles)
                {
                    //farRightPole = xTopOfPoles + 800;
                }

        %>
        <rect x="<% out.print(farRightPole); %>" y="1060" width="<% out.print(97); %>" height="2005"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <% farRightPole -= poleDistance;
            }
            if (shedLength > 0)
            {
        %>
        <!--
             Shed cladding
        -->
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
        <!-- far left shed cladding -->
        <rect x="<% out.print(shedPos - 20); %>" y="1060" width="<% out.print(20); %>" height="2005"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!-- far right shed cladding -->
        <rect x="<% out.print(shedPos + shedLength); %>" y="910" width="<% out.print(20); %>" height="<% out.print(150 + 2005); %>"
              style="stroke: black; fill: white; stroke-width: 10;"/>
         <%   }
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

        <!-- Small lines under edge plates -->
        <rect x="<% out.print(displace / 2); %>" y="0" width="75" height="625"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <rect x="<% out.print((displace / 2) + length); %>" y="0" width="75" height="625"
              style="stroke: black; fill: white; stroke-width: 10;"/>
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

    <text x="30" y="<% out.print(width / 2); %>" fill="red" transform="rotate(-90, 30, 
          <% out.print(width / 2); %> )" style="font-size: 200;">
    <% out.print((widthToString.substring(0, 1)) + "," + widthToString.substring(1, 3)); %></text>
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
    <% String groundToRoof = Integer.toString(2155); %>
    <!-- Y = top of pole Y coordinate + height of it, and pole Y coordinate + height of it. -->
    <text x="400" y="<% out.print((910 + 150 + 1060 + 2005) / 2); %>" fill="red" transform="rotate(-90, 400, 
          <% out.print((910 + 150 + 1060 + 2005) / 2); %> )" style="font-size: 200;">
    <% out.print((groundToRoof.substring(0, 1)) + "," + groundToRoof.substring(1, 3)); %></text>

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
    <line x1="<% out.print(displace / 2); %>" y1="3365" x2="<% out.print((displace / 2) + 38 + 800); %>" y2="3365" 
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
    <line x1="<% out.print(displace / 2); %>" y1="3365" x2="<% out.print((displace / 2) + 38 + 800 + 2750); %>" y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    <%  //TODO Fix int having a - instead of 0 at the start
        int botLeftSpace = ((displace / 2) + 38) - ((displace / 2) + 38 + displace);
        String bottomLeftLine = Integer.toString(botLeftSpace); %>
    <text x="<% out.print((displace / 2) + 100); %>" y="3365" fill="red" style="font-size: 200;">
    <% out.print((bottomLeftLine.substring(0, 1)) + "," + bottomLeftLine.substring(1, 3)); %></text>
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
