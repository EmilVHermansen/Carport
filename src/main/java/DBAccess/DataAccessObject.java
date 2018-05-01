package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.CustomerInfoError;
import FunctionLayer.LineItem;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessObject
{
// Delete this, it's a test.


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

            if (rs.next())
            {
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

    public static Customer getCustomerInfo(String email) throws CustomerInfoError {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `order` "
                    + "WHERE email=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            Customer customer = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String zipCity = rs.getString("zipcode");
                String phoneNo = rs.getString("phonenumber");
                String custEmail = rs.getString("email");
                customer = new Customer(name, address, zipCity, phoneNo, custEmail);
            }
            return customer;

        } catch (ClassNotFoundException | SQLException ex) {

            throw new CustomerInfoError(ex.getMessage());
        }
    }

    public static Order getOrder(int orderId) throws SQLException
    {

        Order order = null;
        try
        {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `order` WHERE `idorder`=?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {

                int id = rs.getInt("idorder");
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
                String custEmail = rs.getString("email");
                String comment = rs.getString("comment");
                String status = rs.getString("status");
                order = new Order(id, length, width, inclination, roofMaterial, shed, name, address, zipCode, phoneNumber, custEmail, price, status);
                order.setComment(comment);
                order.setShedLength(shedLength);
                order.setShedWidth(shedWidth);
            }
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
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

            while (rs.next())
            {
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
    public static void submitOrder(Order order) throws OrderException
    {
        try
        {
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
            ResultSet ids = ps.getGeneratedKeys();
            int id = ids.getInt(1);
            order.setIdOrder(id);
        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new OrderException(ex.getMessage());
        }
    }

    //Make it so String status is a dropdown menu with different choices for update
    public static void updateOrderStatus(Order order) throws LoginSampleException
    {
        try
        {
            //TODO Maybe add more variables that can be changed
            Connection con = Connector.connection();
            String SQL = "UPDATE `order` SET `status`=? WHERE `idorder`=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, order.getStatus());
            ps.setInt(2, order.getIdOrder());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static void createLineItem(LineItem lineItem) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `lineitem` (material_idmaterial, order_idorder, length, qty, unit, description_use) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, lineItem.getIdmaterial());
            ps.setInt(2, lineItem.getIdorder());
            ps.setInt(3, lineItem.getLength());
            ps.setInt(4, lineItem.getQty());
            ps.setString(5, lineItem.getUnit());
            ps.setString(6, lineItem.getDescriptionUse());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            int id = ids.getInt(1);
            lineItem.setIdorder(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }

    }

    //TODO use sebbe and emils BoM class
    public LineItem getLineItems(int idOrder) throws SQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `lineitem` WHERE order_idorder= ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idOrder);
            LineItem lineItem = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int length = rs.getInt("length");
                int qty = rs.getInt("qty");
                String unit = rs.getString("unit");
                String descriptionUse = rs.getString("description_use");
                int orderId = rs.getInt("order_idorder");
                int materialId = rs.getInt("material_idmaterial");
            }
            return lineItem;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }

    }
}
