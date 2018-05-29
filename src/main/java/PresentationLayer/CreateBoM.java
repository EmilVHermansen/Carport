package PresentationLayer;

import FunctionLayer.BillOfMaterialsFlat;
import FunctionLayer.BillOfMaterialsInclination;
import FunctionLayer.LineItem;
import FunctionLayer.LogicFacade;
import FunctionLayer.Material;
import FunctionLayer.MaterialException;
import FunctionLayer.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adams
 */
public class CreateBoM extends Command
{
    

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws MaterialException
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
                request.setAttribute("lineitems", lineItems);
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
                request.setAttribute("lineitems", lineItems);
            }
        }
        return "billofmaterials";
    }

}
