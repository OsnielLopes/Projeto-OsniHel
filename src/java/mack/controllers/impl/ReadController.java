package mack.controllers.impl;

import dao.BancoDaoException;
import dao.ConexaoException;
import dao.ConexaoInterface;
import dao.ConexaoJavaDb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mack.controllers.AbstractController;
import dao.ContaDaoRelacional;

public class ReadController extends AbstractController {

    @Override
    public void execute() {
        try {
            ConexaoInterface conexao = new ConexaoJavaDb("localhost",1527,"app","123","sistema_bancario");
            ContaDaoRelacional contaDaoRelacional = new ContaDaoRelacional(conexao);    
            List contas = contaDaoRelacional.obterTodos();
            this.setReturnPage("/read.jsp");
            this.getRequest().setAttribute("lista_contas", contas);
        } catch (BancoDaoException | ConexaoException ex) {
            Logger.getLogger(ReadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
