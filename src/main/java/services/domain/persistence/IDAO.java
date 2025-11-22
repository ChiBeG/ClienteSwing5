package services.domain.persistence;

import java.util.List;

public interface IDAO<T extends EntityDTO> {

    void insert(T dto);
    void update(T dto);
    void delete(T dto);
    List<T> findAll();
}
