package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;

public abstract class DefensiveEquipment extends Equipment {

    private int hp;

    public DefensiveEquipment(String name, Texture texture, int price, int hp) {
        super(name, texture, price);
        this.hp = hp;
    }
    public DefensiveEquipment(DefensiveEquipment defensiveEquipment){
        super(defensiveEquipment);
        hp = defensiveEquipment.hp;
    }

    public int getHp(){
        return hp;
    }

    public void setHp(int hp) {
        // Pas de point de vie négative.
        this.hp = Math.max(hp, 0);
    }


}
