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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            request.setAttribute("order", order);
            return "orderinformation";
    }

}//CLASS
