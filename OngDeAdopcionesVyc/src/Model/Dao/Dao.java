package Model.Dao;
import java.util.List;
public interface Dao<T> {
    public abstract void save(T t) throws DaoException;
    public abstract List<T> findAll() throws DaoException;
    public abstract T findById(int id) throws DaoException;
    public abstract void update(T t) throws DaoException;
    public abstract void delete(int id) throws DaoException;
    
}
