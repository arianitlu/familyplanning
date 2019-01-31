package com.example.rinor.familyplanning.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    List<Institution> institutionList = new ArrayList<>();

    InstitutionAdapter.InstitutionClickHandler clickHandler;

    Dialog myDialog;

    TextView txtname, txtDescription, txtServices, txtWeb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_instution, container, false);

        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.popup_institution_name);

        txtname = myDialog.findViewById(R.id.txtPopUpName);
        txtDescription = myDialog.findViewById(R.id.txtPopUpDescription);
        txtServices = myDialog.findViewById(R.id.txtPopUpServices);
        txtWeb = myDialog.findViewById(R.id.txtPopupWeb);

        institutionRecyclerView = view.findViewById(R.id.recyclerview_instutiton);

        getInstitutionList();

        myDialog.setCanceledOnTouchOutside(true);

        institutionRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), institutionRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        getInstitutionListById(position);
                        myDialog.show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

        return view;
    }

    public void getInstitutionList() {

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

                            String name = institutionList.get(0).getName();
                            txtname.setText(name);
                            txtDescription.setText(name);

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

    public void getInstitutionListById(final int id) {

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

                            txtname.setText(institutionList.get(id).getName());
                            txtDescription.setText(institutionList.get(id).getDescription());
                            txtServices.setText(institutionList.get(id).getServices());
                            txtWeb.setText(institutionList.get(id).getWebsite());

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
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
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

