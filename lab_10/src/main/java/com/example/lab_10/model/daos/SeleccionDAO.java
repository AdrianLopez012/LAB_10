package com.example.lab_10.model.daos;
import com.example.lab_10.model.beans.Jugador;
import com.example.lab_10.model.beans.Seleccion;

import java.sql.*;

public class SeleccionDAO extends BaseDAO {


    public String obtenerNombreSeleccion(int sn_idSeleccion) {
        String nombre = null;

        try {
            Class.forName( "com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "1234";
        String url = "jdbc:mysql://34.74.105.244:3306/lab7";

        String sql = "SELECT nombre FROM seleccion WHERE idSeleccion = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sn_idSeleccion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombre");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nombre;
    }

    public int obtenerIdSeleccion(String nombreSeleccion) {
        int idSeleccion = 0;

        try {
            Class.forName( "com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "1234";
        String url = "jdbc:mysql://34.74.105.244:3306/lab7";

        String sql = "SELECT idSeleccion FROM seleccion WHERE nombre = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreSeleccion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idSeleccion = rs.getInt("idSeleccion");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idSeleccion;
    }

    public boolean existeSeleccionPorNombre(String nombre) {
        String sql = "SELECT idSeleccion FROM seleccion WHERE nombre = ?";
        boolean existe = false;

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return existe;
    }

    public void agregarSeleccion(Seleccion seleccion) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "1234";
        String url = "jdbc:mysql://34.74.105.244:3306/lab7";

        String sql = "INSERT INTO seleccion (nombre,tecnico,estadio_idEstadio) VALUES ( ?, ?,?)";
        try (Connection conn= DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, seleccion.getNombre());
            pstmt.setString(2, seleccion.getTecnico());
            pstmt.setInt(3,seleccion.getEstadio_idEstadio());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
