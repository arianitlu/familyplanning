package com.example.rinor.familyplanning.utilities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rinor.familyplanning.LanguageActivity;
import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.adapters.LifeSituationAdapter;
import com.example.rinor.familyplanning.model.Institution;
import com.example.rinor.familyplanning.model.LifeSituation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LifeSituationActivity extends AppCompatActivity {

    private static final String FAMILY_PLANNING_BASE_URL = "http://192.168.0.169/familyplanning/readLifeSituation.php";

    RecyclerView situationRecyclerView;
    LifeSituationAdapter adapter;

    List<LifeSituation> situationList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_situation);


        situationRecyclerView = findViewById(R.id.lifesituationlist);

        getLifeSituationList();



    }

    private void getLifeSituationList() {

        Uri baseUri = Uri.parse(FAMILY_PLANNING_BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uriBuilder.toString(),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    situationList = JsonUtil.extractAllLifeSituation(response);
                    adapter = new LifeSituationAdapter(situationList,getApplicationContext());
                    situationRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                    situationRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    situationRecyclerView.setAdapter(adapter);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                } } }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                    }


        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);


    }
}