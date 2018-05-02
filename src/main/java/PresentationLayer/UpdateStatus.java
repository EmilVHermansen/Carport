package PresentationLayer;

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
public class UpdateStatus extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException
    {

        String status = request.getParameter("status");
        int orderId = Integer.parseInt(request.getParameter("orderid"));
        try
        {
            Order order = LogicFacade.getOrder(orderId);
            order.setStatus(status);
            LogicFacade.updateOrder(order);
        } catch (SQLException ex)
        {
            Logger.getLogger(UpdateStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "browseorders";
    }

}
