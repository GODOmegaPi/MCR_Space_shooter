package com.mcr.spaceshooter.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
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
        JsonReader json = new JsonReader();
        JsonValue equipments = json.parse(Gdx.files.internal("equipments.json"));

        for (JsonValue fuselage : equipments.get("fuselages")) {
            fuselageList.add(new Fuselage(
                    fuselage.getString("name"),
                    Asset.getInstance().getFuselagesTexture(fuselage.getInt("textureId")),
                    fuselage.getInt("price"),
                    fuselage.getInt("hp")
            ));
        }

        for (JsonValue fuselage : equipments.get("weapons")) {
            weaponList.add(new Weapon(
                    fuselage.getString("name"),
                    Asset.getInstance().getWeaponsTexture(fuselage.getInt("textureId")),
                    fuselage.getInt("price"),
                    fuselage.getInt("shotSpeed")
            ));
        }

        for (JsonValue fuselage : equipments.get("shields")) {
            shieldList.add(new Shield(
                    fuselage.getString("name"),
                    Asset.getInstance().getShieldsTexture(fuselage.getInt("textureId")),
                    fuselage.getInt("price"),
                    fuselage.getInt("hp")
            ));
        }
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
