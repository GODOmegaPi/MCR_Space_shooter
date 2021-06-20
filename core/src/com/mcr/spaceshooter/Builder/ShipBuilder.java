package com.mcr.spaceshooter.Builder;

import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Entity.Spaceship;

/**
 * Interface représentant un builder d'un vaisseau
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public interface ShipBuilder {
    /**
     * Réinitialise les paramètres d'un vaiseeau
     */
    void reset();

    /**
     *
     * @param fuselage
     * @return un shipBuilder avec
     * @throws ShipBuilderException
     */
    ShipBuilder setFuselage(Fuselage fuselage)throws ShipBuilderException;

    /**
     *
     * @param shield
     * @return
     * @throws ShipBuilderException
     */
    ShipBuilder setShield(Shield shield) throws ShipBuilderException;

    /**
     *
     * @param weapon
     * @return
     * @throws ShipBuilderException
     */
    ShipBuilder setWeapon(Weapon weapon) throws ShipBuilderException;

    /**
     *
     * @return
     * @throws ShipBuilderException
     */
    ShipBuilder clearFuselage()throws ShipBuilderException;

    /**
     *
     * @return
     * @throws ShipBuilderException
     */
    ShipBuilder clearShield() throws ShipBuilderException;

    /**
     *
     * @return
     * @throws ShipBuilderException
     */
    ShipBuilder clearWeapon() throws ShipBuilderException;

    /**
     *
     * @return
     */
    Spaceship build();

    /**
     *
     * @return
     */
    int getTotalCost();
}
