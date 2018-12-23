package org.siepgerencial.extrator.validador.leitorMapeamento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorMapeamento {

    private ArrayList<String> tabelasJuncao = new ArrayList<String>();

    public LeitorMapeamento() {
        File local = new File("");
        this.lerMapeamentoPorLihna(local.getAbsolutePath() + "\\src\\org\\siepgerencial\\extrator\\validador\\mapeamento\\Mapeamento.xml");
    }

    public static void main(String[] args) {
        new LeitorMapeamento();
    }

    public void lerMapeamentoPorLihna(String caminho) {
        String mapeamento = "";
        String str = "";
        File arquivo = new File(caminho);
        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            while ((str = in.readLine()) != null) {
                if (str.startsWith("    <EntidadeOO")) {
                    int teste = 0;
                    String nomeOO = "";
                    String classeOO = "";
                    for (int i = 0; i < str.length(); i++) {
                        if ((str.charAt(i) + "").equalsIgnoreCase("\"")) {
                            teste++;
                        } else {
                            if (teste == 1) {
                                nomeOO += str.charAt(i);
                            } else {
                                if (teste == 3) {
                                    classeOO += str.charAt(i);
                                }
                            }
                        }
                    }
                    mapeamento += "********\nNOME_OO  : " + nomeOO + "\n";
                    mapeamento += "CLASSE_OO: " + classeOO + "\n";
                }

                if (str.startsWith("        <EntidadeER")) {
                    int teste = 0;
                    String nomeER = "";
                    String isJointTablelER = "";
                    String isPrincipalER = "";
                    for (int i = 0; i < str.length(); i++) {
                        if ((str.charAt(i) + "").equalsIgnoreCase("\"")) {
                            teste++;
                        } else {
                            if (teste == 1) {
                                nomeER += str.charAt(i);
                            } else {
                                if (teste == 3) {
                                    isJointTablelER += str.charAt(i);
                                } else {
                                    if (teste == 5) {
                                        isPrincipalER += str.charAt(i);
                                    }
                                }
                            }
                        }
                    }
                    mapeamento += "NOME_ER  : " + nomeER + "\n";
                    mapeamento += "ISJOIN_ER: " + isJointTablelER + "\n";
                    mapeamento += "ISMAIN_ER: " + isPrincipalER + "\n";

                    if(isJointTablelER.equalsIgnoreCase("true")){
                        tabelasJuncao.add(nomeER);
                    }
                }

                if (str.startsWith("            <ChavePrimaria")) {
                    mapeamento += "PKEY     : " + str.substring(str.indexOf("\"")+1, str.length()-3) + "\n";
                }

                if (str.startsWith("            <ChaveEstrangeira")) {
                    mapeamento += "FKEY     : " + str.substring(str.indexOf("\"")+1, str.length()-3) + "\n";
                }

                if (str.startsWith("    <Relacionamento")) {
                    int teste = 0;
                    String entidade1 = "";
                    String entidadeN = "";
                    String tipo = "";
                    for (int i = 0; i < str.length(); i++) {
                        if ((str.charAt(i) + "").equalsIgnoreCase("\"")) {
                            teste++;
                        } else {
                            if (teste == 1) {
                                entidade1 += str.charAt(i);
                            } else {
                                if (teste == 3) {
                                    entidadeN += str.charAt(i);
                                } else {
                                    if (teste == 5) {
                                        tipo += str.charAt(i);
                                    }
                                }
                            }
                        }
                    }
                    mapeamento += "RELACIONAMENTO: \n";
                    mapeamento += "ENTIDADE1: " + entidade1 + "\n";
                    mapeamento += "ENTIDADEN: " + entidadeN + "\n";
                    mapeamento += "TIPO     : " + tipo + "\n";
                }
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return mapeamento;
    }

    public ArrayList<String> getTabelasJuncao() {
        return tabelasJuncao;
    }


}
