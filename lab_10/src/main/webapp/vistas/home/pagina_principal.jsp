<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bienvenidos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
    <body onload="playAudio()">


        <div id="mySidebar" class="sidebar">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="${pageContext.request.contextPath}/Home?action=pagPrincipal">Bienvenida</a>
            <a href="${pageContext.request.contextPath}/Jugador?action=tablaJugadores">Jugadores</a>
            <a href="${pageContext.request.contextPath}/Estadio?action=tablaEstadios">Estadios</a>
        </div>


        <button class="openbtn" onclick="openNav()">&#9776; Menú</button>

        <div class="container mt-5">
            <div class="alert custom-alert" role="alert">
                <h1 class="text-center">Bienvenidos a nuestra página</h1>
                <p>
                    Bienvenidos en esta pagina se podrá tanto registrar a sus jugadores favoritos donde podrá poner tanto el club donde juegan
                    como también los paises pero que más grande que eso comparado con la grandez de Alianza Lima el equipo más ganador más querido
                    El equipo donde ustede aprendio sobre futbol el equipo donde se reparte chocolate y de donde salieron los mejores futbolistas del mundo
                    no como las gallinas que chiste es eso.
                </p>
                <p>
                    Yo tengo una frase en Perú solo existen dos vertientes en la hinchada del futbol de clubes peruanos
                    La primera es que hay un grupo de Alianzistas y el otro gente AntiAlianza que todavía no sabe o que si sabe pero no quiere demostrar
                    que también  en el fondo es hincha de Alianza no hay más si hablamos de futbol el mensaje se queda largo.
                </p>
                <p>
                    DISFRUTEN DE LA PAGINA Y NO SE PES QUE QUIEREN AGREGA TU JUGADOR TU ESTADIO VACILATE UN RATO NO SÉ MANO PERO YA PES
                    ARRIBA ALIANZA LA PTMR
                </p>
            </div>
            <div class="media-content">
                <img src="${pageContext.request.contextPath}/img/images.jpg" alt="Alianza Lima">
                <img src="${pageContext.request.contextPath}/img/images2.jpg" alt="Alianza Lima">
                <img src="${pageContext.request.contextPath}/img/images3.jpg" alt="Alianza Lima">
                <audio id="welcomeAudio" src="${pageContext.request.contextPath}/audio/himnodelmasgrandedelperu.mp3" type="audio/mpeg"></audio>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
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