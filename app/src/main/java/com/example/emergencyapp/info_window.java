package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class info_window extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_window);

        ((TextView)findViewById(R.id.linkedin_link)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView)findViewById(R.id.github_link)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView)findViewById(R.id.version)).setText("Version: " + BuildConfig.VERSION_NAME);

        Button guide_button = (Button)findViewById(R.id.guide_button) ;
        guide_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
