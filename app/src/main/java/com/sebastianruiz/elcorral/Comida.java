package com.sebastianruiz.elcorral;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SEBASTIAN on 02/10/2016.
 */
public class Comida {
    private  int precio;
    private String nombre;
    private int idImage;

    public Comida(int precio, String nombre, int idImage) {
        this.precio = precio;
        this.nombre = nombre;
        this.idImage = idImage;
    }



    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "precio=" + precio +
                ", nombre='" + nombre + '\'' +
                ", idImage=" + idImage +
                '}';
    }
}
