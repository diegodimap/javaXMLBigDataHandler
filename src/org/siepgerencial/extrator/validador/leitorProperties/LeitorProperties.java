package org.siepgerencial.extrator.validador.leitorProperties;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeitorProperties {
    
    private final String BANCO_PROPERTIES = "src\\org\\siepgerencial\\extrator\\validador\\leitorProperties\\banco.properties";
    private static String banco = "";

    public LeitorProperties(){
        this.banco = "src\\org\\siepgerencial\\extrator\\validador\\leitorProperties\\" + this.lerProperties("banco", BANCO_PROPERTIES);
    }

    public static String lerProperties(String chave, String arquivo) {
        Properties props = new Properties();
        File f = new File(arquivo);
        FileInputStream fis;
        try {
            fis = new FileInputStream(f);
            props.load(fis);
            fis.close();
        } catch (Exception ex) {
            Logger.getLogger(LeitorProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props.getProperty(chave);
    }

    public static String getBanco() {
        return banco;
    }
}
    