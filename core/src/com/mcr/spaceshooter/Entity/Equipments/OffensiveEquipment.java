package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;

/**
 * Classe abstraite modélisation un équipement offensif.
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public abstract class OffensiveEquipment extends Equipment {
    /**
     * Constructeur l'équipement offensif.
     * @param name nom de l'équipement.
     * @param texture texture de l'équipement.
     * @param price prix de l'équipement
     */
    public OffensiveEquipment(String name, Texture texture, int price) {
        super(name, texture, price);
    }

    /**
     * Constructeur copiant les attributs de l'équipement
     * @param offensiveEquipment
     */
    public OffensiveEquipment(OffensiveEquipment offensiveEquipment){
        super(offensiveEquipment);
    }

}
