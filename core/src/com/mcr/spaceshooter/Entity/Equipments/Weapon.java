package com.mcr.spaceshooter.Entity.Equipments;
// TODO Enlever hp => Rajouter une classe OffensiveEquipement, DefensiveEquipement
public class Weapon extends Equipment {
    private int damage;

    public Weapon(int hp, int credit, int damage) {
        super(hp, credit);
        this.damage = damage;
    }
}
