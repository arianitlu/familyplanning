package com.example.rinor.familyplanning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rinor.familyplanning.adapters.InstitutionAdapter;
import com.example.rinor.familyplanning.adapters.LifeSituationAdapter;
import com.example.rinor.familyplanning.fragments.FragmentInstitution;
import com.example.rinor.familyplanning.model.LifeSituation;
import com.example.rinor.familyplanning.utilities.JsonUtil;
import com.example.rinor.familyplanning.utilities.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LifeSituationActivity extends AppCompatActivity {

    private static final String LIFE_SITUATION_URL = "http://192.168.0.169/familyplanning/readLifeSituation.php";

    RecyclerView situationRecyclerView;
    LifeSituationAdapter adapter;

    List<LifeSituation> situationList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String MY_PREF = "PREFERENCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_situation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Life Situation");

        situationList = new ArrayList<>();

        situationRecyclerView = findViewById(R.id.lifesituationlist);

        sharedPreferences = getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);

        int languageId = sharedPreferences.getInt("languageId",0);

        getLifeSituationList(2);

    }

    private void getLifeSituationList(int languageId) {

        Uri baseUri = Uri.parse(LIFE_SITUATION_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("languageID", String.valueOf(languageId));


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uriBuilder.toString(),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    situationList = JsonUtil.extractAllLifeSituation(response);
                    adapter = new LifeSituationAdapter(situationList,getApplicationContext());
                    situationRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    situationRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    situationRecyclerView.setAdapter(adapter);

                    Toast.makeText(LifeSituationActivity.this, "LifeSituations array size: " + situationList.size(), Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

            }

        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

    }

}


