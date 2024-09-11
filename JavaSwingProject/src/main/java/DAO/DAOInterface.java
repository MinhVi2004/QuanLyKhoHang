
package DAO;

import java.util.List;


public interface DAOInterface<T> {

    List<T> getAll(Integer maKhoHang);

    T getById(Integer id);


    boolean create(Integer maKhoHang, T t);

    boolean update(T t);

    boolean delete(T t);

}
