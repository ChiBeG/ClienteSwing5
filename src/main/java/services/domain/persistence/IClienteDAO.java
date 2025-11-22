package services.domain.persistence;

public interface IClienteDAO extends IDAO<ClienteDTO>{
    ClienteDTO findByCPF(Long cpf);
}
