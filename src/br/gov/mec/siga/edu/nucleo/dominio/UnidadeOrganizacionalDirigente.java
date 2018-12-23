package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries( {
		@NamedQuery(name = "UnidadeOrganizacionalDirigente.findAll", query = "SELECT c FROM UnidadeOrganizacionalDirigente c")
				})
public class UnidadeOrganizacionalDirigente extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.DATE)
	private Date dataInicioMandato;
	@Temporal(TemporalType.DATE)
	private Date dataFimMandato;
	
	@ManyToOne
	@JoinColumn(name = "id_dirigente")
	private Dirigente dirigente;
	
	@ManyToOne
	@JoinColumn(name = "id_unidade_organizacional")
	private UnidadeOrganizacional unidadeOrganizacional;
	
	private String portaria;

	public UnidadeOrganizacionalDirigente() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public Date getDataInicioMandato() {
		return dataInicioMandato;
	}
	public void setDataInicioMandato(Date dataInicioMandato) {
		this.dataInicioMandato = dataInicioMandato;
	}
	public Date getDataFimMandato() {
		return dataFimMandato;
	}
	public void setDataFimMandato(Date dataFimMandato) {
		this.dataFimMandato = dataFimMandato;
	}
	
	public Dirigente getDirigente() {
		return dirigente;
	}
	public void setDirigente(Dirigente dirigente) {
		this.dirigente = dirigente;
	}
	
	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}
	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}
	public String getPortaria() {
		return portaria;
	}
	public void setPortaria(String portaria) {
		this.portaria = portaria;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataInicioMandato) + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataFimMandato) + ObjetoSIEP.SEPARADOR +
		this.dirigente.getId() + ObjetoSIEP.SEPARADOR +
		this.unidadeOrganizacional.getId() + ObjetoSIEP.SEPARADOR +
		this.portaria + 
		ObjetoSIEP.FINALIZADOR;
	}
	
}