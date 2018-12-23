package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "matriz_curricular")
@NamedQueries( {
		@NamedQuery(name = "MatrizCurricular.findAll", query = "SELECT m FROM MatrizCurricular m"),

		@NamedQuery(name = "MatrizCurricular.findByCurso", query = "SELECT m FROM Curso c join c.matrizesCurriculares m WHERE c.id = :cursoId"),

		@NamedQuery(name = "MatrizCurricular.findByCursoId", query = "SELECT m FROM MatrizCurricular m WHERE m.curso.id = :cursoId"),

		@NamedQuery(name = "MatrizCurricular.findByNome", query = "SELECT m FROM MatrizCurricular m WHERE m.nome = :nome") })
public class MatrizCurricular extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;

	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@OneToMany(mappedBy = "matrizCurricular", fetch = FetchType.LAZY)
	private Collection<ComponenteCurricular> componenteCurricular;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "regra_academica_matriz_curricular", joinColumns = { @JoinColumn(name = "id_matriz_curricular") }, inverseJoinColumns = { @JoinColumn(name = "id_regra_academica") })
	private Set<RegraAcademica> regrasAcademicas;

	@Temporal(TemporalType.DATE)
	@Column(name = "inicio_oferta")
	private Date inicioOferta;

	@Temporal(TemporalType.DATE)
	@Column(name = "fim_oferta")
	private Date fimOferta;

	public MatrizCurricular() {
		super();
	}

	public Collection<ComponenteCurricular> getComponenteCurricular() {
		return componenteCurricular;
	}

	public void setComponenteCurricular(
			Collection<ComponenteCurricular> componenteCurricular) {
		this.componenteCurricular = componenteCurricular;
	}

	public Set<RegraAcademica> getRegrasAcademicas() {
		return regrasAcademicas;
	}

	public void setRegrasAcademicas(Set<RegraAcademica> regrasAcademicas) {
		this.regrasAcademicas = regrasAcademicas;
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Date getInicioOferta() {
		return inicioOferta;
	}

	public void setInicioOferta(Date inicioOferta) {
		this.inicioOferta = inicioOferta;
	}

	public Date getFimOferta() {
		return fimOferta;
	}

	public void setFimOferta(Date fimOferta) {
		this.fimOferta = fimOferta;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.nome + ObjetoSIEP.SEPARADOR +
		this.curso.getId() + ObjetoSIEP.SEPARADOR +
		formatarData(this.inicioOferta) + ObjetoSIEP.SEPARADOR +
		formatarData(this.fimOferta) + 
		ObjetoSIEP.FINALIZADOR;
	}
}