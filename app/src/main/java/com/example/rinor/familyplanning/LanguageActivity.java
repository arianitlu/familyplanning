package com.example.rinor.familyplanning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rinor.familyplanning.adapters.LanguageAdapter;
import com.example.rinor.familyplanning.fragments.FragmentInstitution;
import com.example.rinor.familyplanning.model.Language;
import com.example.rinor.familyplanning.utilities.JsonUtil;
import com.example.rinor.familyplanning.utilities.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LanguageAdapter languageAdapter;
    List<Language> languages = new ArrayList<>();

    SharedPreferences sharedPreferences;
    String MY_PREF = "my_pref";

    private boolean calledOnce = false;

    private static final String LANGUAGE_BASE_URL = "http://192.168.0.169/familyplanning/readLanguages.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        recyclerView = findViewById(R.id.recycler_view_language);

        recyclerView.addOnItemTouchListener(
                new LanguageActivity.RecyclerItemClickListener(getApplicationContext(), recyclerView,
                        new FragmentInstitution.RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        sharedPreferences = getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("language",position);
                        editor.commit();

                        switch (position){
                            case 0: languageToLoad("sq");
                            break;
                            case 1: languageToLoad("en");
                            break;
                            default: languageToLoad("en");
                            break;
                        }
                        restartApp();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

        getLanguages();
    }

    public void getLanguages(){

        Uri baseUri = Uri.parse(LANGUAGE_BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, uriBuilder.toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            languages = JsonUtil.extractAllLanguages(response);
                            languageAdapter = new LanguageAdapter(languages);
                            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                            recyclerView.setAdapter(languageAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LanguageActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private FragmentInstitution.RecyclerItemClickListener.OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, FragmentInstitution.RecyclerItemClickListener.OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }

    public void languageToLoad(String languageToLoad) {
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);

        saveLocale(languageToLoad);

        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    public void saveLocale(String lang) {
        String langPref = "Language";

        SharedPreferences prefs = getSharedPreferences("CommonLanguage",
                Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void restartApp() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

        startActivity(new Intent(LanguageActivity.this,SplashScreen.class));
    }

}
