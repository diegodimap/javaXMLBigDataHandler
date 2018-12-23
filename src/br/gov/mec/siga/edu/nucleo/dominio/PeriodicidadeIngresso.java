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
@Table(name = "periodicidade_ingresso")
@NamedQueries( { @NamedQuery(name = "PeriodicidadeIngresso.findAll", query = "SELECT p FROM PeriodicidadeIngresso p") })
public class PeriodicidadeIngresso extends ObjetoSIEP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;

	@OneToMany(mappedBy = "periodicidadeIngresso", fetch = FetchType.LAZY)
	private List<Curso> curso;

	public PeriodicidadeIngresso() {
		super();
	}

	public List<Curso> getCurso() {
		return curso;
	}

	public void setCurso(List<Curso> curso) {
		this.curso = curso;
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
