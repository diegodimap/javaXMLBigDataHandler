package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "estruturaavaliacao")
@NamedQueries( {
		@NamedQuery(name = "EstruturaDeAvaliacao.buscaTodas", query = "SELECT  a from EstruturaDeAvaliacao a order by a.nome"),
		@NamedQuery(name = "EstruturaDeAvaliacao.buscaPorNome", query = "SELECT  a from EstruturaDeAvaliacao a where a.nome LIKE :nome order by a.nome"),
		@NamedQuery(name = "EstruturaDeAvaliacao.buscaPorNumeroDeEtapas", query = "SELECT  a from EstruturaDeAvaliacao a where a.numEtapas=:numEtapas"),
		@NamedQuery(name = "EstruturaDeAvaliacao.buscaPorUnidadeEnsino", query = "SELECT  a from EstruturaDeAvaliacao a where a.unidadeEnsino.id=:idUnidadeEnsino"),
		@NamedQuery(name = "EstruturaDeAvaliacao.buscaPorModalidadeEnsino", query = "SELECT  a from EstruturaDeAvaliacao a where a.modalidadeEnsino.id=:idModalidadeEnsino"),
		@NamedQuery(name = "EstruturaDeAvaliacao.buscaPorNivelEnsino", query = "SELECT  a from EstruturaDeAvaliacao a where a.nivelEnsino.id=:idNivelEnsino"),
		@NamedQuery(name = "EstruturaDeAvaliacao.buscaPorUnidadeOrganizacional", query = "SELECT a from EstruturaDeAvaliacao a where a.unidadeOrganizacional.id=:idUnidadeOrganizacional"),
		@NamedQuery(name = "EstruturaDeAvaliacao.buscaPorCurso", query = "SELECT a from EstruturaDeAvaliacao a where a.curso.id=:idCurso"),
		@NamedQuery(name = "EstruturaDeAvaliacao.buscaAtivas", query = "SELECT a from EstruturaDeAvaliacao a where a.ativa=true") })
public class EstruturaDeAvaliacao extends ObjetoSIEP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "datainicialvigencia")
	private Date dataInicialVigencia;

	@Column(name = "qt_etapas")
	private int numEtapas;

	private boolean ativa;

	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "unidadeEnsino_id")
	private UnidadeEnsino unidadeEnsino;

	@ManyToOne
	@JoinColumn(name = "modalidadeEnsino_id")
	private ModalidadeEnsino modalidadeEnsino;

	@ManyToOne
	@JoinColumn(name = "nivelEnsino_id")
	private NivelEnsino nivelEnsino;

	@ManyToOne
	@JoinColumn(name = "unidadeOrganizacional_id")
	private UnidadeOrganizacional unidadeOrganizacional;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<EtapaAvaliacao> etapaAvaliacao;

	public EstruturaDeAvaliacao() {
		super();
	}

	public Collection<EtapaAvaliacao> getEtapaAvaliacao() {
		return etapaAvaliacao;
	}

	public void setEtapaAvaliacao(Collection<EtapaAvaliacao> etapaAvaliacao) {
		this.etapaAvaliacao = etapaAvaliacao;
	}

	public void addEtapaDeAvaliacao(EtapaAvaliacao e) {
		this.etapaAvaliacao.add(e);
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

	public Date getDataInicialVigencia() {
		return dataInicialVigencia;
	}

	public void setDataInicialVigencia(Date dataInicialVigencia) {
		this.dataInicialVigencia = dataInicialVigencia;
	}

	public int getNumEtapas() {
		return numEtapas;
	}

	public void setNumEtapas(int numEtapas) {
		this.numEtapas = numEtapas;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public UnidadeEnsino getUnidadeEnsino() {
		return unidadeEnsino;
	}

	public void setUnidadeEnsino(UnidadeEnsino unidadeEnsino) {
		this.unidadeEnsino = unidadeEnsino;
	}

	public ModalidadeEnsino getModalidadeEnsino() {
		return modalidadeEnsino;
	}

	public void setModalidadeEnsino(ModalidadeEnsino modalidadeEnsino) {
		this.modalidadeEnsino = modalidadeEnsino;
	}

	public NivelEnsino getNivelEnsino() {
		return nivelEnsino;
	}

	public void setNivelEnsino(NivelEnsino nivelEnsino) {
		this.nivelEnsino = nivelEnsino;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.nome + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataInicialVigencia) + ObjetoSIEP.SEPARADOR +
		this.numEtapas + ObjetoSIEP.SEPARADOR +
		this.ativa + ObjetoSIEP.SEPARADOR +
		this.curso.getId() + ObjetoSIEP.SEPARADOR +
		this.unidadeEnsino.getId() + ObjetoSIEP.SEPARADOR +
		this.modalidadeEnsino.getId() + ObjetoSIEP.SEPARADOR +
		this.nivelEnsino.getId() + ObjetoSIEP.SEPARADOR +
		this.unidadeOrganizacional.getId() +
		ObjetoSIEP.FINALIZADOR;
		
	}
}