package dao;

import java.util.List;

public interface TitularDaoInterface {
    List<Titular> obterTodos() throws BancoDaoException;
    
    // outras operações
}
