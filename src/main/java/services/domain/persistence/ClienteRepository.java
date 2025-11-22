package services.domain.persistence;

import model.Cliente;
import model.ClienteBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClienteRepository extends Repository<Cliente> {

    private final IClienteDAO dao = DAOFactory.create(DAOType.CLIENTE);

    public Cliente findByCPF(Long cpf) {
        var dto = dao.findByCPF(cpf);

        return dto == null ? null : ClienteBuilder.buildFromDTO(dto);
    }

    @Override
    public void add(Cliente entity) {
        if (entity.getId() == null) {
            // Gera ID do objeto para inseri-lo no BD
            entity.setId(UUID.randomUUID());
            dao.insert(ClienteDTO.fromEntity(entity));
        }
        else {
            dao.update(ClienteDTO.fromEntity(entity));
        }
    }

    @Override
    public void remove(Cliente entity) {
        if (entity.getId() != null) {
            dao.delete(ClienteDTO.fromEntity(entity));
            // Define ID = nulo porque objeto está somente na memória e não mais no BD
            entity.setId(null);
        }
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();

        // Cria um carro para cada DTO
        for (var dto : dao.findAll()) {
            clientes.add(ClienteBuilder.buildFromDTO(dto));
        }

        return clientes;
    }
}
