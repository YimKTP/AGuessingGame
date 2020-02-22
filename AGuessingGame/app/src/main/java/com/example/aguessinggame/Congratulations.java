package com.example.aguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Congratulations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);
        final TextView help = findViewById(R.id.helpText);

        String attempt = getIntent().getExtras().getString("Attempt");

        help.setText("You tried " + attempt + " times");
        Button restart = (Button) findViewById(R.id.restartButton);
        restart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
                finish();
            }
        });
    }
}
