package com.mcr.spaceshooter.Utils;

/**
 * Class used to generate random numbers
 */
public class Rand {

    /**
     * Genreate a random number in given range
     * @param lowerBound the lowest value of the random
     * @param upperBound the biggest value of the random
     * @return the random value generated
     */
    public static int generateRandom(int lowerBound, int upperBound){
        return (int) Math.floor(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }

}
