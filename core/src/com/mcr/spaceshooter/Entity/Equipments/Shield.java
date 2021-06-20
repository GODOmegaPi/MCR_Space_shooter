package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;

/**
 * Cette classe modélise le bouclier d'un vaisseau, le protégant des dangers omniprésents de l'espace.
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class Shield extends DefensiveEquipment {
    /**
     * Constructeur d'un bouclier.
     * @param name nom du bouclier.
     * @param texture texture du bouclier.
     * @param price prix du bouclier.
     * @param hp points de vie qu'offre le bouclier
     */
    public Shield(String name, Texture texture, int price, int hp) {
        super(name, texture, price, hp);
    }

    /**
     * Constructeurs copiant les attributs du bouclier passé en paramètre.
     * @param shield bouclier à copier.
     */
    public Shield(Shield shield){
        super(shield);
    }

}
