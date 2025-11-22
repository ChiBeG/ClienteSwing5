package model;

import java.util.Objects;

/**
 * Value Object do CPF
 */
public class CPF {

	/**
	 * Armazena o numero do CPF propriamente dito
	 */
	private Long numero;
	
	private CPF(Long numero) {
		this.numero = numero;
	}
	
	/**
	 * Retorna o valor do CPF, ou seja, o número propriamente dito
	 * @return Número do CPF
	 */
	public Long numero() {
		return numero;
	}
	
	/**
	 * Cria um objeto CPF somente se o valor representar um cpf válido
	 * 
	 * @param numero Número do CPF
	 * @return Objeto CPF ou nulo, em caso de número inválido
	 */
	public static CPF of(Long numero) {
		// Se número do CPF for inválido, então retorna null
		return numero != null && validaCPF(numero) ? new CPF(numero) : null;
	}

	/**
	 * Valida o número do CPF
	 * 
	 * @param numero Número do CPF
	 * @return True, se for válido; ou false, caso contrário
	 */
	private static boolean validaCPF(long numero) {
		int primeiroDV, segundoDV, soma, resto, j, k;
	    long numeroCPF;
	    int mult[] = new int[]{ 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

	    // Se cpf não está no intervalo válido então é inválido
	    if (numero < 1000000000L || numero > 99999999999L)
	        return false;
		
		long mask = numero >= 10000000000L ? 11111111111L : 1111111111L;
		
		// Se cpf tem todos os dígitos iguais então é inválido
		if (numero % mask == 0)
			return false;
		
		int digitos = numero >= 10000000000L ? 9 : 8;

	    // Pega o primeiro e o segundo DV do cpf
	    primeiroDV = (int) (numero % 100 / 10);
	    segundoDV  = (int) (numero % 10);

	    // Calcula primeiro DV
	    numeroCPF = numero / 100; // Número do cpf sem os DVs
	    soma = 0;
	    for (int i = 0; i < digitos; i++)
	    {
	        soma += (numeroCPF % 10) * mult[i];
	        numeroCPF /= 10;
	    }

	    resto = soma % 11;

	    if (resto == 0 || resto == 1)
	        j = 0;
	    else
	        j = 11 - resto;

	    // Compara o primeiro DV calculado com o informado
	    // Se é inválido, então nem precisa calcular o segundo DV
	    if (j != primeiroDV)
	        return false;

	    // Calcula segundo DV
	    numeroCPF = numero / 10;  // Número do cpf sem segundo DV
	    soma = 0;
	    for (int i = 0; i < digitos+1; i++)
	    {
	        soma += (numeroCPF % 10) * mult[i];
	        numeroCPF /= 10;
	    }

	    resto = soma % 11;

	    if (resto == 0 || resto == 1)
	        k = 0;
	    else
	        k = 11 - resto;

	    // Compara o segundo DV calculado com o informado
	    return k == segundoDV;
	}

    @Override
    public String toString() {
        return "CPF{" +
                "numero=" + numero +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return Objects.equals(numero, cpf.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }
}

