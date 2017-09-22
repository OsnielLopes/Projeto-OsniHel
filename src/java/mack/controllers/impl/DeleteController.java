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
import dao.ContaDaoRelacional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import mack.controllers.AbstractController;

/**
 *
 * @author osniellopesteixeira
 */
public class DeleteController extends AbstractController {

    @Override
    public void execute() {
        HttpServletRequest request = this.getRequest();

        Long conta = Long.parseLong((String) request.getParameter("conta"));

        ConexaoInterface conexao = new ConexaoJavaDb("localhost", 1527, "app", "123", "sistema_bancario");
        ContaDaoRelacional contaDaoRelacional;
        int apagar = -1;
        try {
            contaDaoRelacional = new ContaDaoRelacional(conexao);
            apagar = contaDaoRelacional.apagar(conta);
        } catch (ConexaoException | BancoDaoException ex) {
            Logger.getLogger(DeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("situacao", apagar);
        this.setReturnPage("/confirmaDelete.jsp");
    }

}
