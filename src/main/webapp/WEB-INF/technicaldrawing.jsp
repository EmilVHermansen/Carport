<%-- 
    Document   : technicaldrawing
    Created on : May 2, 2018, 9:56:09 AM
    Author     : wtfak
--%>

<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<!DOCTYPE html>

<!-- TERMINOLOGY

 RAFTER == SPÆR
 WALLPLATE == REM
 PERFORATED BAND == KRYDSBÅND

-->
<%
    Order order = (Order) session.getAttribute("order");
    int width = order.getWidth() * 10;
    int length = order.getLength() * 10;
    int shedWidth = order.getShedWidth() * 10;
    int shedLength = order.getShedLength() * 10;
    int rafterXPos = 0;
    int farRightPoleXPos = length - 425;
    boolean shed = false;
    if (shedWidth > 0 && shedLength > 0)
    {
        shed = true;
    }

    //Used to find the amount of rafters needed
    int qty = 1;
    qty += (length - 45) / 645;
    if ((length - 45) % 645 > 0)
    {
        qty++;
    }
    int holes = qty - 1;
    int rafterDistance = (length - 45) / holes;

    //Used to find the placement of the perforated band if there is a shed.
    int RafterQty = 1;
    RafterQty += ((length - shedLength - 395) - 45) / 645;
    if (((length - shedLength - 395) - 45) % 645 > 0)
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
        <div class="title-container">
            <div class="title-content">
                <h2>Mål: <% out.print(order.getLength() * 10); %> x <% out.print(order.getWidth() * 10); %> mm</h2>
                <% if (shed)
                    {
                %>
                <h2>Skur mål: <% out.print(shedWidth); %> x <% out.print(shedLength); %> mm</h2>
                <%
                    }
                %>
            </div>
        </div>

        <div class="svg-container">
            <div class="svg-content">
                <!-- SVG drawing of carport for the customer -->
                <SVG width="<% out.print(length / 10); %>" height="<% out.print(width / 10); %>" 
                     viewBox="0 0 <% out.print(length); %> <% out.print(width); %> ">

                <!-- carport outline -->
                <rect x="0" y="0" width="<% out.print(length); %>" height="<% out.print(width); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <!-- Shed -->

                <%
                    if (shed)
                    {

                %>
                <!-- vertical -->
                <%  //If the shed is going to overflow, the height is reduced
                    if (shedWidth + 280 < width - 395)
                    {
                %>
                <rect x="<% out.print(length - shedLength - 405); %>" y="350" width="70" height="<% out.print(shedWidth); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <rect x="<% out.print(length - 405); %>" y="350" width="70" height="<% out.print(shedWidth); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <%
                } else
                {
                %>
                <rect x="<% out.print(length - shedLength - 405); %>" y="350" width="70" height="<% out.print(shedWidth - 395); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <rect x="<% out.print(length - 395); %>" y="350" width="70" height="<% out.print(shedWidth - 395); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <%
                    }
                %>

                <!-- horizontal -->
                <rect x="<% out.print(length - shedLength - 395); %>" y="327.5" width="<% out.print(shedLength); %>" height="70"
                      style="stroke: black; fill: white; stroke-width: 10;"/>

                <%  //If the shed is going to overflow, the bottom one is moved to the wallplate
                    if (shedWidth + 280 < width - 395)
                    {
                %>
                <rect x="<% out.print(length - shedLength - 395); %>" y="<% out.print(shedWidth + 280);%>" width="<% out.print(shedLength); %>" height="70"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <%
                } else
                {
                %>
                <rect x="<% out.print(length - shedLength - 395); %>" y="<% out.print(width - 395);%>" width="<% out.print(shedLength); %>" height="70"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <%
                    }
                %>
                <!--Shed poles-->
                <%
                    //If the shed is 3m or wider than that, then an extra pole will be added in the middle
                    if (shedWidth >= 3000)
                    {
                %>

                <!-- 350 is the distance from the edge of the carport to the wallPlate, where the shed starts-->
                <rect x="<% out.print(length - shedLength - 405); %>" y="<% out.print(shedWidth / 2 + 350); %>" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <rect x="<% out.print(length - 425); %>" y="<% out.print(shedWidth / 2 + 350); %>" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/> 
                <%
                    }

                %>
                <%  //If the shed is going to overflow, the poles will be moved to the wall plate                  
                    if (shedWidth + 280 < width - 395)
                    {
                %>
                <rect x="<% out.print(length - shedLength - 405); %>" y="<% out.print(shedWidth + 250); %>" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <rect x="<% out.print(length - 425); %>" y="<% out.print(shedWidth + 250); %>" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <%
                    }
                %>
                <!-- Poles bottom right -->
                <rect x="<% out.print(length - shedLength - 405); %>" y="<% out.print(width - 417.5); %>" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <rect x="<% out.print(length - shedLength - 405); %>" y="327.5" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <%
                    }
                %>







                <!-- Poles -->
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

                <%
                    }
                    //If there's still more space left 6m away from the far right pole
                    //two extra poles will be added on the right side of the carport
                    if (length > farRightPoleXPos - 6000)
                    {
                %>
                <rect x="<% out.print(farRightPoleXPos - 6000); %>" y="327.5" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <rect x="<% out.print(farRightPoleXPos - 6000); %>" y="<% out.print(width - 417.5); %>" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <%
                    }
                %>
                <%
                    //If there's more than 3m between the far right poles and the left edge, a pole needs to be added 3 meters from it
                %>
                <rect x="<% out.print(farRightPoleXPos - 3000); %>" y="327.5" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <rect x="<% out.print(farRightPoleXPos - 3000); %>" y="<% out.print(width - 417.5); %>" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <!-- top, far right pole -->
                <rect x="<% out.print(farRightPoleXPos); %>" y="327.5" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <rect x="<% out.print(farRightPoleXPos); %>" y="<% out.print(width - 417.5); %>" width="100" height="100"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <!--Wallplate-->
                <rect x="0" y="<% out.print(width - 395);%>" width="<% out.print(length); %>" height="45"
                      style="stroke: black; fill: white; stroke-width: 10; "/>
                <rect x="0" y="350" width="<% out.print(length); %>" height="45"
                      style="stroke: black; fill: white; stroke-width: 10;"/>

                <!-- Wall plate -->
                <%
                    for (int i = 0; i <= qty; i++)
                    {
                %> 
                <rect x=" <% out.print(rafterXPos); %>" y="0" width="45" height="<% out.print(width); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <%
                        rafterXPos += rafterDistance;
                    }
                %>

                <!--Perforated band-->

                <!-- if there's no shed present, the perforated band will range from the 
                     start of the first rafter, to the start of the last rafter -->
                <%
                    if (!shed)
                    {
                %>
                <!-- Top left to bottom right -->
                <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 75); %>" y1="395" x2="<% out.print(length - rafterDistance - 25); %>" y2="<% out.print(width - 395); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 50); %>" y1="415" x2="<% out.print(length - rafterDistance - 85); %>" y2="<% out.print(width - 395); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>

                <!-- Top right to bottom left -->
                <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 75); %>" y1="<% out.print(width - 395); %>" x2="<% out.print(length - rafterDistance - 25); %>" y2="395"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 50); %>" y1="<% out.print(width - 415); %>" x2="<% out.print(length - rafterDistance - 85); %>" y2="415"
                      style="stroke: black; fill: white; stroke-width: 10;"/>

                <%
                    }
                %>

                <%
                    if (shed)
                    {
                %>
                <!-- Top left to bottom right -->

                <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 75); %>" y1="395" x2="<% out.print(holesWithoutShed * rafterDistance); %>" y2="<% out.print(width - 395); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 50); %>" y1="415" x2="<% out.print(holesWithoutShed * rafterDistance - 50); %>" y2="<% out.print(width - 395); %>"
                      style="stroke: black; fill: white; stroke-width: 10;"/>

                <!-- Bottom left to top right -->
                <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 75); %>" y1="<% out.print(width - 375); %>" x2="<% out.print(holesWithoutShed * rafterDistance); %>" y2="395"
                      style="stroke: black; fill: white; stroke-width: 10;"/>
                <line stroke-dasharray="50, 50"              x1="<% out.print(rafterDistance + 50); %>" y1="<% out.print(width - 395); %>" x2="<% out.print(holesWithoutShed * rafterDistance - 50); %>" y2="415"
                      style="stroke: black; fill: white; stroke-width: 10;"/>

                <%
                    }
                %>

                </SVG>
            </div>
        </div>

        <div class="drawing-but-container">
            <div class="drawing-but-content">
                <form name="login" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="returntoorders">
                    <input type="submit" name="submit" value="Tilbage til ordre">
                </form>
            </div>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
