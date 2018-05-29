package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wtfak
 */
public class UpdateStatus extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException
    {
        HttpSession session = request.getSession();
        String status = request.getParameter("status");
        int orderId = Integer.parseInt(request.getParameter("idorder"));
        Order order = LogicFacade.getOrder(orderId);
        if (order != null)
        {
            order.setStatus(status);
            LogicFacade.updateOrderStatus(order);
            List<Order> orders = LogicFacade.getOrders();
            session.removeAttribute("orders");
            session.setAttribute("orders", orders);
            
            return "browseorders";
        } else
            throw new OrderException("Der gik noget galt. Prøv igen senere, ellers kontakt support");
    }

}
