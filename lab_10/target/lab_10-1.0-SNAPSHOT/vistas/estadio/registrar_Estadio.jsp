<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Registrar Estadio</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
            }

            /* Sidebar styles */
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

            /* Open/Close button styles */
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

            /* Content styles */
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
        <!-- Sidebar -->
        <div id="mySidebar" class="sidebar">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="${pageContext.request.contextPath}/Home?action=pagPrincipal">Bienvenida</a>
            <a href="${pageContext.request.contextPath}/Jugador?action=tablaJugadores">Jugadores</a>
            <a href="${pageContext.request.contextPath}/Estadio?action=tablaEstadios">Estadios</a>
        </div>

        <!-- Open button -->
        <button class="openbtn" onclick="openNav()">&#9776; Menú</button>
        <div class="container mt-5">
            <h2 class="mb-4">Nuevo Estradiol</h2>
            <form action="${pageContext.request.contextPath}/Estadio?action=registrarEstadio" method="post" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="nombre">Nombre :</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" maxlength="50" required>
                    <div class="invalid-feedback">Por favor, ingrese el nombre .</div>
                </div>
                <div class="form-group">
                    <label for="provincia">Provincia:</label>
                    <input type="text" class="form-control" id="provincia" name="provincia" placeholder="provincia" maxlength="50" required>
                    <div class="invalid-feedback">Por favor, ingrese el nombre .</div>
                </div>
                <div class="form-group">
                    <label for="club">Club:</label>
                    <input type="text" class="form-control" id="club" name="club" placeholder="club" maxlength="50" required>
                    <div class="invalid-feedback">Por favor, ingrese el nombre.</div>
                </div>

                <button type="submit" class="btn btn-primary" onclick="return Confirmacion();">Registrar</button>
                <button type="submit" class="btn btn-secondary" onclick="history.back();">Cancelar</button>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>

            (function () {
                'use strict'
                window.addEventListener('load', function () {

                    var forms = document.getElementsByClassName('needs-validation')

                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault()
                                event.stopPropagation()
                            }
                            form.classList.add('was-validated')
                        }, false)
                    })
                }, false)
            })()

            function Confirmacion() {
                Swal.fire({
                    title: "¿Estás seguro?",
                    text: "El proceso se dará",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#00913f",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Sí, registrar",
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire({
                            title: "¡Registrado!",
                            text: "El Estadio ha sido registrado con éxito",
                            icon: "success",
                        }).then(() => {
                            document.querySelector("form").submit();
                        });
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
