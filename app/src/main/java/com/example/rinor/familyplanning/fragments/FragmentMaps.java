package com.example.rinor.familyplanning.fragments;

import android.Manifest;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
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
import com.example.rinor.familyplanning.model.Institution;
import com.example.rinor.familyplanning.model.InstitutionCategory;
import com.example.rinor.familyplanning.utilities.GPStracker;
import com.example.rinor.familyplanning.utilities.JsonUtil;
import com.example.rinor.familyplanning.utilities.MySingleton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FragmentMaps extends Fragment implements  OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    double latGPS;
    double lonGPS;
    private boolean gpsCalled = false;
    float colorMarkerNormal = BitmapDescriptorFactory.HUE_RED;
    float colorMarkerGPS = BitmapDescriptorFactory.HUE_AZURE;

    List<Institution> institutionList = new ArrayList<>();

    private static final String FAMILY_PLANNING_BASE_URL = "http://192.168.0.169/familyplanning/readInstitutions.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getInstitutionList();

        callGpsOnTouch();

    }

    public void callGpsOnTouch() {

        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        if (gpsCalled == false) {

            GPStracker g = new GPStracker(getContext());
            Location l = g.getLocation();

            if (l != null) {
                latGPS = l.getLatitude();
                lonGPS = l.getLongitude();

                createPointMap(latGPS, lonGPS, "Your location", colorMarkerGPS,100);
            }
        }

        goToPointMap(latGPS, lonGPS);

        gpsCalled = true;
    }

    public void createPointMap(double latitude, double longitude, String title, float color,int id) {

        LatLng location = new LatLng(latitude, longitude);

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
                .zoom(14)
                .build();

       mMap.addMarker(new MarkerOptions()
                .position(location)
                .title(title)
                .icon(BitmapDescriptorFactory.defaultMarker(color)));

       mMap.addCircle(new CircleOptions()
                .center(location)
                .radius(100)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(64, 0, 255, 0)));
    }

    public void goToPointMap(double latitude, double longitude) {

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
                .zoom(8)
                .build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 4000, null);
    }

    public void getInstitutionList(){

        Uri baseUri = Uri.parse(FAMILY_PLANNING_BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, uriBuilder.toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            institutionList = JsonUtil.extractAllInstitutions(response);
                            for (int i = 0; i < institutionList.size(); i++) {
                                createPointMap(institutionList.get(i).getLat(), institutionList.get(i).getLng(),
                                        institutionList.get(i).getName(), colorMarkerNormal,i);

                                Toast.makeText(getContext(), "SpitaliPrishtine lat: " + institutionList.get(0).getLat(), Toast.LENGTH_SHORT).show();
                            }

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
