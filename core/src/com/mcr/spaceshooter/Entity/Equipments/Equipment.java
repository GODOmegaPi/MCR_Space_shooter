package com.mcr.spaceshooter.Entity.Equipments;

abstract public class Equipment {
    private final int price;
    private final String name;
    protected int hp;

    public Equipment(String name, int price, int hp) {
        this.name = name;
        this.price = price;
        this.hp = hp;
    }


    public Equipment(String name, int price) {
        // TODO Probablement ne pas laisser HP dans Equipement...
        this.name = name;
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

    public int getHp(){
        return hp;
    }

    public void setHp(int hp) {
        // Pas de point de vie négative.
        this.hp = Math.max(hp, 0);
    }
}
