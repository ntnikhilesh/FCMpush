package com.example.dell.fcmpush;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by DELL on 12/23/2016.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;

    Context ctx;
    BackgroundTask(Context ctx)
    {
     this.ctx=ctx;
    }



    @Override
    protected void onPreExecute() {
//        alertDialog=new AlertDialog.Builder(ctx).create();
       // alertDialog.setTitle("Login information....");

    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://172.16.0.2/webapp/register.php";//172.16.0.2 is the system IPV4 address
        String login_url="http://172.16.0.2/webapp/login.php";
        String fcm_login_url="http://172.16.0.2/fcm/fcmregister.php";
        String method=params[0];

        if (method.equals("register"))
        {
            String name=params[1];
            String user_name=params[2];
            String user_pass=params[3];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "Registration success....";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("login"))
        {
            //recive values whiech send from execute()
            String user_name=params[1];
            String user_pass=params[2];

            try {
                //Create Object of the URL
                URL url=new URL(login_url);
                //Create Object of HTTP URL Conn
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                //Set some paramenter for HTTP URL Conn
                //Set request method ie POST
                httpURLConnection.setRequestMethod("POST");
                //To send info to server
                httpURLConnection.setDoOutput(true);
                //To get response from server
                httpURLConnection.setDoInput(true);

                //Get out put stream from Http URL conn
                OutputStream OS= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                //We are using post method ,,so we need to specify that how we encode data
                String data=URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
                //Now wite the data
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                //How to get response from the server
                InputStream IS=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(IS,"ISO-8859-1"));
                String response="";
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (method.equals("register1"))
        {
            String token=params[1];
            Log.d("regtoken","="+token);

            try {
                URL url=new URL(fcm_login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
               // httpURLConnection.setDoInput(true);

                OutputStream OS= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data=URLEncoder.encode("token","UTF-8")+"="+URLEncoder.encode(token,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "FCM Registration success";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Registration success....")) {
            Toast.makeText(ctx, "result = " + result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("FCM Registration success"))
        {
            Toast.makeText(ctx, "result = " + result, Toast.LENGTH_LONG).show();
        }
        else {
            //Toast.makeText(ctx, "result = " + result, Toast.LENGTH_LONG).show();
               alertDialog.setMessage(result);
                alertDialog.show();
        }

    }
}
