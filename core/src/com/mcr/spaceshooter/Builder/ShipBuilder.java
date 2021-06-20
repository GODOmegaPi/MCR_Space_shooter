package com.mcr.spaceshooter.Builder;

import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Entity.Spaceship;

/**
 * Interface représentant un builder d'un vaisseau
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public interface ShipBuilder {
    /**
     * Réinitialise les paramètres du shipBuilder (vaisseau en construction)
     */
    ShipBuilder reset();

    /**
     * Définit le  fuselage
     *
     * @param fuselage
     * @return Un ShipBuilder avec un fuselage
     */
    ShipBuilder setFuselage(Fuselage fuselage);

    /**
     * Définit le bouclier
     *
     * @param shield
     * @return Un ShipBuilder avec un bouclier
     * @throws ShipBuilderException si les conditions d'ajout du bouclier ne sont pas respectées
     */
    ShipBuilder setShield(Shield shield) throws ShipBuilderException;

    /**
     * Définit l'arme
     *
     * @param weapon
     * @return Un ShipBuilder avec une arme
     * @throws ShipBuilderException si les conditions d'ajout de l'arme ne sont pas respectées
     */
    ShipBuilder setWeapon(Weapon weapon) throws ShipBuilderException;

    /**
     * Retire le fuselage
     *
     * @return Un ShipBuilder sans fuselage
     * @throws ShipBuilderException si les conditions de suppression du fuselage ne sont pas respectées
     */
    ShipBuilder clearFuselage() throws ShipBuilderException;

    /**
     * Retire le bouclier
     *
     * @return Un ShipBuilder sans bouclier
     * @throws ShipBuilderException si les conditions de suppression du bouclier ne sont pas respectées
     */
    ShipBuilder clearShield();

    /**
     * Retire l'arme
     *
     * @return Un ShipBuilder sans arme
     * @throws ShipBuilderException si les conditions de suppression d'une arme ne sont pas respectées
     */
    ShipBuilder clearWeapon();

    /**
     * Construit le vaisseau
     *
     * @return Un vaisseau construit avec les paramètres configurés
     * @throws ShipBuilderException si le vaisseau n'est pas valide
     */
    Spaceship build();

    /**
     * Calcule du coût total des équipements du vaisseau en construction
     *
     * @return Le coût total des équipements du shipBuilder
     */
    int getTotalCost();
}
