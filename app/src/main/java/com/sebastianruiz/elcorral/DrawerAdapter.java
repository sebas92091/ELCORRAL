package com.sebastianruiz.elcorral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SEBASTIAN on 25/09/2016.
 */
 public class DrawerAdapter extends ArrayAdapter<Lista_DrawerLayout> {
    public DrawerAdapter (Context context, Lista_DrawerLayout[] datos){
        super(context,R.layout.opcion_drawer_layout, datos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.opcion_drawer_layout,null);

        Lista_DrawerLayout datos = getItem(position);

        TextView opcion = (TextView) item.findViewById(R.id.tOpcionDrawer);
        opcion.setText(datos.getOpcion());

        ImageView icono = (ImageView) item.findViewById(R.id.ic_Drawer);
        icono.setImageResource(datos.getIdIcon());
        return (item);
    }
}
