package com.sebastianruiz.elcorral;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

//

public class VaqueroFragment extends Fragment {

    private Comida[] datos;
    private ListView list;
    private Datos_DB usuarios;
    private SQLiteDatabase dbUsuarios;
    private String descripcion1;


    public VaqueroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_vaquero,container, false);

        usuarios= new Datos_DB(getActivity(), "DatosDB", null, 1);
        dbUsuarios = usuarios.getWritableDatabase();
        Cursor c = dbUsuarios.rawQuery("select * from productos where id='"+5+"'",null);
        c.moveToFirst();
        Comida c1 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),c.getInt(4));
        descripcion1 = getString(c.getInt(2));

        datos = new Comida[]{ c1};

        AdaptadorPromo adaptador = new AdaptadorPromo(view.getContext(), datos);
        list = (ListView) view.findViewById(R.id.lVaqueros);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Comida promocion = (Comida)parent.getItemAtPosition(position);
                SharedPreferences MyPrefsFile = getActivity().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = MyPrefsFile.edit();
                editor.putInt("precioDescripcion",promocion.getPrecio());
                editor.putInt("imagenDescripcion",promocion.getIdImage());
                editor.putString("nombreDescripcion",promocion.getNombre());



                switch (position){
                    case 0:
                        editor.putString("descripcionDescripcion",descripcion1);
                        break;

                    default:

                }
                editor.commit();
                String opcion = ((Comida)parent.getItemAtPosition(position)).getNombre();
                Log.d("click ", opcion);

                Intent intent = new Intent(getActivity(),DescripcionProducto.class);
                startActivity(intent);
            }
        });

        return  view;
    }
}
