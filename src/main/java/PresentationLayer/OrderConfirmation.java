/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LineItem;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import FunctionLayer.PriceCalculator;
import FunctionLayer.User;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author s_ele
 */
public class OrderConfirmation extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException, SQLException
    {
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        String inclination = request.getParameter("inclination");
        int angle = Integer.parseInt(request.getParameter("angle"));
        String roofMaterial = request.getParameter("roofMaterial");
        String shed = request.getParameter("shed");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Order order = new Order(length, width, inclination, angle, roofMaterial, shed, name, address, zipcode, phoneNumber, email, width);

        order.setShedLength(Integer.parseInt(request.getParameter("shedLength")));
        order.setShedWidth(Integer.parseInt(request.getParameter("shedWidth")));

        if (!request.getParameter("comment").isEmpty());
        {
            order.setComment(request.getParameter("comment"));
        }
        
        List<LineItem> BoM = LogicFacade.createBoM(order);
        for (LineItem lineItem : BoM)
        {
            Material material = LogicFacade.getMaterial(lineItem.getIdmaterial());
            lineItem.setPrice(material.getMSRP());   
        }
         int orderPrice = LogicFacade.calcPrice(BoM);
         order.setPrice(orderPrice);

        try
        {
            LogicFacade.submitOrder(order);

        } catch (OrderException ex)
        {
            Logger.getLogger(OrderConfirmation.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("order", order);
        return "orderconfirmationpage";
    }

}
