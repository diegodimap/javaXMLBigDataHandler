package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "componente_curricular")
@NamedQueries( {
		@NamedQuery(name = "ComponenteCurricular.findByMatrizCurricular",
				query = "SELECT c FROM MatrizCurricular m, in(m.componentesCurriculares) c WHERE m.id = :matrizCurricularId"),
		@NamedQuery(name = "ComponenteCurricular.findByMatrizCurricularId",
				query = "SELECT c FROM ComponenteCurricular c WHERE c.matrizCurricular.id = :matrizCurricularId"),				
		@NamedQuery(name = "ComponenteCurricular.findAll",
				query = "SELECT c FROM ComponenteCurricular c"),
		@NamedQuery(name = "ComponenteCurricular.findByModuloId",
				query = "SELECT c FROM ComponenteCurricular c WHERE c.modulo.id = :moduloId"),
		@NamedQuery(name = "ComponenteCurricular.findByCursoId",
				query = "SELECT c FROM ComponenteCurricular c WHERE c.matrizCurricular.curso.id = :cursoId"),
		@NamedQuery(name= "ComponenteCurricular.findByNome",
				query = "SELECT c FROM ComponenteCurricular c WHERE c.nome = :nome"),
		@NamedQuery(name = "ComponenteCurricular.findByAtividadeAndMatrizId",
				query = "SELECT c FROM MatrizCurricular m, in(m.componentesCurriculares) c WHERE m.id = :matrizCurricularId " +
						"AND c.atividadeAcademica.id = :atividadeId" )})
public class ComponenteCurricular extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "matrizcurricular_id")
	private MatrizCurricular matrizCurricular;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "atividadeAcademica_id")
	private AtividadeAcademica atividadeAcademica;
	
	public ComponenteCurricular() {
		super();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public MatrizCurricular getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(MatrizCurricular matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	public AtividadeAcademica getAtividadeAcademica() {		
		return atividadeAcademica;
	}

	public void setAtividadeAcademica(AtividadeAcademica atividadeAcademica) {
		this.atividadeAcademica = atividadeAcademica;
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
		this.nome + ObjetoSIEP.SEPARADOR +
		this.matrizCurricular.getId() + ObjetoSIEP.SEPARADOR +
		this.atividadeAcademica.getId()+
		ObjetoSIEP.FINALIZADOR;
		
	}
	
}