package org.siepgerencial.extrator.validador.comparadores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LeitorColunaXML {

    private ArrayList<String> colunas = new ArrayList<String>();
    private ArrayList<String> tipos = new ArrayList<String>();
    private String erros = "";

    public ArrayList lerColunas(String caminho) {
        String str, texto = "";
        File arquivo = new File(caminho);

        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            while ((str = in.readLine()) != null) {
                texto += str;
                if (str.startsWith("<name>")) {
                    colunas.add(str.substring(6, str.length() - 8));
                }

                if (str.startsWith("<type>")) {
                    tipos.add(str.substring(6, str.length() - 7));
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("ERRO AO LER ARQUIVO NO LEITOR XML COLUNAS");
            System.out.println("CAMINHO: " + caminho);
        }
        return colunas;
    }

    public String getErros() {
        return erros;
    }

    public ArrayList<String> getTipos() {
        ArrayList<String> temp = tipos;
        tipos = new ArrayList<String>();
        for (String string : temp) {
            tipos.add(string.substring(string.lastIndexOf('.') + 1, string.length()));
        }

        return tipos;
    }
}
