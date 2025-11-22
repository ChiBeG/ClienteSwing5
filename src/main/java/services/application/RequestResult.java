package services.application;

/**
 * Classe que representa o resultado de um request
 * 
 * @param <T> Tipo do objeto retornado no payload
 */
public class RequestResult<T> {

	/**
	 * Indicador de sucesso ou fracasso do request
	 */
	private boolean success;
	
	/**
	 * Informação adicional sobre a resposta do request
	 */
	private T payload;
	
	private RequestResult(boolean success, T payload) {
		this.success = success;
		this.payload = payload;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public boolean isFailure() {
		return ! success;
	}
	
	public T payload() {
		return payload;
	}
	
	/**
	 * Cria um resultado COM sucesso SEM payload
	 * 
	 * @param <T> 
	 * @return Objeto RequestResult
	 */
	public static <T> RequestResult<T> success() {
		return new RequestResult<T>(true, null);
	}
	
	/**
	 * Cria um resultado COM sucesso COM payload
	 * 
	 * @param <T> Tipo do payload
	 * @param payload Payload
	 * @return Objeto RequestResult
	 */
	public static <T> RequestResult<T> success(T payload) {
		return new RequestResult<T>(true, payload);
	}
	
	/**
	 * Cria um resultado SEM sucesso SEM payload
	 * @param <T> Tipo do payload
	 * @return Objeto RequestResult
	 */
	public static <T> RequestResult<T> failure() {
		return new RequestResult<T>(false, null);
	}
	
	/**
	 * Cria um resultado SEM sucesso COM payload
	 * @param <T> Tipo do payload
	 * @param payload Payload
	 * @return Objeto RequestResult
	 */
	public static <T> RequestResult<T> failure(T payload) {
		return new RequestResult<T>(false, payload);
	}
}
