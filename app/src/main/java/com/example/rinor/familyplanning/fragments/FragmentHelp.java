package com.example.rinor.familyplanning.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.adapters.InstitutionCategoryAdapter;
import com.example.rinor.familyplanning.model.InstitutionCategory;
import com.example.rinor.familyplanning.utilities.JsonUtil;
import com.example.rinor.familyplanning.utilities.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentHelp extends Fragment {

    RecyclerView recyclerView;
    InstitutionCategoryAdapter adapter;
    List<InstitutionCategory> categoryList = new ArrayList<>();

    private static final String FAMILY_PLANNING_BASE_URL = "http://192.168.0.169/familyplanning/readInstitutionsCategory.php";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_help, container, false);

        recyclerView =view.findViewById(R.id.recycler_view_categories);

        getInstitutionsCategory();

        return view;

    }

    public void getInstitutionsCategory(){

        Uri baseUri = Uri.parse(FAMILY_PLANNING_BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, uriBuilder.toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            categoryList = JsonUtil.extractInstitutionsCategory(response);
                            adapter = new InstitutionCategoryAdapter(categoryList);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                    }
                });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }
}
