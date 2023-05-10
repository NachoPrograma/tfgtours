package com.example.tfgtours.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tfgtours.Activities.Login;
import com.example.tfgtours.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}