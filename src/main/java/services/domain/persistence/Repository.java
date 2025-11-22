package services.domain.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Cliente;
import model.ClienteBuilder;

public abstract class Repository<T> implements IRepository<T>{

        private final IClienteDAO dao = DAOFactory.create(DAOType.CLIENTE);

    public Cliente findByCPF(Long cpf) {
        var dto = dao.findByCPF(cpf);

        return dto == null ? null : ClienteBuilder.buildFromDTO(dto);
    }

    @Override
    public void add() {
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
    public void remove() {
        if (entity.getId() != null) {
            dao.delete(ClienteDTO.fromEntity(entity));
            // Define ID = nulo porque objeto está somente na memória e não mais no BD
            entity.setId(null);
        }
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> entidades = new ArrayList<>();

        // Cria um carro para cada DTO
        for (var dto : dao.findAll()) {
            entidades.add(ClienteBuilder.buildFromDTO(dto));
        }

        return entidades;
    }


 
}
