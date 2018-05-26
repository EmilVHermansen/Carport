/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LineItem;
import FunctionLayer.LogicFacade;
import FunctionLayer.Material;
import FunctionLayer.MaterialException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import FunctionLayer.SubmitOrderException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author s_ele
 */
public class OrderConfirmation extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException, MaterialException, SubmitOrderException
    {
        // Carport details
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        String inclination = request.getParameter("inclination");
        int angle = Integer.parseInt(request.getParameter("angle"));
        String roofMaterial = request.getParameter("roofMaterial");
        String shed = request.getParameter("shed");
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        
        // customer details
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String comment = "";
        if (request.getParameter("comment") != null);
        {
            comment = request.getParameter("comment");
        }
        
        
        // Order creation
        Order order = new Order(length, width, inclination, angle, roofMaterial, shed, name, address, zipcode, phoneNumber, email);
        order.setShedLength(shedLength);
        order.setShedWidth(shedWidth);
        order.setComment(request.getParameter("comment"));

        // SubmitOrderExceptionCheck
        checkForSubmitOrderException(order, request);
        
        
        List<LineItem> BoM = LogicFacade.createBoM(order);
        for (LineItem lineItem : BoM)
        {
            Material material = LogicFacade.getMaterial(lineItem.getIdmaterial());
            lineItem.setPrice(material.getMSRP());
        }
        order.setPrice(LogicFacade.calcPrice(BoM));
        
        

        // Submit order to database
        LogicFacade.submitOrder(order);

        
        // return 
        request.setAttribute("order", order);
        return "orderconfirmationpage";
    }
    
    private static void checkForSubmitOrderException(Order o, HttpServletRequest request) throws SubmitOrderException 
    {
        String msg = "";
        if ((o.getShed().equals("shed") && ((o.getShedLength() == 0) || (o.getShedWidth() == 0))))
        {
            msg = "Du har valgt med skur, men har ikke indtastet længde og/eller bredde på skuret";
        }
        else if ((o.getShed().equals("noShed") && ((o.getShedLength() > 0) || (o.getShedWidth() > 0))))
        {
            msg = "Du har valgt uden skur, men har indtastet længde og/eller bredde større end 0 på skuret";
        }
        else if (o.getShedLength() >= o.getLength())
        {
            msg = "Længden af dit skur kan ikke være samme længde eller længere end din carport";
        }
        else if (o.getShedWidth() >= o.getWidth())
        {
            msg = "Bredden af dit skur kan ikke være samme bredde eller bredere end din carport";
        }
        else if (o.getInclination().equals("Med rejsning") && o.getAngle() == 0)
        {
            msg = "Du har valgt med rejsning, men har ikke valgt en vinkel større end 0 grader";
        }
        else if (o.getInclination().equals("Fladt tag") && o.getAngle() > 0)
        {
            msg = "Du har valgt med fladt tag, men har valgt en vinkel større end 0 grader";
        }
        
        if (!msg.isEmpty())
        {
            request.setAttribute("order", o); // Set to use by the exception
            throw new SubmitOrderException(msg);
        }
    }

}// CLASS
