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
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import mack.controllers.AbstractController;

/**
 *
 * @author osniellopesteixeira
 */
public class UpdateController extends AbstractController {

    @Override
    public void execute() {
        HttpServletRequest request = this.getRequest();
        String sSaldo = (String) request.getParameter("saldo");
        Long conta = Long.parseLong(request.getParameter("conta"));
        BigDecimal saldo = new BigDecimal(sSaldo);

        ConexaoInterface conexao = new ConexaoJavaDb("localhost", 1527, "app", "123", "sistema_bancario");
        ContaDaoRelacional contaDaoRelacional;
        
        int situacao = -1;
        try {
            contaDaoRelacional = new ContaDaoRelacional(conexao);
            Conta novaConta = new Conta(conta, saldo);
            situacao = contaDaoRelacional.atualizar(novaConta);
        } catch (ConexaoException | BancoDaoException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("situacao", situacao);
        this.setReturnPage("/confirmaUpdate.jsp");
        
    }

}
