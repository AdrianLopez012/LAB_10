package com.example.lab_10.model.beans;

public class Seleccion {
    private int idSeleccion;
    private String nombre;
    private String tecnico;
    private int estadio_idEstadio;

    public int getIdSeleccion() {
        return idSeleccion;
    }

    public void setIdSeleccion(int idSeleccion) {
        this.idSeleccion = idSeleccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public int getEstadio_idEstadio() {
        return estadio_idEstadio;
    }

    public void setEstadio_idEstadio(int estadio_idEstadio) {
        this.estadio_idEstadio = estadio_idEstadio;
    }
}
