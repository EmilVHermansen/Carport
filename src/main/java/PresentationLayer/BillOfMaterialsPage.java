package PresentationLayer;

import FunctionLayer.BillOfMaterialsFlat;
import FunctionLayer.BillOfMaterialsInclination;
import FunctionLayer.CustomerInfoError;
import FunctionLayer.LineItem;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import java.sql.SQLException;
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
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, OrderException, CustomerInfoError, SQLException
    {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (order.getInclination().equals("Med rejsning"))
        {
            BillOfMaterialsInclination bom = new BillOfMaterialsInclination(order);
            bom.createBoM();
            List<LineItem> lineItems = bom.getBillOfMaterials();
            for (LineItem lineItem : lineItems)
            {
                Material mat = LogicFacade.getMaterial(lineItem.getIdmaterial());
                lineItem.setName(mat.getName());
                lineItem.setPrice(mat.getMSRP()); //TODO price is multiplied by either meterprice or quantity
                session.setAttribute("lineitems", lineItems);
            }
        } else
        {
            BillOfMaterialsFlat bom = new BillOfMaterialsFlat(order);
            bom.createBoM();
            List<LineItem> lineItems = bom.getBillOfMaterials();
            for (LineItem lineItem : lineItems)
            {
                Material mat = LogicFacade.getMaterial(lineItem.getIdmaterial());
                lineItem.setName(mat.getName());
                lineItem.setPrice(mat.getMSRP()); //TODO price is multiplied by either meterprice or quantity
                session.setAttribute("lineitems", lineItems);
            }
        }
        return "billofmaterials";
    }

}
