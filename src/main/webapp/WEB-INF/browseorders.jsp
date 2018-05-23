<%-- 
    Document   : orderhistory
    Created on : 26-Mar-2018, 21:18:28
    Author     : emilv
--%>

<%@page import="FunctionLayer.LogicFacade"%>
<%@page import="DBAccess.DataAccessObject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
%>
<%@include file="header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordre</title>
    </head>
    <body>
        <div class="top-container">
            <div class="top-content-left">
                <form name="customerInfo" action="FrontController" method="POST">
                    <h2>Find kunde information</h2>
                    <p>Indtast ordrenr.</p>
                    <input type="hidden" name="command" value="customerInfo">
                    <input type="number" name ="custorderid" placeholder="Ordrenr." required>
                    <input type="submit" name="submit" value="Søg">
                </form>
            </div>
            <div class="top-content-right">
                <form name="updatestatus" action="FrontController" method="post">
                    <input type="hidden" name="command" value="updatestatus">
                    <h2>Opdater ordre status: </h2>
                    <p>Indtast ordrenr.</p>
                    <input type="number" name ="orderid" placeholder="Ordrenr.">
                    <select name="status" value="Opdater Status">
                        <option name="Behandler">Behandler</option>
                        <option name="Behandlet">Behandlet</option>
                        <option name="Afsendt">Afsendt</option>
                        <option name="Annulleret">Annulleret</option>
                    </select>
                    <input type="submit" name="submit" value="Opdater">
                </form>
            </div>
        </div>
        <div class="bot-container">
            <div class="bot-title">
                <h1>Ordre historie</h1>
            </div>
            <div class="bot-content">
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
                <th>Rediger ordre</th>
                </thead>
                <tbody>
                    <%for (Order order : orders)
                        { %>
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
                        <td>
                            <form name="orderinfo" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="orderinfo">
                                <input type="hidden" name ="idOrder" value="<% out.print(order.getIdOrder()); %>">
                                <input type="submit" name="Rediger" value="Rediger">
                            </form>
                        </td>
                    </tr>
                    <%};%>
                </tbody>
            </table>
            </div>
        </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <%@include file="footer.jsp" %>
    </body>

</html>
