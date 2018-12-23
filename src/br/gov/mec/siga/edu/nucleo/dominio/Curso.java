package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries( {
		@NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
		@NamedQuery(name = "Curso.findBySituacaoAtivo", query = "SELECT c FROM Curso c WHERE c.situacaoCurso.nome = 'ativo'"),
		@NamedQuery(name = "Curso.findByUnidadeOrganizacionalId", query = "SELECT c FROM Curso c WHERE c.unidadeOrganizacional.id = :unidadeOrganizacionalId"),
		@NamedQuery(name = "Curso.findByInstituicaoId", query = "SELECT c FROM Curso c WHERE c.unidadeOrganizacional.instituicao.id = :instituicaoId"),
		@NamedQuery(name = "Curso.findByUnidadeEnsinoId", query = "SELECT c FROM Curso c WHERE c.unidadeOrganizacional.unidadeEnsino.id = :unidadeEnsinoId") })
public class Curso extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String codigo;
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date inicioFuncionamento;
	@Temporal(TemporalType.DATE)
	private Date fimFuncionamento;
	private int tempoMinimoIntegralizacao;
	private int tempoMaximoIntegralizacao;
	private String codigoComposicaoTurma;
	private String codigoComposicaoMatricula;
	private String sigla;
	private int numeroVagas;
	@Temporal(TemporalType.DATE)
	private Date dataAutorizacao;

	@OneToMany(mappedBy = "curso")
	private Collection<Matricula> matricula;

	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	private Collection<MatrizCurricular> matrizCurricular;

	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	private Collection<Certificacao> certificacao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "regra_academica_curso", 
		joinColumns = { @JoinColumn(name = "curso_id") }, 
		inverseJoinColumns = 
		{ @JoinColumn(name = "regraAcademica_id") })
	private Set<RegraAcademica> regrasAcademicas;

	@OneToMany(mappedBy = "curso")
	private Set<EstruturaDeAvaliacao> estruturaDeAvaliacao;

	@ManyToMany(mappedBy = "cursos")
	private Collection<CalendarioAcademico> calendariosAcademicos;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "curso_turno", joinColumns = { @JoinColumn(name = "id_curso") }, inverseJoinColumns = { @JoinColumn(name = "id_turno") })
	private Set<Turno> turnos;

	@ManyToOne
	@JoinColumn(name = "periodicidadeingresso_id")
	private PeriodicidadeIngresso periodicidadeIngresso;

	@ManyToOne
	@JoinColumn(name = "regimematricula_id")
	private RegimeMatricula regimeMatricula;

	@ManyToOne
	@JoinColumn(name = "unidadeorganizacional_id")
	private UnidadeOrganizacional unidadeOrganizacional;

	@ManyToOne
	@JoinColumn(name = "modalidadeensino_id")
	private ModalidadeEnsino modalidadeEnsino;

	@ManyToOne
	@JoinColumn(name = "nivelEnsino_id")
	private NivelEnsino nivelEnsino;

	@ManyToOne
	@JoinColumn(name = "situacaoCurso_id")
	private SituacaoCurso situacaoCurso;

	@ManyToOne
	@JoinColumn(name = "documentoOficial_id")
	private DocumentoOficial documentoOficial;

	public Curso() {
		super();

		this.turnos = new HashSet<Turno>();
		this.matricula = new ArrayList<Matricula>();
		this.matrizCurricular = new ArrayList<MatrizCurricular>();
		this.calendariosAcademicos = new HashSet<CalendarioAcademico>();
	}

//	public boolean equals(Object obj) {
//		if (obj instanceof Curso) {
//			Long id = ((Curso) obj).getId();
//			return id == getId()
//					|| (id != null && getId() != null && getId().equals(id));
//		}
//		return false;
//	}

	
	public Collection<MatrizCurricular> getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(
			Collection<MatrizCurricular> matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	
	public Set<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(Set<Turno> turnos) {
		this.turnos = turnos;
	}

	public Collection<Matricula> getMatricula() {
		return matricula;
	}

	public void setMatricula(Collection<Matricula> matricula) {
		this.matricula = matricula;
	}

	public Set<RegraAcademica> getRegrasAcademicas() {
		return regrasAcademicas;
	}

	public void setRegrasAcademicas(Set<RegraAcademica> regrasAcademicas) {
		this.regrasAcademicas = regrasAcademicas;
	}

	public Set<EstruturaDeAvaliacao> getEstruturaDeAvaliacao() {
		return estruturaDeAvaliacao;
	}

	public void setEstruturaDeAvaliacao(
			Set<EstruturaDeAvaliacao> estruturaDeAvaliacao) {
		this.estruturaDeAvaliacao = estruturaDeAvaliacao;
	}

	public String toString() {
		return this.nome;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String umCodigo) {
		this.codigo = umCodigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String umNome) {
		this.nome = umNome;
	}

	public Date getInicioFuncionamento() {
		return this.inicioFuncionamento;
	}

	public void setInicioFuncionamento(Date umaData) {
		this.inicioFuncionamento = umaData;
	}

	public int getTempoMinimoIntegralizacao() {
		return this.tempoMinimoIntegralizacao;
	}

	public void setTempoMinimoIntegralizacao(int umTempoMinimoIntegralizacao) {
		this.tempoMinimoIntegralizacao = umTempoMinimoIntegralizacao;
	}

	public int getTempoMaximoIntegralizacao() {
		return this.tempoMaximoIntegralizacao;
	}

	public void setTempoMaximoIntegralizacao(int umTempoMaximoIntegralizacao) {
		this.tempoMaximoIntegralizacao = umTempoMaximoIntegralizacao;
	}

	public String getCodigoComposicaoTurma() {
		return this.codigoComposicaoTurma;
	}

	public void setCodigoComposicaoTurma(String umCodigoComposicaoTurma) {
		this.codigoComposicaoTurma = umCodigoComposicaoTurma;
	}

	public String getCodigoComposicaoMatricula() {
		return this.codigoComposicaoMatricula;
	}

	public void setCodigoComposicaoMatricula(String codigoComposicaoMatricula) {
		this.codigoComposicaoMatricula = codigoComposicaoMatricula;
	}

	public int getNumeroVagas() {
		return this.numeroVagas;
	}

	public void setNumeroVagas(int umNumeroVagas) {
		this.numeroVagas = umNumeroVagas;
	}

	public Date getDataAutorizacao() {
		return this.dataAutorizacao;
	}

	public void setDataAutorizacao(Date umaDataAutorizacao) {
		this.dataAutorizacao = umaDataAutorizacao;
	}

	public SituacaoCurso getSituacaoCurso() {
		return this.situacaoCurso;
	}

	public void setSituacaoCurso(SituacaoCurso umaSituacaoCurso) {
		this.situacaoCurso = umaSituacaoCurso;
	}

	public PeriodicidadeIngresso getPeriodicidadeIngresso() {
		return this.periodicidadeIngresso;
	}

	public void setPeriodicidadeIngresso(
			PeriodicidadeIngresso periodicidadeIngresso) {
		this.periodicidadeIngresso = periodicidadeIngresso;
	}

	public RegimeMatricula getRegimeMatricula() {
		return this.regimeMatricula;
	}

	public void setRegimeMatricula(RegimeMatricula umRegimeMatricula) {
		this.regimeMatricula = umRegimeMatricula;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return this.unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			UnidadeOrganizacional umaUnidadeOrganizacional) {
		this.unidadeOrganizacional = umaUnidadeOrganizacional;
	}

	public ModalidadeEnsino getModalidadeEnsino() {
		return this.modalidadeEnsino;
	}

	public void setModalidadeEnsino(ModalidadeEnsino umaModalidadeEnsino) {
		this.modalidadeEnsino = umaModalidadeEnsino;
	}

	public NivelEnsino getNivelEnsino() {
		return this.nivelEnsino;
	}

	public void setNivelEnsino(NivelEnsino umNivelEnsino) {
		this.nivelEnsino = umNivelEnsino;
	}

	public Collection<Certificacao> getCertificacao() {
		return this.certificacao;
	}

	public void setCertificacao(Collection<Certificacao> certificacoes) {
		this.certificacao = certificacoes;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String umaSigla) {
		this.sigla = umaSigla;
	}

	public Date getFimFuncionamento() {
		return this.fimFuncionamento;
	}

	public void setFimFuncionamento(Date umaData) {
		this.fimFuncionamento = umaData;
	}

	public Collection<CalendarioAcademico> getCalendariosAcademicos() {
		return calendariosAcademicos;
	}

	public void setCalendariosAcademicos(
			Collection<CalendarioAcademico> calendariosAcademicos) {
		this.calendariosAcademicos = calendariosAcademicos;
	}

	public DocumentoOficial getDocumentoOficial() {
		return documentoOficial;
	}

	public void setDocumentoOficial(DocumentoOficial documentoOficial) {
		this.documentoOficial = documentoOficial;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.codigo + ObjetoSIEP.SEPARADOR +
		this.nome + ObjetoSIEP.SEPARADOR +
		formatarData(this.inicioFuncionamento) + ObjetoSIEP.SEPARADOR +
		formatarData(this.fimFuncionamento) + ObjetoSIEP.SEPARADOR +
		this.tempoMinimoIntegralizacao + ObjetoSIEP.SEPARADOR +
		this.tempoMaximoIntegralizacao + ObjetoSIEP.SEPARADOR +
		this.codigoComposicaoTurma + ObjetoSIEP.SEPARADOR +
		this.codigoComposicaoMatricula + ObjetoSIEP.SEPARADOR +
		this.sigla + ObjetoSIEP.SEPARADOR +
		this.numeroVagas + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataAutorizacao) + ObjetoSIEP.SEPARADOR +
		this.periodicidadeIngresso.getId() + ObjetoSIEP.SEPARADOR +
		this.regimeMatricula.getId() + ObjetoSIEP.SEPARADOR +
		this.unidadeOrganizacional.getId() + ObjetoSIEP.SEPARADOR +
		this.modalidadeEnsino.getId() + ObjetoSIEP.SEPARADOR +
		this.nivelEnsino.getId() + ObjetoSIEP.SEPARADOR +
		this.situacaoCurso.getId() + ObjetoSIEP.SEPARADOR +
		this.documentoOficial.getId()+
		ObjetoSIEP.FINALIZADOR;
		
	}

}
