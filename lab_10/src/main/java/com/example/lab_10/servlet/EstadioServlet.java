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

@WebServlet(name = "EstadioServlet", value = "/Estadio")
public class EstadioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null? "pagPrincipal" : request.getParameter("action");
        String vista;
        RequestDispatcher rd;
        EstadioDAO estadioDAO = new EstadioDAO();
        estadioDAO.insertarFilaPorDefecto();


        switch(action) {
            case "pagPrincipal":
                vista = "vistas/home/pagina_principal.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;

            case "tablaEstadios":
                ArrayList<Estadio> list = estadioDAO.listarEstadioTabla();
                request.setAttribute("listaestadio", list);
                vista = "/vistas/estadio/lista_estadios.jsp";
                rd = request.getRequestDispatcher(vista);
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("eliminarEstadio")) {
            int idEstadio = Integer.parseInt(request.getParameter("id"));
            EstadioDAO estadioDAO = new EstadioDAO();
            estadioDAO.actualizarYEliminarEstadio(idEstadio);
            response.sendRedirect(request.getContextPath() + "/Estadio?action=tablaEstadios");
        }else if(action.equals("registrarEstadio")) {
            String nombre = request.getParameter("nombre");
            String provincia = request.getParameter("provincia");
            String club = request.getParameter("club");


            Estadio nuevoEstadio = new Estadio();
            nuevoEstadio.setNombre(nombre);
            nuevoEstadio.setProvincia(provincia);
            nuevoEstadio.setClub(club);


            // Insertar el nuevo jugador
            EstadioDAO estadioDAO = new EstadioDAO();
            estadioDAO.agregarEstadio(nuevoEstadio);

            response.sendRedirect(request.getContextPath() + "/Estadio?action=tablaEstadios");
        }

    }
}
