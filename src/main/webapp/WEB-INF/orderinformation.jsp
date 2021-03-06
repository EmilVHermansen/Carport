<%-- 
    Document   : editorder
    Created on : May 22, 2018, 11:37:54 PM
    Author     : s_ele
--%>

<%@page import="FunctionLayer.Order"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% session = request.getSession(true); %>
<% Order order = (Order) session.getAttribute("order"); %>

<%@include file="header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordre Information</title>
    </head>
    <body>
        <div class="title-container">
            <div class="title-content">
                <h1>Ordre Information</h1>
            </div>
        </div>
        <div class="error-container">
            <div class="error-content">
                <% String error = (String) request.getAttribute("error");
                    if (error != null)
                    {%>
                <H2>Error!!</h2>
                <p><%= error%>
                    <% } %>
            </div>
        </div>
        <% if (error == null)
            { %>
        <div class="order-table">
            <table class="table table-hover">
                <thead class="thead-dark">
                <th>Ordrenr.</th>
                <th>Længde</th>
                <th>Bredde</th>
                <th>Hældning</th>
                <th>Grader</th>
                <th>Tag materiale</th>
                <th>Skur</th>
                <th>Skur længde</th>
                <th>Skur bredde</th>
                <th>Kommentar</th>
                <th>Vejledende Pris</th>
                <th>Salgspris</th>
                <th>Status</th>
                </thead>
                <tbody>
                    <tr>
                        <td><% out.print(order.getIdOrder()); %></td>
                        <td><% out.print(order.getLength()); %></td>
                        <td><% out.print(order.getWidth()); %></td>
                        <td><% out.print(order.getInclination()); %></td>
                        <td><% out.print(order.getAngle()); %></td>
                        <td><% out.print(order.getRoofMaterial()); %></td>
                        <td><% out.print(order.getShed()); %></td>
                        <td><% out.print(order.getShedLength()); %></td>
                        <td><% out.print(order.getShedWidth()); %></td>
                        <td><% out.print(order.getComment()); %></td>
                        <td><% out.print(order.getPrice()); %></td>
                        <td><% out.print(order.getSalesprice()); %></td>
                        <td><% out.print(order.getStatus()); %></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <form name="editLength" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
                                <select name="length">
                                    <% for (int j = 240; j < 780; j += 30)
                                        {
                                    %>  <option value=<% out.print(j); %>><% out.print(j);%>cm</option><%
                                        } %>
                                </select>
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>
                            <form name="editWidth" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
                                <select name="width">
                                    <% for (int j = 240; j < 780; j += 30)
                                        {
                                    %>  <option value=<% out.print(j); %>><% out.print(j);%>cm</option><%
                                        } %>
                                </select>
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>
                            <form name="editInclination" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
                                <select name="inclination">
                                    <option value="Med rejsning">Med rejsning</option>
                                    <option value="Fladt tag">Flat tag</option>
                                </select>
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>
                            <form name="editAngle" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
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
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>
                            <form name="editRoofMaterial" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
                                <select name="roofMaterial">
                                    <option value="ingen">Ingen</option>
                                    <option value="betontagsten">Betontagsten</option>
                                    <option value="eternittag b6">Eternittag B6</option>
                                </select>
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>
                            <form name="editShed" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
                                <select name="shed">
                                    <option value="Med skur">Med Skur</option>
                                    <option value="Uden skur">Uden Skur</option>
                                </select>
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>
                            <form name="editShedLength" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
                                <select name="shedLength">
                                    <% for (int i = 0; i < 690; i += 30)
                                        {
                                            if (i == 0 || i >= 210)
                                    %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><%
                                        } %>
                                </select>
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>
                            <form name="editShedWidth" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
                                <select name="shedWidth">
                                    <% for (int i = 0; i < 720; i += 30)
                                        {
                                            if (i == 0 || i >= 150)
                                    %>  <option value=<% out.print(i); %>><% out.print(i);%>cm</option><%
                                        } %>
                                </select>
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>
                            <form name="editComment" action="FrontController" method="POST" accept-charset="ISO-8859-1">
                                <input type="hidden" name="command" value="editorder">
                                <input type="text" name="comment">
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>N/A</td>
                        <td>
                            <form name="editSalesprice" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
                                <input type="number" name="salesprice" min="0" value="<% out.print(order.getPrice()); %>">
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                        <td>
                            <form name="editStatus" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="editorder">
                                <select name="status">
                                    <option value="Behandler">Behandler</option>
                                    <option value="Behandlet">Behandlet</option>
                                    <option value="Afsendt">Afsendt</option>
                                    <option value="Annulleret">Annulleret</option>
                                </select>
                                <input type="submit" name="Opdater" value="Opdater">
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <%};%>
        <div class="customer-container">
            <div class="customer-content">

                <span id="title">Navn:</span> <span id="customer-info"><% out.print(order.getName()); %> </span> <br>
                <span id="title">Adresse: </span><span id="customer-info"><% out.print(order.getAddress()); %> </span><br>
                <span id="title">Postnummer & By :</span><span id="customer-info"> <% out.print(order.getZipCode()); %> </span><br>
                <span id="title">Telefon: </span><span id="customer-info"><% out.print(order.getPhoneNumber()); %> </span><br>
                <span id="title">Email: </span><span id="customer-info"><% out.print(order.getEmail());%> </span>



            </div>
        </div>
        <div class="buttons-container">
            <!-- button to access the bill of materials -->
            <div class="button1">
                <form name="createbom" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="createbom">
                    <input type="submit" name="submit" value="Stykliste">
                </form>
            </div>
            <div class="button2">
                <!-- button to access the technical drawing -->
                <form name="tegning" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="tegning">
                    <input type="submit" name="tegning" value="Tegning">
                </form>
            </div>
            <div class="button3">
                <!-- button to go back to order history-->
                <form name="login" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="returntoorders">
                    <% //TODO change value to employee in session when loggin in %>
                    <input type="submit" name="submit" value="Tilbage til ordrer">
                </form>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <%@include file="footer.jsp" %>
    </body>
</html>
