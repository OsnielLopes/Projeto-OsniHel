/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mack.controllers.impl;

import dao.BancoDaoException;
import dao.ConexaoException;
import dao.ConexaoInterface;
import dao.ConexaoJavaDb;
import dao.Conta;
import dao.ContaDaoRelacional;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author osniellopesteixeira
 */
public class UpdateController extends HttpServlet {

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
            throws ServletException, IOException, ConexaoException, BancoDaoException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Long conta = (Long) request.getAttribute("conta");
            BigDecimal saldo = (BigDecimal) request.getAttribute("saldo");
            
            ConexaoInterface conexao = new ConexaoJavaDb("localhost",1527,"app","123","sistema_bancario");
            ContaDaoRelacional contaDaoRelacional = new ContaDaoRelacional(conexao);
            
            Conta novaConta = new Conta(conta, saldo);
            int atualizar = contaDaoRelacional.atualizar(novaConta);
            request.setAttribute("situacao", atualizar);
            
            RequestDispatcher requestDispatcher
                    = getServletContext().getRequestDispatcher("confirmaUpdate.jsp");
            requestDispatcher.forward(request, response);
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
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ConexaoException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BancoDaoException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ConexaoException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BancoDaoException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
