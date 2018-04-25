package DBAccess;

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

public class DataAccessObject {

    public static User login(String email, String password) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT idUser, role FROM User "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                int id = rs.getInt("idUser");
                User user = new User(email, password, role);
                user.setId(id);
                return user;
            } else {
                throw new LoginSampleException("Could not validate user");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static List<Order> getOrders(int id) throws OrderException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `Order` WHERE idordre = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ArrayList<Order> orders = new ArrayList();
            Order order = null;
            ResultSet rs = null;
            rs = ps.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("idordre");
                int length = rs.getInt("længde");
                int width = rs.getInt("bredde");
                String inclination = rs.getString("rejsning");
                String roofMaterial = rs.getString("tagtype");
                String shed = rs.getString("skur");
                int shedLength = rs.getInt("skur_længde");
                int shedWidth = rs.getInt("skur_bredde");
                String name = rs.getString("navn");
                String address = rs.getString("adresse");
                String zipCode = rs.getString("postnummer_by");
                String phoneNumber = rs.getString("telefon");
                String email = rs.getString("email");
                String comment = rs.getString("bemærkning");
                String status = rs.getString("status");
                order = new Order(orderId, length, width, inclination, roofMaterial, shed, name, address, zipCode, phoneNumber, email, status);
                order.setComment(comment);
                order.setShedLength(shedLength);
                order.setShedWidth(shedWidth);
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex) {
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

   
        //Adapt emils order class XD
        //Make it so String status is a dropdown menu with different choices for update
        public static void updateOrderStatus(String status, int idOrder) throws LoginSampleException{
    try {
            Connection con = Connector.connection();
            String SQL = "UPDATE `order` SET `status`=? WHERE `idorder`=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, status);
            ps.setInt(2, idOrder);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

}
