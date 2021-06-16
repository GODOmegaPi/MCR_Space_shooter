package builder;

import equipment.Fuselage;
import equipment.Shield;
import equipment.Weapon;

public class Ship {
    private int currentHp;
    private Fuselage fuselage;
    private Weapon weapon;
    private Shield shield;

    public int getCurrentHp() {
        return currentHp;
    }

    public Ship(PlayableShipBuilder playableShipBuilder) {
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
