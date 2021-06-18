package com.mcr.spaceshooter.Entity.Equipments;

abstract public class Equipment {
    private final int price;
    private final String name;

    public Equipment(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
