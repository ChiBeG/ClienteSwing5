package services.application.cep;

import services.application.RequestResult;

/**
 * Serviço de busca pelo CEP
 */
public class CEPService implements CEPPort {

	private IBuscaCEP servicoBusca;
	
	public CEPService(IBuscaCEP servicoBusca) {
		this.servicoBusca = servicoBusca;
	}
	
	@Override
	public RequestResult<DadosEndereco> buscaPorCEP(Integer cep) { 
		
		var dados = servicoBusca.buscaEnderecoPorCEP(cep);
		
		return dados;
	}
}
