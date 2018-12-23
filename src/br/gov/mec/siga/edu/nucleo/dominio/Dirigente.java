package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries( { @NamedQuery(name = "Dirigente.findAll", query = "SELECT c FROM Dirigente c") })
public class Dirigente extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String cargo;
	private String titulo;

	@OneToMany(mappedBy = "dirigente")
	private Collection<UnidadeOrganizacionalDirigente> unidadeOrganizacionalDirigente;

	public Dirigente() {
		super();
	}
	
	public Collection<UnidadeOrganizacionalDirigente> getUnidadeOrganizacionalDirigente() {
		return unidadeOrganizacionalDirigente;
	}

	public void setUnidadeOrganizacionalDirigente(
			Collection<UnidadeOrganizacionalDirigente> unidadeOrganizacionalDirigente) {
		this.unidadeOrganizacionalDirigente = unidadeOrganizacionalDirigente;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.cargo + ObjetoSIEP.SEPARADOR +
		this.titulo  +
		ObjetoSIEP.SEPARADOR;
	}
}