package com.mcr.spaceshooter.Entity.Equipments;

public abstract class OffensiveEquipment extends Equipment {
    private final int damage;

    public OffensiveEquipment(String name, int price, int damage) {
        super(name, price);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

}
