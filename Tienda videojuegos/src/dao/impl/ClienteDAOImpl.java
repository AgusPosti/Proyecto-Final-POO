package dao.impl;

import dao.ClienteDAO;
import models.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private final String url =
            "jdbc:mysql://localhost:3306/tienda_videojuegos";

    private final String usuario = "root";

    private final String password = "Chandi123Chandi123";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }
    
@Override
public void agregar(Cliente cliente) throws SQLException {

    String sql = "INSERT INTO cliente "
            + "(nombre, apellido, dni, telefono, email) "
            + "VALUES (?, ?, ?, ?, ?)";

    try (Connection conexion = conectar();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setString(1, cliente.getNombre());
        stmt.setString(2, cliente.getApellido());
        stmt.setString(3, cliente.getDni());
        stmt.setString(4, cliente.getTelefono());
        stmt.setString(5, cliente.getEmail());

        stmt.executeUpdate();
    }
}

@Override
public Cliente buscarPorId(int id) throws SQLException {

    String sql = "SELECT * FROM cliente WHERE id = ?";

    try (Connection conexion = conectar();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setInt(1, id);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
            }
        }
    }

    return null;
}

@Override
public List<Cliente> listarTodos() throws SQLException {

    List<Cliente> clientes = new ArrayList<>();

    String sql = "SELECT * FROM cliente";

    try (Connection conexion = conectar();
         PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getString("telefono"),
                    rs.getString("email")
            );

            clientes.add(cliente);
        }
    }

    return clientes;
}

@Override
public void modificar(Cliente cliente) throws SQLException {

    String sql = "UPDATE cliente SET "
            + "nombre = ?, "
            + "apellido = ?, "
            + "dni = ?, "
            + "telefono = ?, "
            + "email = ? "
            + "WHERE id = ?";

    try (Connection conexion = conectar();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setString(1, cliente.getNombre());
        stmt.setString(2, cliente.getApellido());
        stmt.setString(3, cliente.getDni());
        stmt.setString(4, cliente.getTelefono());
        stmt.setString(5, cliente.getEmail());
        stmt.setInt(6, cliente.getId());

        stmt.executeUpdate();
    }
}

@Override
public void eliminar(int id) throws SQLException {

    String sql = "DELETE FROM cliente WHERE id = ?";

    try (Connection conexion = conectar();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setInt(1, id);

        stmt.executeUpdate();
    }
}

    @Override
    public Cliente buscarPorDni(String dni) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
