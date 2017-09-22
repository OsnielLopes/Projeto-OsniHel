package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.*;

public class ContaDaoRelacional implements ContaDaoInterface {
    
    private PreparedStatement stmObterTodos;
    private PreparedStatement stmInserir;
    private PreparedStatement stmApagar;
    private PreparedStatement stmAtualizar;

    public ContaDaoRelacional(ConexaoInterface conexao) throws ConexaoException, BancoDaoException {
        Connection connection = conexao.getConnection();
        String sql = "";
        try {
            sql = "SELECT nro_conta, saldo FROM contas";
            stmObterTodos = connection.prepareStatement(sql);
            sql = "INSERT INTO contas VALUES (?,?)";
            stmInserir = connection.prepareStatement(sql);
            sql = "DELETE FROM contas WHERE nro_conta=?";
            stmApagar = connection.prepareStatement(sql);
            sql = "UPDATE contas SET saldo=? WHERE nro_conta=?";
            stmAtualizar = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            throw new BancoDaoException("Erro ao preparar sentença SQL: " + sql);
        }
    }
    
    @Override
    public int atualizar(Conta conta) throws BancoDaoException {
        int ret = -1;
        try {
            stmAtualizar.setBigDecimal(1, conta.getSaldo());
            stmAtualizar.setLong(2, conta.getNumero());
            ret = stmAtualizar.executeUpdate();
        } catch(SQLException ex) {
            throw new BancoDaoException("Erro ao atualizar!");
        }
        return ret;
    }
    
    @Override
    public int apagar(long numero) throws BancoDaoException {
        int ret = -1;
        try {
            stmApagar.setLong(1,numero);
            ret = stmApagar.executeUpdate();
        } catch (SQLException ex) {
            throw new BancoDaoException("Erro ao apagar!");
        }
        return ret;
    }
    
    @Override
    public int inserir(Conta novaConta) throws BancoDaoException {
        int ret = -1;
        try {
            stmInserir.setLong(1, novaConta.getNumero());
            stmInserir.setBigDecimal(2, novaConta.getSaldo());
            ret = stmInserir.executeUpdate();
        } catch (SQLException ex) {
            out.println(ex.getMessage());
            throw new BancoDaoException("Erro na operação de inserir nova conta!");
        }
        return ret;
    }
    
    @Override
    public List<Conta> obterTodos() throws BancoDaoException {
        List<Conta> contas = new ArrayList<>();
        try {
            ResultSet resultados = stmObterTodos.executeQuery();
            while (resultados.next()) {
                Conta c = new Conta(resultados.getLong("nro_conta"),
				    resultados.getBigDecimal("saldo"));
                contas.add(c);
            }
        } catch (SQLException ex) {
            throw new BancoDaoException("Erro ao executar consulta de todas as contas");
        }
        return contas;
    }
}