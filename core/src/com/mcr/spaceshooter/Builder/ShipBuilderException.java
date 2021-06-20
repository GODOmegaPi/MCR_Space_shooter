package com.mcr.spaceshooter.Builder;

/**
 * Classe représentant une exception lors d'une construction invalide d'un vaisseau avec un builder
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class ShipBuilderException extends IllegalStateException {
    /**
     * Lève une exception
     *
     * @param errorMessage le message décrivant l'exception
     */
    public ShipBuilderException(String errorMessage) {
        super(errorMessage);
    }
}
