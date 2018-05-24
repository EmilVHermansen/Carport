package FunctionLayer;

import DBAccess.Connector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author emilv
 */
public class DataAccessObjectTest
{
//    Test date in the UsersTest table
//    INSERT INTO `UsersTest` VALUES 
//    (1,'jens@somewhere.com','jensen','customer'),
//    (2,'ken@somewhere.com','kensen','customer'),
//    (3,'robin@somewhere.com','batman','employee'),
//    (4,'someone@nowhere.com','sesam','customer');

    private static Connection testConnection;
    private static String USER = "carportusertest";
    private static String USERPW = "monner";
    private static String DBNAME = "CarportTest";
    private static String HOST = "138.68.68.197";

    @Before
    public void setUp()
    {
        try
        {
            // awoid making a new connection for each test
            if (testConnection == null)
            {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                Connector.setConnection(testConnection);
            }
            // reset test database
            try (Statement stmt = testConnection.createStatement())
            {
                stmt.execute("drop table if exists material");
                stmt.execute("drop table if exists `order`");
                stmt.execute("drop table if exists `user`");
                
                stmt.execute("create table material like materialTest");
                stmt.execute("create table `order` like orderTest");
                stmt.execute("create table `user` like userTest");
                
                stmt.execute("insert into material select * from materialTest");
                stmt.execute("insert into `order` select * from orderTest");
                stmt.execute("insert into `user` select * from userTest");
                
            }

        } catch (ClassNotFoundException | SQLException ex)
        {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    public void testSetUpOK()
    {
        // Just check that we have a connection.
        assertNotNull(testConnection);
    }

    @Test
    public void testLogin01() throws LoginSampleException
    {
        // Can we log in
        User user = LogicFacade.login("a01", "admin");
        assertTrue(user != null);
    }

    @Test(expected = LoginSampleException.class)
    public void testLogin02() throws LoginSampleException
    {
        // We should get an exception if we use the wrong password
        User user = LogicFacade.login("a01", "adminugabuga");
        assertTrue(user != null);
    }
    
    
}
