package br.gov.mec.siga.edu.nucleo.dominio;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Antonio Lucas
 * @author Tiago Trocoli
 * @author Sandala Barbosa
 * @author Núcleo SIGA CEFET-BA
 * @since 02/06/08
 * 
 * {@literal CDU's 073}
 */ 

@Entity
@Table(name="etapaavaliacao")
@NamedQueries({
		@NamedQuery(name="ListaTodasEtapas", query = "SELECT e FROM EtapaAvaliacao e order by e.nome"),
		@NamedQuery(name="obterEtapaPorNomeExato",query = "SELECT e FROM EtapaAvaliacao e WHERE e.nome = :nome order by e.id"),
		@NamedQuery(name="obterUltimaEtapa",query = "SELECT e FROM EtapaAvaliacao e WHERE e.id = (SELECT MAX(e1.id) FROM EtapaAvaliacao e1)")
	})

public class EtapaAvaliacao extends ObjetoSIEP implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "estruturaDeAvaliacao_id")
	private EstruturaDeAvaliacao estruturaDeAvaliacao;
	
	public EtapaAvaliacao() {
		super();
		this.nome = "";
	}
	
	
	public long getId(){
		return this.id;
	}
	
	public void setId(int umId){
		this.id = umId;
	}
	
	public String getNome(){
		return this.nome;
	}
	public void setNome(String umNome){
		this.nome = umNome;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EstruturaDeAvaliacao getEstruturaDeAvaliacao() {
		return estruturaDeAvaliacao;
	}


	public void setEstruturaDeAvaliacao(EstruturaDeAvaliacao estruturaDeAvaliacao) {
		this.estruturaDeAvaliacao = estruturaDeAvaliacao;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.nome + ObjetoSIEP.SEPARADOR +
		this.estruturaDeAvaliacao.getId()+
		ObjetoSIEP.FINALIZADOR;
}
	
}

