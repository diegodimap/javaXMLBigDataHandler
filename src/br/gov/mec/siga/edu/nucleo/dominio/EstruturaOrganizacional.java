package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="estrutura_organizacional")
@NamedQueries( { 
	@NamedQuery(name = "EstruturaOrganizacional.findAll", query = "SELECT e FROM EstruturaOrganizacional e"),
	@NamedQuery(name = "EstruturaOrganizacional.findByPublicacaoDO", query = "SELECT e FROM EstruturaOrganizacional e WHERE e.publicacao_DO = :publicacao_DO"),
	@NamedQuery(name = "EstruturaOrganizacional.findByIdUnidadeEnsino", query = "SELECT e FROM EstruturaOrganizacional e WHERE e.unidadeEnsino.id = :idUnidadeEnsino")
	})
public class EstruturaOrganizacional extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	private Date publicacao_DO;

	@ManyToOne
	@JoinColumn(name = "unidadeEnsino_id")
	private UnidadeEnsino unidadeEnsino;
	
	@OneToMany(mappedBy="estruturaOrganizacional",cascade={CascadeType.MERGE})
	private List<UnidadeOrganizacional> unidadeOrganizacional;
	
	public EstruturaOrganizacional() {
		super();
		unidadeOrganizacional = new ArrayList<UnidadeOrganizacional>();
	}
	
	public List<UnidadeOrganizacional> getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			List<UnidadeOrganizacional> unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	
	public long getId() {
		return this.id;
	}
	

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getPublicacao_DO() {
		return this.publicacao_DO;
	}

	public void setPublicacao_DO(Date publicacao_DO) {
		this.publicacao_DO = publicacao_DO;
	}

	public UnidadeEnsino getUnidadeEnsino() {
		return this.unidadeEnsino;
	}

	public void setUnidadeEnsino(UnidadeEnsino unidadeEnsino) {
		this.unidadeEnsino = unidadeEnsino;
	}

		
	public void addUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional){
		this.unidadeOrganizacional.add(unidadeOrganizacional);
	}
	
	public void removeUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional){
		this.unidadeOrganizacional.remove(unidadeOrganizacional);
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataInicio) + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataFim) + ObjetoSIEP.SEPARADOR +
		formatarData(this.publicacao_DO) + ObjetoSIEP.SEPARADOR +
		this.unidadeEnsino.getId() +
		ObjetoSIEP.FINALIZADOR;
	}

}
