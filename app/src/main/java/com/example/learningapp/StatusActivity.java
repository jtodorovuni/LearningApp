package com.example.learningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StatusActivity extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        image = findViewById(R.id.statusImage);
    }

    public void onButtonClicked(View view){

        if(view.getId() == R.id.s200b){
            image.setImageResource(R.drawable.s200);
        }else if(view.getId() == R.id.s400b){
            image.setImageResource(R.drawable.s400);
        }else if(view.getId() == R.id.s409b){
            image.setImageResource(R.drawable.s409);
        }else if(view.getId() == R.id.s500b){
            image.setImageResource(R.drawable.s500);
        }
    }
}