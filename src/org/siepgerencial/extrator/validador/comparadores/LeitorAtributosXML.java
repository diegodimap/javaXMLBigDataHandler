package org.siepgerencial.extrator.validador.comparadores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorAtributosXML {
    ArrayList<String> tipos = null;

    public ArrayList lerAtributosArquivo(String caminho) {
        ArrayList<String> atributos = new ArrayList<String>();
        tipos = new ArrayList<String>();
        
        String str, att = "";
        File arquivo = new File(caminho);
        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            while ((str = in.readLine()) != null) {
                if(str.startsWith("<atribute ")){
                    att = str.substring(str.indexOf(">")+1,str.length()-11);
                    if(!att.equalsIgnoreCase("serialVersionUID")){
                        atributos.add(att);
                        tipos.add(str.substring(str.indexOf("=")+2, str.indexOf(">")-1));
                    }
                }
            }
            
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return atributos;
    }

    public ArrayList<String> getTipos() {
        ArrayList<String> temp = tipos; 
        tipos = new ArrayList<String>();
        for (String string : temp) {
            tipos.add(string.substring(string.lastIndexOf('.')+1, string.length()));
        }
        
        return tipos;
    }
    
}
