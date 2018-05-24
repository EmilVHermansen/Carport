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
        commands.put("orderhistory", new OrderHistory());
        commands.put("customerInfo", new CustomerInfo());
        commands.put("updatestatus", new UpdateStatus());
        commands.put("submitorder", new SubmitOrder());
        commands.put("orderconfirmation", new OrderConfirmation());
        commands.put("billofmaterials", new BillOfMaterialsPage());
        commands.put("tegning", new TechnicalDrawing());
        commands.put("orderinfo", new OrderInfo());
        commands.put("orderedit", new OrderEdit());

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
