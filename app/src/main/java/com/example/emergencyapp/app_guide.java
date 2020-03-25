package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class app_guide extends AppCompatActivity{
    protected ImageView img0, img1, img2, img3, img4, img5 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_guide);

        img0 = (ImageView)findViewById(R.id.img0) ;
        img1 = (ImageView)findViewById(R.id.img1) ;
        img2 = (ImageView)findViewById(R.id.img2) ;
        img3 = (ImageView)findViewById(R.id.img3) ;
        img4 = (ImageView)findViewById(R.id.img4) ;
        img5 = (ImageView)findViewById(R.id.img5) ;

        img0.setVisibility(View.VISIBLE);
        img1.setVisibility(View.INVISIBLE);
        img2.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        img4.setVisibility(View.INVISIBLE);
        img5.setVisibility(View.INVISIBLE);

        img0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img0.setVisibility(View.GONE);
                img1.setVisibility(View.VISIBLE);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.setVisibility(View.GONE);
                img3.setVisibility(View.VISIBLE);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.setVisibility(View.GONE);
                img4.setVisibility(View.VISIBLE);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img4.setVisibility(View.GONE);
                img5.setVisibility(View.VISIBLE);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img5.setVisibility(View.GONE);
                app_guide.super.onBackPressed();
            }
        });
    }
}
