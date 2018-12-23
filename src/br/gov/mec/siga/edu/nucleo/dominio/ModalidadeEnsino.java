package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "modalidade_ensino")
@NamedQueries( { @NamedQuery(name = "ModalidadeEnsino.findAll", query = "SELECT m FROM ModalidadeEnsino m") })
public class ModalidadeEnsino extends ObjetoSIEP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;

	@OneToMany(mappedBy = "modalidadeEnsino", fetch = FetchType.LAZY)
	private List<Curso> curso;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "modalidadeEnsino_RegraAcademica", 
			joinColumns = { @JoinColumn(name = "modalidadeEnsino_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "regraAcademica_id") })
	private Set<RegraAcademica> regrasAcademicas;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "modalidadeEnsino")
	private Set<EstruturaDeAvaliacao> estruturaAvaliacao;

	public ModalidadeEnsino() {
		super();
	}

	public List<Curso> getCurso() {
		return curso;
	}

	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}

	public Set<RegraAcademica> getRegrasAcademicas() {
		return regrasAcademicas;
	}

	public void setRegrasAcademicas(Set<RegraAcademica> regrasAcademicas) {
		this.regrasAcademicas = regrasAcademicas;
	}

	public Set<EstruturaDeAvaliacao> getEstruturaAvaliacao() {
		return estruturaAvaliacao;
	}

	public void setEstruturaAvaliacao(
			Set<EstruturaDeAvaliacao> estruturaAvaliacao) {
		this.estruturaAvaliacao = estruturaAvaliacao;
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
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.nome +
		ObjetoSIEP.FINALIZADOR;
	}
}
