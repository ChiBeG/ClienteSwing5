package services.domain.persistence;

import java.util.List;

public interface IRepository<T extends Entity> {

    void add(T entity);
    void remove(T entity);
    List<T> findAll();
}
