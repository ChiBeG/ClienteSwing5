package adapters.infrastructure.viacep;

/**
 * Dados do endereço recebidos no JSON do serviço ViaCEP
 */
public record DadosViaCEP(
		String cep,
		String logradouro,
		String complemento,
		String bairro,
		String localidade,
		String uf,
		String ibge,
		String erro) {
}

//public class DadosViaCEP {
//	private String cep;
//	private String logradouro;
//	private String complemento;
//	private String bairro;
//	private String localidade;
//	private String uf;
//	private String ibge;
//	private String erro;
//	
//	public DadosViaCEP(String cep, 
//			           String logradouro, 
//			           String complemento, 
//			           String bairro, 
//			           String localidade, 
//			           String uf,
//			           String ibge, 
//			           String erro) {
//		super();
//		this.cep = cep;
//		this.logradouro = logradouro;
//		this.complemento = complemento;
//		this.bairro = bairro;
//		this.localidade = localidade;
//		this.uf = uf;
//		this.ibge = ibge;
//		this.erro = erro;
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
//	public String erro() {
//		return erro;
//	}
//}
