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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author s_ele
 */
public class ViewOrder extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException, MaterialException, SubmitOrderException
    {
        int idorder = Integer.parseInt(request.getParameter("idorder"));
        Order order = LogicFacade.getOrder(idorder);
        if (order != null)
        {
        HttpSession session = request.getSession();
        session.setAttribute("order", order);
        return "orderinformation";
        } else 
        {
            throw new OrderException("Det valgte ordrenummer kunne ikke findes");
        }
    }

}//CLASS
