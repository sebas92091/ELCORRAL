package com.sebastianruiz.elcorral;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sebastianruiz.elcorral.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class datosPerfil extends Fragment {
    TextView tNombre,tCorreo,tPassword;
    String PREFS_NAME = "MyPrefsFile";

    public datosPerfil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos_perfil, container, false);
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
