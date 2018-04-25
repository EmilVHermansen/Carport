package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import FunctionLayer.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends Command {

    @Override


    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException {
        String empnumber = request.getParameter("empnumber");
        String password = request.getParameter("password");
        User user = LogicFacade.login(empnumber, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        List<Order> orders = LogicFacade.getOrders();
        session.setAttribute("orders", orders);
        return "browseorders";

    }

}
