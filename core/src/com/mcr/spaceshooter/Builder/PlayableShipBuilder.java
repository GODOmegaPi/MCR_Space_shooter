package com.mcr.spaceshooter.Builder;

import com.mcr.spaceshooter.Entity.Spaceship;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Utils.Constants;

/**
 * Builder implémentant l'interface ShipBuilder, permettant de construire un vaisseau jouable
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class PlayableShipBuilder implements ShipBuilder {
    // Représente le coût total des équipements du vaisseau en construction
    private int totalCost;

    // Représente les équipements
    private Fuselage fuselage;
    private Weapon weapon;
    private Shield shield;

    /**
     * Constructeur
     */
    public PlayableShipBuilder() {
    }

    /**
     * Réinitialise les paramètres du builder
     */
    @Override
    public ShipBuilder reset() {
        fuselage = null;
        weapon = null;
        shield = null;
        totalCost = 0;
        return this;
    }

    /**
     * Définit le fuselage
     *
     * @param fuselage le fuselage voulu
     * @return Un ShipBuilder avec un fuselage
     */
    public ShipBuilder setFuselage(Fuselage fuselage) {
        this.fuselage = fuselage;
        this.totalCost += fuselage.getPrice();
        return this;
    }

    /**
     * Définit le bouclier si le vaisseau en contruction possède un fuselage
     *
     * @param shield le bouclier voulu
     * @return Un ShipBuilder avec un bouclier
     * @throws ShipBuilderException si le Fuselage n'a pas été définit auparavant
     */
    @Override
    public ShipBuilder setShield(Shield shield) throws ShipBuilderException {
        if(fuselage == null) {
            throw new ShipBuilderException("Fuselage nécessaire avant l'ajout d'un bouclier !");
        }
        this.shield = shield;
        this.totalCost += shield.getPrice();
        return this;
    }

    /**
     * Définit l'arme si le vaisseau en contruction possède un fuselage
     *
     * @param weapon l'arme voulue
     * @return Un ShipBuilder avec une arme
     * @throws ShipBuilderException si le Fuselage n'a pas été définit auparavant
     */
    @Override
    public ShipBuilder setWeapon(Weapon weapon) throws ShipBuilderException {
        if(fuselage == null) {
            throw new ShipBuilderException("Fuselage nécessaire avant l'ajout d'une arme !");
        }
        this.weapon = weapon;
        this.totalCost += weapon.getPrice();
        return this;
    }

    /**
     * Retire le fuselage si le vaisseau en construction ne possède pas de bouclier et d'arme
     *
     * @return Un ShipBuilder sans fuselage
     * @throws ShipBuilderException si on essaie de déséquiper le Fuselage sans avoir préalablement déséquipé tous les équipements
     */
    @Override
    public ShipBuilder clearFuselage() throws ShipBuilderException {
        if (fuselage != null) {
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

    /**
     * Retire le bouclier
     *
     * @return Un ShipBuilder sans bouclier
     */
    @Override
    public ShipBuilder clearShield() {
        if (shield != null) {
            totalCost -= shield.getPrice();
            shield = null;
        }
        return this;
    }

    /**
     * Retire l'arme
     *
     * @return Un ShipBuilder sans arme
     */
    @Override
    public ShipBuilder clearWeapon() {
        if(weapon != null) {
            totalCost -= weapon.getPrice();
            weapon = null;
        }
        return this;
    }

    /**
     * Construit le vaisseau en contrôlant que sa construction soit valide
     *
     * @return Un vaisseau construit avec les paramètres configurés dans le builder
     * @throws ShipBuilderException si le vaisseau n'est pas valide
     */
    @Override
    public Spaceship build() {
        if(!isValidShip()) {
            throw new ShipBuilderException("Construction invalide !\n" + whyShipInvalid());
        }
        Spaceship ship = new Spaceship(this);
        return ship;
    }

    /**
     * Calcul du coût total des équipements du vaisseau en construction
     *
     * @return Le coût total des équipements du shipBuilder
     */
    @Override
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * Récupère le fuselage du vaisseau en construction
     *
     * @return Un fuselage Fuselage
     */
    public Fuselage getFuselage() {
        return fuselage;
    }

    /**
     * Récupère l'arme du vaisseau en construction
     *
     * @return une arme Weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Récupère le bouclier du vaisseau en construction
     *
     * @return un bouclier Shield
     */
    public Shield getShield() {
        return shield;
    }

    /**
     * Fonction permettant de contrôler que le vaisseau en construction soit valide
     *
     * @return Si le vaisseau en construction est valide o
     */
    private boolean isValidShip() {
        return fuselage != null && weapon != null && getTotalCost() <= Constants.MAX_COST;
    }

    /**
     * Affiche pourquoi le vaisseau en construction n'est pas valide
     *
     * @return La raison de la construction invalide
     */
    private String whyShipInvalid() {
        String reason = "";

        if(fuselage == null) {
            reason += "- Fuselage nécessaire !\n";
        }
        if (weapon == null) {
            reason += "- Arme nécessaire !\n";
        }
        if (getTotalCost() > Constants.MAX_COST) {
            reason += "- Coût total du vaisseau supérieur à " + Constants.MAX_COST + " !\n";
        }

        return reason;
    }
}
