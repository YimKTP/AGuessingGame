package com.example.aguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity {
    EditText userGuess;
    SecretNumber game = new SecretNumber();
    int n = 0;
    int min=1,max=100;
    boolean SHOW_SUGGEST = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game.start();
        Button guess = (Button) findViewById(R.id.guessButton);
        userGuess = (EditText) findViewById(R.id.guessInput);
        final TextView response = findViewById(R.id.responseText);
        final TextView help = findViewById(R.id.helpText);
        response.setText("Enter a number \n(1 - 100)");
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String g = userGuess.getText().toString();
                if (g.length() != 0 && Integer.parseInt(g)<=100 && Integer.parseInt(g)>0) {
                    int RESPONSE = game.checkDigit(Integer.parseInt(g));
                    if (RESPONSE != 0) {
                        min = RESPONSE==2 && min<=Integer.parseInt(g) ? Integer.parseInt(g)+1 : min;
                        max = RESPONSE==1 && max>=Integer.parseInt(g) ? Integer.parseInt(g)-1 : max;
                        int sug = (min+max)/2!=Integer.parseInt(g) ? (min+max)/2 : (min+max)/2+1;
                        String range = "Suggest "+(min!=max?"range":"number")+" : " + (min!=max ? min+"-"+max : min);
                        response.setText(game.response[RESPONSE]);
                        help.setText("Last guessed : "+g+"\n" + "Attempted : "+(++n)+" times\n"+range);
                        userGuess.setText("");
                        if (SHOW_SUGGEST) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Suggest number : " + sug, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }else {
                        Intent intent = new Intent(getApplicationContext(), Congratulations.class);
                        intent.putExtra("Attempt",Integer.toString(++n));
                        startActivity(intent);
                        finish();
                    }
                } else {
                    if (g.length() == 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please guess number!", Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        if (g.equals("000")){
                            SHOW_SUGGEST = SHOW_SUGGEST==false ? true : false;
                            Toast toast = Toast.makeText(getApplicationContext(), "Show suggestion number", Toast.LENGTH_SHORT);
                            userGuess.setText("");
                            toast.show();
                        }else if (g.equals("999")){
                            Toast toast = Toast.makeText(getApplicationContext(), "The secret number is "+game.getSecret(), Toast.LENGTH_SHORT);
                            userGuess.setText("");
                            toast.show();
                        }else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Invalid number!", Toast.LENGTH_SHORT);
                            userGuess.setText("");
                            toast.show();
                        }
                    }
                }
            }
        });
    }
}

