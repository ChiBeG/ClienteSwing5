package services.application.cliente;

import model.ErroCliente;
import services.application.RequestResult;

import java.util.List;

/**
 * Interface que define o serviço de cadastro de clientes
 */
public interface CadastroClientePort {

	RequestResult<List<ErroCliente>> criarCliente(CadastroClienteRequest dto);

}