package com.example.lab_10.model.daos;

import com.example.lab_10.model.beans.Jugador;

import java.sql.*;
import java.util.ArrayList;

public class JugadorDAO extends BaseDAO {

    public ArrayList<Jugador> listarJugadoresTabla() {
        ArrayList<Jugador> listarJugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugador";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setIdJugador(rs.getInt("idJugador"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setEdad(rs.getInt("edad"));
                jugador.setPosicion(rs.getString("posicion"));
                jugador.setClub(rs.getString("club"));
                jugador.setSn_idSeleccion(rs.getInt("sn_idSeleccion"));

                listarJugadores.add(jugador);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listarJugadores;
    }

    public void eliminarJugador(int idJugador) {
        String deleteQuery = "DELETE FROM jugador WHERE idJugador = ?";

        Connection conn = null;

        try {
            conn = this.getConnection();
            conn.setAutoCommit(false); // Inicia la transacción

            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                deleteStmt.setInt(1, idJugador);
                deleteStmt.executeUpdate();

                conn.commit(); // Confirma la transacción
            } catch (SQLException e) {
                if (conn != null) {
                    conn.rollback(); // Revierte la transacción en caso de error
                }
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaura el modo de auto-commit
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void agregarJugador(Jugador jugador) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "1234";
        String url = "jdbc:mysql://34.74.105.244:3306/lab7";

        String sql = "INSERT INTO jugador (nombre,edad,posicion,club,sn_idSeleccion) VALUES ( ?, ?,?, ?, ?)";
        try (Connection conn= DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, jugador.getNombre());
            pstmt.setInt(2, jugador.getEdad());
            pstmt.setString(3,jugador.getPosicion());
            pstmt.setString(4,jugador.getClub());
            pstmt.setInt(5,jugador.getSn_idSeleccion());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

