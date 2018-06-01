package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.MaterialException;
import FunctionLayer.OrderException;
import FunctionLayer.SubmitOrderException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("returntoorders", new ReturnToOrders());
        commands.put("updatestatus", new UpdateStatus());
        commands.put("forwardtosubmitorder", new ForwardToSubmitOrder());
        commands.put("submitorder", new SubmitOrder());
        commands.put("createbom", new CreateBoM());
        commands.put("tegning", new TechnicalDrawing());
        commands.put("vieworder", new ViewOrder());
        commands.put("editorder", new EditOrder());

    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws LoginSampleException, OrderException, MaterialException, SubmitOrderException;

}
