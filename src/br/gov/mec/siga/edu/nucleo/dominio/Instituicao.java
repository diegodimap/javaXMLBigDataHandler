package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@NamedQueries( { @NamedQuery(name = "Instituicao.findAll", query = "SELECT i FROM Instituicao i") })
public class Instituicao extends ObjetoSIEP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String sigla;
	private String razaoSocial;
	private String cnpj;
	private String endereco;
	private String telefonePrincipal;
	private String emailPrincipal;
	private String siteOficial;
	private String regimeJuridico;
	private String organizacaoAcademica;
	private String categoriaAdministrativa;
	private String estatutoDoRegimento;
	private String documentoOficial;
	private String situacaoLegal;
	private String situacaoDeFuncionamento;

	@Temporal(TemporalType.DATE)
	private Date dataFundacao;

	@OneToMany(mappedBy = "instituicao", fetch = FetchType.LAZY)
	private List<UnidadeEnsino> unidadeEnsino;

	@OneToMany(mappedBy = "instituicao", fetch = FetchType.LAZY)
	private List<UnidadeOrganizacional> unidadeOrganizacional;

	public Instituicao() {
		super();
	}

	
	public List<UnidadeEnsino> getUnidadeEnsino() {
		return unidadeEnsino;
	}

	public void setUnidadeEnsino(List<UnidadeEnsino> unidadeEnsino) {
		this.unidadeEnsino = unidadeEnsino;
	}

	public List<UnidadeOrganizacional> getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			List<UnidadeOrganizacional> unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (obj instanceof Instituicao) {
			Long id = ((Instituicao) obj).getId();
			return id == getId()
					|| (id != null && getId() != null && getId().equals(id));
		}
		return false;
	}*/

	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public String getEmailPrincipal() {
		return emailPrincipal;
	}

	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}

	public String getSiteOficial() {
		return siteOficial;
	}

	public void setSiteOficial(String siteOficial) {
		this.siteOficial = siteOficial;
	}

	public String getRegimeJuridico() {
		return regimeJuridico;
	}

	public void setRegimeJuridico(String regimeJuridico) {
		this.regimeJuridico = regimeJuridico;
	}

	public String getOrganizacaoAcademica() {
		return organizacaoAcademica;
	}

	public void setOrganizacaoAcademica(String organizacaoAcademica) {
		this.organizacaoAcademica = organizacaoAcademica;
	}

	public String getCategoriaAdministrativa() {
		return categoriaAdministrativa;
	}

	public void setCategoriaAdministrativa(String categoriaAdministrativa) {
		this.categoriaAdministrativa = categoriaAdministrativa;
	}

	public String getEstatutoDoRegimento() {
		return estatutoDoRegimento;
	}

	public void setEstatutoDoRegimento(String estatutoDoRegimento) {
		this.estatutoDoRegimento = estatutoDoRegimento;
	}

	public String getDocumentoOficial() {
		return documentoOficial;
	}

	public void setDocumentoOficial(String documentoOficial) {
		this.documentoOficial = documentoOficial;
	}

	public String getSituacaoLegal() {
		return situacaoLegal;
	}

	public void setSituacaoLegal(String situacaoLegal) {
		this.situacaoLegal = situacaoLegal;
	}

	public String getSituacaoDeFuncionamento() {
		return situacaoDeFuncionamento;
	}

	public void setSituacaoDeFuncionamento(String situacaoDeFuncionamento) {
		this.situacaoDeFuncionamento = situacaoDeFuncionamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Transient
	public String getDados(){
		return "i"+this.id + ObjetoSIEP.SEPARADOR +
		this.nome + ObjetoSIEP.SEPARADOR +
		this.sigla + ObjetoSIEP.SEPARADOR +
		this.razaoSocial + ObjetoSIEP.SEPARADOR +
		this.cnpj + ObjetoSIEP.SEPARADOR +
		this.endereco + ObjetoSIEP.SEPARADOR +
		this.telefonePrincipal + ObjetoSIEP.SEPARADOR +
		this.emailPrincipal + ObjetoSIEP.SEPARADOR +
		this.siteOficial + ObjetoSIEP.SEPARADOR +
		this.regimeJuridico + ObjetoSIEP.SEPARADOR +
		this.organizacaoAcademica + ObjetoSIEP.SEPARADOR +
		this.categoriaAdministrativa + ObjetoSIEP.SEPARADOR +
		this.estatutoDoRegimento + ObjetoSIEP.SEPARADOR +
		this.documentoOficial + ObjetoSIEP.SEPARADOR +
		this.situacaoLegal + ObjetoSIEP.SEPARADOR +
		this.situacaoDeFuncionamento + ObjetoSIEP.SEPARADOR +
		formatarData(this.dataFundacao)+ 
		ObjetoSIEP.FINALIZADOR;
	}
}