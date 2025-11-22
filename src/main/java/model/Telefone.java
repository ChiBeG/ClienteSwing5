package model;

import util.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Value object do telefone
 */
public class Telefone {

	private Integer ddd;
	private Integer numero;
	
	public Telefone(Integer ddd, Integer numero) {
		super();
		this.ddd = ddd;
		this.numero = numero;
	}
	
	/**
	 * Construtor estático do telefone
	 * 
	 * @param ddd DDD
	 * @param numero Número do telefone
	 * @return Telefone ou lista de erros, em caso de erros de validação
	 */
	public static Result<Telefone, ErroTelefone> of(Integer ddd, Integer numero) {
		
		// Valida os dados
		var erros = validaDados(ddd, numero);
				
		// Retorna o par (Telefone, null) ou (null, Lista de erros)
		return erros.isEmpty() ? Result.success(new Telefone(ddd, numero)) :
			                     Result.failure(erros);
	}

	public Integer getDdd() {
		return ddd;
	}

	public Integer getNumero() {
		return numero;
	}

	/**
	 * Valida os dados do telefone
	 * 
	 * @param ddd DDD
	 * @param numero Número do telefone
	 * @return Lista de erros de validação. Vai estar vazia se não houver nenhum erro.
	 */
	private static List<ErroTelefone> validaDados(Integer ddd, Integer numero) {
		List<ErroTelefone> erros = new ArrayList<>();
		
		// Valida DDD
        if (ddd == null || ddd < 11 || ddd > 99)
			erros.add(ErroTelefone.DDD_INVALIDO);
		
		// Valida número
		if (numero == null || numero <= 20000000 || numero > 999999999)
			erros.add(ErroTelefone.NUMERO_TEL_INVALIDO);
		
		return erros;
	}

    @Override
    public String toString() {
        return "Telefone{" +
                "ddd=" + ddd +
                ", numero=" + numero +
                '}';
    }
}
