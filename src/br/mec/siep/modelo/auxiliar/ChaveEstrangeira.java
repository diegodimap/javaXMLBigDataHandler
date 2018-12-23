package br.mec.siep.modelo.auxiliar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Esta classe representa a ChaveEstrangeira de uma tabela no banco e
 * extende a Chave normal herdando seus atributos.
 */
@Entity
public class ChaveEstrangeira extends Chave implements Serializable{
    @Id
    @Column(insertable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    public String referencia;

    /**
     * Construtor padrão que chama o construtor da classe pai.
     */
    public ChaveEstrangeira(){
        super();
    }

    /**
     * @return a variável referencia.
     */
    public String getReferencia() {
        return this.referencia;
    }

    /**
     * Configura a variável referência com o valor do parâmetro recebido.
     * @param referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
