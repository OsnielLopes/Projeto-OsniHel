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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import mack.controllers.AbstractController;

public class SearchController extends AbstractController{

    @Override
    public void execute() {
        HttpServletRequest request = this.getRequest();

        Long conta = Long.parseLong((String) request.getParameter("conta"));

        ConexaoInterface conexao = new ConexaoJavaDb("localhost", 1527, "app", "123", "sistema_bancario");
        ContaDaoRelacional contaDaoRelacional;
        Conta c = new Conta();
        try {
            contaDaoRelacional = new ContaDaoRelacional(conexao);
            c = contaDaoRelacional.buscar(conta);
        } catch (ConexaoException | BancoDaoException ex) {
            Logger.getLogger(DeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("conta", String.valueOf(c.getNumero()));
        request.setAttribute("saldo", String.valueOf(c.getSaldo()));
        
        this.setReturnPage("/confirmaBusca.jsp");
    }
    
}
