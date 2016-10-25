package com.sebastianruiz.elcorral;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class favoritosPerfil extends Fragment {


    public favoritosPerfil() {
        // Required empty public constructor
    }

    private List<ListaFavoritos> lista;

    private ListView list;
    private Datos_DB usuarios;
    private SQLiteDatabase dbUsuarios;
    private String descripcion1,descripcion2,descripcion3,descripcion4;
    int idUsuario;
    SharedPreferences datos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos_perfil, container, false);


        datos = getActivity().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);

         usuarios= new Datos_DB(getActivity(), "DatosDB", null, 1);
         dbUsuarios = usuarios.getWritableDatabase();
         Cursor c = dbUsuarios.rawQuery("select * from usuarios where usuario='"+datos.getString("usuario","error")+"'",null);
        c.moveToFirst();
        c = dbUsuarios.rawQuery("select * from favoritos where idusuario='"+c.getInt(0)+"'",null);
        lista= new ArrayList<>();

        if (c.moveToFirst()){


           while (!c.isAfterLast()){
               Cursor p = dbUsuarios.rawQuery("select * from productos where id='"+c.getInt(2)+"'",null);
               p.moveToFirst();
              lista.add(new ListaFavoritos(p.getString(1),p.getInt(3))) ;
               c.moveToNext();
               Log.d("productoFavorito",p.getString(1) );
           }
        }
        else {

            lista.add(new ListaFavoritos("-",0));
        }
        AdaptadorFavoritos adaptadorFavoritos =new AdaptadorFavoritos(getActivity(), lista);

        list =(ListView) view.findViewById(R.id.ListaFavoritos);

        list.setAdapter(adaptadorFavoritos);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListaFavoritos item = (ListaFavoritos)parent.getItemAtPosition(position);
                usuarios= new Datos_DB(getActivity(), "DatosDB", null, 1);
                dbUsuarios = usuarios.getWritableDatabase();
                Cursor f = dbUsuarios.rawQuery("select * from productos where producto='"+item.getProducto()+"'",null);

                SharedPreferences.Editor editor = datos.edit();
                if (f.moveToFirst()){
                    editor.putInt("precioDescripcion",f.getInt(3));
                    editor.putInt("imagenDescripcion",f.getInt(4));
                    editor.putString("nombreDescripcion",f.getString(1));
                    editor.putString("descripcionDescripcion",getString(f.getInt(2)));
                }
                editor.commit();

                Intent intent = new Intent(getActivity(),DescripcionProducto.class);
                startActivity(intent);
            }
        });

        return view;
    }

   /* @Override
    public void onDestroyView() {
        super.onDestroyView();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(getFragmentManager().findFragmentById(R.id.FragmentFavoritos))
                .attach(getFragmentManager().findFragmentById(R.id.FragmentFavoritos))
                .commit();
    }*/
}
