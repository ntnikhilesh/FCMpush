package com.example.dell.fcmpush.UsingVolley;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.dell.fcmpush.BackgroundTask;
import com.example.dell.fcmpush.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by DELL on 12/19/2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String recent_token=FirebaseInstanceId.getInstance().getToken();
        Log.d("nttoken1","-"+recent_token);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(getString(R.string.FCM_TOKEN),recent_token);
        editor.commit();

//        Toast.makeText(this,token,Toast.LENGTH_LONG).show();

/*
        //Store token in MySQL db using Async task
       registerToken(recent_token); */


    }



}
