package com.example.turgutre1s.firstaidapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.turgutre1s.firstaidapp.R;
import com.example.turgutre1s.firstaidapp.adapters.NotfallAdpater;
import com.example.turgutre1s.firstaidapp.jsonparser.Parser;
import com.example.turgutre1s.firstaidapp.socketconnection.SocketHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONArray;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.sample_notfall_fragment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 1. Quelle http://loopj.com/android-async-http/
        // Verbindung zum  REST-Server Firstaid-server.
        // Macht einen GET Aufruf - JSON

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2:3000/notfall/json", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONArray jsonArray) {

                //hier kommen die daten aus der DB rein
                NotfallAdpater notfallAdpater = new NotfallAdpater(new Parser().getNotfallList(jsonArray.toString()));
                recyclerView.setAdapter(notfallAdpater);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  Throwable e, JSONArray errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

        });
        // Inflate the layout for this fragment

        /*
        SocketHandler test = new SocketHandler();
        test.getSocket().connect();
        */
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}




