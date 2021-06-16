package builder;

import equipment.Fuselage;
import equipment.Shield;
import equipment.Weapon;

public class PlayableShipBuilder implements ShipBuilder{
    private int currentHp;
    private Fuselage fuselage;
    private Weapon weapon;
    private Shield shield;

    public PlayableShipBuilder(){} // TODO : Avoir un name comme param ?

    @Override
    public void reset() {
        // TODO A quoi cela sert de reset ?
        currentHp = 0;
        fuselage = null;
        weapon = null;
        shield = null;
    }

    @Override
    public ShipBuilder fuselage(int hp, int credit) {
        this.fuselage = new Fuselage(hp, credit);
        return this;
    }

    @Override
    public ShipBuilder shield(int hp, int credit) {
        this.shield = new Shield(hp, credit);
        return this;
    }

    @Override
    public ShipBuilder weapon(int hp, int credit, int damage) {
        if (fuselage == null){
            //throw new Exception("Fuselage nécessaire avant l'ajout d'une arme !");
        }
        this.weapon = new Weapon(hp, credit, damage);
        return this;
    }

    @Override
    public Ship build() {
        Ship ship = new Ship(this);
        //validateShip(ship);
        return ship;
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

    private void validateShip(Ship ship) throws Exception {
        if (ship.getFuselage() == null || ship.getWeapon() == null){
            throw new Exception("Le vaisseau n'est pas construit correctement !");
        }
    }

    /*
    @Override
    public void setFuselage() {

    }

    @Override
    public void setShield() {

    }

    @Override
    public void setWeapon() {

    }
     */
}
