package com.example.rinor.familyplanning.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rinor.familyplanning.HomeActivity;
import com.example.rinor.familyplanning.LanguageActivity;
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

    SharedPreferences sharedPreferences;
    String MY_PREF = "PREFERENCE";
    SharedPreferences.Editor editor;

    private static final String FAMILY_PLANNING_BASE_URL = "http://192.168.0.169/familyplanning/readInstitutionsCategory.php";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_help, container, false);

        recyclerView =view.findViewById(R.id.recycler_view_categories);

        sharedPreferences = getActivity().getSharedPreferences(MY_PREF,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int languageId = sharedPreferences.getInt("languageId",0);
        int lifeSituationId = sharedPreferences.getInt("lifeSituationId",0);


        getInstitutionsCategory(languageId,lifeSituationId);

        recyclerView.addOnItemTouchListener(
                new FragmentInstitution.RecyclerItemClickListener(getContext(), recyclerView ,
                        new FragmentInstitution.RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        editor.putInt("category",categoryList.get(position).getID());
                        editor.commit();

                        FragmentInstitution fragmentInstitution= new FragmentInstitution();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, fragmentInstitution, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

        return view;

    }

    public void getInstitutionsCategory(int languageId,int lifeSituation){

        Uri baseUri = Uri.parse(FAMILY_PLANNING_BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("languageID", String.valueOf(languageId));
        uriBuilder.appendQueryParameter("lifeSituation", String.valueOf(lifeSituation));


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

}
