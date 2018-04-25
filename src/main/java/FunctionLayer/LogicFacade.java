package FunctionLayer;

import DBAccess.DataAccessObject;
import java.util.ArrayList;
import java.util.List;

public class LogicFacade {

//    public static User login(String email, String password) throws LoginSampleException {
//        return DataAccessObject.login(email, password);
//    }
//
//    public static User createUser(String email, String password) throws LoginSampleException {
//        User user = new User(email, password, "customer");
//        DataAccessObject.createUser(user);
//        return user;
//    }

    public static List<Order> getOrders(int orderId) throws OrderException {
        List<Order> orders = new ArrayList();
        orders = DataAccessObject.getOrders(orderId);
        return orders;
    }

//    public static Order createOrder(int width, int length, int height, User user) throws OrderException {
//        int userId = user.getId();
//        Order order = new Order(length, width, height, userId);
//        DataAccessObject.createOrder(order, userId);
//        return order;
//    }

    public static ArrayList<Brick> listOfBricks(int length, int width, int height) {
        ArrayList<Brick> bricks = new ArrayList();
        LegoHouse legoHouse = new LegoHouse(length, width, height);

        if (length % 2 == 0 && width % 2 == 0) {
            bricks = legoHouse.HasEvenNumbers();
        }

        if (length % 2 == 1 && width % 2 == 1) {
            bricks = legoHouse.HasUnevenNumbers();
        }

        if (length % 2 == 1 && width % 2 == 0 || length % 2 == 0 && width % 2 == 1) {
            bricks = legoHouse.OneUnevenOneEven();
        }

        return bricks;
    }

}
