package com.example.learningapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    EditText playerGuessET;
    TextView resultTV;
    String computerNumber;
    int guessesLeft = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerGuessET = findViewById(R.id.playerGuessET);
        resultTV = findViewById(R.id.resultTV);

        initGame();
    }

    private void initGame() {
        computerNumber = "";
        generateNumber();
    }

    public void guessClicked(View view){
        String playerGuess = playerGuessET.getText().toString();

        if(playerGuess.length() != 4){
            Toast.makeText(GameActivity.this, "Въведете 4 символа", Toast.LENGTH_LONG).show();
            return;
        }

        guessesLeft--;

        int cows = 0;
        int bulls = 0;

        for(int i = 0; i < computerNumber.length(); i++){
            if(playerGuess.charAt(i) == computerNumber.charAt(i)){
                bulls++;
            }else if(computerNumber.contains(String.valueOf(playerGuess.charAt(i)))){
                cows++;
            }
        }

        Log.d("game", playerGuessET.getText().toString() + "   К:" + cows + " Б:" + bulls);

        resultTV.setText(playerGuessET.getText().toString() + "   К:" + cows + "Б:" + bulls + "\n" + resultTV.getText());

        if(bulls == computerNumber.length()){
            Toast.makeText(this, "БРАВООООО!", Toast.LENGTH_SHORT).show();
        }else if(guessesLeft <= 0){
            Toast.makeText(this, "ЗАГУБЕНЯК!!", Toast.LENGTH_SHORT).show();
        }

    }

    private void generateNumber() {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        while(sb.length() < 4){
            int digit = random.nextInt(10);

            if(sb.indexOf(digit + "") == -1){
                sb.append(digit);
            }
        }

        computerNumber = sb.toString();
        Log.wtf("cheat", computerNumber);
    }
}