package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries( { @NamedQuery(name = "Certificacao.findAll", query = "SELECT c FROM Certificacao c") })
public class Certificacao extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name = "nivelEnsino_id")
	private NivelEnsino nivelEnsino;
	
	public Certificacao() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public NivelEnsino getNivelEnsino() {
		return nivelEnsino;
	}

	public void setNivelEnsino(NivelEnsino nivelEnsino) {
		this.nivelEnsino = nivelEnsino;
	}
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.titulo + ObjetoSIEP.SEPARADOR +
		this.curso.getId() + ObjetoSIEP.SEPARADOR +
		this.nivelEnsino.getId() +
		ObjetoSIEP.FINALIZADOR;
	}

}