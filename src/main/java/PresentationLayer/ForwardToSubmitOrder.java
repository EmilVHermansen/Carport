package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author emilv
 */
public class ForwardToSubmitOrder extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException
    {
        return "submitorder";
    }

}