package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;

public abstract class OffensiveEquipment extends Equipment {
    public OffensiveEquipment(String name, Texture texture, int price) {
        super(name, texture, price);
    }
    public OffensiveEquipment(OffensiveEquipment offensiveEquipment){
        super(offensiveEquipment);
    }

}
