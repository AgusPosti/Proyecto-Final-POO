package dao;

import java.sql.SQLException;
import models.Cliente;

public interface ClienteDAO extends DAO<Cliente> {

    Cliente buscarPorDni(String dni) throws SQLException;
}