package services.domain.persistence;

import model.Cliente;
import model.ClienteBuilder;

public class ClienteRepository extends Repository<Cliente, ClienteDTO> {

    @Override
    protected IDAO<ClienteDTO> getDAO() {
        return DAOFactory.create(DAOType.CLIENTE);
    }

    @Override
    protected ClienteDTO toDTO(Cliente entity) {
        return ClienteDTO.fromEntity(entity);
    }

    @Override
    protected Cliente toEntity(ClienteDTO dto) {
        return ClienteBuilder.buildFromDTO(dto);
    }

    public Cliente findByCPF(Long cpf) {
        var dao = getDAO();
        var dto = dao.findByCPF(cpf);
        return dto == null ? null : toEntity(dto);
    }
}
