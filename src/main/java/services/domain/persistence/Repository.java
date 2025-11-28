package services.domain.persistence;


public abstract class Repository{

    // private final IClienteDAO dao = DAOFactory.create(DAOType.CLIENTE);

    // @Override
    // public void add() {
    //     if (entity.getId() == null) {
    //         // Gera ID do objeto para inseri-lo no BD
    //         entity.setId(UUID.randomUUID());
    //         dao.insert(ClienteDTO.fromEntity(entity));
    //     }
    //     else {
    //         dao.update(ClienteDTO.fromEntity(entity));
    //     }
    // }

    // @Override
    // public void remove() {
    //     if (entity.getId() != null) {
    //         dao.delete(ClienteDTO.fromEntity(entity));
    //         // Define ID = nulo porque objeto está somente na memória e não mais no BD
    //         entity.setId(null);
    //     }
    // }

    // @Override
    // public List<Cliente> findAll() {
    //     List<Cliente> entidades = new ArrayList<>();

    //     // Cria um carro para cada DTO
    //     for (var dto : dao.findAll()) {
    //         entidades.add(ClienteBuilder.buildFromDTO(dto));
    //     }

    //     return entidades;
    // }


}
