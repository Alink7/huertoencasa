package com.example.alink.huerto;

/**
 * Created by Nicolas on 07-07-16.
 */
public class Cultivo {

    private double largo, ancho, profundidad;
    private String tipoSuelo;

    public Cultivo(double largo, double ancho, double profundidad, String tipoSuelo) {
        this.largo = largo;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.tipoSuelo = tipoSuelo;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }
}
