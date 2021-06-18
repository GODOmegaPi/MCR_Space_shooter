package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;

abstract public class Equipment {
    private final int price;
    private final String name;
    private final Texture texture;

    public Equipment(String name, Texture texture, int price) {
        this.name = name;
        this.texture = texture;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    protected int credit;

    public int getCredit() {
        return credit;
    }

    public Texture getTexture() {
        return texture;
    }
}
