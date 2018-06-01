/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReturnToOrders extends Command
{

    public ReturnToOrders()
    {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException
    {
        HttpSession session = request.getSession();
        List<Order> orders = LogicFacade.getOrders();
        session.setAttribute("orders", orders);
        return "browseorders";

    }

}
