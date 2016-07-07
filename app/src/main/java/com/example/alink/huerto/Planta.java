package com.example.alink.huerto;

/**
 * Created by Luis on 07-07-2016.
 */
public class Planta {
    int idPlanta;
    String nombre;
    String nombreCientifico;

    public Planta(int idPlanta, String nombre, String nombreCientifico){
        this.idPlanta = idPlanta;
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
    }

    public int getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

}
