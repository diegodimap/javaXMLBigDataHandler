package br.gov.mec.siga.edu.nucleo.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "unidade_ensino")
@NamedQueries( {
		@NamedQuery(name = "UnidadeEnsino.findAll", query = "SELECT u FROM UnidadeEnsino u"),
		@NamedQuery(name = "UnidadeEnsino.findByInstituicao", query = "SELECT u FROM UnidadeEnsino u WHERE u.instituicao = :instituicao"),
		@NamedQuery(name = "UnidadeEnsino.findByInstituicao_id", query = "SELECT u FROM UnidadeEnsino u WHERE u.instituicao.id = :instituicao_id") })
public class UnidadeEnsino extends ObjetoSIEP implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String tipo;
	private String nome;
	private int areaTerreno;
	private int areaConstruida;
	private int areaAdministrativa;
	private int areaPedagogica;
	private int areaLaboratorios;
	private int areaEsportiva;
	private int areaTotal;
	private Boolean conectividade;
	private int velocidadeConexao;

	@ManyToOne
	@JoinColumn(name = "id_instituicao")
	private Instituicao instituicao;

	@OneToMany(mappedBy = "unidadeEnsino", fetch = FetchType.LAZY)
	private List<UnidadeOrganizacional> unidadeOrganizacional;

	@OneToMany
	private List<EstruturaOrganizacional> estruturaOrganizacional;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="unidadesEnsino")
	private Set<RegraAcademica> regrasAcademicas;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "unidadeEnsino")
	private Set<EstruturaDeAvaliacao> estruturaAvaliacao;

	@OneToMany(mappedBy = "unidadeEnsino")
	private Collection<CalendarioAcademico> calendarioAcademico;

	public UnidadeEnsino() {
		super();
		this.calendarioAcademico = new HashSet<CalendarioAcademico>();
	}

	/*public boolean equals(Object obj) {
		if (obj instanceof UnidadeEnsino) {
			Long id = ((UnidadeEnsino) obj).getId();
			return id == getId()
					|| (id != null && getId() != null && getId().equals(id));
		}
		return false;
	}*/

	public List<UnidadeOrganizacional> getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			List<UnidadeOrganizacional> unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public List<EstruturaOrganizacional> getEstruturaOrganizacional() {
		return estruturaOrganizacional;
	}

	public void setEstruturaOrganizacional(
			List<EstruturaOrganizacional> estruturaOrganizacional) {
		this.estruturaOrganizacional = estruturaOrganizacional;
	}

	public Set<RegraAcademica> getRegrasAcademicas() {
		return regrasAcademicas;
	}

	public void setRegrasAcademicas(Set<RegraAcademica> regrasAcademicas) {
		this.regrasAcademicas = regrasAcademicas;
	}

	public Set<EstruturaDeAvaliacao> getEstruturaAvaliacao() {
		return estruturaAvaliacao;
	}

	public void setEstruturaAvaliacao(
			Set<EstruturaDeAvaliacao> estruturaAvaliacao) {
		this.estruturaAvaliacao = estruturaAvaliacao;
	}

	
	public Collection<CalendarioAcademico> getCalendarioAcademico() {
		return calendarioAcademico;
	}

	public void setCalendarioAcademico(
			Collection<CalendarioAcademico> calendarioAcademico) {
		this.calendarioAcademico = calendarioAcademico;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAreaTerreno() {
		return areaTerreno;
	}

	public void setAreaTerreno(int areaTerreno) {
		this.areaTerreno = areaTerreno;
	}

	public int getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(int areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public int getAreaAdministrativa() {
		return areaAdministrativa;
	}

	public void setAreaAdministrativa(int areaAdministrativa) {
		this.areaAdministrativa = areaAdministrativa;
	}

	public int getAreaPedagogica() {
		return areaPedagogica;
	}

	public void setAreaPedagogica(int areaPedagogica) {
		this.areaPedagogica = areaPedagogica;
	}

	public int getAreaLaboratorios() {
		return areaLaboratorios;
	}

	public void setAreaLaboratorios(int areaLaboratorios) {
		this.areaLaboratorios = areaLaboratorios;
	}

	public int getAreaEsportiva() {
		return areaEsportiva;
	}

	public void setAreaEsportiva(int areaEsportiva) {
		this.areaEsportiva = areaEsportiva;
	}

	public int getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(int areaTotal) {
		this.areaTotal = areaTotal;
	}

	public Boolean getConectividade() {
		return conectividade;
	}

	public void setConectividade(Boolean conectividade) {
		this.conectividade = conectividade;
	}

	public int getVelocidadeConexao() {
		return velocidadeConexao;
	}

	public void setVelocidadeConexao(int velocidadeConexao) {
		this.velocidadeConexao = velocidadeConexao;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	@Transient
	public String getDados(){
		return "i" + this.id + ObjetoSIEP.SEPARADOR +
		this.tipo + ObjetoSIEP.SEPARADOR +
		this.nome + ObjetoSIEP.SEPARADOR +
		this.areaTerreno + ObjetoSIEP.SEPARADOR +
		this.areaConstruida + ObjetoSIEP.SEPARADOR +
		this.areaAdministrativa + ObjetoSIEP.SEPARADOR +
		this.areaPedagogica + ObjetoSIEP.SEPARADOR +
		this.areaLaboratorios + ObjetoSIEP.SEPARADOR +
		this.areaEsportiva + ObjetoSIEP.SEPARADOR +
		this.areaTotal + ObjetoSIEP.SEPARADOR +
		this.conectividade + ObjetoSIEP.SEPARADOR +
		this.velocidadeConexao + ObjetoSIEP.SEPARADOR +
		this.instituicao.getId() + 
		ObjetoSIEP.FINALIZADOR;
		
	}
}
