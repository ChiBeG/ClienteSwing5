package services.application.cep;

import services.application.RequestResult;

/**
 * Interface do serviço de busca de endereço pelo CEP
 */
public interface CEPPort {
	RequestResult<DadosEndereco> buscaPorCEP(Integer cep);
}
