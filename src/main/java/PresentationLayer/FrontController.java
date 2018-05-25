package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.MaterialException;
import FunctionLayer.OrderException;
import FunctionLayer.SubmitOrderException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns =
{
    "/FrontController"
})
public class FrontController extends HttpServlet
{
    private final static Logger logger = Logger.getLogger(PresentationLayer.FrontController.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            Command action = Command.from(request);
            String view = action.execute(request, response);
            request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
        } catch (LoginSampleException ex)
        {
            request.setAttribute("error", ex.getMessage());
            LogicFacade.log(logger, ex);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        } catch (OrderException ex)
        {
            request.setAttribute("error", ex.getMessage());
            LogicFacade.log(logger, ex);
            request.getRequestDispatcher("/WEB-INF/browseorders.jsp").forward(request, response);
        } catch (MaterialException ex)
        {
            request.setAttribute("error", ex.getMessage());
            LogicFacade.log(logger, ex);
            request.getRequestDispatcher("/WEB-INF/orderedit.jsp").forward(request, response);
            
        } catch (SubmitOrderException ex)
        {
            request.setAttribute("error", ex.getMessage());
            LogicFacade.log(logger, ex);
            request.getRequestDispatcher("/WEB-INF/submitorder.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws CustomerInfoError if the customer does not exist
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws CustomerInfoError if the customer does not exist
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
