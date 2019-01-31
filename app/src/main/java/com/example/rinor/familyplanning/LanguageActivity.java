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

    private boolean calledOnce = false;

    Locale locale;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String MY_PREF = "PREFERENCE";

    private static final String LANGUAGE_BASE_URL = "http://192.168.0.169/familyplanning/readLanguages.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        recyclerView = findViewById(R.id.recycler_view_language);

        sharedPreferences = getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String languageToLoad = sharedPreferences.getString("languageToLoad","sq");
        languageToLoad(languageToLoad);

        if (sharedPreferences.getBoolean("isCalled",false)){
            startActivity(new Intent(LanguageActivity.this,SplashScreen.class));
        }else {
            recyclerView.addOnItemTouchListener(
                    new LanguageActivity.RecyclerItemClickListener(getApplicationContext(), recyclerView,
                            new FragmentInstitution.RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (position) {
                                        case 0:
                                            editor.putInt("languageId",position);
                                            editor.putBoolean("isCalled",true);
                                            languageToLoad("sq");
                                            break;
                                        case 1:
                                            editor.putInt("languageId",2);
                                            editor.putBoolean("isCalled",true);
                                            languageToLoad("en");
                                            break;
                                        case 2:
                                            editor.putInt("languageId",3);
                                            editor.putBoolean("isCalled",true);
                                            languageToLoad("sq");
                                            break;
                                        case 3:
                                            editor.putInt("languageId",7);
                                            editor.putBoolean("isCalled",true);
                                            languageToLoad("sq");
                                            break;
                                        case 4:
                                            editor.putInt("languageId",8);
                                            editor.putBoolean("isCalled",true);
                                            languageToLoad("sq");
                                            break;
                                        case 5:
                                            editor.putInt("languageId",9);
                                            editor.putBoolean("isCalled",true);
                                            languageToLoad("sq");
                                            break;
                                        case 6:
                                            editor.putInt("languageId",10);
                                            editor.putBoolean("isCalled",true);
                                            languageToLoad("sq");
                                            break;
                                    }

                                    startActivity(new Intent(LanguageActivity.this, SplashScreen.class));
                                }

                                @Override
                                public void onLongItemClick(View view, int position) {
                                }
                            })
            );

            getLanguages();
        }

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

    public void languageToLoad(String language){

        locale = new Locale(language);
        Locale.setDefault(locale);
        editor.putString("languageToLoad",language);
        editor.commit();

        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

}
