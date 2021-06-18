package com.mcr.spaceshooter.Utils;
import com.mcr.spaceshooter.Entity.Equipments.Equipment;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Loader {
    private static Loader instance;
    private List<Equipment> fuselageList;
    private List<Equipment> weaponList;
    private List<Equipment> shieldList;

    private Loader() {
        fuselageList = new ArrayList<>();
        weaponList = new ArrayList<>();
        shieldList = new ArrayList<>();
        init();
    }

    private void init() {

        fuselageList.add(new Fuselage("Falcon 1", Asset.getInstance().getFuselagesTexture(1), 30, 75));
        fuselageList.add(new Fuselage("Falcon 9", Asset.getInstance().getFuselagesTexture(5), 40, 90));
        fuselageList.add(new Fuselage("Falcon Heavy", Asset.getInstance().getFuselagesTexture(9), 50, 100));
        weaponList.add(new Weapon("SIG 550", Asset.getInstance().getWeaponsTexture(1), 30, 200));
        weaponList.add(new Weapon("Browning M2HB", Asset.getInstance().getWeaponsTexture(2), 40, 100));
        weaponList.add(new Weapon("Panzerfaust", Asset.getInstance().getWeaponsTexture(3), 50, 50));
        shieldList.add(new Shield("Phantom Shield", Asset.getInstance().getShieldsTexture(1), 30, 10));
        shieldList.add(new Shield("Diamond Shield", Asset.getInstance().getShieldsTexture(2), 40, 20));
        shieldList.add(new Shield("Plasma Shield", Asset.getInstance().getShieldsTexture(3), 50, 30));
    }

    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }
        return instance;
    }

    public List<Equipment> getFuselageList() {
        return fuselageList;
    }

    public List<Equipment> getWeaponList() {
        return weaponList;
    }

    public List<Equipment> getShieldList() {
        return shieldList;
    }
}
