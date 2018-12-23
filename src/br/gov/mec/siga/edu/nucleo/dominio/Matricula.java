package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name="Matricula.buscarPorAluno",query="SELECT m FROM Matricula m WHERE m.aluno=:aluno"),
	@NamedQuery(name="Matricula.buscarPorMatriculaECurso",query="SELECT m FROM Matricula m WHERE m.numeroMatricula LIKE :numeroMatricula and m.curso.id=:curso")
})
public class Matricula extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String numeroMatricula;
	@Temporal(TemporalType.DATE)
	private Date dataMatricula;

	@ManyToOne
	@JoinColumn(name="curso_id")
	private Curso curso;
	
	public Matricula() {
		super();
	}
	
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long umId) {
		this.id = umId;
	}

	public String getNumeroMatricula() {
		return this.numeroMatricula;
	}

	public void setNumeroMatricula(String umaMatricula) {
		this.numeroMatricula = umaMatricula;
	}

	public Date getDataMatricula() {
		return this.dataMatricula;
	}

	public void setDataMatricula(Date umaData) {
		this.dataMatricula = umaData;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso umCurso) {
		this.curso = umCurso;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.numeroMatricula + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataMatricula) + ObjetoSIEP.SEPARADOR +
		this.curso.getId() + 
		ObjetoSIEP.FINALIZADOR;
	}

}
