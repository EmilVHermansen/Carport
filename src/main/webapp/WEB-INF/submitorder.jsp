<%-- 
    Document   : submitorder
    Created on : 26-Apr-2018, 10:23:55
    Author     : emilv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
     <%@include file="header.jsp" %>

    <body>
        <h1>Bestil din carport her</h1>

        <table>
            <tr>Vælg specifikationer på din carport
                <td>
                    <form name="submitorder" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="orderconfirmation">
                        <%--TODO custom exceptions--%>
                        Bredde i cm:<br>
                        <input type="number" name="width" min="240" max="750" required>
                        <br>
                        Længde i cm:<br>
                        <input type="number" name="length" min="240" max="780" required>
                        <br>
                        Med rejsning eller fladt tag:<br>
                        <select name="inclination">
                            <option value="Med rejsning">Med rejsning</option>
                            <option value="Fladt tag">Fladt tag</option>
                        </select>
                        <br>
                        Tag materiale (hvis der er valgt med rejsning):<br>
                        <select name="roofMaterial">
                            <option value="ingen">Ingen</option>
                            <option value="betontagsten">Betontagsten</option>
                            <option value="eternittag b6">Eternittag B6</option>
                        </select>
                        <br>
                        Med skur:<br>
                        <select name="shed">
                            <option value="shed">Med Skur</option>
                            <option value="noShed">Uden Skur</option>
                        </select>
                        <%--TODO custom exceptions--%>
                        <br>
                        Skur bredde i cm:<br>
                        <input type="number" name="shedWidth" min="0" max="720" value="0">
                        <br>
                        Skur længde i cm:<br>
                        <input type="number" name="shedLength" min="0" max="690" value="0">
                        <br>
                        Navn:<br>
                        <input type="text" name="name" required>
                        <br>
                        Adresse:<br>
                        <input type="text" name="address" required>
                        <br>
                        Postnr. og by:<br>
                        <input type="text" name="zipcode" required>
                        <br>
                        Telefon:<br>
                        <input type="text" name="phoneNumber" required>
                        <br>
                        Email:<br>
                        <input type="text" name="email" required>
                        <br>
                        Evt. bemærkninger:<br>
                        <input type="text" name="comment">
                        <br>
                        <input type="submit" value="Submit">
                    </form>
                </td>
            </tr>
        </table>
        
    </body>
</html>
