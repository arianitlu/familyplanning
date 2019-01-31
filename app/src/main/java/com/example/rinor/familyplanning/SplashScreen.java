package com.example.rinor.familyplanning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String MY_PREF = "PREFERENCE";

    Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        sharedPreferences = getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);
        String languageToLoad = sharedPreferences.getString("languageToLoad","sq");

        languageToLoad(languageToLoad);

    }

    public void onClick (View view) {

        Intent i = new Intent(getApplicationContext(),IntroActivity.class);
        startActivity(i);
    }

    public void languageToLoad(String language){
        locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }



}
