package adapters.infrastructure.opencep;

/**
 * Dados do endereço recebidos no JSON do serviço OpenCEP
 */
public record DadosOpenCEP(
	String cep,
	String logradouro,
	String complemento,
	String bairro,
	String localidade,
	String uf,
	String ibge,
	String error) {
}

//public class DadosOpenCEP {
//	private String cep;
//	private String logradouro;
//	private String complemento;
//	private String bairro;
//	private String localidade;
//	private String uf;
//	private String ibge;
//	private String error;
//	
//	public DadosOpenCEP(String cep, 
//                      String logradouro, 
//                      String complemento, 
//                      String bairro,
//                      String localidade, String uf,
//			            String ibge, 
//                      String error) {
//		super();
//		this.cep = cep;
//		this.logradouro = logradouro;
//		this.complemento = complemento;
//		this.bairro = bairro;
//		this.localidade = localidade;
//		this.uf = uf;
//		this.ibge = ibge;
//		this.error = error;
//	}
//	
//	public String cep() {
//		return cep;
//	}
//
//	public String logradouro() {
//		return logradouro;
//	}
//
//	public String complemento() {
//		return complemento;
//	}
//
//	public String bairro() {
//		return bairro;
//	}
//
//	public String localidade() {
//		return localidade;
//	}
//
//	public String uf() {
//		return uf;
//	}
//
//	public String ibge() {
//		return ibge;
//	}
//
//	public String error() {
//		return error;
//	}
//}
