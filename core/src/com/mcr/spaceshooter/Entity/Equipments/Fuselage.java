package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;

/**
 * Fuselage de notre vaisseau. Il s'agit d'un équipement essentiel d'un vaisseau se déplaçant dans le vide
 * qu'est l'espace.
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class Fuselage extends DefensiveEquipment {
    /**
     * Constructeur du fuselage.
     * @param name nom du fuselage.
     * @param texture texture d u fuselage.
     * @param price prix qu'offre le fuselage.
     * @param hp points de vie du fuselage.
     */
    public Fuselage(String name, Texture texture, int price, int hp) {
        super(name, texture, price, hp);
    }

    /**
     * Constructeur de copie du fuselage.
     * @param fuselage fuselage à copier.
     */
    public Fuselage(Fuselage fuselage){
        super(fuselage);
    }
}
