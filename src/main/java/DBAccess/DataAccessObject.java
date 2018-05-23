package DBAccess;

import FunctionLayer.Customer;
import FunctionLayer.CustomerInfoError;
import FunctionLayer.LineItem;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
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

    public static Customer getCustomerInfo(int orderId) throws CustomerInfoError {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `order` "
                    + "WHERE idorder=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
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

                int length = rs.getInt("length");
                int width = rs.getInt("width");
                String inclination = rs.getString("inclination");
                int angle = rs.getInt("angle");
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
                int price = rs.getInt("price");
                int salesPrice = rs.getInt("salesprice");
                String status = rs.getString("status");
                order = new Order(orderId, length, width, inclination, angle, roofMaterial, shed, name, address, zipCode, phoneNumber, custEmail, price);
                order.setComment(comment);
                order.setShedLength(shedLength);
                order.setShedWidth(shedWidth);
                order.setStatus(status);
                order.setSalesprice(salesPrice);
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
                int price = rs.getInt("price");
                String inclination = rs.getString("inclination");
                int angle = rs.getInt("angle");
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
                order = new Order(orderId, length, width, inclination, angle, roofMaterial, shed, name, address, zipCode, phoneNumber, email, price);
                order.setComment(comment);
                order.setShedLength(shedLength);
                order.setShedWidth(shedWidth);
                order.setStatus(status);
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
            String SQL = "INSERT INTO `order` (length, width, inclination, angle, roof_material, shed, shed_length, shed_width, name, address, zipcode, phonenumber, email, comment, price, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getLength());
            ps.setInt(2, order.getWidth());
            ps.setString(3, order.getInclination());
            ps.setInt(4, order.getAngle());
            ps.setString(5, order.getRoofMaterial());
            ps.setString(6, order.getShed());
            ps.setInt(7, order.getShedLength());
            ps.setInt(8, order.getShedWidth());
            ps.setString(9, order.getName());
            ps.setString(10, order.getAddress());
            ps.setString(11, order.getZipCode());
            ps.setString(12, order.getPhoneNumber());
            ps.setString(13, order.getEmail());
            ps.setString(14, order.getComment());
            ps.setInt(15, order.getPrice());
            ps.setString(16, order.getStatus());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            order.setIdOrder(id);
        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new OrderException(ex.getMessage());
        }
    }

    //TODO Make it so String status is a dropdown menu with different choices for update
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

    public static void updateOrder(Order order, String attribute) throws LoginSampleException
    {
        try
        {
            Connection con = Connector.connection();
            String SQL = "UPDATE `order` SET `"+ attribute + "`=? WHERE `idorder`=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            
            if (attribute.equals("length"))
                ps.setInt(1, order.getLength());
            else if (attribute.equals("width"))
                ps.setInt(1, order.getWidth());
            else if (attribute.equals("inclination"))
                ps.setString(1, order.getInclination());
            else if (attribute.equals("angle"))
                ps.setInt(1, order.getAngle());
            else if (attribute.equals("roof_material"))
                ps.setString(1, order.getRoofMaterial());
            else if (attribute.equals("shed"))
                ps.setString(1, order.getShed());
            else if (attribute.equals("shed_length"))
                ps.setInt(1, order.getShedLength());
            else if (attribute.equals("shed_width"))
                ps.setInt(1, order.getShedWidth());
            else if (attribute.equals("comment"))
                ps.setString(1, order.getComment());
            else if (attribute.equals("salesprice"))
                ps.setInt(1, order.getSalesprice());
            else if (attribute.equals("status"))
                ps.setString(1, order.getStatus());
            
            ps.setInt(2, order.getIdOrder());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new LoginSampleException(ex.getMessage());
        }
    }
    
    
    // TODO Maybe
//    public static void createLineItem(LineItem lineItem) throws SQLException {
//        try {
//            Connection con = Connector.connection();
//            String SQL = "INSERT INTO `lineitem` (material_idmaterial, order_idorder, length, qty, unit, description_use) "
//                    + "VALUES (?, ?, ?, ?, ?, ?)";
//            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, lineItem.getIdmaterial());
//            ps.setInt(2, lineItem.getIdorder());
//            ps.setInt(3, lineItem.getLength());
//            ps.setInt(4, lineItem.getQty());
//            ps.setString(5, lineItem.getUnit());
//            ps.setString(6, lineItem.getDescriptionUse());
//            ps.executeUpdate();
//            ResultSet ids = ps.getGeneratedKeys();
//            ids.next();
//            int id = ids.getInt(1);
//            lineItem.setIdorder(id);
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new SQLException(ex.getMessage());
//        }
//
//    }

    //TODO use sebbe and emils BoM class
//    public LineItem getLineItems(int idOrder) throws SQLException {
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT * FROM `lineitem` WHERE order_idorder= ?";
//            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, idOrder);
//            LineItem lineItem = null;
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                int length = rs.getInt("length");
//                int qty = rs.getInt("qty");
//                String unit = rs.getString("unit");
//                String descriptionUse = rs.getString("description_use");
//                int orderId = rs.getInt("order_idorder");
//                int materialId = rs.getInt("material_idmaterial");
//            }
//            return lineItem;
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new SQLException(ex.getMessage());
//        }
//
//    }
    
    public static Material getMaterial(int idMaterial) throws SQLException
    {
        
        Material material = null;
        try
        {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `material` WHERE `idmaterial`=?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, idMaterial);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {

                int id = rs.getInt("idmaterial");
                String name = rs.getString("name");
                int MSRP = rs.getInt("MSRP");
                material = new Material(id, name, MSRP);
            }
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return material;
    }

}
