package com.mcr.spaceshooter.Entity.Equipments;

abstract public class Equipment {
    private int hp;
    private int price;

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getPrice() {
        return price;
    }

    public Equipment(int hp, int price){
        this.hp = hp;
        this.price = price;
    }
}
