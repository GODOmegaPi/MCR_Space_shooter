package com.mcr.spaceshooter.Entity.Equipments;

public abstract class DefensiveEquipment extends Equipment {
    private int hp;

    public DefensiveEquipment(String name, int price, int hp) {
        super(name, price);
        this.hp = hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
