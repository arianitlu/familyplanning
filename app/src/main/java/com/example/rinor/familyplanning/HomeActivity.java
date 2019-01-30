package com.example.rinor.familyplanning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.rinor.familyplanning.fragments.FragmentHelp;
import com.example.rinor.familyplanning.fragments.FragmentInstitution;
import com.example.rinor.familyplanning.fragments.FragmentMaps;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        ,PopupMenu.OnMenuItemClickListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FragmentHelp()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_help) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentHelp()).commit();
            toolbar.setTitle(getResources().getString(R.string.where_gethelp));

        } else if (id == R.id.nav_map) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentMaps()).commit();
            toolbar.setTitle(getResources().getString(R.string.map));

        } else if (id == R.id.nav_pregnancy) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentInstitution()).commit();
            toolbar.setTitle(getResources().getString(R.string.pregnancy));

        } else if (id == R.id.nav_info) {


        }else if (id == R.id.nav_chat) {
            Intent i = new Intent(getApplicationContext(),StartActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showPopupMain(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.setOnMenuItemClickListener(this);

        popupMenu.inflate(R.menu.language_menu);

        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.german:
                Toast.makeText(this, "German", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.english:
                Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.arabic:
                Toast.makeText(this, "Arabic", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.farsi:
                Toast.makeText(this, "Farsi", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.french:
                Toast.makeText(this, "French", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }


/*
    public boolean onMenuItemClick(MenuItem item) {

    }*/
}
