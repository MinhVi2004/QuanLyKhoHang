package DAO;

import java.util.ArrayList;

public interface DAOAccount<T> {

    ArrayList<T> getAllAccounts();

    int insert(T t);

    int update(T t);

    int delete(T t);

    T getUserByUserName(String t);
}
