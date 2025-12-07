package services.domain.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Repository<T extends Entity, D extends EntityDTO> implements IRepository<T> {

    protected abstract IDAO<D> getDAO();

    protected abstract D toDTO(T entity);

    protected abstract T toEntity(D dto);

    @Override
    public void add(T entity) {
        if (entity.getId() == null) {
            // Gera ID do objeto para inseri-lo no BD
            entity.setId(UUID.randomUUID());
            getDAO().insert(toDTO(entity));
        }
        else {
            getDAO().update(toDTO(entity));
        }
    }

    @Override
    public void remove(T entity) {
        if (entity.getId() != null) {
            getDAO().delete(toDTO(entity));
            entity.setId(null);
        }
    }

    @Override
    public List<T> findAll() {
        List<T> entidades = new ArrayList<>();
        for (var dto : getDAO().findAll()) {
            entidades.add(toEntity(dto));
        }

        return entidades;
    }
}
