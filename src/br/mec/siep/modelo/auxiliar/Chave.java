package br.mec.siep.modelo.auxiliar;

import javax.persistence.MappedSuperclass;

/**
 * Classe que representa a Chave e possui métodos para configuração e
 * recuperação de seus dados.
 */
@MappedSuperclass
public class Chave {

    private String nome;
    private String tipo;
    private String valor;

    /**
     * Construtor padrão vazio.
     */
    public Chave() { }

    /**
     * @return a variável nome.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Configura a variável nome com o valor do parâmetro recebido.
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return a variável valor.
     */
    public String getValor() {
        return this.valor;
    }

    /**
     * Configura a variável valor com o conteúdo do parâmetro recebido.
     * @param valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return a variável tipo.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Configura a variável tipo com o valor do parâmetro recebido.
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
