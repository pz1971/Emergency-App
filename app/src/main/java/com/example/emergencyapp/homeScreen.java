package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class homeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        setupButton1();
        setupButton2();
        setupButton3();
        setupButton4();
        setupButton5();
        setupExitButton();
        setupUserButton();
        setupInfoButton();
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    protected void setupButton1() {
        ((ImageButton) findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("333");
            }
        });
    }
    protected void setupButton2(){
        ((ImageButton)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("999");
            }
        });
    }
    protected void setupButton3(){
        ((ImageButton)findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("109");
            }
        });
    }
    protected void setupButton4(){
        ((ImageButton)findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("106");
            }
        });
    }
    protected void setupButton5(){
        ((ImageButton)findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("1090");
            }
        });
    }
    protected void setupExitButton(){
        ((ImageButton)findViewById(R.id.exitButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }
    protected void setupUserButton(){
        ((ImageButton)findViewById(R.id.userButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homeScreen.this, user_activity.class) ;
                startActivity(i);
            }
        });
    }
    protected void setupInfoButton(){
        ((ImageButton)findViewById(R.id.infoButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
