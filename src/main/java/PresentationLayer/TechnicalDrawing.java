package PresentationLayer;

import FunctionLayer.Customer;
import FunctionLayer.CustomerInfoError;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wtfak
 */


public class TechnicalDrawing extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException, CustomerInfoError {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (order.getInclination().equals("ja"))
            return "techdrawinclination";
        else
            return "technicaldrawing";
    }
    
}
