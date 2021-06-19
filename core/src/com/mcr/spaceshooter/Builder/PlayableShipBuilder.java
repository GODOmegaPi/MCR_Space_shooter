package com.mcr.spaceshooter.Builder;

import com.badlogic.gdx.graphics.Texture;
import com.mcr.spaceshooter.Entity.Spaceship;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Utils.Constants;


public class PlayableShipBuilder implements ShipBuilder {
    private int totalCost;
    private Fuselage fuselage;
    private Weapon weapon;
    private Shield shield;

    public PlayableShipBuilder() {
    } // TODO : Avoir un name comme param ?

    @Override
    public void reset() {
        fuselage = null;
        weapon = null;
        shield = null;
        totalCost = 0;
    }

    public ShipBuilder setFuselage(Fuselage fuselage) throws ShipBuilderException {
        this.fuselage = fuselage;
        this.totalCost += fuselage.getPrice();
        return this;
    }

    @Override
    public ShipBuilder setShield(Shield shield) throws ShipBuilderException {
        if(fuselage == null) {
            throw new ShipBuilderException("Fuselage nécessaire avant l'ajout d'un bouclier !");
        }
        this.shield = shield;
        this.totalCost += shield.getPrice();
        return this;
    }

    @Override
    public ShipBuilder setWeapon(Weapon weapon) throws ShipBuilderException {
        if(fuselage == null) {
            throw new ShipBuilderException("Fuselage nécessaire avant l'ajout d'une arme !");
        }
        this.weapon = weapon;
        this.totalCost += weapon.getPrice();
        return this;
    }

    @Override
    public ShipBuilder clearFuselage() throws ShipBuilderException {
        if (fuselage != null) { // TODO controler que c'est pas vide ?
            if(shield != null || weapon != null) {
                StringBuilder msg = new StringBuilder("Vous devez déséquiper: \n");
                if(shield != null) msg.append("- le bouclier \n");
                if(weapon != null) msg.append("- l'arme");
                throw new ShipBuilderException(msg.toString());
            }
                totalCost -= fuselage.getPrice();
                fuselage = null;
        }
        return this;
    }

    @Override
    public ShipBuilder clearShield() throws ShipBuilderException {
        if (shield != null) {
            totalCost -= shield.getPrice();
            shield = null;
        }
        return this;
    }

    @Override
    public ShipBuilder clearWeapon() throws ShipBuilderException {
        if(weapon != null) {
            totalCost -= weapon.getPrice();
            weapon = null;
        }
        return this;
    }

    @Override
    public Spaceship build() {
        /*
        if(shield == null) {
            //TODO: Merci guillaume tu nous rajoutes de bugs à 2h du mat fdp
            shield = new Shield("none", null,0, 0);
        }
        
         */
        if(!isValidShip()) {
            throw new ShipBuilderException("Construction invalide !");
        }
        Spaceship ship = new Spaceship(this);
        return ship;
    }

    @Override
    public int getTotalCost() {
        return totalCost;
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

    private boolean isValidShip() throws ShipBuilderException {
        return fuselage != null && weapon != null && getTotalCost() <= Constants.MAX_COST;
    }
}
