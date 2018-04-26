package FunctionLayer;

import DBAccess.DataAccessObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogicFacade {

    public static Customer getCustomerInfo(int orderId) throws CustomerInfoError {
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
    
    public static Order getOrder(int orderId) throws OrderException, SQLException {
        Order order = DataAccessObject.getOrder(orderId);
        return order;
    }
    
    

    public static void updateOrder(Order order) throws LoginSampleException {
        DataAccessObject.updateOrderStatus(order);

    }

}
