package com.example.turgutre1s.firstaidapp.fragments;

import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.turgutre1s.firstaidapp.R;
import com.example.turgutre1s.firstaidapp.activitys.MainActivity;
import com.example.turgutre1s.firstaidapp.jsonparser.Krankenhaeuser;
import com.example.turgutre1s.firstaidapp.jsonparser.Notfall;
import com.example.turgutre1s.firstaidapp.jsonparser.Parser;
import com.example.turgutre1s.firstaidapp.socketconnection.SocketHandler;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.List;

public class MapsFragment extends Fragment {

    private MapView mapView;



    // Quellenangabe Google Maps Services

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mapView != null) {
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                    googleMap.getUiSettings().setCompassEnabled(true);
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(50.937531, 6.960278600000038), 12.0f));
                   // googleMap.addMarker(new MarkerOptions();
                    // 1. Quelle http://loopj.com/android-async-http/

                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get("http://10.0.2.2:3000/notfall/json", new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers,
                                              JSONArray jsonArray) {

                            //hier kommen die daten aus der DB rein
                            List<Notfall> notfallListe = new Parser().getNotfallList(jsonArray.toString());
                            for (Notfall n : notfallListe) {
                                LatLng l = new LatLng(n.getLat(), n.getLng());
                                googleMap.addMarker(new MarkerOptions().position(l).title(n.getName() + ", " + n.getVorname()));

                            }
                        }


                        @Override
                        public void onFailure(int statusCode, Header[] headers,
                                              Throwable e, JSONArray errorResponse) {
                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        }

                    });
                  //  mapView.getOverlay().add();
                    client.get("http://10.0.2.2:3000/krankenhaus/json", new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers,
                                              JSONArray jsonArray) {

                            //hier kommen die daten aus der DB rein
                            List<Krankenhaeuser> krankenhausListe = new Parser().getKrankenhausList(jsonArray.toString());
                            for (Krankenhaeuser k : krankenhausListe) {
                                LatLng l = new LatLng(k.getLat(), k.getLng());
                                googleMap.addMarker(new MarkerOptions()
                                        .position(l)
                                        .title(k.getName())
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                            }
                        }


                        @Override
                        public void onFailure(int statusCode, Header[] headers,
                                              Throwable e, JSONArray errorResponse) {
                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        }

                    });
                }
            });
        }

        return view;

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}
