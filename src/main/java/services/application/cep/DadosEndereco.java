package services.application.cep;

/**
 * Dados do endereço retornados pelo serviço de busca pelo CEP
 */
public record DadosEndereco(String logradouro, 
		                    String bairro, 
		                    String cidade,
		                    String uf) {

}

//public class DadosEndereco {
//	private String logradouro;
//	private String bairro;
//	private String cidade;
//	private String uf;
//	private boolean encontrado;
//	
//	public DadosEndereco(String logradouro, String bairro, String cidade, String uf, boolean encontrado) {
//		super();
//		this.logradouro = logradouro;
//		this.bairro = bairro;
//		this.cidade = cidade;
//		this.uf = uf;
//		this.encontrado = encontrado;
//	}
//
//	public String logradouro() {
//		return logradouro;
//	}
//
//	public String bairro() {
//		return bairro;
//	}
//
//	public String cidade() {
//		return cidade;
//	}
//
//	public String uf() {
//		return uf;
//	}
//
//	public boolean encontrado() {
//		return encontrado;
//	}
//}
