package com.example.turgutre1s.firstaidapp.socketconnection;

import android.app.Application;
import android.location.Location;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Ali Mazlum on 28.12.2016.
 */

public class SocketHandler extends Application {

    private Socket mSocket;

    public SocketHandler() {

        try {
            mSocket = IO.socket("http://10.0.2.2:3001");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        });

        mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        });


    }

    public void connect(){
        mSocket.connect();
    }



    public void emitGPS(Location loc) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("lat", loc.getLatitude());
            obj.put("lng", loc.getLongitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("gps", obj.toString());
    }


}