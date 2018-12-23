package org.siepgerencial.extrator.validador.gui;

import org.siepgerencial.extrator.validador.comparadores.ComparadorViewEntidade;

public class ValidarViews extends javax.swing.JFrame {

    private String testeJanela = "";

    public ValidarViews(String driver, String url, String nomeBanco, String usuario, String senha, boolean geraXML) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(700, 585);
        this.setTitle("Relatório de erros do Validador de Views");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            ComparadorViewEntidade cve = new ComparadorViewEntidade();
            String erros = cve.lerArquivosPasta(driver, url, nomeBanco, usuario, senha, geraXML);
            areaErros.setText(erros);
            testeJanela = cve.getTesteJanela();

            if (!testeJanela.equals("")) {
                System.out.println(testeJanela);
                areaErros.append("Quantidade de views com erros: " + testeJanela.length());
                this.setVisible(true);
            }else{
                this.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public boolean isViewsValidas(){
        if(testeJanela.length()!=0){
            return false;
        }else{
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaErros = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 585));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Erros"));

        areaErros.setColumns(20);
        areaErros.setEditable(false);
        areaErros.setRows(5);
        jScrollPane1.setViewportView(areaErros);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ValidarViews("net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sqlserver://localhost:1433", "Academicov2.0", "sa", "diego", true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaErros;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
