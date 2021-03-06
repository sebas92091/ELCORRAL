package com.sebastianruiz.elcorral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogginActivity extends AppCompatActivity {
    TextView tRegistrarse;
    EditText eUsuario, ePassword;
    Button bLoggin;
    String usuario= " ", password =" ", correo= " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        tRegistrarse = (TextView) findViewById(R.id.registrarse);
        eUsuario = (EditText) findViewById(R.id.usuario);
        ePassword = (EditText) findViewById(R.id.contrasena);
        bLoggin = (Button) findViewById(R.id.loggin);



        tRegistrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivityForResult(i,1234);
            }
        });


    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == 1234 && resultCode == RESULT_OK){
            usuario = data.getExtras().getString("usuario");
            password = data.getExtras().getString("password");
            correo = data.getExtras().getString("correo");
        }

     }

    public void LoggIn(View view){
        if ( (eUsuario.getText().toString().matches("")) || ( ePassword.getText().toString().matches("") ) ){
            Toast.makeText(this,R.string.faltan_datos,Toast.LENGTH_SHORT).show();
        }
        else if (!eUsuario.getText().toString().matches(usuario) || (!ePassword.getText().toString().matches(password)) ){
            Toast.makeText(this,getString(R.string.incorrecto),Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
