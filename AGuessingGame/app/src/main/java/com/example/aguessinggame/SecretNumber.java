package com.example.aguessinggame;

import java.util.Random;

public class SecretNumber {
    private Random gen = new Random();
    private int secret;
    public static final String[] response = {"You won","Your guess is too high","You guess is too low"};
    public void start(){
        secret = gen.nextInt(100)+1;
    }

    public int checkDigit(int n){
        if (n<secret)
            return 2;
        else if (n>secret)
            return 1;
        else
            return 0;
    }

    public int getSecret(){
        return secret;
    }
}
