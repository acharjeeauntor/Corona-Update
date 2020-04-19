package com.auntor.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenImageActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        imageView=findViewById(R.id.image_view);

        getSupportActionBar().hide();
        getSupportActionBar().setTitle("Information");


        Intent i = getIntent();
       int position =  i.getExtras().getInt("id");

       ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext());
       imageView.setImageResource(imageAdapter.imageArray[position]);


    }
}
