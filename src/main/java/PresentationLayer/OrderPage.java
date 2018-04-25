///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package PresentationLayer;
//
//import FunctionLayer.Brick;
//import FunctionLayer.LogicFacade;
//import FunctionLayer.Order;
//import FunctionLayer.OrderException;
//import FunctionLayer.User;
//import java.util.ArrayList;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class OrderPage extends Command {
//
//    @Override
//    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        int width = Integer.parseInt("0" + request.getParameter("width"));
//        int length = Integer.parseInt("0" + request.getParameter("length"));
//        int height = Integer.parseInt("0" + request.getParameter("height"));
//        if (width >= 8 && height >= 8 && length >= 8) {
//            Order order = LogicFacade.createOrder(length, width, height, user);
//            session.setAttribute("order", order);
//
//            ArrayList<Brick> bricks = LogicFacade.listOfBricks(length, width, height);
//            session.setAttribute("bricks", bricks);
//
//        } else {
//            throw new OrderException("Order was not successfully created. Please contact support and show this to them: OrderPage");
//        }
//        return "orderconfirmationpage";
//    }
//
//}
