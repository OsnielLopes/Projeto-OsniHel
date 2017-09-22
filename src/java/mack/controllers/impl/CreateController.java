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
import javax.servlet.http.HttpServletRequest;
import mack.controllers.AbstractController;

/**
 *
 * @author osniellopesteixeira
 */
public class CreateController extends AbstractController {

    @Override
    public void execute() {

        HttpServletRequest request = this.getRequest();
        String sSaldo = (String) request.getParameter("saldo");
        sSaldo = sSaldo.replaceAll(",", ".");
        Long conta = Long.parseLong(request.getParameter("conta"));
        BigDecimal saldo = new BigDecimal(sSaldo);
        
        ConexaoInterface conexao = new ConexaoJavaDb("localhost", 1527, "app", "123", "sistema_bancario");
        ContaDaoRelacional contaDaoRelacional;
        int inserir = -1;
        try {
            contaDaoRelacional = new ContaDaoRelacional(conexao);
            Conta novaConta = new Conta(conta, saldo);
            inserir = contaDaoRelacional.inserir(novaConta);
        } catch (ConexaoException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BancoDaoException ex) {
            inserir = ex.getNumErro();
        }

        request.setAttribute("situacao", inserir);
        this.setReturnPage("/confirmaNovaConta.jsp");
    }

}
