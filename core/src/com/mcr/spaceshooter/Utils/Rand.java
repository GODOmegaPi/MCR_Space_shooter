package com.mcr.spaceshooter.Utils;

/**
 * Classe utilisée pour générer des nombres aléatoires
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class Rand {
    /**
     * Génère un nombre aléatoire dans un intervalle donné
     * @param lowerBound la borne inférieure du nombre aléatoire
     * @param upperBound la borne supérieure du nombre aléatoire
     * @return le nombre aléatoire généré
     */
    public static int generateRandom(int lowerBound, int upperBound){
        return (int) Math.floor(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }

}
