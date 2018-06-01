package FunctionLayer;

import DBAccess.DataAccessObject;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogicFacade
{

    public static User login(String empnumber, String password) throws LoginSampleException
    {
        return DataAccessObject.login(empnumber, password);
    }

    public static List<Order> getOrders() throws OrderException
    {
        List<Order> orders = DataAccessObject.getOrders();
        return orders;
    }

    public static Order getOrder(int orderId) throws OrderException
    {
        Order order = DataAccessObject.getOrder(orderId);
        return order;
    }

    public static void updateOrderStatus(Order order) throws OrderException
    {
        DataAccessObject.updateOrderStatus(order);

    }

    public static void updateOrder(Order order, String attribute) throws OrderException
    {
        DataAccessObject.updateOrder(order, attribute);
    }

    public static void submitOrder(Order order) throws SubmitOrderException
    {
        DataAccessObject.submitOrder(order);
    }

    public static Material getMaterial(int materialId) throws MaterialException
    {
        Material material;
        material = DataAccessObject.getMaterial(materialId);
        return material;
    }

    public static List<LineItem> createBoM(Order order) throws OrderException
    {
        List<LineItem> BoM;
        if (order.getInclination().equals("Med rejsning"))
        {
            BillOfMaterialsInclination BoMInclination = new BillOfMaterialsInclination(order);
            BoMInclination.createBoM();
            BoM = BoMInclination.getBillOfMaterials();
        } else
        {
            BillOfMaterialsFlat BoMFlat = new BillOfMaterialsFlat(order);
            BoMFlat.createBoM();
            BoM = BoMFlat.getBillOfMaterials();
        }
        return BoM;
    }

    public static int calcPrice(List<LineItem> BoM)
    {
        return PriceCalculator.calcPrice(BoM);
    }

    public static void log(Logger logger, Exception ex) throws IOException
    {
        String exName = ex.getClass().getSimpleName();
        switch (exName)
        {
            case "SubmitOrderException":
                SubmitErrorLogger.init();
                break;
            default:
                SystemLogger.init();
                break;
        }
        StackTraceElement[] stackTraceArr = ex.getStackTrace();
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement stackTraceArr1 : stackTraceArr)
        {
            stackTrace.append("\r\n");
            stackTrace.append(stackTraceArr1.toString());
        }
        logger.log(Level.INFO, ex.getMessage() + stackTrace + "\r\n");
    }

}
