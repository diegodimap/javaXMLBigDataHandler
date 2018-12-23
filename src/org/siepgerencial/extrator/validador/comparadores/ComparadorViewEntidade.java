package org.siepgerencial.extrator.validador.comparadores;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.siepgerencial.extrator.validador.leitorMapeamento.LeitorMapeamento;
import org.siepgerencial.extrator.validador.leitorProperties.LeitorProperties;

public class ComparadorViewEntidade {

    private String conteudoView;
    private final String CAMINHO = new File("").getAbsolutePath();
    private final String CAMINHO_XML = CAMINHO + "/ENTIDADES_XML/";
    private final String CAMINHO_XML_NN = CAMINHO + "/ENTIDADES_XML_NN/";
    private final String CAMINHO_COLUNAS = CAMINHO + "/ENTIDADES_COLUNAS/";
    private final String CAMINHO_COLUNAS_NN = CAMINHO + "/ENTIDADES_COLUNAS_NN/";
    private ArrayList<String> nomes = new ArrayList<String>();
    private ArrayList<String> atributos = new ArrayList<String>();
    private ArrayList<String> tiposAtributos = new ArrayList<String>();
    private ArrayList<String> colunas = new ArrayList<String>();
    private ArrayList<String> colunasBanco = new ArrayList<String>();
    private ArrayList<String> tiposColunasBanco = new ArrayList<String>();
    private ArrayList<String> colunasNN = new ArrayList<String>();
    private ArrayList<String> tiposColunasNN = new ArrayList<String>();
    private ArrayList<String> attNN = new ArrayList<String>();
    private ArrayList<String> tiposAttNN = new ArrayList<String>();
    private boolean viewValida = true;
    private String DRIVER = "";
    private String URL = "";
    private String NOME_BANCO = "";
    private String USUARIO = "";
    private String SENHA = "";
    private String VIEW = "";
    private String testeJanela = ""; //se for vazio não há erros e a janela nem aparece.
    private String erros = "";
    private String tipoBanco = "";

    public ComparadorViewEntidade() {
        //super();
    }

    public ComparadorViewEntidade(String driver, String url, String nomeBanco, String usuario, String senha, boolean geraXML) {
        this.lerArquivosPasta(driver, url, nomeBanco, usuario, senha, geraXML);
    }

    public String lerArquivosPasta(String driver, String url, String nomeBanco, String usuario, String senha, boolean geraXML) {
        this.DRIVER = driver;
        this.URL = url;
        this.NOME_BANCO = nomeBanco;
        this.USUARIO = usuario;
        this.SENHA = senha;
        this.erros = "";

        String caminho = "";
        File f = new File(caminho + "/" + CAMINHO_XML);
        File arquivos[] = f.listFiles();

        for (File file : arquivos) {
            String nome = (String) file.getName().subSequence(0, file.getName().length() - 4);
            nomes.add(nome);
        }

        for (File file : arquivos) {
            viewValida = true;
            atributos = new ArrayList<String>();
            colunas = new ArrayList<String>();
            colunasBanco = new ArrayList<String>();

            String nome = (String) file.getName().subSequence(0, file.getName().length() - 4);

            if (!nome.equals("")) { //arquivo .SVN oculto
                this.lerAtributosXML(CAMINHO_XML + nome + ".xml");

                if (geraXML) {
                    //testa a permissão do usuário para cada view
                    VIEW = "SIEP_" + nome;
                    TestarPermissaoBanco tpb = new TestarPermissaoBanco();
                    String permissao = tpb.TestarPermissaoBanco(DRIVER, URL, NOME_BANCO, USUARIO, SENHA, VIEW);

                    if (!permissao.equals("ok")) {
                        erros += permissao + "\n";
                    } else {
                        //erros += "BANCO: O usuário " + USUARIO + " tem permissão para acessara view " + VIEW + " do banco." + NOME_BANCO + "\n";
                        this.lerColunasBancoEscreverXML(CAMINHO_COLUNAS + nome + ".xml", nome);
                    }
                }
                this.lerColunasXML(CAMINHO_COLUNAS + nome + ".xml");

                String temp = this.validarView(nome);

                if (temp.equals("")) {
                    erros += "\nA View " + nome + " é válida!\n\n";
                } else {
                    erros += "View: " + nome + "\nErros: \n" + temp + "\n";
                    this.testeJanela += "X";
                }
            }
        }

        File apagador = new File("/" + CAMINHO_COLUNAS);
        File arquivosApagar[] = apagador.listFiles();

        for (File file : arquivosApagar) {
            file.deleteOnExit();
        }

        //N:N

        LeitorMapeamento lm = new LeitorMapeamento();
        ArrayList<String> tabelasJuncao = lm.getTabelasJuncao();

        for (String viewNN : tabelasJuncao) {
            String nome = viewNN.substring(5);
            System.out.println("\nVIEW: " + viewNN);
            //gerar XML view
            this.lerColunasBancoEscreverXML(CAMINHO_COLUNAS_NN + nome + ".xml", nome);

            //ler campos do xml do banco
            LeitorColunaXML lcxml = new LeitorColunaXML();

            this.colunasNN = lcxml.lerColunas(CAMINHO_COLUNAS_NN + nome + ".xml");
            this.tiposColunasNN = lcxml.getTipos();

            for (String string : colunasNN) {
                System.out.println("C: " + string + ", T: " + tiposColunasNN.get(colunasNN.indexOf(string)));
            }

            //ler os campos do xml da entidade

            String entidades[] = nome.split("_");

            int contadorContem = 0;
            for (String entidade : entidades) {

                System.out.println("ENTIDADE: " + entidade);
                LeitorAtributosXML leitorAtt = new LeitorAtributosXML();
                this.attNN = leitorAtt.lerAtributosArquivo(CAMINHO_XML_NN + entidade + ".xml");
                this.tiposAttNN = leitorAtt.getTipos();

                for (String atts : attNN) {
                    System.out.println("A: " + atts + ", T: " + tiposAttNN.get(attNN.indexOf(atts)));
                }

                if (attNN.contains(colunasNN.get(0))) {
                    System.out.println("Contém: " + colunasNN.get(0));
                    contadorContem++;
                } else {
                    if (attNN.contains(colunasNN.get(1))) {
                        System.out.println("Contém: " + colunasNN.get(1));
                        contadorContem++;
                    }
                }
            }

            if (tiposColunasNN.get(0).equalsIgnoreCase("numeric") && tiposColunasNN.get(1).equalsIgnoreCase("numeric")) {
                if (contadorContem == 2) {
                    System.out.println("\nTABELA DE JUNÇÃO " + viewNN + " VÁLIDA!!!\n");
                } else {
                    System.out.println("\nTABELA DE JUNÇÃO " + viewNN + " INVÁLIDA!!!\n");
                }
                System.out.println("TIPOS CORRETOS");
            } else {
                System.out.println("\nTABELA DE JUNÇÃO " + viewNN + " INVÁLIDA!!!\n");
                System.out.println("TIPOS INCORRETOS: " + tiposColunasNN.get(0) + " e " + tiposColunasNN.get(1));
            }
        }

        //N:N

        return erros;
    }

    public String getTesteJanela() {
        return this.testeJanela;
    }

    public void lerAtributosXML(String caminho) {
        LeitorAtributosXML leitorAtributos = new LeitorAtributosXML();
        atributos = leitorAtributos.lerAtributosArquivo(caminho);
        tiposAtributos = leitorAtributos.getTipos();
    }

    public void lerColunasBancoEscreverXML(String caminho, String nome) {
        nome = nome.toLowerCase();
        nome = "SIEP_" + nome;
        GeradorXMLColunas lerColunas = new GeradorXMLColunas(DRIVER, URL, NOME_BANCO, USUARIO, SENHA);
        String texto = lerColunas.lerColunasView(nome, NOME_BANCO);
        lerColunas.escreverNoArquivo(caminho, texto);
    }

    public void lerColunasXML(String caminho) {
        LeitorColunaXML lcxml = new LeitorColunaXML();
        this.colunasBanco = lcxml.lerColunas(caminho);
        this.tiposColunasBanco = lcxml.getTipos();
        erros += lcxml.getErros();
    }

    //OBS para a construção da interface gráfica: todas as saídas do validador estão dentro deste método.
    public String validarView(String nome) {
        LeitorProperties leitorProperties = new LeitorProperties();
        String erros = "";
        HashMap<String, String> atributosETipos = new HashMap<String, String>();
        HashMap<String, String> colunasETipos = new HashMap<String, String>();

        //passando pra minúsculo pois o ordenador é case sensitive
        for (int i = 0; i < tiposAtributos.size(); i++) {
            if (nomes.contains(tiposAtributos.get(i))) {
                tiposAtributos.set(i, "long");
            } else {
                tiposAtributos.set(i, tiposAtributos.get(i).toLowerCase());
            }
        }
        for (int i = 0; i < atributos.size(); i++) {
            atributos.set(i, atributos.get(i).toLowerCase());
        }

        for (int i = 0; i < colunasBanco.size(); i++) {
            colunasBanco.set(i, colunasBanco.get(i).toLowerCase());
        }


        //preenchendo os mapas
        for (int t = 0; t < atributos.size(); t++) {
            String typeAtributo = tiposAtributos.get(t);
            atributosETipos.put(atributos.get(t), leitorProperties.lerProperties(typeAtributo.toLowerCase(), leitorProperties.getBanco()));
        }

        for (int t = 0; t < colunasBanco.size(); t++) {
            colunasETipos.put(colunasBanco.get(t), tiposColunasBanco.get(t));
        }

        //ordenando
        Collections.sort(atributos);
        Collections.sort(colunasBanco);

        if (colunasBanco.size() >= atributos.size()) { //>=
            System.out.println("NOME: " + nome);

            for (int i = 0; i < atributos.size(); i++) {
                System.out.printf("%-4s %-25s %-4s %-25s %-4s %-10s %-4s %-10s %-4s %-10s %-4s %-10s\n", "AT: ", atributos.get(i), " CL: ", colunasBanco.get(i), "TAT: ", tiposAtributos.get(i), " TCL: ", tiposColunasBanco.get(i), " TATHM:", atributosETipos.get(atributos.get(i)), "TCLHM", colunasETipos.get(colunasBanco.get(i)));

                if (colunasBanco.contains(atributos.get(i))) {
                    if (!atributosETipos.get(atributos.get(i)).equalsIgnoreCase(colunasETipos.get(atributos.get(i)))) {
                        erros += "Tipos diferentes para o atributo " + atributos.get(i) + ":   " + atributosETipos.get(atributos.get(i)) + " != " + colunasETipos.get(colunasBanco.get(i)) + " \n";
                    }

                } else {
                    erros += "A view " + nome + " não possui uma coluna para o atributo " + atributos.get(i) + "\n";
                }
            }

            System.out.println("");
        } else {
            if (colunasBanco.size() == 0) {
                erros += "O arquivo da view " + nome + " não foi encontrado. (ENTIDADES_COLUNAS\\" + nome + ".xml)\n";
            } else {
                erros += "A view " + nome + " não possui colunas para todos os atributos da entidade.\n";
            }
        }

        return erros;
    }
}
