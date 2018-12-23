package org.siepgerencial.extrator.validador.comparadores;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestarPermissaoBanco {

    private Connection conexao;
    private ResultSet rs;
    private Statement st;
    private String mensagem = "";

    public String TestarPermissaoBanco(String driver, String url, String nomeBanco, String usuario, String senha, String view) {

        try {
            Class.forName(driver); 
            conexao = DriverManager.getConnection(url, usuario, senha);
            conexao.setCatalog(nomeBanco);
            st = conexao.createStatement();
            rs = st.executeQuery("select * from " + view); 
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 208) {
                this.mensagem = "A view " + view + " não existe na Base de dados informada";
            } else if (ex.getErrorCode() == 229) {
               this.mensagem = "O usuario " + usuario + " não tem permissão para acesso aos dados da view " + view;
            } else if (ex.getErrorCode() == 18456) {
               this.mensagem = "O usuario " + usuario + " não está cadastrado na base de dados";
            } else {
                this.mensagem = "CODIGO: " + ex.getErrorCode() + "\nMESSAGE: " + ex.getMessage();
            }
        } catch (ClassNotFoundException e) {
            this.mensagem = e.getMessage();
        }
        if(this.mensagem.equals("")){
            this.mensagem = "ok";
        }
        
        return this.mensagem;
    }
}