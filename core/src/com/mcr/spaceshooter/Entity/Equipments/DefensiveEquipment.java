package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;

/**
 * Classe abstraite modélisation un équipement défensif.
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public abstract class DefensiveEquipment extends Equipment {

    private int hp;

    /**
     * Constructeur équipement défensif.
     * @param name nom
     * @param texture texture à afficher
     * @param price prix de l'équipement
     * @param hp points de vie que confére l'équipement au vaisseau.
     */
    public DefensiveEquipment(String name, Texture texture, int price, int hp) {
        super(name, texture, price);
        this.hp = hp;
    }

    /**
     * Constructeur de copie d'un équipement défensif.
     * @param defensiveEquipment équipement à copier.
     */
    public DefensiveEquipment(DefensiveEquipment defensiveEquipment){
        super(defensiveEquipment);
        hp = defensiveEquipment.hp;
    }

    /**
     * @return points de vie que confére l'équipement au vaisseau.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Set les points de vie de l'équipement. Un équipement ne peut avoir des points de vie négatif,
     * toute valeure négative sera remplacée par 0.
     * @param hp
     */
    public void setHp(int hp) {
        // Pas de point de vie négative.
        this.hp = Math.max(hp, 0);
    }


}
