package com.sebastianruiz.elcorral;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    EditText eUsuario,ePassword,eRpassword,eCorreo;
    Button bAceptar, bCancelar;
    String PREFS_NAME = "MyPrefsFile";
    SharedPreferences datos;
    Datos_DB usuarios;
    SQLiteDatabase dbUsuarios;
    ContentValues dataDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        eUsuario = (EditText) findViewById(R.id.Rusuario);
        ePassword = (EditText) findViewById(R.id.Rpassword);
        eRpassword = (EditText) findViewById(R.id.Rrpassword);
        eCorreo = (EditText) findViewById(R.id.Rcorreo);
        bAceptar = (Button) findViewById(R.id.RAceptar);
        bCancelar = (Button) findViewById(R.id.RCancelar);


        datos = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        usuarios= new Datos_DB(this, "DatosDB", null, 1);
        dbUsuarios = usuarios.getWritableDatabase();

    }

    public  void Click(View v){
        Intent i = new Intent();
        switch (v.getId()){

            case R.id.RAceptar:
                if ( (eUsuario.getText().toString().matches("") ) || ( ePassword.getText().toString().matches("") ) ||
                        ( eRpassword.getText().toString().matches("") ) || ( eRpassword.getText().toString().matches("") ) ||
                        ( eCorreo.getText().toString().matches("") ) ){
                    Toast.makeText(this,getString(R.string.faltan_datos),Toast.LENGTH_SHORT).show();
                }
                else if (!eRpassword.getText().toString().matches(ePassword.getText().toString())){

                    eRpassword.setError(getString( R.string.PasswordNoCoincide ));
                }
                else{

                    SharedPreferences.Editor edit = datos.edit();
                    edit.putString("usuario",eUsuario.getText().toString());
                    edit.putString("password",ePassword.getText().toString());
                    edit.putString("correo",eCorreo.getText().toString());
                    edit.commit();

                    dataDB = new ContentValues();
                    dataDB.put("usuario", eUsuario.getText().toString());
                    dataDB.put("contrasenna" ,ePassword.getText().toString());
                    dataDB.put("correo",eCorreo.getText().toString());

                    dbUsuarios.insert("usuarios",null,dataDB);


                    i.putExtra("usuario",eUsuario.getText().toString());
                    i.putExtra("password",ePassword.getText().toString());
                    i.putExtra("correo",eCorreo.getText().toString());
                    setResult(RESULT_OK,i);
                    finish();
                }
                break;
            case R.id.RCancelar:
                setResult(RESULT_CANCELED,i);
                finish();
                break;

        }

    }
}
