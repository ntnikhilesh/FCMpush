package com.example.dell.fcmpush.UsingVolley;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.fcmpush.R;
import com.example.dell.fcmpush.UsingVolley.Mysingleton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button send_reg_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String app_server_url="http://192.168.43.44/fcmtest/fcm_insert.php";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        send_reg_token=(Button)findViewById(R.id.button_send_reg_token);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Store token in database using Volley
        send_reg_token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
                final String token=sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
                // final String token="eVbJqAkYVas:APA91bEpGWBGeKs77fAIJRVHruKP8iv3vhYpsBMDjIlZdVjpF18jnVdADrALX5ZWA-dTHbOf-QuJF62dd0HseEbYbjkPZFZ5frDycttf8clw2Ok3V2RM0BVS8u0ynbJmJMfLmji_nc0z";//sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
                Log.d("mytoken",""+token);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {



                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(RegisterActivity.this, "Some thing wrong with registration...", Toast.LENGTH_LONG).show();
                        Log.d("regerror", ": " + error);

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> prams = new HashMap<String, String>();
                        prams.put("fcm_token", token);

                        return prams;
                    }
                };

                //Add String request to Request Queue
                Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


            }
        });




        //Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        //startActivity(intent);





        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
