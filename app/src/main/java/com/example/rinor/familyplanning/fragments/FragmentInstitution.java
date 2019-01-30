package com.example.rinor.familyplanning.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.adapters.InstitutionAdapter;
import com.example.rinor.familyplanning.model.Institution;
import com.example.rinor.familyplanning.utilities.JsonUtil;
import com.example.rinor.familyplanning.utilities.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentInstitution extends Fragment {

    private static final String FAMILY_PLANNING_BASE_URL = "http://192.168.0.169/familyplanning/readInstitutions.php";
    RecyclerView institutionRecyclerView;
    InstitutionAdapter adapter;
    List<Institution> list = new ArrayList<>();
    List<Institution> institutionList = new ArrayList<>();

    InstitutionAdapter.InstitutionClickHandler clickHandler;

    Dialog myDialog;

    TextView txtname, txtDescription, txtServices, txtWeb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_instution, container, false);

        myDialog = new Dialog(getActivity());

        institutionRecyclerView = view.findViewById(R.id.recyclerview_instutiton);

        getInstitutionList(clickHandler);

        myDialog.setCanceledOnTouchOutside(true);


        return view;
    }

    public void fillData(Institution institution) {
        txtname.setText(institution.getName());
        txtDescription.setText(institution.getDescription());
    }

    public void getInstitutionList(final InstitutionAdapter.InstitutionClickHandler clickHandler) {

        Uri baseUri = Uri.parse(FAMILY_PLANNING_BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, uriBuilder.toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            institutionList = JsonUtil.extractAllInstitutions(response);
                            institutionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                            adapter = new InstitutionAdapter(institutionList);

                            institutionRecyclerView.setAdapter(adapter);

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

