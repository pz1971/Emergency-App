package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    boolean flag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flag = false;
        try{
            FileInputStream fis = openFileInput("em_app_log.txt") ;
            InputStreamReader isr = new InputStreamReader(fis) ;
            BufferedReader buffer = new BufferedReader(isr) ;
            String str ;

            while ((str = buffer.readLine()) != null)
                if(!str.isEmpty())
                    flag = true;

            fis.close(); isr.close(); buffer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("em_app_log.txt", getApplicationContext().MODE_PRIVATE);
            fos.write(("not first time\n").getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                Intent i3 ;
                if(flag == true)        // not first time
                    i3 = new Intent(MainActivity.this, homeScreen.class);
                else
                    i3 = new Intent(MainActivity.this, app_guide.class);

                i3.putExtra("flag", flag) ;
                startActivity(i3);
            }
        }, 1000);   // auto opens home screen after 1 second
    }
}
