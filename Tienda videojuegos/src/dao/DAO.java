package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    void agregar(T entity) throws SQLException;

    T buscarPorId(int id) throws SQLException;

    List<T> listarTodos() throws SQLException;

    void modificar(T entity) throws SQLException;

    void eliminar(int id) throws SQLException;
}