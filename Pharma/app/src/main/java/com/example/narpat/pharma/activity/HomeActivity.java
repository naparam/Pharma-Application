package com.example.narpat.pharma.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.narpat.pharma.R;
import com.example.narpat.pharma.fragments.DrugsNotAvailableFragment;
import com.example.narpat.pharma.fragments.HomeFragment;
import com.example.narpat.pharma.fragments.OrderFragment;
import com.example.narpat.pharma.fragments.PendingBillsFragment;
import com.example.narpat.pharma.fragments.PendingOrderFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentContainer = (FrameLayout) findViewById(R.id.fragmentContainer);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
                if(!(currentFragment instanceof OrderFragment)){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new OrderFragment()).commit();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (id == R.id.nav_order) {
            // Tanzeel - Check if we are not already in Order fragment and then decide to show that fragment
            if(!(currentFragment instanceof OrderFragment)){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new OrderFragment()).commit();
            }

        } else if (id == R.id.nav_pending_order) {
            // Tanzeel - Do similarly as in above if condition
            if(!(currentFragment instanceof PendingOrderFragment)){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PendingOrderFragment()).commit();
            }
        } else if (id == R.id.nav_not_available_order) {
            // Tanzeel - Do similarly as in above if condition
            if(!(currentFragment instanceof DrugsNotAvailableFragment)){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new DrugsNotAvailableFragment()).commit();
            }
        } else if (id == R.id.nav_pending_bills) {
            // Tanzeel - Do similarly as in above if condition
            if(!(currentFragment instanceof PendingBillsFragment)){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PendingBillsFragment()).commit();
            }
        } else if (id == R.id.nav_log_out) {
            finish();

        } else if (id == R.id.nav_send) {
            if(!(currentFragment instanceof HomeFragment)){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
            }

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
