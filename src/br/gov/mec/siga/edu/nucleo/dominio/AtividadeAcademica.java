package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries( {
		@NamedQuery(name = "AtividadeAcademica.findAll", query = "SELECT a FROM AtividadeAcademica a"),
		@NamedQuery(name = "AtividadeAcademica.findByInstituicaoId", query = "SELECT a FROM AtividadeAcademica a WHERE a.unidadeOrganizacional.instituicao.id = :instituicaoId"),
		@NamedQuery(name = "AtividadeAcademica.findByUnidadeOrganizacionalId", query = "SELECT a FROM AtividadeAcademica a WHERE a.unidadeOrganizacional.id = :unidadeOrganizacionalId"),
		@NamedQuery(name = "AtividadeAcademica.findByNome", query = "SELECT a FROM AtividadeAcademica a WHERE a.nome = :nome"),
		@NamedQuery(name = "AtividadeAcademica.findBySigla", query = "SELECT a FROM AtividadeAcademica a WHERE a.sigla = :sigla") })
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("ac")
public class AtividadeAcademica extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String descricao;
	private String sigla;
	private Float cargaHoraria;
	private String ementa;

	@OneToMany(mappedBy = "atividadeAcademica", fetch = FetchType.LAZY)
	private Collection<ComponenteCurricular> componenteCurricular;

	@OneToMany(mappedBy = "atividadeAcademica")
	private Collection<AtividadesRequisitadas> atividadesRequisitadas;

	@ManyToOne
	@JoinColumn(name = "unidadeorganizacional_id")
	private UnidadeOrganizacional unidadeOrganizacional;

	public AtividadeAcademica() {
		super();
	}

	
	public Collection<ComponenteCurricular> getComponenteCurricular() {
		return componenteCurricular;
	}

	public void setComponenteCurricular(
			Collection<ComponenteCurricular> componenteCurricular) {
		this.componenteCurricular = componenteCurricular;
	}

	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Float getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Float cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public Collection<AtividadesRequisitadas> getAtividadesRequisitadas() {
		return atividadesRequisitadas;
	}

	public void setAtividadesRequisitadas(
			Collection<AtividadesRequisitadas> atividadesRequisitadas) {
		this.atividadesRequisitadas = atividadesRequisitadas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AtividadeAcademica other = (AtividadeAcademica) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.nome + ObjetoSIEP.SEPARADOR +
		this.descricao + ObjetoSIEP.SEPARADOR +
		this.sigla + ObjetoSIEP.SEPARADOR +
		this.cargaHoraria + ObjetoSIEP.SEPARADOR +
		this.ementa + ObjetoSIEP.SEPARADOR +
		this.unidadeOrganizacional.getId() +
		ObjetoSIEP.FINALIZADOR;
	}

}
