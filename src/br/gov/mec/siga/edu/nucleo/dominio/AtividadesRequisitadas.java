package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries( {
		@NamedQuery(name = "AtividadesRequisitadas.findAll", query = "SELECT c FROM AtividadesRequisitadas c")
		})
public class AtividadesRequisitadas extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String codigo;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "atividadeacademica_id")
	private AtividadeAcademica atividadeAcademica;

	public AtividadesRequisitadas() {
		super();	
	}
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public AtividadeAcademica getAtividadeAcademica() {
		return atividadeAcademica;
	}
	public void setAtividadeAcademica(AtividadeAcademica atividadeAcademica) {
		this.atividadeAcademica = atividadeAcademica;
	}
	
	@Transient
	public String getDados(){
		return "i"+ this.id + ObjetoSIEP.SEPARADOR +
		this.codigo + ObjetoSIEP.SEPARADOR +
		this.descricao + ObjetoSIEP.SEPARADOR +
		this.atividadeAcademica.getId()+
		ObjetoSIEP.FINALIZADOR;
	}
}
