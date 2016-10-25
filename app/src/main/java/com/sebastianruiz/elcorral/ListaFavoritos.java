package com.sebastianruiz.elcorral;

/**
 * Created by SEBASTIAN on 25/10/2016.
 */
public class ListaFavoritos {


    private String Producto;
    private int precio;

    public ListaFavoritos() {

    }

    public ListaFavoritos(String producto, int precio) {
        this.Producto = producto;
        this.precio = precio;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        this.Producto = producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
