package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void buttonClicked(View view){
        Intent intent = null;

        if(view.getId() == R.id.helloActivityB){
            intent = new Intent(MenuActivity.this, MainActivity.class);
        }else if(view.getId() == R.id.statusActivityB){
            intent = new Intent(MenuActivity.this, StatusActivity.class);
        }else if(view.getId() == R.id.listActivity){
            intent = new Intent(MenuActivity.this, ExerciseActivity.class);
        }else{
            intent = new Intent(MenuActivity.this, GameActivity.class);
        }

        startActivity(intent);
    }
}