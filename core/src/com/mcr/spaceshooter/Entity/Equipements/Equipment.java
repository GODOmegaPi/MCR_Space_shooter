package com.mcr.spaceshooter.Entity.Equipements;

/**
 * TODO faire une sous classe pour les equipement défensif vs offensif
 */
abstract public class Equipment {
    protected int hp;
    protected int credit;
    protected Equipment(int hp, int credit){
        this.hp = hp;
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }
    public int getHp(){
        return hp;
    }
}
