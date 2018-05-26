/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.MaterialException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import FunctionLayer.SubmitOrderException;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author s_ele
 */
public class OrderEdit extends Command
{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException, MaterialException, SubmitOrderException
    {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
//        Order order = (Order) request.getAttribute("order");
        
        if (order != null ) {
        String attribute = "";

        // Check which attribute was chosen to be changed
        if (request.getParameter("length") != null)
        {
            order.setLength(Integer.parseInt(request.getParameter("length")));
            attribute = "length";
        } else if (request.getParameter("width") != null)
        {
            order.setWidth(Integer.parseInt(request.getParameter("width")));
            attribute = "width";
        } else if  (request.getParameter("inclination") != null)
        {
            order.setInclination(request.getParameter("inclination"));
            attribute = "inclination";
        } else if  (request.getParameter("roofMaterial") != null)
        {
            order.setRoofMaterial(request.getParameter("roofMaterial"));
            attribute = "roof_material";
        } else if  (request.getParameter("angle") != null)
        {
            order.setAngle(Integer.parseInt(request.getParameter("angle")));
            attribute = "angle";
        } else if  (request.getParameter("shed") != null)
        {
            order.setShed(request.getParameter("shed"));
            attribute = "shed";
        }else if  (request.getParameter("shedLength") != null)
        {
            order.setShedLength(Integer.parseInt(request.getParameter("shedLength")));
            attribute = "shed_length";
        }else if  (request.getParameter("shedWidth") != null)
        {
            order.setShedWidth(Integer.parseInt(request.getParameter("shedWidth")));
            attribute = "shed_width";
        }else if  (request.getParameter("comment") != null)
        {
            order.setComment(request.getParameter("comment"));
            attribute = "comment";
        }else if  (request.getParameter("salesprice") != null)
        {
            order.setSalesprice(Integer.parseInt(request.getParameter("salesprice")));
            attribute = "salesprice";
        }else if  (request.getParameter("status") != null)
        {
            order.setStatus(request.getParameter("status"));
            attribute = "status";
        }
                
        request.setAttribute("order", order);
        LogicFacade.updateOrder(order, attribute);
        
        }
        
        return "orderedit";
    }

}
