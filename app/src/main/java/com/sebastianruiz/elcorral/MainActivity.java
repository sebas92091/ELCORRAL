package com.sebastianruiz.elcorral;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.design.widget.NavigationView;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerlayout;
    private NavigationView navView;
    private  TextView tUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);


         tUsuario= (TextView) findViewById(R.id.usuarioHeader) ;
     //   tUsuario.setText(getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE).getString("usuario","error"));

        agregarToolbar();

        drawerlayout = (DrawerLayout)findViewById(R.id.contenedorPrincipal);

        navView = (NavigationView)findViewById(R.id.navview);
        View header = navView.getHeaderView(0);

        //pone nombre al header del drawer menu
        tUsuario= (TextView) header.findViewById(R.id.usuarioHeader);
        tUsuario.setText(getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE).getString("usuario","error"));

        if (navView != null) {
            prepararDrawer(navView);
            // Seleccionar item por defecto
            seleccionarItem(navView.getMenu().getItem(0));

        }

    }

    private void prepararDrawer(NavigationView navView) {
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerlayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void seleccionarItem(MenuItem menuItem) {

        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.opcionPerfil:
                fragment = new MiPerfilActivity();
                fragmentTransaction = true;
                break;
            case R.id.opcionPpal:
                 fragment = new fragment_Promociones();
                fragmentTransaction = true;
                break;
            case R.id.opcionProductos:
                fragment = new ClasificacionProductos();
                fragmentTransaction = true;
                break;
            case R.id.opcionExit:
                opcionExit();
                break;
            default:
                break;

        }

        if(fragmentTransaction) {
            //getSupportActionBar().removeAllTabs();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedorFrame, fragment)
                    .commit();
            setTitle(menuItem.getTitle());
        }
    }





    private void opcionExit(){

        //SharedPreferences datos;
        SharedPreferences datos = getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        Intent intent = new Intent(getBaseContext(),LogginActivity.class);

        SharedPreferences.Editor edit = datos.edit();
        edit.putInt("Loggeado",-1);
        edit.commit();
        startActivity(intent);

        Log.d("cerrar", " si ");
        Log.d("loggeado", String.valueOf(datos.getInt("Loggeado",-1)));
        finish();
    }

    private void agregarToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

   // @Override
  //  public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.drawer_menu, menu);
        //ponel el nombre del usuario en el header del drawer layout

     //   return true;
   // }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                drawerlayout.openDrawer(GravityCompat.START);
                Log.d("click menu", "si ");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


