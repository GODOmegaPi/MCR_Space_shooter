package com.mcr.spaceshooter.Utils;


public class Rand {

    public static int generateRandom(int lowerBound, int upperBound){
        return (int) Math.floor(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }

}
