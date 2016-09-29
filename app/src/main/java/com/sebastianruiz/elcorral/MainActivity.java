package com.sebastianruiz.elcorral;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private   Lista_DrawerLayout[] menuDrawer ;
    private ListView lst;
    private DrawerLayout drawerlayout;
    private ActionBarDrawerToggle drawerToggle;

   // private String[] opcion  = new String[]{"Perfil","principal","Salir"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuDrawer = new Lista_DrawerLayout[]{
                new Lista_DrawerLayout(R.drawable.ic_perfil,getString(R.string.MiPerfil)),
                new Lista_DrawerLayout(R.drawable.ic_stars,getString(R.string.Principal)),
                new Lista_DrawerLayout(R.drawable.ic_list,getString(R.string.Productos)),
                new Lista_DrawerLayout(R.drawable.ic_exit,getString(R.string.CerrarSesion))


        };
          ActionBar actionBar = getSupportActionBar();
          if (actionBar != null){
             actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
             actionBar.setDisplayHomeAsUpEnabled(true);
         }

        drawerlayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);
        lst =(ListView) findViewById(R.id.menu_izq);

        DrawerAdapter adapter = new DrawerAdapter(this,menuDrawer);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                switch (position){

                    case 0:
                        fragment = new MiPerfilActivity();
                        Log.d("pefil", " si ");
                        break;
                    case 1:
                        Log.d("ppal", " si ");
                        break;
                    case 2:
                        fragment = new ClasificacionProductos();
                        Log.d("productos", " si ");
                        break;
                    case 3:

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
                        break;
                    default:
                        break;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedorFrame,fragment).commit();
                lst.setItemChecked(position,true);
                drawerlayout.closeDrawer(lst);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this,drawerlayout,R.string.abierto,R.string.cerrado){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerlayout.setDrawerListener(drawerToggle);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.MiPerfil:
                Intent a = new Intent(this,MiPerfilActivity.class);
                startActivity(a);
                finish();
                break;
            case R.id.Principal:

                break;
            case R.id.Productos:
                startActivity(new Intent(this,ClasificacionProductos.class));
                finish();
                break;
            case android.R.id.home:
                drawerlayout.openDrawer(Gravity.LEFT);
                Log.d("click menu", "si ");
                return true;


        }

        return super.onOptionsItemSelected(item);
    }
}
