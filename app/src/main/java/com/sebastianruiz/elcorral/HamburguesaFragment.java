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
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

//

public class HamburguesaFragment extends Fragment {

    private Comida[] datos;
    private ListView list;
    private Datos_DB usuarios;
    private SQLiteDatabase dbUsuarios;
    private String descripcion1,descripcion2,descripcion3,descripcion4;

    public HamburguesaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_hamburguesa,container,false);

        usuarios= new Datos_DB(getActivity(), "DatosDB", null, 1);
        dbUsuarios = usuarios.getWritableDatabase();
        Cursor c = dbUsuarios.rawQuery("select * from productos where id='"+1+"'",null);
        c.moveToFirst();
        Comida c1 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),c.getInt(4));
        descripcion1 = getString(c.getInt(2));

        c = dbUsuarios.rawQuery("select * from productos where id='"+2+"'",null); c.moveToFirst();
        Comida c2 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),c.getInt(4));
        descripcion2 = getString(c.getInt(2));

        c = dbUsuarios.rawQuery("select * from productos where id='"+3+"'",null); c.moveToFirst();
        Comida c3 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),c.getInt(4));
        descripcion3 = getString(c.getInt(2));

        c = dbUsuarios.rawQuery("select * from productos where id='"+4+"'",null); c.moveToFirst();
        Comida c4 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),c.getInt(4));
        descripcion4 = getString(c.getInt(2));

        datos = new Comida[]{ c1,c2,c3,c4 };

        AdaptadorPromo adaptador = new AdaptadorPromo(view.getContext(), datos);
        list = (ListView) view.findViewById(R.id.lHamburguesas);
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
                    case 1:
                        editor.putString("descripcionDescripcion",descripcion2);
                        break;
                    case 2:
                        editor.putString("descripcionDescripcion",descripcion3);
                        break;
                    case 3:
                        editor.putString("descripcionDescripcion",descripcion4);
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

        return view;
    }
}
