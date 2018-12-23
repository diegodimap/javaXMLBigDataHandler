package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "unidade_organizacional")
@NamedQueries( {
		@NamedQuery(name = "UnidadeOrganizacional.findAll", query = "SELECT u FROM UnidadeOrganizacional u"),
		@NamedQuery(name = "UnidadeOrganizacional.findByNome", query = "SELECT u FROM UnidadeOrganizacional u WHERE u.nome = :nome"),
		@NamedQuery(name = "UnidadeOrganizacional.findByEstruturaOrganizacionalID", query = "SELECT u FROM UnidadeOrganizacional u WHERE u.estruturaOrganizacional.id = :idEstruturaOrganizacional"),
		@NamedQuery(name = "UnidadeOrganizacional.findNoByEstruturaOrganizacionalID", query = "SELECT u FROM UnidadeOrganizacional u WHERE u.estruturaOrganizacional IS NULL AND u.unidadeEnsino.id = :idUnidadeEnsino"),
		@NamedQuery(name = "UnidadeOrganizacional.findByUnidadeEnsinoID", query = "SELECT u FROM UnidadeOrganizacional u WHERE u.unidadeEnsino.id = :idUnidadeEnsino"),
		@NamedQuery(name = "UnidadeOrganizacional.findNoByUnidadeEnsinoID", query = "SELECT u FROM UnidadeOrganizacional u WHERE u.unidadeEnsino.id <> :idUnidadeEnsino"),
		@NamedQuery(name = "UnidadeOrganizacional.findBySuperiorID", query = "SELECT u FROM UnidadeOrganizacional u WHERE u.superior.id = :idSuperior"),
		@NamedQuery(name = "UnidadeOrganizacional.findByUnidadeEnsinoId", query = "SELECT c FROM UnidadeOrganizacional c WHERE c.unidadeEnsino.id = :unidadeEnsinoId"),
		@NamedQuery(name = "UnidadeOrganizacional.findByInstituicaoId", query = "SELECT u FROM UnidadeOrganizacional u WHERE u.instituicao.id = :instituicaoId") })
public class UnidadeOrganizacional extends ObjetoSIEP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;
	private String responsavel;

	@ManyToOne
	@JoinColumn(name = "instituicao_id")
	private Instituicao instituicao;

	@Temporal(value = TemporalType.DATE)
	private Date dataInicio;

	@Temporal(value = TemporalType.DATE)
	private Date dataFim;

	@ManyToOne
	@JoinColumn(name = "id_unidade_organizacional")
	private UnidadeOrganizacional unidadeOrganizacional;

	@ManyToOne
	@JoinColumn(name = "id_unidade_ensino")
	private UnidadeEnsino unidadeEnsino;

	@OneToMany(mappedBy = "unidadeOrganizacional", fetch = FetchType.LAZY)
	private List<Curso> curso;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "unidadeOrganizacional")
	private Set<EstruturaDeAvaliacao> estruturaDeAvaliacao;

	@ManyToOne
	@JoinColumn(name = "id_estrutura_organizacional")
	private EstruturaOrganizacional estruturaOrganizacional;

	@OneToMany(mappedBy = "unidadeOrganizacional")
	private Collection<UnidadeOrganizacionalDirigente> unidadeOrganizacionalDirigente;

	@OneToMany(mappedBy = "unidadeOrganizacional")
	private Collection<AtividadeAcademica> atividadeAcademica;
	
	@OneToMany(mappedBy = "unidadeOrganizacional")
	private Collection<UnidadeOrganizacional> unidadeOrganizacionals;

	public UnidadeOrganizacional() {
		super();
	}
	
	public Collection<UnidadeOrganizacional> getUnidadeOrganizacionals() {
		return unidadeOrganizacionals;
	}

	public void setUnidadeOrganizacionals(
			Collection<UnidadeOrganizacional> unidadeOrganizacionals) {
		this.unidadeOrganizacionals = unidadeOrganizacionals;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	
	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	
	public List<Curso> getCurso() {
		return curso;
	}

	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}


	
	public Set<EstruturaDeAvaliacao> getEstruturaDeAvaliacao() {
		return estruturaDeAvaliacao;
	}

	public void setEstruturaDeAvaliacao(
			Set<EstruturaDeAvaliacao> estruturaDeAvaliacao) {
		this.estruturaDeAvaliacao = estruturaDeAvaliacao;
	}

	
	public Collection<UnidadeOrganizacionalDirigente> getUnidadeOrganizacionalDirigente() {
		return unidadeOrganizacionalDirigente;
	}

	public void setUnidadeOrganizacionalDirigente(
			Collection<UnidadeOrganizacionalDirigente> unidadeOrganizacionalDirigente) {
		this.unidadeOrganizacionalDirigente = unidadeOrganizacionalDirigente;
	}

	public Collection<AtividadeAcademica> getAtividadeAcademica() {
		return atividadeAcademica;
	}

	public void setAtividadeAcademica(
			Collection<AtividadeAcademica> atividadeAcademica) {
		this.atividadeAcademica = atividadeAcademica;
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

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public UnidadeEnsino getUnidadeEnsino() {
		return unidadeEnsino;
	}

	public void setUnidadeEnsino(UnidadeEnsino unidadeEnsino) {
		this.unidadeEnsino = unidadeEnsino;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public EstruturaOrganizacional getEstruturaOrganizacional() {
		return estruturaOrganizacional;
	}

	public void setEstruturaOrganizacional(
			EstruturaOrganizacional estruturaOrganizacional) {
		this.estruturaOrganizacional = estruturaOrganizacional;
	}

	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.nome + ObjetoSIEP.SEPARADOR +
		this.responsavel + ObjetoSIEP.SEPARADOR +
		this.instituicao.getId() + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataInicio) + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataFim) + ObjetoSIEP.SEPARADOR +
		this.unidadeOrganizacional.getId() + ObjetoSIEP.SEPARADOR +
		this.unidadeEnsino.getId() + ObjetoSIEP.SEPARADOR +
		this.estruturaOrganizacional.getId() + 
		ObjetoSIEP.FINALIZADOR;
	}
}