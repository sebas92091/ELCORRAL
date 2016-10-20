package com.sebastianruiz.elcorral;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MiPerfilActivity extends Fragment { //AppCompatActivity
    TextView tNombre,tCorreo,tPassword;
    String PREFS_NAME = "MyPrefsFile";

    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_mi_perfil, container, false);

        if (savedInstanceState == null) {
            insertarTabs(container);

            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pagerPerfil);
            poblarViewPager(viewPager);
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabs.setupWithViewPager(viewPager);
        }

        return view;
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());
        adapter.addFragment(new datosPerfil(), "Datos");
        adapter.addFragment(new favoritosPerfil(), "Favoritos");

        viewPager.setAdapter(adapter);
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
        tabs = new TabLayout(getActivity());
        // tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(tabs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabs);
    }

    private class AdaptadorSecciones extends FragmentStatePagerAdapter {

        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

     /*   tNombre = (TextView) view.findViewById(R.id.PerfilUsuario);
        tPassword = (TextView) view.findViewById(R.id.PerfilPasswors);
        tCorreo = (TextView) view.findViewById(R.id.PerfilCorreo);


        SharedPreferences datos = this.getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String Usuario = ": "+datos.getString("usuario","");
        String Password = ": "+datos.getString("password","");
        String Correo = ": "+datos.getString("correo","");
        Log.d("Usuario", Usuario);
        Log.d("Password", Password);
        Log.d("Correo", Correo);
        tNombre.setText(getString(R.string.Usuario)+Usuario);
        tPassword.setText(getString(R.string.Contrasena)+Password);
        tCorreo.setText(getString(R.string.Correo)+Correo);*/

    }
}


