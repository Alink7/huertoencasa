package com.example.alink.huerto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Luis on 07-07-2016.
 */
public class Planta {
    String idPlanta;
    String nombre;
    String nombreCientifico;
    String clase;
    double distanciaPlantas;
    double distanciaOtrasPlantas;
    double profundidadNecesaria;
    double volumenNecesario;
    String nAbono;
    String nRiego;
    String nSol;
    String tipoSuelo;
    String nTemperatura;

    /*public Planta(String idPlanta, String nombreCientifico, String nombre, String clase,
                  double distanciaPlantas, double distanciaOtrasPlantas, double profundidadNecesaria,
                  double volumenNecesario, String nAbono, String nSol, String nRiego, String tipoSuelo, String nTemperatura) {
        this.idPlanta = idPlanta;
        this.nombreCientifico = nombreCientifico;
        this.nombre = nombre;
        this.clase = clase;
        this.distanciaPlantas = distanciaPlantas;
        this.distanciaOtrasPlantas = distanciaOtrasPlantas;
        this.profundidadNecesaria = profundidadNecesaria;
        this.volumenNecesario = volumenNecesario;
        this.nAbono = nAbono;
        this.nSol = nSol;
        this.nRiego = nRiego;
        this.tipoSuelo = tipoSuelo;
        this.nTemperatura = nTemperatura;
    }*/


    public String getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(String idPlanta) {
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

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public double getDistanciaPlantas() {
        return distanciaPlantas;
    }

    public void setDistanciaPlantas(double distanciaPlantas) {
        this.distanciaPlantas = distanciaPlantas;
    }

    public double getDistanciaOtrasPlantas() {
        return distanciaOtrasPlantas;
    }

    public void setDistanciaOtrasPlantas(double distanciaOtrasPlantas) {
        this.distanciaOtrasPlantas = distanciaOtrasPlantas;
    }

    public double getProfundidadNecesaria() {
        return profundidadNecesaria;
    }

    public void setProfundidadNecesaria(double profundidadNecesaria) {
        this.profundidadNecesaria = profundidadNecesaria;
    }

    public double getVolumenNecesario() {
        return volumenNecesario;
    }

    public void setVolumenNecesario(double volumenNecesario) {
        this.volumenNecesario = volumenNecesario;
    }

    public String getnAbono() {
        return nAbono;
    }

    public void setnAbono(String nAbono) {
        this.nAbono = nAbono;
    }

    public String getnRiego() {
        return nRiego;
    }

    public void setnRiego(String nRiego) {
        this.nRiego = nRiego;
    }

    public String getnSol() {
        return nSol;
    }

    public void setnSol(String nSol) {
        this.nSol = nSol;
    }

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    public String getnTemperatura() {
        return nTemperatura;
    }

    public void setnTemperatura(String nTemperatura) {
        this.nTemperatura = nTemperatura;
    }
}
