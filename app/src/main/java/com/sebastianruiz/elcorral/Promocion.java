package com.sebastianruiz.elcorral;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Promocion extends Fragment {


    public Promocion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_promocion, container, false);

        SharedPreferences datos = getActivity().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        TextView nombre = (TextView) view.findViewById(R.id.nombre_Promo_);
        TextView precio = (TextView) view.findViewById(R.id.precio_Promo_);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion_promo);
        ImageView imagen = (ImageView) view.findViewById(R.id.image_Promo_);


        nombre.setText(datos.getString("nombrePromo", "error"));
        precio.setText( "$"+String.valueOf(datos.getInt("precioPromo", -1)) );
        imagen.setImageResource(datos.getInt("imagenPromo",-1));
        descripcion.setText(datos.getString("descripcionPromo","error"));


        return view;
    }

}
