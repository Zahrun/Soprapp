package webapp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webapp.business.DataService;

/**
 * Example of a Servlet able to retrieve data from the client side (html form)
 * and to insert it into a Database available at server side.
 * A list of all the available data will be sent back within the response to the client side
 * @author ernestoexposito
 */
@WebServlet(name = "HelloData", urlPatterns = {"/HelloData"})
public class HelloData extends HttpServlet {

    /**
     * the Servlet requires access to the database service
     */
    protected static DataService ds = getDataService();

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // retrieving the parameters received from the client via the HTTP request
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloData</title>");
            out.println("</head>");
            out.println("<body>");
            // for now, only showing back the received parameters
            out.println("<h1>The name is " + name + " and the age is " + age + "</h1>");
             //  now the data is inserted
            out.println("<h2>Inserting data ....");
            String result = getDataService().insertData(name, age);
            //  and all the data within the database is retrieved and sent back
            out.println(result + "</h2>");
            out.println("<h2>The current available data within the database is:</br>"
                    + getDataService().selectData() + "</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Retrieves a DataService instance
     *
     * @return DataService instance
     */
    protected static DataService getDataService() {
        if (ds == null) {
            ds = DataService.getInstance();
        }
        return ds;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "able to retrieve form parameters from the client side "+
                "and insert them into a Database at server side";
    }// </editor-fold>

    /**
     * Servlet destruction and data service shutdown
     */
    @Override
    public void destroy() {
        super.destroy();
        ds.shutdown();
        ds = null;
    }
}
