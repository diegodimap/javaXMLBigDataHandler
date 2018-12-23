package br.mec.siep.modelo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public class GeradorXMLEntidadeSIEP {

    private String PACOTE_SIEP = "br.mec.siep.modelo.";
    private String CAMINHO_SIEP = "\\src\\br\\mec\\siep\\modelo\\";

    public GeradorXMLEntidadeSIEP() {
        String local = new File("").getAbsolutePath() + CAMINHO_SIEP; // = JOptionPane.showInputDialog(null, "Digite o diretório das entidades", "Diretório", JOptionPane.QUESTION_MESSAGE);

        this.lerArquivosPasta(local);
    }

    public static void main(String args[]) {
        new GeradorXMLEntidadeSIEP();
    }

    public void lerArquivosPasta(String caminho) {
        File f = new File(caminho);
        //System.out.println("CAMINHO: " + caminho);
        File arquivos[] = f.listFiles();
        for (File file : arquivos) {
        	String nome = file.getName();
            if (nome.length() > 5) {
                if (nome.subSequence(nome.length() - 5, nome.length()).equals(".java")) { //só .java
                	if(!(nome.equals("ObjetoSIEP.java")) && !(nome.equals("GeradorXMLEntidadeSIEP.java"))){
	                    nome = (String) file.getName().subSequence(0, file.getName().length() - 5);
	                    //System.out.println("NOME: " + nome);
	                    String texto = this.lerEntidadeEscreverXMLAtributos(nome);
	                    this.escreverNoArquivo(new File("").getAbsolutePath() + "/ENTIDADES_XML/" + nome + ".xml", texto);
                	}
                }
            }
        }
    }

    public String lerEntidadeEscreverXMLAtributos(String nome) {
        try {
            String texto = "";
            String nomeClasse = "";
            Class cls = Class.forName(PACOTE_SIEP + nome);
            Field fieldlist[] = cls.getDeclaredFields();

            for (int i = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                nomeClasse = fld.getName(); //.substring(fld.getName().lastIndexOf("."), fld.getName().length());
                String tipo = fld.getType() + "";
                String fimTipo = tipo.substring(tipo.lastIndexOf(".")+1, tipo.length());
                if((!fimTipo.equalsIgnoreCase("Collection")) && (!fimTipo.equalsIgnoreCase("Set") && !fimTipo.equalsIgnoreCase("List")) && (!nomeClasse.equals("serialVersionUID"))){
                    texto += "<atribute type=\"" + fld.getType() + "\">" + nomeClasse + "</atribute>\n";
                }else{
                    //DO NOTHING
                }
            }

            texto = "<atributes>\n" + texto + "</atributes>";
            texto = "<name>" + cls.getName() + "</name> \n" + texto;
            texto = "<entidade>\n" + texto + "\n</entidade>";

            return texto;
        } catch (Exception e) {
            e.printStackTrace();
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
