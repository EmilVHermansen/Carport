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
        ArrayList<Order> orders = LogicFacade.getOrders(user.getId());
        for (Order order : orders) {
            order.setBricks(LogicFacade.listOfBricks(order.getLength(), order.getWidth(), order.getHeight()));
        }
        session.setAttribute("orders", orders);
        return "orderhistory";

    }

}
