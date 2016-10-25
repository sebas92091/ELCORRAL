package com.sebastianruiz.elcorral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SEBASTIAN on 25/10/2016.
 */
public class AdaptadorFavoritos extends ArrayAdapter<ListaFavoritos> {



    public AdaptadorFavoritos(Context context, List<ListaFavoritos> datos){
        super(context, R.layout.item_lista_favoritos, datos);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view  = inflater.inflate(R.layout.item_lista_favoritos, null);

        ListaFavoritos datos = getItem(position);

        TextView Producto = (TextView) view.findViewById(R.id.ProductoListaFavoritos);
        Producto.setText(datos.getProducto());

        TextView Precio  = (TextView) view.findViewById(R.id.PrecioListaFavoritos);
        Precio.setText(String.valueOf(datos.getPrecio()));
        return view;

    }
}
