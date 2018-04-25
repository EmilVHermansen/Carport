package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adams
 */
public class CustomerInfo extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException
    {
        return "customerinfo";
    }
    
}
