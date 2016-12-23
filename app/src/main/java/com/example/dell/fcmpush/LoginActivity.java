package com.example.dell.fcmpush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String method="login";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,"nikhil1234","nikhil@1234");

    }
}
