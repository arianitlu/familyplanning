package com.example.rinor.familyplanning.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rinor.familyplanning.R;
import com.example.rinor.familyplanning.adapters.InstitutionCategoryAdapter;
import com.example.rinor.familyplanning.adapters.TopicsAdapter;
import com.example.rinor.familyplanning.model.InstitutionCategory;
import com.example.rinor.familyplanning.model.Topics;
import com.example.rinor.familyplanning.utilities.JsonUtil;
import com.example.rinor.familyplanning.utilities.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentInformation extends Fragment {

    private static final String FAMILY_PLANNING_BASE_URL = "http://192.168.0.169/familyplanning/topics.php";

    TopicsAdapter listAdapter;
    ExpandableListView expandableListView;
    List<Topics> listDataHeader;
    ArrayList<String> childTest;
    HashMap<Topics,String> listDataChild;

    ArrayList<String> header;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_info, container, false);

        expandableListView = view.findViewById(R.id.expandablelist);

        prepareListData();


        return view;

    }

    private void prepareListData (){
        Uri baseUri = Uri.parse(FAMILY_PLANNING_BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uriBuilder.toString(),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TOPIC","onResponse");
                try {


                    listDataHeader = JsonUtil.extractAllTopics(response);
                    Log.d("TOPIC",listDataHeader.size()+" size");

                    listDataChild = new HashMap<Topics,String>();

                    for (int i = 0; i<listDataHeader.size(); i++) {
                        Log.d("TOPIC",listDataHeader.size()+" size");
                        Log.d("TOPIC",listDataHeader.get(i).getTopicName()+" name");
                        Log.d("TOPIC",listDataHeader.get(i).getTopicCategory()+"Test");
                        listDataChild.put(listDataHeader.get(i),listDataHeader.get(i).getTopicCategory());
                    }
                    listAdapter = new TopicsAdapter(getContext(),listDataHeader,listDataChild);
                    expandableListView.setAdapter(listAdapter);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                } } }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

            }


        });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

    }

}
