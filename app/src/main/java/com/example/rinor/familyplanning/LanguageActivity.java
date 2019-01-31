package com.example.rinor.familyplanning;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rinor.familyplanning.adapters.LanguageAdapter;
import com.example.rinor.familyplanning.model.Language;
import com.example.rinor.familyplanning.utilities.JsonUtil;
import com.example.rinor.familyplanning.utilities.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LanguageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LanguageAdapter languageAdapter;
    List<Language> languages = new ArrayList<>();

    private static final String LANGUAGE_BASE_URL = "http://192.168.0.169/familyplanning/readLanguages.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        recyclerView = findViewById(R.id.recycler_view_language);

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
}
