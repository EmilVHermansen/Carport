package PresentationLayer;

import FunctionLayer.CustomerInfoError;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wtfak
 */
public class ChangePrice extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException, CustomerInfoError
    {
        int newPrice = Integer.parseInt(request.getParameter("newprice"));
        int orderId = Integer.parseInt(request.getParameter("orderid"));
        
        try
        {
            Order order = LogicFacade.getOrder(orderId);
            order.setPrice(newPrice);
            LogicFacade.changePrice(order);
        } catch (SQLException ex)
        {
            Logger.getLogger(UpdateStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "browseorders";

    }

}
