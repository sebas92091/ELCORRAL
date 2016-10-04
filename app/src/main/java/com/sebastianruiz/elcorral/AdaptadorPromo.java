package com.sebastianruiz.elcorral;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by SEBASTIAN on 02/10/2016.
 */
public class AdaptadorPromo
        extends ArrayAdapter<Comida> {

    public AdaptadorPromo(Context context, Comida[] datos){
        super(context,R.layout.item_lista_promos,datos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Comida item = Comida.PROMOCIONES.get(i);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item =inflater.inflate(R.layout.item_lista_promos,null);

        Comida datos = getItem(position);

        TextView nombre = (TextView) item.findViewById(R.id.nombre_Promo);
        nombre.setText(datos.getNombre());

        TextView precio = (TextView) item.findViewById(R.id.precio_Promo);
        precio.setText( "$"+String.valueOf(datos.getPrecio()) );

        ImageView imagen = (ImageView) item.findViewById(R.id.image_Promo);
        imagen.setImageResource(datos.getIdImage());

      /*  Glide.with(convertView.itemView.getContext())
                .load(item.getIdImage())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("$" + item.getPrecio());*/


        return (item);
    }
}
