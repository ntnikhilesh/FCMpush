package com.example.dell.fcmpush.UsingVolley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by DELL on 12/25/2016.
 */

//Instance of request queue that used for life time using Singleton patteren
public class Mysingleton {

    private static Mysingleton mInstance;
    private static RequestQueue requestQueue;
    private static Context mcontext;

    //Constructor for this class
    private Mysingleton(Context context)
    {

        mcontext=context;
        requestQueue=getRequestQueue();

    }

    //Get instance of request queue
    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }

    //Get instance of this class
    public static synchronized Mysingleton getInstance(Context context)
    {
        if (mInstance==null)
        {
            mInstance=new Mysingleton(context);
        }
        return mInstance;
    }

    //Add request to the request queue
    public<T> void addToRequestQueue(Request< T> request)
    {
            requestQueue.add(request);
    }
}
