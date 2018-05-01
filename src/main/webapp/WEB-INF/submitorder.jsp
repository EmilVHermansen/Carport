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
    <body>
        <h1>Order your carport here</h1>

        <table>
            <tr>Vælg specifikationer på din carport
                <td>
                    <form name="submitorder" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="orderconfirmation">
                        Længde i cm:<br>
                        <input type="number" name="length" min="1" max="500" required>
                        <br>
                        Bredde i cm:<br>
                        <input type="number" name="width" min="1" max="500" required>
                        <br>
                        Med rejsning eller fladt tag:<br>
                        <select name="inclination">
                            <option value="inclination">Med rejsning</option>
                            <option value="flat">Flat tag</option>
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
                        <br>
                        Skur længde i cm:<br>
                        <input type="number" name="shedLength" min="0" max="200" value="0">
                        <br>
                        Skur bredde i cm:<br>
                        <input type="number" name="shedWidth" min="0" max="200" value="0">
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
