package services.application.cep;

import services.application.RequestResult;

/**
 * Essa interface define os métodos que o serviço de busca de endereço por CEP deve implementar
 */
public interface IBuscaCEP {
	RequestResult<DadosEndereco> buscaEnderecoPorCEP(Integer cep);
}
