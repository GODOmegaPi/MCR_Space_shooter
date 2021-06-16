package com.mcr.spaceshooter.Utils;


public class Rand {

    public static int generateRandom(int a, int b){
        return (int) Math.floor(Math.random() * (b - a + 1) + a);

    }

}
