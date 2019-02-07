package com.example.rinor.familyplanning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.rinor.familyplanning.fragments.FragmentHelp;
import com.example.rinor.familyplanning.fragments.FragmentInstitution;
import com.example.rinor.familyplanning.fragments.FragmentMaps;
import com.example.rinor.familyplanning.fragments.FragmentInformation;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        ,PopupMenu.OnMenuItemClickListener {

    Toolbar toolbar;
    Locale locale;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String MY_PREF = "PREFERENCE";

    TextView txt_iso_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_iso_language = findViewById(R.id.txt_toolbar_language);

        sharedPreferences = getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int colorOfLifeSituation = sharedPreferences.getInt("colorOfLifeSituation",R.color.colorAccent);

        getWindow().setNavigationBarColor(getResources().getColor(colorOfLifeSituation));
        getWindow().setStatusBarColor(getResources().getColor(colorOfLifeSituation));
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(colorOfLifeSituation));

        String languageToLoad = sharedPreferences.getString("languageToLoad","EN");
        txt_iso_language.setText(languageToLoad.toUpperCase());

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
            Intent intent = new Intent(HomeActivity.this, LifeSituationActivity.class);
            startActivity(intent);
            finish();
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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentInformation()).commit();
            toolbar.setTitle(getResources().getString(R.string.general_info));

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
                Toast.makeText(this, "Albanian", Toast.LENGTH_SHORT).show();
                editor.putInt("languageId",0);
                languageToLoad("sq");
                return true;
            case R.id.english:
                Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
                editor.putInt("languageId",2);
                languageToLoad("en");
                return true;
            case R.id.arabic:
                Toast.makeText(this, "Arabic", Toast.LENGTH_SHORT).show();
                languageToLoad("en");
                return true;
            case R.id.farsi:
                Toast.makeText(this, "Farsi", Toast.LENGTH_SHORT).show();
                languageToLoad("en");
                return true;
            case R.id.french:
                Toast.makeText(this, "French", Toast.LENGTH_SHORT).show();
                languageToLoad("en");
                return true;
            default:
                return false;
        }
    }

    public void languageToLoad(String language){
        locale = new Locale(language);
        Locale.setDefault(locale);

        editor.putString("languageToLoad",language);
        editor.commit();

        txt_iso_language.setText(language.toUpperCase());

        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }
}
