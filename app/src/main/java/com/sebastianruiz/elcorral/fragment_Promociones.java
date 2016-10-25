package com.sebastianruiz.elcorral;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.sebastianruiz.elcorral.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_Promociones extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;

    private Comida[] datos;
    private ListView list;
    private Datos_DB usuarios;
    private SQLiteDatabase dbUsuarios;
    private String promo1, promo2, promo3, promo4, promo5;
   // private ContentValues dataDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__promociones, container, false);

        usuarios= new Datos_DB(getActivity(), "DatosDB", null, 1);
        dbUsuarios = usuarios.getWritableDatabase();
        Cursor c = dbUsuarios.rawQuery("select * from productos where id='"+1+"'",null);
        c.moveToFirst();
        Comida c1 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),R.drawable.media_libra);
        promo1 = getString(c.getInt(2));

         c = dbUsuarios.rawQuery("select * from productos where id='"+2+"'",null); c.moveToFirst();
        Comida c2 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),R.drawable.corralisima);
        promo2 = getString(c.getInt(2));

         c = dbUsuarios.rawQuery("select * from productos where id='"+3+"'",null); c.moveToFirst();
        Comida c3 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),R.drawable.brownie_con_helado);
        promo3 = getString(c.getInt(2));

         c = dbUsuarios.rawQuery("select * from productos where id='"+4+"'",null); c.moveToFirst();
        Comida c4 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),R.drawable.malteada_mediana);
        promo4 = getString(c.getInt(2));

         c = dbUsuarios.rawQuery("select * from productos where id='"+5+"'",null); c.moveToFirst();
        Comida c5 = new Comida(Integer.valueOf(c.getString(3)),c.getString(1),R.drawable.anillos_de_cebolla);
        promo5 = getString(c.getInt(2));


        datos = new Comida[]{ c1,c2,c3,c4,c5 };

     /*           {new Comida(Integer.valueOf(c.getString(3)),c.getString(1),R.drawable.media_libra),
                new Comida(Integer.valueOf(c.move(2)),"Corralisima",R.drawable.corralisima),
                new Comida(Integer.valueOf(c.getString(3)),"Brownie con helado",R.drawable.brownie_con_helado),
                new Comida(Integer.valueOf(c.getString(3)),"Malteada mediana",R.drawable.malteada_mediana),
                new Comida(Integer.valueOf(c.getString(3)),"Anillos de cebolla 1/2 Libra",R.drawable.anillos_de_cebolla)
        };*/

        AdaptadorPromo adaptador = new AdaptadorPromo(view.getContext(), datos);
        list = (ListView) view.findViewById(R.id.rPromo);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Comida promocion = (Comida)parent.getItemAtPosition(position);
                SharedPreferences MyPrefsFile = getActivity().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = MyPrefsFile.edit();
                editor.putInt("precioPromo",promocion.getPrecio());
                editor.putInt("imagenPromo",promocion.getIdImage());
                editor.putString("nombrePromo",promocion.getNombre());



                switch (position){
                    case 0:
                        editor.putString("descripcionPromo",promo1);
                        break;
                    case 1:
                        editor.putString("descripcionPromo",promo2);
                        break;
                    case 2:
                        editor.putString("descripcionPromo",promo3);
                        break;
                    case 3:
                        editor.putString("descripcionPromo",promo4);
                        break;
                    case 4:
                        editor.putString("descripcionPromo",promo5);
                        break;
                    default:

                }
                editor.commit();
                String opcion = ((Comida)parent.getItemAtPosition(position)).getNombre();
                Log.d("click ", opcion);

                Fragment fragment = new Promocion();
                FragmentActivity myContext = (FragmentActivity) getActivity() ;
                myContext.getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFrame,fragment).commit();
            }
        });

        return view;
    }
}