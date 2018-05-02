package PresentationLayer;

import FunctionLayer.BillOfMaterials;
import FunctionLayer.CustomerInfoError;
import FunctionLayer.LineItem;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adams
 */
public class BillOfMaterialsPage extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException, CustomerInfoError
    {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        BillOfMaterials bom = new BillOfMaterials(order);
        bom.createBoM();
        List<LineItem> lineItems = bom.getBillOfMaterials();
        session.setAttribute("lineitems", lineItems);
        return "billofmaterials";
    }

}
