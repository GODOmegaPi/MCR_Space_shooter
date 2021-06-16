package com.mcr.spaceshooter.Builder;

import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Entity.Spaceship;

// TODO : interface utile  ?
public interface ShipBuilder {
    public void reset();
    public ShipBuilder setFuselage(Fuselage fuselage)throws ShipBuilderException;
    public ShipBuilder setShield(Shield shield) throws ShipBuilderException;
    public ShipBuilder setWeapon(Weapon weapon) throws ShipBuilderException;
    public ShipBuilder clearFuselage()throws ShipBuilderException;
    public ShipBuilder clearShield() throws ShipBuilderException;
    public ShipBuilder clearWeapon() throws ShipBuilderException;
    public Spaceship build();
    public int getTotalCost();
}
