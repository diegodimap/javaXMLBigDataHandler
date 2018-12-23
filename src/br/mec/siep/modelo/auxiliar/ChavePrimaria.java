package br.mec.siep.modelo.auxiliar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Esta classe representa uma chave primária de uma tabela no banco de dados.
 * Ela extende a classe Chave e herda seus atributos.
 */
@Entity
public class ChavePrimaria extends Chave implements Serializable{
    @Id
    @Column(insertable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    /**
     * Construtor padrão que chama o construtor da classe pai.
     */
    public ChavePrimaria(){
        super();
    }

    /**
     * @return a variável id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Configura a variável id com o valor do parâmetro recebido.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
