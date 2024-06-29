package com.example.lab_10.servlet;

import com.example.lab_10.model.beans.Jugador;
import com.example.lab_10.model.beans.Estadio;
import com.example.lab_10.model.beans.Seleccion;
import com.example.lab_10.model.daos.EstadioDAO;
import com.example.lab_10.model.daos.JugadorDAO;
import com.example.lab_10.model.daos.SeleccionDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "JugadorServlet", value = "/Jugador")
public class JugadorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null? "pagPrincipal" : request.getParameter("action");
        String vista;
        RequestDispatcher rd;

        switch(action) {
            case "pagPrincipal":
                vista = "vistas/home/pagina_principal.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;

            case "tablaJugadores":
                JugadorDAO jugadorDAO = new JugadorDAO();
                ArrayList<Jugador> list = jugadorDAO.listarJugadoresTabla();
                request.setAttribute("listajugador", list);
                vista = "/vistas/jugador/lista_jugadores.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("eliminarJugador")) {
            int idJugador = Integer.parseInt(request.getParameter("id"));
            JugadorDAO jugadorDAO = new JugadorDAO();
            jugadorDAO.eliminarJugador(idJugador);
            response.sendRedirect(request.getContextPath() + "/Jugador?action=tablaJugadores");
        } else if(action.equals("registrarJugador")) {
            String nombre = request.getParameter("nombre");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String posicion = request.getParameter("posicion");
            String club = request.getParameter("club");
            String nombreSeleccion = request.getParameter("seleccion");

            Jugador nuevojugador = new Jugador();
            nuevojugador.setNombre(nombre);
            nuevojugador.setEdad(edad);
            nuevojugador.setPosicion(posicion);
            nuevojugador.setClub(club);

            SeleccionDAO seleccionDAO = new SeleccionDAO();
            boolean existeSeleccion = seleccionDAO.existeSeleccionPorNombre(nombreSeleccion);

            if (!existeSeleccion) {
                try {

                    String filePath = getServletContext().getRealPath("/WEB-INF/selecciones.json");


                    FileReader reader = new FileReader(filePath);
                    Type seleccionListType = new TypeToken<List<JsonObject>>(){}.getType();
                    List<JsonObject> selecciones = new Gson().fromJson(reader, seleccionListType);


                    Optional<JsonObject> seleccionOpt = selecciones.stream()
                            .filter(s -> s.get("nombre").getAsString().equalsIgnoreCase(nombreSeleccion))
                            .findFirst();

                    if (seleccionOpt.isPresent()) {
                        JsonObject seleccionJson = seleccionOpt.get();
                        Seleccion seleccion = new Seleccion();
                        seleccion.setNombre(seleccionJson.get("nombre").getAsString());
                        seleccion.setTecnico(seleccionJson.get("tecnico").getAsString());

                        JsonObject estadioJson = seleccionJson.getAsJsonObject("estadio");
                        Estadio estadio = new Estadio();
                        estadio.setNombre(estadioJson.get("nombre").getAsString());
                        estadio.setProvincia(estadioJson.get("provincia").getAsString());
                        estadio.setClub(estadioJson.has("club") && !estadioJson.get("club").isJsonNull() ? estadioJson.get("club").getAsString() : null);


                        EstadioDAO estadioDAO = new EstadioDAO();
                        estadioDAO.agregarEstadio(estadio);

                        int idEstadio = estadioDAO.obtenerIdEstadio(estadio.getNombre());
                        seleccion.setEstadio_idEstadio(idEstadio);
                        seleccionDAO.agregarSeleccion(seleccion);


                        int idSeleccion = seleccionDAO.obtenerIdSeleccion(seleccion.getNombre());
                        nuevojugador.setSn_idSeleccion(idSeleccion);
                    } else {
                        response.getWriter().write("Selección no encontrada en el archivo JSON.");
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                int idSeleccion = seleccionDAO.obtenerIdSeleccion(nombreSeleccion);
                nuevojugador.setSn_idSeleccion(idSeleccion);
            }

            JugadorDAO jugadorDAO = new JugadorDAO();
            jugadorDAO.agregarJugador(nuevojugador);

            response.sendRedirect(request.getContextPath() + "/Jugador?action=tablaJugadores");
        }
    }
}
