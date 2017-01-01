package com.example.dell.fcmpush;

import android.app.Service;
import android.util.Log;
import android.widget.Toast;

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
//        Toast.makeText(this,token,Toast.LENGTH_LONG).show();

/*
        //Store token in MySQL db using Async task
       registerToken(recent_token); */


    }

    private void registerToken(String token) {

        String method="register1";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,token);


        /*

        Log.d("nt2you r here",token);
        OkHttpClient client=new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("Token",token)
                .build();

        Request request=new Request.Builder()
                .url("http://localhost/fcm/register.php")
                .post(body)
                .build();

        Response response = null; try { response = client.newCall(request).execute();
           System.out.println("nt3"+response.body().string()); } catch (IOException e) {Log.d("nt4",""+e.getMessage());}

       /* try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
