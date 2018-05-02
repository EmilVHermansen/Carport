package PresentationLayer;

import FunctionLayer.Customer;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author adams
 */
public class CustomerInfo extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomerInfoError, OrderException {

        try {

            HttpSession session = request.getSession();
            int orderid = Integer.parseInt(request.getParameter("custorderid"));
            Customer customer = LogicFacade.getCustomerInfo(orderid);
            Order order = LogicFacade.getOrder(orderid);
            if (customer != null) {
                session.setAttribute("customer", customer);
                session.setAttribute("order", order);
                return "customerinfo";
            } else {
                throw new CustomerInfoError("A customer with that email does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "customerinfo";

    }

}
