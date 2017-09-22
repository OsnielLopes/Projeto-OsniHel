package mack.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mack.controllers.Controller;
import mack.controllers.ControllerFactory;

public class FrontControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String controller = request.getParameter("control");
            String returnPage;
            switch(controller){
                case "novaContaForm":
                    returnPage = "/novaContaForm.html";
                    break;
                case "atualizaContaForm":
                    returnPage = "/atualizaContaForm.html";
                    break;
                case "apagarContaForm":
                    returnPage = "/apagarContaForm.html";
                    break;
                default:
                    Controller control = ControllerFactory.getControllerByFullClassName(controller);
                    control.init(request);
                    control.execute();
                    returnPage = control.getReturnPage();
            }
            RequestDispatcher requestDispatcher
                    = getServletContext().getRequestDispatcher(returnPage);
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            out.println(e.getMessage());
        } finally {
            out.close();
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
