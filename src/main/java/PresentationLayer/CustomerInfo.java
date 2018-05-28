package PresentationLayer;

import FunctionLayer.Customer;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adams
 */
public class CustomerInfo extends Command {
    
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException {

            HttpSession session = request.getSession();
            int orderid = Integer.parseInt(request.getParameter("custorderid"));
            Customer customer = LogicFacade.getCustomerInfo(orderid);
            Order order = LogicFacade.getOrder(orderid);
            if (customer != null) {
                request.setAttribute("customer", customer);
                session.setAttribute("order", order);
                return "customerinfo";
            } else {
                throw new OrderException("An order with that order id does not exist");
                
            }

    }

}
