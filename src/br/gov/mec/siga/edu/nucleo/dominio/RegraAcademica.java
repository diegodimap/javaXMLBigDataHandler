package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "regras_academicas")
@NamedQueries( {
		@NamedQuery(name = "RegraAcademica.procuraPorUnidadeEnsino", query = "SELECT r FROM UnidadeEnsino u, in(u.regrasAcademicas) r WHERE u.id=:idUnidadeEnsino"),
		@NamedQuery(name = "RegraAcademica.procuraPorModalidadeEnsino", query = "SELECT r FROM ModalidadeEnsino m, in(m.regrasAcademicas) r WHERE m.id=:idModalidadeEnsino"),
		@NamedQuery(name = "RegraAcademica.procuraPorNivelEnsino", query = "SELECT r FROM NivelEnsino n,in(n.regrasAcademicas) r WHERE n.id=:idNivelEnsino"),
		@NamedQuery(name = "RegraAcademica.procuraPorCurso", query = "SELECT r FROM Curso c,in(c.regrasAcademicas) r WHERE c.id=:idCurso"),
		@NamedQuery(name = "RegraAcademica.procuraPorMatrizCurricular", query = "SELECT r FROM MatrizCurricular mc, in(mc.regrasAcademicas) r WHERE mc.id=:idMatrizCurricular"),
		@NamedQuery(name = "RegraAcademica.procuraPorTipo", query = "SELECT r FROM RegraAcademica r where r.tipoRegra=:idTipoRegra"),
		@NamedQuery(name = "RegraAcademica.procuraPorTodas", query = "SELECT r FROM RegraAcademica r order by r.id") })
public class RegraAcademica extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicial_de_vigencia")
	private Date dataInicialVigencia;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_de_criacao")
	private Date dataCriacao;
	private double valor;
	private String descricao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "regraAcademica_unidadeEnsino", 
			joinColumns = { @JoinColumn(name = "regraAcademica_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "unidadeEnsino_id") })
	private Set<UnidadeEnsino> unidadesEnsino;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="regrasAcademicas")
	private Set<Curso> cursos;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="regrasAcademicas")
	private Set<NivelEnsino> niveisEnsino;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="regrasAcademicas")
	private Set<ModalidadeEnsino> modalidadesEnsino;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "regra_academica_matriz_curricular", joinColumns = { @JoinColumn(name = "id_regra_academica") }, inverseJoinColumns = { @JoinColumn(name = "id_matriz_curricular") })
	private Set<MatrizCurricular> matrizesCurriculares;

	public RegraAcademica() {
		super();
	}

	public Set<UnidadeEnsino> getUnidadesEnsino() {
		return unidadesEnsino;
	}


	public void setUnidadesEnsino(Set<UnidadeEnsino> unidadesEnsino) {
		this.unidadesEnsino = unidadesEnsino;
	}


	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

	public Set<NivelEnsino> getNiveisEnsino() {
		return niveisEnsino;
	}

	public void setNiveisEnsino(Set<NivelEnsino> niveisEnsino) {
		this.niveisEnsino = niveisEnsino;
	}

	public Set<ModalidadeEnsino> getModalidadesEnsino() {
		return modalidadesEnsino;
	}

	public void setModalidadesEnsino(Set<ModalidadeEnsino> modalidadesEnsino) {
		this.modalidadesEnsino = modalidadesEnsino;
	}

	public Set<MatrizCurricular> getMatrizesCurriculares() {
		return matrizesCurriculares;
	}

	public void setMatrizesCurriculares(Set<MatrizCurricular> matrizesCurriculares) {
		this.matrizesCurriculares = matrizesCurriculares;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataInicialVigencia() {
		return dataInicialVigencia;
	}

	public void setDataInicialVigencia(Date dataInicialVigencia) {
		this.dataInicialVigencia = dataInicialVigencia;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataInicialVigencia) + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataCriacao) + ObjetoSIEP.SEPARADOR +
		this.valor + ObjetoSIEP.SEPARADOR +
		this.descricao + 
		ObjetoSIEP.FINALIZADOR;
	}
}
