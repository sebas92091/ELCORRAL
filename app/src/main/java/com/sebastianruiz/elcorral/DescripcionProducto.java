package com.sebastianruiz.elcorral;

import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class DescripcionProducto extends AppCompatActivity {

    private  SharedPreferences datos;
    private ContentValues dataDB;
    private Datos_DB usuarios;
    private SQLiteDatabase dbUsuarios;
    private String idusuario, idproducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_producto);
        datos = getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);

        usuarios= new Datos_DB(this, "DatosDB", null, 1);
        dbUsuarios = usuarios.getWritableDatabase();

        agregarToolbar();

        TextView nombre = (TextView) findViewById(R.id.nombre_Descripcion);
        TextView precio = (TextView) findViewById(R.id.precio_Descripcion);
        TextView descripcion = (TextView) findViewById(R.id.descripcion_Descripcion);
        ImageView imagen = (ImageView) findViewById(R.id.image_Descripcion);
        final Switch favoritos = (Switch) findViewById(R.id.favorito);

        Cursor c = dbUsuarios.rawQuery("select * from productos where producto='"+ datos.getString("nombreDescripcion", "error") +"'",null);
        c.moveToFirst();
        idproducto = String.valueOf(c.getInt(0));
        c = dbUsuarios.rawQuery("select * from favoritos where idproducto='"+ c.getInt(0) +"'",null);
        if (c.moveToFirst()){
            c = dbUsuarios.rawQuery("select * from usuarios where usuario='"+ datos.getString("usuario", "error") +"'",null);
            c.moveToFirst();

            c = dbUsuarios.rawQuery("select * from favoritos where idusuario='"+ c.getInt(0) +"'",null);
            if(c.moveToFirst()){

                favoritos.setChecked(true);
            }
        }

        nombre.setText(datos.getString("nombreDescripcion", "error"));
        precio.setText( "$"+String.valueOf(datos.getInt("precioDescripcion", -1)) );
        imagen.setImageResource(datos.getInt("imagenDescripcion",-1));
        descripcion.setText(datos.getString("descripcionDescripcion","error"));

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favoritos.isChecked()){
                    Cursor c = dbUsuarios.rawQuery("select * from usuarios where usuario='"+ datos.getString("usuario", "error") +"'",null);
                    c.moveToFirst();
                    idusuario = c.getString(0);
                    dataDB = new ContentValues();
                    dataDB.put("idusuario", idusuario);
                    dataDB.put("idproducto" ,idproducto);

                    dbUsuarios.insert("favoritos",null,dataDB);
                }
                else {
                    Cursor c = dbUsuarios.rawQuery("select * from favoritos where idproducto='"+ idproducto +"'",null);
                    if(c.moveToFirst()){

                        dbUsuarios.delete("favoritos","idproducto="+c.getString(2),null);
                    }
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:

                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void agregarToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDescripcion);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(datos.getString("nombreDescripcion", "error"));
    }
}
