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
import java.sql.SQLException;
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
        if ((o.getShed().equals("shed") && ((o.getShedLength() == 0) || (o.getShedWidth() == 0))))
        {
            request.setAttribute("order", o); // Set to use by the exception
            throw new SubmitOrderException("Du valgte med skur, men har ikke indtastet længde og/eller bredde på skuret");
        }
        if ((o.getShed().equals("noShed") && ((o.getShedLength() > 0) || (o.getShedWidth() > 0))))
        {
            request.setAttribute("order", o); // Set to use by the exception
            throw new SubmitOrderException("Du valgte uden skur, men har indtastet længde og/eller bredde større end 0 på skuret");
        }
        if (o.getShedLength() >= o.getLength())
        {
            request.setAttribute("order", o); // Set to use by the exception
            throw new SubmitOrderException("Længden af dit skur kan ikke være samme længde eller længere end din carport");
        }
        if (o.getShedWidth() >= o.getWidth())
        {
            request.setAttribute("order", o); // Set to use by the exception
            throw new SubmitOrderException("Bredden af dit skur kan ikke være samme bredde eller bredere end din carport");
        }
        if (o.getInclination().equals("Med rejsning") && o.getAngle() == 0)
        {
            request.setAttribute("order", o); // Set to use by the exception
            throw new SubmitOrderException("Du har valgt med rejsning, men har ikke valgt en vinkel større end 0 grader");
        }
        if (o.getInclination().equals("Fladt tag") && o.getAngle() > 0)
        {
            request.setAttribute("order", o); // Set to use by the exception
            throw new SubmitOrderException("Du har valgt med fladt tag, men har valgt en vinkel større end 0 grader");
        }
    }

}// CLASS
