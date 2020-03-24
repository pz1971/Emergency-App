package com.example.emergencyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class user_activity extends AppCompatActivity {
    protected EditText name, guardian_name, guardian_contact, blood_group, address, birthday ;
    protected ImageButton call_guardian, edit_button ;
    protected Button save_button ;
    protected ImageView dp ;

    private static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initialize_widgets();
        dp.setClickable(false);
    }

    void change_clickable(boolean flag){
        if(flag){
            name.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            guardian_name.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            guardian_contact.setInputType(InputType.TYPE_CLASS_PHONE);
            blood_group.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
            address.setInputType(InputType.TYPE_CLASS_TEXT);
            birthday.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
            dp.setClickable(true);
        }
        else{
            name.setInputType(InputType.TYPE_NULL);
            guardian_name.setInputType(InputType.TYPE_NULL);
            guardian_contact.setInputType(InputType.TYPE_NULL);
            blood_group.setInputType(InputType.TYPE_NULL);
            address.setInputType(InputType.TYPE_NULL);
            birthday.setInputType(InputType.TYPE_NULL);
            dp.setClickable(false);
        }
    }

    void initialize_widgets(){
        name = (EditText) findViewById(R.id.name) ;
        guardian_name = (EditText) findViewById(R.id.guardian_name) ;
        guardian_contact = (EditText) findViewById(R.id.guardian_contact) ;
        blood_group = (EditText) findViewById(R.id.blood_group) ;
        address = (EditText) findViewById(R.id.address) ;
        birthday = (EditText) findViewById(R.id.birthday) ;

        call_guardian = (ImageButton) findViewById(R.id.call_guardian) ;
        edit_button = (ImageButton) findViewById(R.id.edit_button) ;

        save_button = (Button) findViewById(R.id.save_button) ;
        save_button.setVisibility(View.INVISIBLE);

        dp = (ImageView) findViewById(R.id.dp) ;

        change_clickable(false);

        call_guardian.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String num = guardian_contact.getText().toString() ;
                if(num.isEmpty())
                    Toast.makeText(getApplicationContext(),"Contact Number Not Given",Toast.LENGTH_SHORT).show();
                else
                    dialContactPhone(num);
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_button.setVisibility(View.INVISIBLE);
                save_button.setVisibility(View.VISIBLE);
                change_clickable(true);
            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_button.setVisibility(View.INVISIBLE);
                edit_button.setVisibility(View.VISIBLE);
                change_clickable(false);
            }
        });

        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = findViewById(R.id.dp);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
