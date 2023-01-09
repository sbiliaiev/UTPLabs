package repository;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    List<T> findByName(String query);
    T findById(int id);
    T add(T dto);
    void update(T dto);
    void addOrUpdate(T dto);
    void delete(T dto);
    int getCount();
    boolean exists(T dto);
}
