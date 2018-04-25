package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        return null;
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        User user = LogicFacade.login(email, password);
//        HttpSession session = request.getSession();
//        session.setAttribute("user", user);
//        session.setAttribute("role", user.getRole());
//        return user.getRole() + "page";
    }

}
