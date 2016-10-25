package com.sebastianruiz.elcorral;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Promocion extends Fragment {

    private Datos_DB usuarios;
    private SQLiteDatabase dbUsuarios;
    private ContentValues dataDB;
    private String idusuario, idproducto;

    public Promocion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_promocion, container, false);

        usuarios= new Datos_DB(getActivity(), "DatosDB", null, 1);
        dbUsuarios = usuarios.getWritableDatabase();


        final SharedPreferences datos = getActivity().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        TextView nombre = (TextView) view.findViewById(R.id.nombre_Promo_);
        TextView precio = (TextView) view.findViewById(R.id.precio_Promo_);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion_promo);
        ImageView imagen = (ImageView) view.findViewById(R.id.image_Promo_);
        final Switch favoritos = (Switch) view.findViewById(R.id.favorito);



        nombre.setText(datos.getString("nombrePromo", "error"));
        precio.setText( "$"+String.valueOf(datos.getInt("precioPromo", -1)) );
        imagen.setImageResource(datos.getInt("imagenPromo",-1));
        descripcion.setText(datos.getString("descripcionPromo","error"));

        return view;
    }

}
