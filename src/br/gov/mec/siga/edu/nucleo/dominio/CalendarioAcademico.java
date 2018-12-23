package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name = "CalendarioAcademico.findAll", query = "SELECT ca FROM CalendarioAcademico ca")
})
public class CalendarioAcademico extends ObjetoSIEP implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int codigo;
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	private Date dataFim;
	private int anoLetivo;

	@ManyToOne
	@JoinColumn(name = "unidadeensino_id")
	private UnidadeEnsino unidadeEnsino;

	@ManyToMany
    @JoinTable(name="CalendarioAcademico_Curso",
    joinColumns=@JoinColumn(name="calendarioAcademico_id"),
    inverseJoinColumns=@JoinColumn(name="curso_id"))
	private Collection<Curso> cursos;
	
	public CalendarioAcademico() {
		super();
		this.cursos = new HashSet<Curso>();
		
	}
	
	public Collection<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Collection<Curso> cursos) {
		this.cursos = cursos;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public int getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(int anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public UnidadeEnsino getUnidadeEnsino() {
		return unidadeEnsino;
	}

	public void setUnidadeEnsino(UnidadeEnsino unidadeEnsino) {
		this.unidadeEnsino = unidadeEnsino;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.codigo + ObjetoSIEP.SEPARADOR +
		this.descricao + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataInicio) + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataFim) + ObjetoSIEP.SEPARADOR +
		this.anoLetivo + ObjetoSIEP.SEPARADOR +
		this.unidadeEnsino.getId() +
		ObjetoSIEP.FINALIZADOR;
	}
}