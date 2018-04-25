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
import java.util.List;

public class DataAccessObject
{

    public static User login(String employeenumber, String password) throws LoginSampleException
    {
        try
        {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM user "
                    + "WHERE empno=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, employeenumber);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("iduser");
                String empnumber = rs.getString("empno");
                User user = new User(id, empnumber);
                return user;
            } else
            {
                throw new LoginSampleException("Could not validate user");
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static Customer getCustomerInfo(int orderId) throws CustomerInfoError
    {
        try
        {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `order` "
                    + "WHERE orderId=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String zipCity = rs.getString("zipcode");
                String phoneNo = rs.getString("phonenumber");
                String email = rs.getString("email");
                Customer customer = new Customer(name, address, zipCity, phoneNo, email);
                return customer;
            } else
            {
                throw new CustomerInfoError("Could not validate user");
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            throw new CustomerInfoError(ex.getMessage());
        }
    }

    public static List<Order> getOrders() throws OrderException
    {
        try
        {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `order`";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ArrayList<Order> orders = new ArrayList();
            Order order = null;
            ResultSet rs = null;
            rs = ps.executeQuery();


            while (rs.next()) {
                int orderId = rs.getInt("idorder");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                double price = rs.getDouble("price");
                String inclination = rs.getString("inclination");
                String roofMaterial = rs.getString("roof_material");
                String shed = rs.getString("shed");
                int shedLength = rs.getInt("shed_length");
                int shedWidth = rs.getInt("shed_width");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String zipCode = rs.getString("zipcode");
                String phoneNumber = rs.getString("phonenumber");
                String email = rs.getString("email");
                String comment = rs.getString("comment");
                String status = rs.getString("status");
                order = new Order(orderId, length, width, inclination, roofMaterial, shed, name, address, zipCode, phoneNumber, email, price, status);
                order.setComment(comment);
                order.setShedLength(shedLength);
                order.setShedWidth(shedWidth);
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new OrderException(ex.getMessage());
        }
    }

//    public static void createOrder(Order order, int userId) throws OrderException {
//        try {
//            Connection con = Connector.connection();
//            String SQL = "INSERT INTO `Order` (length, width, height, User_idUser) VALUES (?, ?, ?, ?)";
//            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, order.getLength());
//            ps.setInt(2, order.getWidth());
//            ps.setInt(3, order.getHeight());
//            ps.setInt(4, userId);
//            ps.executeUpdate();
//            ResultSet ids = ps.getGeneratedKeys();
//            ids.next();
//            int id = ids.getInt(1);
//            order.setId(id);
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new OrderException(ex.getMessage());
//        }
//    }
    
    public static void submitOrder(Order order) throws OrderException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `order` (length, width, inclination, roof_material, shed, shed_length, shed_width, name, address, zipcode, phonenumber, email, comment, price, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getLength());
            ps.setInt(2, order.getWidth());
            ps.setString(3, order.getInclination());
            ps.setString(4, order.getRoofMaterial());
            ps.setString(5, order.getShed());
            ps.setInt(6, order.getShedLength());
            ps.setInt(7, order.getShedWidth());
            ps.setString(8, order.getName());
            ps.setString(9, order.getAddress());
            ps.setString(10, order.getZipCode());
            ps.setString(11, order.getPhoneNumber());
            ps.setString(12, order.getEmail());
            ps.setString(13, order.getComment());
            ps.setDouble(14, order.getPrice());
            ps.setString(15, order.getStatus());
            ps.executeUpdate();
//            ResultSet ids = ps.getGeneratedKeys();
//            int id = ids.getInt(1);
//            order.setIdOrder(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new OrderException(ex.getMessage());
        }
    }

}
