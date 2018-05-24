package FunctionLayer;

import DBAccess.DataAccessObject;
import java.sql.SQLException;
import java.util.List;

public class LogicFacade {

    public static Customer getCustomerInfo(int orderId) throws OrderException
    {
        Customer customer;
        customer = DataAccessObject.getCustomerInfo(orderId);
        return customer;
    }

    public static User login(String empnumber, String password) throws LoginSampleException {
        return DataAccessObject.login(empnumber, password);
    }

    public static List<Order> getOrders() throws OrderException {
        List<Order> orders = DataAccessObject.getOrders();
        return orders;
    }
    
    public static Order getOrder(int orderId) throws OrderException {
        Order order = DataAccessObject.getOrder(orderId);
        return order;
    }
    
    

    public static void updateOrderStatus(Order order) throws OrderException {
        DataAccessObject.updateOrderStatus(order);

    }
    
    public static void updateOrder(Order order, String attribute) throws OrderException {
        DataAccessObject.updateOrder(order, attribute);
    }
    
    public static void submitOrder(Order order) throws SubmitOrderException {
        DataAccessObject.submitOrder(order);
    }
    
    public static Material getMaterial(int materialId) throws MaterialException {
        Material material;
        material = DataAccessObject.getMaterial(materialId);
        return material;
    }
    
    public static List<LineItem> createBoM(Order order) throws OrderException {
        List<LineItem> BoM;
        if (order.getInclination().equals("Med rejsning"))
        {
            BillOfMaterialsInclination BoMInclination = new BillOfMaterialsInclination(order);
            BoMInclination.createBoM();
            BoM = BoMInclination.getBillOfMaterials();
        } else{
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

}
