<%-- 
    Document   : dummy
    Created on : 25-Apr-2018, 10:58:53
    Author     : emilv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP test page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <form name="orderhistorybutton" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="orderhistory">
                        <input type="submit" value="Press here to show orders">
                    </form>
    </body>
</html>
