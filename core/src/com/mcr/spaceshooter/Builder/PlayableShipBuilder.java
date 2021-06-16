package com.mcr.spaceshooter.Builder;

import com.mcr.spaceshooter.Entity.Spaceship;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Utils.Constants;

public class PlayableShipBuilder implements ShipBuilder {
    private int currentHp;
    private int totalCost;
    private Fuselage fuselage;
    private Weapon weapon;
    private Shield shield;

    public PlayableShipBuilder() {
    } // TODO : Avoir un name comme param ?

    @Override
    public void reset() {
        // TODO A quoi cela sert de reset ?
        currentHp = 0;
        fuselage = null;
        weapon = null;
        shield = null;
    }

    public ShipBuilder setFuselage(Fuselage fuselage) throws ShipBuilderException {
        this.fuselage = fuselage;
        this.totalCost += fuselage.getPrice();
        return this;
    }

    @Override
    public ShipBuilder setShield(Shield shield) throws ShipBuilderException {
        if (fuselage == null) {
            throw new ShipBuilderException("Fuselage nécessaire avant l'ajout d'un bouclier !");
        }
        this.shield = shield;
        this.totalCost += shield.getPrice();
        return this;
    }

    @Override
    public ShipBuilder setWeapon(Weapon weapon) throws ShipBuilderException {
        if (fuselage == null) {
            throw new ShipBuilderException("Fuselage nécessaire avant l'ajout d'une arme !");
        }
        this.weapon = weapon;
        this.totalCost += weapon.getPrice();
        return this;
    }

    @Override
    public ShipBuilder clearFuselage() throws ShipBuilderException {
        if (shield != null) {
            throw new ShipBuilderException("Déséquiper le bouclier avant !");
        } else if (weapon != null) {
            throw new ShipBuilderException("Déséquiper l'arme avant !");
        }
        fuselage = null;
        return this;
    }

    @Override
    public ShipBuilder clearShield() throws ShipBuilderException {
        shield = null;
        return this;
    }

    @Override
    public ShipBuilder clearWeapon() throws ShipBuilderException {
        weapon = null;
        return this;
    }

    @Override
    public Spaceship build() {
        Spaceship ship = new Spaceship(this);
        if (!isValidShip(ship)) {
            throw new ShipBuilderException("Construction invalide !");
        }
        return ship;
    }

    @Override
    public int getTotalCost() {
        return totalCost;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public Fuselage getFuselage() {
        return fuselage;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Shield getShield() {
        return shield;
    }

    private boolean isValidShip(Spaceship ship) throws ShipBuilderException {
        return ship.getFuselage() != null && ship.getWeapon() != null && getTotalCost() <= Constants.MAX_COST;
    }
}
