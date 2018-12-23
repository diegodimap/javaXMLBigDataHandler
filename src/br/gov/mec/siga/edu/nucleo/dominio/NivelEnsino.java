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
@Table(name = "nivel_ensino")
@NamedQueries( { @NamedQuery(name = "NivelEnsino.findAll", query = "SELECT n FROM NivelEnsino n") })
public class NivelEnsino extends ObjetoSIEP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;

	@OneToMany(mappedBy = "nivelEnsino", fetch = FetchType.LAZY)
	private List<Curso> curso;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "NIVELENSINO_REGRAACADEMICA", 
			joinColumns = { @JoinColumn(name = "nivelEnsino_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "regraAcademica_id") })
	private Set<RegraAcademica> regrasAcademicas;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "nivelEnsino")
	private Set<EstruturaDeAvaliacao> estruturaDeAvaliacao;

	@OneToMany(mappedBy = "nivelEnsino", fetch = FetchType.LAZY)
	private List<Certificacao> certificacao;

	public NivelEnsino() {
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

	public Set<EstruturaDeAvaliacao> getEstruturaDeAvaliacao() {
		return estruturaDeAvaliacao;
	}

	public void setEstruturaDeAvaliacao(
			Set<EstruturaDeAvaliacao> estruturaDeAvaliacao) {
		this.estruturaDeAvaliacao = estruturaDeAvaliacao;
	}

	public List<Certificacao> getCertificacao() {
		return certificacao;
	}

	public void setCertificacao(List<Certificacao> certificacao) {
		this.certificacao = certificacao;
	}

	
	public long getId() {
		return this.id;
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
