package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;

public abstract class OffensiveEquipment extends Equipment {
    private final int damage;

    public OffensiveEquipment(String name, Texture texture, int price, int damage) {
        super(name, texture, price);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

}
