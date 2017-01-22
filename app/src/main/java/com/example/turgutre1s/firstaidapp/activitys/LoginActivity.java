package com.example.turgutre1s.firstaidapp.activitys;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.turgutre1s.firstaidapp.R;
import com.example.turgutre1s.firstaidapp.adapters.NotfallAdpater;
import com.example.turgutre1s.firstaidapp.fragments.FragmentDrawer;
import com.example.turgutre1s.firstaidapp.jsonparser.Parser;
import com.example.turgutre1s.firstaidapp.jsonparser.Rettungswagen;
import com.example.turgutre1s.firstaidapp.socketconnection.SocketHandler;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

/**
 * Created by TurgutRe1s on 12.01.2017.
 */

//Quellenangabe: Zu hilfe genommene Quelle http://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/

    // neues Updated Login Activity
    //Die LoginActivity wird beim Start der APP ausgeführt, das ist wichtig, damit der Rettungsdienst ein gesicherten Zugang auf die Notfall Informationen hat.

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;


    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);
        // Bei Klick auf das Login Button wird die Methode login() Ausgeführt.
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        // 1. Quelleangabe http://loopj.com/android-async-http/
        //Wenn das Login geklappt hat, also alles eingegeben wurde, wird über das client Objekt ein HTTP GET auf die Localhost URL gemacht.
        // mit RequestParams schickt der Client bei Eingabe von Email und Passwort, Informationen zum Server.
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("fahrbezeichnung", _emailText.getText().toString());
        params.put("passwort", _passwordText.getText().toString());

        client.get("http://10.0.2.2:3000/rettungswagen/login", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONArray jsonArray) {
                //Wenn alles passt also Email und Passwort von der Collection Rettungswagen und die Eingabe übereinstimmen, wird die MainActivity gestartet.
                //  Falls nicht soll er ein Toast machen Login fehlerhaft
                //hier kommen die daten aus der DB rein
                List<Rettungswagen> rettungsListe = (new Parser().getRettungswagenList(jsonArray.toString()));
                if (!rettungsListe.isEmpty()) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), "Login fehlerhaft", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  Throwable e, JSONArray errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    // Falls Passwort nicht nachdem forgegebenen Format ist oder die Email. LOGIN FAILED
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
    }


// Überprüft ob in dem EditText Email und Passwort korrekt eingegeben wurden.
    public boolean validate() {
        boolean valid = true;
        String bezeichnung = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
// Hier wird überprüft ob das Email Format richtig Eingegeben wurde
        if (bezeichnung.isEmpty()) {
            _emailText.setError("enter a valid String");
            valid = false;
        } else {
            _emailText.setError(null);
        }
// Passwort Box wird überprüft
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


}

