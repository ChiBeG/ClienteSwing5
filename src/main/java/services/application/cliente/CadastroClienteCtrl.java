package services.application.cliente;

import model.ClienteBuilder;
import model.ErroCliente;
import services.application.RequestResult;
import services.domain.persistence.ClienteRepository;
import services.domain.persistence.RepositoryPool;

import java.util.List;

/**
 * Classe que implementa o serviço de cadastramento de clientes
 */
public class CadastroClienteCtrl implements CadastroClientePort {
	
	/**
	 * Cria um cliente e salva no banco de dados ou retorna os erros de validação
	 * 
	 * @return Cliente ou lista de erros de validação, em caso de fracasso
	 */
	public RequestResult<List<ErroCliente>> criarCliente(CadastroClienteRequest dto) {

		// Cria o cliente usando o builder
		var builder = new ClienteBuilder();
		
		var result = builder.setCpf(dto.cpf())
				            .setNome(dto.nome())
				            .setDataNasc(dto.dataNasc())
				            .setLogradouro(dto.logradouro())
				            .setNumero(dto.numero())
				            .setComplemento(dto.complemento())
				            .setBairro(dto.bairro())
				            .setCidade(dto.cidade())
				            .setCEP(dto.cep())
				            .setUF(dto.uf())
				            .setDDD(dto.DDD())
				            .setNumeroTel(dto.numeroTel())
				            .build();
		
		// Verifica se houve sucesso ou fracasso
		if (result.isSuccess()) {
			var cliente = result.value();

            // Cria o repositório para persistir o cliente
            // ClienteRepository repo = RepositoryFactory.create(RepoType.CLIENTE);
            // var repo = RepositoryFactory.create(ClienteRepository.class);
            var repo = RepositoryPool.acquire(ClienteRepository.class);

            // Adiciona o cliente no repositório
            repo.add(cliente);
			
			return RequestResult.success();
		}
		else {
			var erros = result.errors();
			
			return RequestResult.failure(erros);
		}
	}
}
