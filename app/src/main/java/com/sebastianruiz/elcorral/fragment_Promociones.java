package com.sebastianruiz.elcorral;


import android.content.Context;
import android.content.SharedPreferences;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__promociones, container, false);

        datos = new Comida[]{
                new Comida(9500,"Corralisima 1/2 Libra",R.drawable.media_libra),
                new Comida(11500,"Corralisima",R.drawable.corralisima),
                new Comida(7500,"Brownie con helado",R.drawable.brownie_con_helado),
                new Comida(8500,"Malteada mediana",R.drawable.malteada_mediana),
                new Comida(5500,"Anillos de cebolla 1/2 Libra",R.drawable.anillos_de_cebolla)
        };

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
                        editor.putString("descripcionPromo",getString(R.string.corralisimaMediaLibra_promo));
                        break;
                    case 1:
                        editor.putString("descripcionPromo",getString(R.string.corralisima_promo));
                        break;
                    case 2:
                        editor.putString("descripcionPromo",getString(R.string.brownieConHelado_promo));
                        break;
                    case 3:
                        editor.putString("descripcionPromo",getString(R.string.malteadaMediana_promo));
                        break;
                    case 4:
                        editor.putString("descripcionPromo",getString(R.string.anillosDeCebolla_promo));
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