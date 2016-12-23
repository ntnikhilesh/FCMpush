package com.example.dell.fcmpush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String method="register";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,"NIKHIL","nikhil1234","nikhil@1234");
        finish();
    }
}
