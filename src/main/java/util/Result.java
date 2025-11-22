package util;

import java.util.List;

/**
 * Classe que armazena o resultado de uma operação ou uma lista de erros, 
 * caso a operação não seja bem sucedida
 * O resultado é armazenado em value e a lista de erros é armazenada em errors.
 * Se value estiver preenchido, então errors é nulo.
 * Se errors estiver preenchido, então value é nulo.
 * 
 * @param <T> Tipo do resultado
 * @param <S> Tipo armazenado na lista errors
 */
public class Result<T, S> {

	private T value;
	private List<S> errors;
	
	/**
	 * Construtor privado, ou seja, o objeto não pode ser construído diretamente.
	 * O objeto Result vai ser criado pelo métodos success ou failure
	 * 
	 * @param value Valor a ser armazenado em value
	 * @param errors Lista de erros a serem armazenados em errors
	 */
	private Result(T value, List<S> errors) {
		this.value = value;
		this.errors = errors;
	}
	
	/**
	 * Retorna o valor do resultado
	 * 
	 * @return Valor
	 */
	public T value() {
		return value;
	}
	
	/**
	 * Retorna a lista de erros
	 * 
	 * @return Lista de erros
	 */
	public List<S> errors() {
		return errors;
	}
	
	/**
	 * Indica se o resultado foi bem sucedido
	 * 
	 * @return true, se existe conteúdo em value, false caso contrário.
	 */
	public boolean isSuccess() {
		return value != null;
	}
	
	/**
	 * Indica se o resultado foi mal sucedido
	 * 
	 * @return true, se existe conteúdo em errors, false caso contrário.
	 */
	public boolean isFailure() {
		return errors != null;
	}
	
	/**
	 * Método factory que cria um Result com value
	 * 
	 * @param <T> Tipo do value
	 * @param <S> Tipo armazenado na lista de erros
	 * @param value Valor da operação bem sucedida
	 * @return Objeto Result com value e errors nulo
	 */
	public static <T, S> Result<T, S> success(T value) {
		return new Result<T, S>(value, null);
	}
	
	/**
	 * Método factory que cria um Result com errors
	 * 
	 * @param <T> Tipo do value
	 * @param <S> Tipo armazenado na lista de erros
	 * @param value Valor da operação bem sucedida
	 * @return Objeto Result com errors e value nulo
	 */
	public static <T, S> Result<T, S> failure(List<S> errors) {
		return new Result<T, S>(null, errors);
	}
}
