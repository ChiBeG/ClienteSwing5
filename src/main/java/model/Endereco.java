package model;

import util.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Value object do Endereço
 */
public class Endereco {

	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private Integer cep;
	private UF uf;
	
	public Endereco(String logradouro, Integer numero, String complemento, String bairro, String cidade, Integer cep, UF uf) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.uf = uf;
	}


	
	/**
	 * Construtor estático que valida os dados e cria o Endereço
	 * 
	 * @param logradouro Logradouro
	 * @param numero Número
	 * @param complemento COmplemento
	 * @param bairro Bairro
	 * @param cidade Cidade
	 * @param cep CEP
	 * @param uf UF
	 * @return Endereço ou lista de erros, em caso de erro de validação
	 */
	public static Result<Endereco, ErroEndereco> of(String logradouro, Integer numero, String complemento, String bairro, String cidade, Integer cep, UF uf) {
		
		// Valida os dados
		var erros = validaDados(logradouro, numero, complemento, bairro, cidade, cep, uf);
				
		// Retorna o par (Endereco, null) ou (null, Lista de erros)
		return erros.isEmpty() ? Result.success(new Endereco(logradouro, numero, complemento, bairro, cidade, cep, uf)) :
			                     Result.failure(erros);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public Integer getCEP() {
		return cep;
	}

	public UF getUf() {
		return uf;
	}

	/**
	 * Valida so dados do enrereço
	 * 
	 * @param logradouro Logradouro
	 * @param numero Numero
	 * @param complemento Complemento
	 * @param bairro Bairro
	 * @param cidade Cidade
	 * @param cep CEP
	 * @param uf UF
	 * @return Lista de erros de validação. Vai estar vazia se não houver nenhum erro.
	 */
	private static List<ErroEndereco> validaDados(String logradouro, Integer numero, String complemento, String bairro, String cidade, Integer cep, UF uf) {
		List<ErroEndereco> erros = new ArrayList<>();
		
		// Valida logradouro
		if (logradouro == null || logradouro.isBlank())
			erros.add(ErroEndereco.LOGRADOURO_INVALIDO);
		
		// Valida numero
		if (numero == null || numero <= 0)
			erros.add(ErroEndereco.NUMERO_INVALIDO);
		
		// Valida bairro
		if (bairro == null || bairro.isBlank())
			erros.add(ErroEndereco.BAIRRO_INVALIDO);
		
		// Valida cidade
		if (cidade == null || cidade.isBlank())
			erros.add(ErroEndereco.CIDADE_INVALIDA);
		
		// Valida CEP
		if (cep == null || cep < 10000000 || cep > 99999999)
			erros.add(ErroEndereco.CEP_INVALIDO);
		
		// Valida UF
		if (uf == null)
			erros.add(ErroEndereco.UF_INVALIDA);
		
		return erros;
	}

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cep=" + cep +
                ", uf=" + uf +
                '}';
    }
}
