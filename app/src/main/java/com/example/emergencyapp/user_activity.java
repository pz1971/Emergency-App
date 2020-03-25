package com.example.emergencyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

        initialize_widgets();       // initialized all the widgets, assignes view by id and sets initial states
        dp.setClickable(false);
    }

    void change_clickable(boolean flag){    // if flag is true, all the widgets will be clickable
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

        set_all_data() ;

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
                store_all_data();
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

    protected void store_all_data(){
        String text ;

        try{
            FileOutputStream fos = openFileOutput("em_app_data.txt", getApplicationContext().MODE_PRIVATE) ;

            text = "0" +  name.getText().toString() + "\n";
            fos.write(text.getBytes());
            text = "1" + guardian_name.getText().toString() + "\n";
            fos.write(text.getBytes());
            text = "2" + guardian_contact.getText().toString() + "\n";
            fos.write(text.getBytes());
            text = "3" + blood_group.getText().toString() + "\n";
            fos.write(text.getBytes());
            text = "4" + address.getText().toString() + "\n";
            fos.write(text.getBytes());
            text = "5"+ birthday.getText().toString() + "\n";
            fos.write(text.getBytes());

            BitmapDrawable drawable = (BitmapDrawable) dp.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            text = "6" + saveToInternalStorage(bitmap) + "\n" ;
            fos.write(text.getBytes());

            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void set_all_data(){
        try{
            FileInputStream fis = openFileInput("em_app_data.txt") ;
            InputStreamReader isr = new InputStreamReader(fis) ;
            BufferedReader buffer = new BufferedReader(isr) ;

            String line ;
            while((line = buffer.readLine()) != null){
                switch (line.charAt(0)){

                    case '0':
                        name.setText(line.substring(1));
                        break ;
                    case '1':
                        guardian_name.setText(line.substring(1));
                        break ;
                    case '2':
                        guardian_contact.setText(line.substring(1));
                        break ;
                    case '3':
                        blood_group.setText(line.substring(1));
                        break ;
                    case '4':
                        address.setText(line.substring(1));
                        break ;
                    case '5':
                        birthday.setText(line.substring(1));
                        break ;
                    case '6' :
                        loadImageFromStorage(line.substring(1));
                        break ;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            dp.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    private void dialContactPhone(final String phoneNumber) {   // dials a number
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
