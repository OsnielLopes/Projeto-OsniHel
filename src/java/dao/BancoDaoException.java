package dao;

public class BancoDaoException extends Exception {
    private String mensagem;
    private int erro;

    public BancoDaoException() {
    }

    public BancoDaoException(String mensagem) {
        this.mensagem = mensagem;
        this.erro = 0;
    }

    BancoDaoException(String erro_na_operação_de_inserir_nova_conta, int errorCode) {
        this.mensagem = erro_na_operação_de_inserir_nova_conta;
        this.erro = errorCode;
    }

    public String getMensagem() {
        return mensagem;
    }
    
    public int getNumErro(){
        return this.erro;
    }
    
}
