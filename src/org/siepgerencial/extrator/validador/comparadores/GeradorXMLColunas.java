package org.siepgerencial.extrator.validador.comparadores;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GeradorXMLColunas {

    private Connection conexao;
    private ArrayList<String> colunas = new ArrayList<String>();
    private ArrayList<String> tipos = new ArrayList<String>();

    public ArrayList<String> getColunas() {
        return colunas;
    }

    public void setColunas(ArrayList<String> colunas) {
        this.colunas = colunas;
    }

    public ArrayList<String> getTipos() {
        return tipos;
    }

    public void setTipos(ArrayList<String> tipos) {
        this.tipos = tipos;
    }
    
    public GeradorXMLColunas(String driver, String urlBanco, String nomeBanco, String usuario, String senha) {
        try {
            Class.forName(driver); //"net.sourceforge.jtds.jdbc.Driver"
            String url = urlBanco; //"jdbc:jtds:sqlserver://localhost:1433";
            conexao = DriverManager.getConnection(urlBanco, usuario, senha); //getConnection(url, "sa", "extrator")
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getMessage();
            System.out.println("Erro ao conectar com banco!!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String lerColunasView(String tabela, String nomeBanco) {
        ResultSetMetaData resultMetaData = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            this.conexao.setCatalog(nomeBanco);
            statement = this.conexao.createStatement();
            resultSet = statement.executeQuery("select * from " + tabela);
            resultMetaData = resultSet.getMetaData();

            String texto = "<colunas>\n";
            for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                String tipoColuna = resultMetaData.getColumnTypeName(i);
                if(tipoColuna.contains(" ")){
                    tipoColuna = tipoColuna.substring(0, tipoColuna.indexOf(" "));
                }
                texto += "<coluna>\n" +
                        "<name>" + resultMetaData.getColumnName(i) + "</name> \n" +
                        "<type>" + tipoColuna + "</type>\n" +
                        "</coluna>\n";
                colunas.add(resultMetaData.getColumnName(i));
                tipos.add(resultMetaData.getColumnClassName(i));
            }
            texto += "</colunas>";

            resultSet.close();
            
            return texto;
        } catch (SQLException umaException) {
            umaException.printStackTrace();
        }
        
        return null;
    }
    
    public void escreverNoArquivo(String caminho, String texto) {
        try {
            File arquivo = new File(caminho);
            PrintWriter pw = new PrintWriter(arquivo);
            pw.write(texto);
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
