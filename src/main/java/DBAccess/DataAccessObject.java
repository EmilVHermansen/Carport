package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.CustomerInfoError;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataAccessObject {

    public static void createUser(User user) throws LoginSampleException {
        try {

            Connection con = Connector.connection();
            String SQL = "INSERT INTO User (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setId(id);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static Customer getCustomerInfo(int orderId) throws CustomerInfoError {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `ordre` "
                    + "WHERE orderId=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("navn");
                String address = rs.getString("adresse");
                String zipCity = rs.getString("postnummer_by");
                String phoneNo = rs.getString("telefon");
                String email = rs.getString("email");
                Customer customer = new Customer(name, address, zipCity, phoneNo, email);
                return customer;
            } else {
                throw new CustomerInfoError("Could not validate user");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CustomerInfoError(ex.getMessage());
        }
    }

    public static ArrayList<Order> getOrders(int userId) throws OrderException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `Order` WHERE User_idUser = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ArrayList<Order> orders = new ArrayList();
            Order order = null;
            ResultSet rs = null;
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idOrder");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                String status = rs.getString("status");
                order = new Order(length, width, height, userId);
                order.setStatus(status);
                order.setId(id);
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new OrderException(ex.getMessage());
        }
    }

    public static void createOrder(Order order, int userId) throws OrderException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `Order` (length, width, height, User_idUser) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getLength());
            ps.setInt(2, order.getWidth());
            ps.setInt(3, order.getHeight());
            ps.setInt(4, userId);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            order.setId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new OrderException(ex.getMessage());
        }
    }

}
