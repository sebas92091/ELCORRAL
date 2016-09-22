package com.sebastianruiz.elcorral;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//
public class ClasificacionProductos extends AppCompatActivity {

    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion_productos);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.ClasificacionProductos);
        mViewPager.setAdapter(pagerAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new   ActionBar.TabListener(){
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft ){
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            }
        };

        ActionBar.Tab tab = actionBar.newTab().setIcon(R.drawable.hamburguesas_icon).setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setIcon(R.drawable.vaqueros_icon).setTabListener(tabListener);
        actionBar.addTab(tab);

        tab=actionBar.newTab().setIcon(R.drawable.postres_icon).setTabListener(tabListener);
        actionBar.addTab(tab);

        tab=actionBar.newTab().setIcon(R.drawable.tambien_disfruta_icon).setTabListener(tabListener);
        actionBar.addTab(tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public  void onPageSelected(int position){
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });
    }

    public class PagerAdapter extends FragmentPagerAdapter{

        public PagerAdapter(android.support.v4.app.FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:return new HamburguesaFragment();
                case 1:return new VaqueroFragment();
                case 2:return new PostresFragment();
                case 3:return new OtrosFragment();
                default:return null;
            }

        }

        @Override
        public int getCount() {
            return 4;
        }
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
               // Intent a = new Intent(this,MiPerfilActivity.class);
                startActivity(new Intent(this,MiPerfilActivity.class));
                finish();
                break;
            case R.id.Principal:
                //Intent b = new Intent(this,MainActivity.class);

                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
            case R.id.Productos:

                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
