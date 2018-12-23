package br.gov.mec.siga.edu.nucleo.dominio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.mec.siep.modelo.auxiliar.ChaveEstrangeira;
import br.mec.siep.modelo.auxiliar.ChavePrimaria;

/**
 *
 *
 *
 */


@MappedSuperclass
public class ObjetoSIEP{

	public static final int IGUAL = 0;
	public static final int NOVO = 1;
	public static final int ALTERADO = 2;
	public static final int REMOVIDO = 3;
	private int status;
	
	public static final String SEPARADOR = "£";
	public static final String FINALIZADOR = "¢";

	@OneToMany(cascade={CascadeType.ALL})
	private Collection<ChavePrimaria> chavesPrimarias;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private Collection<ChaveEstrangeira> chavesEstrangeiras;

	@Transient
	String dados;
    @Transient
	List<String> nomesAtributosRelacionamentos;

	public ObjetoSIEP() {
		
	}

	public Collection<ChavePrimaria> getChavesPrimarias() {
		return chavesPrimarias;
	}

	public void setChavesPrimarias(Collection<ChavePrimaria> chavesPrimarias) {
		this.chavesPrimarias = chavesPrimarias;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public Collection<ChaveEstrangeira> getChavesEstrangeiras() {
		return chavesEstrangeiras;
	}

	public void setChavesEstrangeiras(
			Collection<ChaveEstrangeira> chavesEstrangeiras) {
		this.chavesEstrangeiras = chavesEstrangeiras;
	}

	/**
	 *
	 * @return uma lista de Strings com todos os dados da entidade
	 */
	public String getDados() {
		return dados;
	}

    public List<String> getNomesAtributosRelacionamentos() {
        return nomesAtributosRelacionamentos;
    }

    public void setNomesAtributosRelacionamentos(List<String> nomesAtributosRelacionamentos) {
        this.nomesAtributosRelacionamentos = nomesAtributosRelacionamentos;
    }

	public String formatarData  (Calendar data){
        if(data != null){
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = formatador.format(data.getTime());
            return dataFormatada;
        }
        return "--/--/----";
	}
	
	public String formatarData  (Date data){
        if(data != null){
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = formatador.format(data.getTime());
            return dataFormatada;
        }
        return "--/--/----";
	}

}
