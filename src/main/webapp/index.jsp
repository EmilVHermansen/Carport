<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <title>Welcome page</title>
    </head>
    <%@include file="WEB-INF/header.jsp" %>
    <body>
        <div class="container">
            <h1>Fog carport</h1>

            <table>
                <thead>

                <th>Login</th>
                </thead>
                <tr>
                    <td>
                        <form name="login" action="FrontController" method="POST">
                            <input type="hidden" name="command" value="login"> Email:
                            <br>
                            <input type="text" name="empnumber" value="a01">
                            <br> Password:
                            <br>
                            <input type="password" name="password" value="admin">
                            <br>
                            <input type="submit" value="Login">
                        </form>
                    </td>
                    <td>Eller indsend ordre</td>
                    <td>
                        <form name="register" action="FrontController" method="POST">
                            <input type="hidden" name="command" value="forwardtosubmitorder">
                            <input type="submit" value="Ok">
                        </form>
                    </td>
                </tr>
            </table>
            <% String error = (String) request.getAttribute("error");
                if (error != null)
                {%>
            <H2>Error!!</h2>
            <p>
                <%= error%>
                <% }
                %>
        </div>
        <%@include file="WEB-INF/footer.jsp" %>
    </body>

</html>
