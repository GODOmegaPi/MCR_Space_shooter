package com.mcr.spaceshooter.Entity;

import com.mcr.spaceshooter.Builder.PlayableShipBuilder;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.sun.tools.jdeps.JdepsFilter;

import java.security.KeyStore;
import java.util.Calendar;

public class Spaceship {
    private int currentHp;
    private Fuselage fuselage;
    private Weapon weapon;
    private Shield shield;

    public int getCurrentHp() {
        return currentHp;
    }

    public Spaceship(PlayableShipBuilder playableShipBuilder) {
        currentHp = playableShipBuilder.getCurrentHp();
        fuselage = playableShipBuilder.getFuselage();
        weapon = playableShipBuilder.getWeapon();
        shield = playableShipBuilder.getShield();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Shield getShield() {
        return shield;
    }

    public Fuselage getFuselage() {
        return fuselage;
    }

    // TODO :
    // Does not have any setter method, so it’s state can not be changed once it has been built.
    // This provides the desired immutability.

}
