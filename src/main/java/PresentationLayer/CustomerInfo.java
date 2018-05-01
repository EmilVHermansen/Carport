package PresentationLayer;

import FunctionLayer.Customer;
import FunctionLayer.CustomerInfoError;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adams
 */
public class CustomerInfo extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomerInfoError
    {

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        Customer customer = LogicFacade.getCustomerInfo(email);
        if (customer != null)
        {
            request.setAttribute("customer", customer);
            return "customerinfo";
        } else
        {
            throw new CustomerInfoError("A customer with that email does not exist");
        }

    }

}
