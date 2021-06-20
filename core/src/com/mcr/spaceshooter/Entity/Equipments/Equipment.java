package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;

/**
 * Un vaisseau possède des équipements (fuselage, bouclier, arme, ...).
 * Chaque équipement possède des propriétés offensives et défensives dont le vaisseau en bénéficie.
 * Plus un équipement sera efficace, plus ce dernier aura un prix élevé.
 * Un équipement est modélisé par cette classe abstraite.
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
abstract public class Equipment {
    private final int price;
    private final String name;
    private final Texture texture;

    /**
     * Constructeur standard.
     * @param name nom de l'équipement.
     * @param texture texture de l'équipement, qui va être affiché.
     * @param price prix de l'équipement.
     */
    public Equipment(String name, Texture texture, int price) {
        this.name = name;
        this.texture = texture;
        this.price = price;
    }

    /**
     * Constructeur de copie. Copie les attributs de l'équipement donné en paramètre.
     * @param equipement équipement copié.
     */
    public Equipment(Equipment equipement){
        name = equipement.name;
        texture = equipement.texture;
        price = equipement.price;
    }

    /**
     * @return retourne le prix de l'équipement.
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return retourne le nom de l'équipement.
     */
    public String getName() {
        return name;
    }

    /**
     * @return retourne la texture.
     */
    public Texture getTexture() {
        return texture;
    }
}
