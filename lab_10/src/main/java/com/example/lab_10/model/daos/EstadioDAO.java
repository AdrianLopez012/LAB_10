package com.example.lab_10.model.daos;

import com.example.lab_10.model.beans.Estadio;
import com.example.lab_10.model.beans.Jugador;

import java.sql.*;
import java.util.ArrayList;

public class EstadioDAO extends BaseDAO{
    public void agregarEstadio(Estadio estadio) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "1234";
        String url = "jdbc:mysql://34.74.105.244:3306/lab7";

        String sql = "INSERT INTO estadio (nombre,provincia,club) VALUES ( ?, ?,?)";
        try (Connection conn= DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estadio.getNombre());
            pstmt.setString(2, estadio.getProvincia());
            pstmt.setString(3,estadio.getClub());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int obtenerIdEstadio(String nombreEstadio) {
        int idEstadio = 0;

        try {
            Class.forName( "com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "1234";
        String url = "jdbc:mysql://34.74.105.244:3306/lab7";

        String sql = "SELECT idEstadio FROM estadio WHERE nombre = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreEstadio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idEstadio = rs.getInt("idEstadio");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idEstadio;
    }

    public void insertarFilaPorDefecto() {
        String checkIfExistsSql = "SELECT COUNT(*) FROM estadio WHERE idEstadio = 1000";
        String insertDefaultSql = "INSERT INTO estadio (idEstadio, nombre, provincia, club) " +
                "VALUES (1000, 'NULL', 'NULL', 'NULL')";

        try (Connection conn = this.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkIfExistsSql);
             ResultSet rs = checkStmt.executeQuery()) {

            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertDefaultSql)) {
                    insertStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Estadio> listarEstadioTabla() {
        ArrayList<Estadio> listarEstadios = new ArrayList<>();
        String selectSql = "SELECT * FROM estadio";

        try (Connection conn = this.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery()) {

            // Listar los estadios
            while (rs.next()) {
                Estadio estadio = new Estadio();
                estadio.setIdEstadio(rs.getInt("idEstadio"));
                estadio.setNombre(rs.getString("nombre"));
                estadio.setProvincia(rs.getString("provincia"));
                estadio.setClub(rs.getString("club"));

                listarEstadios.add(estadio);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listarEstadios;
    }
    public void actualizarYEliminarEstadio(int idEstadio) {
        String updateSeleccionQuery = "UPDATE seleccion SET estadio_idEstadio = 1000 WHERE estadio_idEstadio = ?";
        String deleteEstadioQuery = "DELETE FROM estadio WHERE idEstadio = ?";

        try (Connection conn = this.getConnection()) {
            conn.setAutoCommit(false);  // Deshabilitar el auto-commit

            try (PreparedStatement updateSeleccionStmt = conn.prepareStatement(updateSeleccionQuery);
                 PreparedStatement deleteEstadioStmt = conn.prepareStatement(deleteEstadioQuery)) {


                updateSeleccionStmt.setInt(1, idEstadio);
                updateSeleccionStmt.executeUpdate();


                deleteEstadioStmt.setInt(1, idEstadio);
                deleteEstadioStmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al actualizar y eliminar el estadio", e);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la conexión", e);
        }
    }

}
