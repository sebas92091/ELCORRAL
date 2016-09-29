package com.sebastianruiz.elcorral;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//
public class ClasificacionProductos extends Fragment { //AppCompatActivity

    private ViewPager mViewPager;
    private FragmentActivity myContext;
    ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_clasificacion_productos,container,false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myContext = (FragmentActivity) activity;


    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //   ((ActionBarActivity) this.getActivity()).getSupportActionBar();
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        PagerAdapter pagerAdapter = new PagerAdapter(myContext.getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);



        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


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
             actionBar.setSelectedNavigationItem(position);
        }
         });



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager = (ViewPager) view.findViewById(R.id.ClasificacionProductos);
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


}
