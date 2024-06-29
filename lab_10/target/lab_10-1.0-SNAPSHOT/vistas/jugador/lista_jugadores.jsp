<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab_10.model.beans.Jugador" %>
<%@ page import="com.example.lab_10.model.daos.SeleccionDAO" %>

<%
    ArrayList<Jugador> lista = (ArrayList<Jugador>) request.getAttribute("listajugador");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lista Jugadores</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
            }


            .sidebar {
                height: 100%;
                width: 0;
                position: fixed;
                z-index: 1;
                top: 0;
                left: 0;
                background-color: #111;
                overflow-x: hidden;
                transition: 0.5s;
                padding-top: 60px;
            }

            .sidebar a {
                padding: 10px 15px;
                text-decoration: none;
                font-size: 25px;
                color: white;
                display: block;
                transition: 0.3s;
            }

            .sidebar a:hover {
                background-color: #575757;
            }

            .sidebar .closebtn {
                position: absolute;
                top: 0;
                right: 25px;
                font-size: 36px;
                margin-left: 50px;
            }


            .openbtn {
                font-size: 20px;
                cursor: pointer;
                background-color: #111;
                color: white;
                padding: 10px 15px;
                border: none;
            }

            .openbtn:hover {
                background-color: #444;
            }


            .custom-alert {
                background-color: rgba(0, 0, 0, 0.8);
                color: white;
                padding: 50px;
                border-radius: 15px;
                position: relative;
                margin-left: 200px;
            }

            .media-content {
                margin-top: 20px;
                text-align: center;
            }

            .media-content img {
                width: 100%;
                max-width: 500px;
                border-radius: 15px;
            }

            .media-content video, .media-content audio {
                width: 100%;
                max-width: 500px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>

        <div id="mySidebar" class="sidebar">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="${pageContext.request.contextPath}/Home?action=pagPrincipal">Bienvenida</a>
            <a href="${pageContext.request.contextPath}/Jugador?action=tablaJugadores">Jugadores</a>
            <a href="${pageContext.request.contextPath}/Estadio?action=tablaEstadios">Estadios</a>
        </div>


        <button class="openbtn" onclick="openNav()">&#9776; Menú</button>
        <div class="container mt-5">
            <h2 class="text-dark">Lista de Jugadores</h2>
            <div class="mb-3">
                <button class="btn btn-primary mb-3" onclick="window.location.href = 'vistas/jugador/registrar_Jugador.jsp';">
                    Nuevo Jugador
                </button><br>
            </div>

            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Nombre</th>
                            <th>Edad</th>
                            <th>Posición</th>
                            <th>Club</th>
                            <th>Selección</th>
                            <th>Accion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (lista != null) {
                                for (Jugador jugador : lista) {
                                    SeleccionDAO seleccionDAO = new SeleccionDAO();
                        %>
                        <tr>
                            <td><%= jugador.getIdJugador() %></td>
                            <td><%= jugador.getNombre() %></td>
                            <td><%= jugador.getEdad() %></td>
                            <td><%= jugador.getPosicion() %></td>
                            <td><%= jugador.getClub() %></td>
                            <td><%= seleccionDAO.obtenerNombreSeleccion(jugador.getSn_idSeleccion()) %></td>
                            <td>
                                <button class="btn btn-danger btn-sm" onclick="return Eliminacion(<%= jugador.getIdJugador() %>, '<%= request.getContextPath() %>');">
                                    Borrar
                                </button>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="7" class="text-center">No hay Jugadores disponibles.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
        <script>
            function Eliminacion(id, contextPath) {
                Swal.fire({
                    title: "¿Estás seguro?",
                    text: "Una vez eliminado, la información asociada al jugador será eliminada del sistema",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#00913f",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Sí, eliminar",
                }).then((result) => {
                    if (result.isConfirmed) {

                        const form = document.createElement('form');
                        form.method = 'post';
                        form.action = contextPath + '/Jugador?action=eliminarJugador';


                        const input = document.createElement('input');
                        input.type = 'hidden';
                        input.name = 'id';
                        input.value = id;

                        form.appendChild(input);
                        document.body.appendChild(form);
                        form.submit();
                    }
                });

                return false;
            }
        </script>
        <script>
            function playAudio() {
                var audio = document.getElementById('welcomeAudio');
                audio.play().catch(function(error) {
                    console.log('Error al reproducir el audio: ' + error);
                });
            }

            function openNav() {
                document.getElementById("mySidebar").style.width = "250px";
                document.querySelector('.custom-alert').style.marginLeft = "250px";
            }

            function closeNav() {
                document.getElementById("mySidebar").style.width = "0";
                document.querySelector('.custom-alert').style.marginLeft = "0";
            }
        </script>
    </body>
</html>
