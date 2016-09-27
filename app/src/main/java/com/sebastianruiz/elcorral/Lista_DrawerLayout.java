package com.sebastianruiz.elcorral;

/**
 * Created by SEBASTIAN on 25/09/2016.
 */
public class Lista_DrawerLayout {
    private int idIcon;
    private String Opcion;

    public Lista_DrawerLayout(int idIcon, String opcion){

        this.idIcon = idIcon;
        this.Opcion = opcion;
    }

    public int getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(int idIcon) {
        this.idIcon = idIcon;
    }

    public  String getOpcion() {
        return Opcion;
    }

    public void setOpcion(String opcion) {
        Opcion = opcion;
    }

    @Override
    public String toString() {
        return "Lista_DrawerLayout{" +
                "idIcon=" + idIcon +
                ", Opcion='" + Opcion + '\'' +
                '}';
    }
}
