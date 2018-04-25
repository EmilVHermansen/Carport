/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Brick;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import FunctionLayer.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderHistory extends Command {

    public OrderHistory() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException, OrderException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orders = LogicFacade.getOrders(user.getId());
        session.setAttribute("orders", orders);
        return "browseorders";

    }

}
