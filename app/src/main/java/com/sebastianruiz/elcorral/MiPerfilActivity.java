package com.sebastianruiz.elcorral;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MiPerfilActivity extends Fragment { //AppCompatActivity
    TextView tNombre,tCorreo,tPassword;
    String PREFS_NAME = "MyPrefsFile";


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_mi_perfil, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tNombre = (TextView) view.findViewById(R.id.PerfilUsuario);
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
        tCorreo.setText(getString(R.string.Correo)+Correo);
    }
}


