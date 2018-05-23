<%-- 
    Document   : techdrawinclination
    Created on : 14-05-2018, 11:36:09
    Author     : adams
--%>

<%@page import="FunctionLayer.Order"%>
<%@page import="FunctionLayer.TechDrawing"%>
<%@page import="PresentationLayer.TechnicalDrawing"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<!DOCTYPE html>
<%
    Order order = (Order) session.getAttribute("order");
    //We call the method mmToMeter from the TechDrawing class, to show the measurements above the lines.
    TechDrawing td = new TechDrawing();
    int width = 3050;
    int length = order.getLength() * 10;

    //Displace is used to expand the SVG canvas, and place the drawing of the carport in the middle of the canvas (done by dividing by 2)
    // to make room for arrows and the dimensions
    int displace = 800;
    int shedLength = order.getShedLength() * 10; //2412
    int xTopOfPoles = (displace / 2) + 150;

    int topOfPoleLength = xTopOfPoles + length - 250;
    //Set the far right pole to the edge of the top of the poles & shed
    int farRightPole = topOfPoleLength - 97;
    int shedPos = farRightPole - shedLength + 97;
    // Calculate how many poles are needed, and the distance inbetween
    // 1200 is the distance from the left side of the Wallplate to the first pole
    int poleQty = 1;
    int holes;
    int poleDistance;
    // 3097 is the max distance between the poles
    // 97 is the width of the poles
    // 1200 is the max distance between the left side of the wallplate to the first pole
    if (shedLength == 0)
    {
        poleQty += ((topOfPoleLength) - 1200 - 97) / 3097;
        if (((topOfPoleLength) - 97 - 1200) % 3097 > 0)
        {
            poleQty++;
        }
        holes = poleQty - 1;
        poleDistance = ((topOfPoleLength) - 1200 - 97) / holes;
    } else
    {
        poleQty += ((topOfPoleLength) - 1200 - 97 - shedLength) / 3097;
        if (((topOfPoleLength) - 97 - 1200 - shedLength) % 3097 > 0)
        {
            poleQty++;
        }
        holes = poleQty - 1;
        poleDistance = ((topOfPoleLength) - 1200 - 97 - shedLength) / holes;
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carport with inclination</title>
    </head>
    <body>
        <h1>Carport with Inclination!</h1>
        <h3><% out.print("Length: " + td.mmToMeter(length) + "m"); %></h3>
        <h3><% out.print("Height:  " + td.mmToMeter(width) + "m"); %></h3>
        <SVG width="<% out.print(length / 6); %>" height="<% out.print(width / 6); %>" 
             viewBox="0 0 <% out.print(length + displace); %> <% out.print(width + displace); %> ">


        <!-- Top part of roof 
             x = displace + (roof plate width/2)
             38 is half the width of the edge plates on the roof
        -->
        <rect x="<% out.print((displace / 2) + 38); %>" y="50" width="<% out.print(length); %>" height="670"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Bottom part of roof -->
        <rect x="<% out.print((displace / 2) + 38); %>" y="685" width="<% out.print(length); %>" height="225"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Line that goes from start of edge plate 1 to end of edge plate 2 -->
        <rect x="<% out.print(displace / 2); %>" y="50" width="<% out.print(length + 75); %>" height="725"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- Wall plate / Top of pole & shed-->
        <rect x="<% out.print(xTopOfPoles); %>" y="910" width="<% out.print(length - 250); %>" height="100"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <!-- poles  -->

        <%
            int poleX = shedPos;
            for (int i = 0; i <= poleQty; i++)
            {
                if (shedLength == 0)
                {
        %>
        <rect x="<% out.print(farRightPole); %>" y="1010" width="<% out.print(97); %>" height="2005"
              style="stroke: black; fill: none; stroke-width: 10;"/>

        <% farRightPole -= poleDistance;
        } else
        {
        %>
        <rect x="<% out.print(poleX); %>" y="1010" width="<% out.print(97); %>" height="2005"
              style="stroke: black; fill: none; stroke-width: 10;"/>
        <%
                    poleX -= poleDistance;
                }
            } %>

        <% if (shedLength > 0)
            {
        %>
        <!--
             Shed cladding
        -->
        <!-- Shed
             Starts at Second pole X + 1.2 metres
             width = X coordinates subtracted with each other
        --> 
        <rect x="<% out.print(shedPos); %>" y="975" width="<% out.print(shedLength); %>" height="2040"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!-- Shed middle cladding -->
        <%
            int qty = 1;
            int cladderPos = shedPos;
            //making the cladding on the shed with two different widths (50 & 75) to make them shifted
            for (int i = cladderPos; i < cladderPos + shedLength - 50;)
            {
                if (qty % 2 == 0)
                {
        %>
        <rect x="<% out.print(i); %>" y="975" width="<% out.print(50); %>" height="2040"
              style="stroke: black; fill: white; stroke-width: 10;"/>

        <%
            i += 50;
        } else
        {
        %>
        <rect x="<% out.print(i); %>" y="975" width="<% out.print(75); %>" height="2040"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <%
                    i += 75;
                }
                qty++;
            }
        %>


        <!-- far left shed cladding -->
        <rect x="<% out.print(shedPos - 20); %>" y="1010" width="20" height="2005"
              style="stroke: black; fill: white; stroke-width: 10;"/>
        <!-- far right shed cladding -->
        <rect x="<% out.print(shedPos + shedLength); %>" y="910" width="20" height="<% out.print(100 + 2005); %>"
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
        <rect x=" <% out.print(i); %>" y="150" width="50" height="<% out.print(570); %>"
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
        <line x1="<% out.print((displace / 2) - 100); %>" y1="3015" x2="<% out.print(length + (displace / 2) + 100); %>" y2="3015"
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
    <line x1="150" y1="0" x2="150" y2="3065" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    <%  // Top of roof height + bottom part of roof height + wall plate height + pole height
        int groundToTop = 670 + 225 + 150 + 2005;
    %>
    <text x="150" y="<% out.print(width / 2); %>" fill="red" transform="rotate(-90, 100, 
          <% out.print(width / 2); %> )" style="font-size: 150;">
    <% out.print(td.mmToMeter(groundToTop)); %></text>

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
    <% int groundToRoof = 2005 + 100; %>
    <!-- Y = top of pole Y coordinate + height of it, and pole Y coordinate + height of it. -->
    <text x="400" y="<% out.print((910 + 150 + 1060 + 2005) / 2); %>" fill="red" transform="rotate(-90, 400, 
          <% out.print((910 + 150 + 1060 + 2005) / 2); %> )" style="font-size: 150;">
    <% out.print(td.mmToMeter(groundToRoof)); %></text>



    <!-- Small arrow bottom left -->
    <%
        if (shedLength == 0)
        {
            //TODO fix this :)) Setting FarRightPole to it's original value, so we won't have to create a new variable
            farRightPole = topOfPoleLength - 97;
            for (int i = 1; i <= poleQty; i++)
            {
    %> 
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
    <%
        if (i == 1)
        {
    %>
    <line x1="<% out.print(farRightPole - poleDistance); %>"  y1="3365" x2="<% out.print(farRightPole); %>"   y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    <text x="<% out.print((farRightPole + (farRightPole - poleDistance)) / 2); %>" y="3365" fill="red" style="font-size: 150;">
    <% out.print(td.mmToMeter((farRightPole - poleDistance) - farRightPole)); %></text>
    <%
    } else if (i == poleQty)
    {
    %>
    <line x1="<% out.print(farRightPole - (poleDistance * (i - 1))); %>"  y1="3365" x2="<% out.print(displace / 2); %>"   y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    <text x="<% out.print((displace / 2) + 125); %>" y="3365" fill="red" style="font-size: 150;">
    <% out.print(td.mmToMeter((farRightPole - (poleDistance * (i - 1))) - (displace / 2))); %></text>
    <%
    } else
    {
    %>
    <line x1="<% out.print(farRightPole - (poleDistance * (i - 1))); %>"  y1="3365" x2="<% out.print(farRightPole - (poleDistance * i)); %>"   y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    <text x="<% out.print(((farRightPole - (poleDistance * (i - 1))) + (farRightPole - (poleDistance * i))) / 2); %>" y="3365" fill="red" style="font-size: 150;">
    <% out.print(td.mmToMeter((farRightPole - (poleDistance * i)) - (farRightPole - (poleDistance * (i - 1))))); %></text>
    <%
            }
        }
    } else //If carport has a shed
    {
        for (int i = 1; i <= poleQty; i++)
        {
    %>
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

    <%
        if (i == 1)
        {
    %>
    <line x1="<% out.print(shedPos - poleDistance); %>"  y1="3365" x2="<% out.print(shedPos); %>"   y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    <text x="<% out.print((shedPos + (shedPos - poleDistance)) / 2); %>" y="3365" fill="red" style="font-size: 150;">
    <% out.print(td.mmToMeter((shedPos - poleDistance) - shedPos)); %></text>


    <%        } else if (i == poleQty)
    {
    %>
    <line x1="<% out.print(shedPos - (poleDistance * (i - 1))); %>"  y1="3365" x2="<% out.print(displace / 2); %>"   y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    <text x="<% out.print((displace / 2) + 125); %>" y="3365" fill="red" style="font-size: 150;">
    <% out.print(td.mmToMeter((shedPos - (poleDistance * (i - 1))) - (displace / 2))); %></text>
    <%
    } else
    {
    %>
    <line x1="<% out.print(shedPos - (poleDistance * (i - 1))); %>"  y1="3365" x2="<% out.print(shedPos - (poleDistance * i)); %>"   y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    <text x="<% out.print(((shedPos - (poleDistance * (i - 1))) + (shedPos - (poleDistance * i))) / 2); %>" y="3365" fill="red" style="font-size: 150;">
    <% out.print(td.mmToMeter((shedPos - (poleDistance * i)) - (shedPos - (poleDistance * (i - 1))))); %></text>
    <%
            }
        }
    %>
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
    <!-- shed line -->
    <line x1="<% out.print(shedPos); %>"  y1="3365" x2="<% out.print(shedPos + shedLength); %>"   y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);
          marker-end: url(#endArrow);"/>
    <text x="<% out.print((shedPos + (shedLength / 2))); %>" y="3365" fill="red" style="font-size: 150;"><%out.print(td.mmToMeter(shedLength));%></text>
    <%
        }
    %> 



    <!-- Arrow bottom, LAST one-->
    <defs>
    <marker id="beginArrow" 
            markerWidth="9" markerHeight="9" 
            refX="50" refY="4" 
            orient="auto">
        <path d="M0,4 L8,0 L8,8 L0,4" style="fill: #000000s;" />
    </marker>

    </defs>
    <line x1="<% out.print(length + (displace / 2) + 38); %>" y1="3365" x2="<% out.print((displace / 2) + length + 500);%>" y2="3365" 
          style="stroke:#006600;
          stroke-width: 10;
          marker-start: url(#beginArrow);"/>
    <text x="<% out.print(((((displace / 2) + 75) + length) + ((displace / 2) + length + 500)) / 2 - 125); %>" y="3365" fill="red" style="font-size: 150;"> <% out.print(td.mmToMeter(300));%>
    </text>
    </svg>
    <form name="login" action="FrontController" method="POST">
        <input type="hidden" name="command" value="login">
        <% //TODO change value to employee in session when loggin in %>
        <input type="hidden" name="empnumber" value="a01">
        <input type="hidden" name="password" value="admin">
        <input type="submit" name="submit" value="Tilbage til ordre">
    </form>
    <%@include file="footer.jsp" %>

</body>
</html>
