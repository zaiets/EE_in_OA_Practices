package Practice01.jdbc;

import java.util.Set;

public interface IDao<T> {
    T create(T t);
    T read(int id);
    Set<T> readAll();
    T update(T t);
    T delete(T t);
}
