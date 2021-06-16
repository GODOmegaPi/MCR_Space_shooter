package com.mcr.spaceshooter.Entity.Equipments;

abstract public class Equipment {
    private int hp;
    private int credit;

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getCredit() {
        return credit;
    }

    public Equipment(int hp, int credit){
        this.hp = hp;
        this.credit = credit;
    }
}
