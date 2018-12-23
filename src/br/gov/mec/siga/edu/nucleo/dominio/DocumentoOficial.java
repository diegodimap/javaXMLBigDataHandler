package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries( { @NamedQuery(name = "DocumentoOficial.findAll", query = "SELECT do FROM DocumentoOficial do") })
public class DocumentoOficial extends ObjetoSIEP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String descricao;

	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@Temporal(TemporalType.DATE)
	private Date dataPublicacao;

	@OneToMany(mappedBy = "documentoOficial", fetch = FetchType.LAZY)
	private Collection<Curso> curso;

	public DocumentoOficial() {
		super();
	}

	public Collection<Curso> getCurso() {
		return curso;
	}

	public void setCurso(Collection<Curso> curso) {
		this.curso = curso;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (obj instanceof DocumentoOficial) {
//			Long id = ((DocumentoOficial) obj).getId();
//			return id == getId()
//					|| (id != null && getId() != null && getId().equals(id));
//		}
//		return false;
//	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public long getId() {
		return id;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.descricao + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataEmissao) + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataPublicacao) +
		ObjetoSIEP.SEPARADOR;
	}

}
