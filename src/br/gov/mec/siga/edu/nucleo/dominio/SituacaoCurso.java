package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "situacao_curso")
@NamedQueries( { @NamedQuery(name = "SituacaoCurso.findAll", query = "SELECT s FROM SituacaoCurso s") })
public class SituacaoCurso extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;

	@OneToMany(mappedBy = "situacaoCurso", fetch = FetchType.LAZY)
	private List<Curso> curso;

	public SituacaoCurso() {
		super();
	}

	public List<Curso> getCurso() {
		return curso;
	}

	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}

	public long getId() {
		return this.id;
	}
	
	public void setId(long umId) {
		this.id = umId;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String umNome) {
		this.nome = umNome;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.nome + 
		ObjetoSIEP.FINALIZADOR;
	}

}
