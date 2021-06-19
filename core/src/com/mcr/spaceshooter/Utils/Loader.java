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

    private final String FUSELAGES_DEF = "fuselages";
    private final String WEAPONS_DEF = "weapons";
    private final String SHIELDS_DEF = "shields";

    private final String EQUIPMENT_NAME_DEF = "name";
    private final String EQUIPMENT_TEXTURE_ID_DEF = "textureId";
    private final String EQUIPMENT_PRICE_DEF = "price";
    private final String EQUIPMENT_HP_DEF = "hp";
    private final String EQUIPMENT_SHOT_SPEED_DEF = "shotSpeed";

    private Loader() {
        fuselageList = new ArrayList<>();
        weaponList = new ArrayList<>();
        shieldList = new ArrayList<>();
        init();
    }

    private void init() {
        JsonReader json = new JsonReader();
        JsonValue equipments = json.parse(Asset.getFile(Constants.EQUIPMENTS_DEFINITION_PATH));

        for (JsonValue fuselage : equipments.get(FUSELAGES_DEF)) {
            fuselageList.add(new Fuselage(
                    fuselage.getString(EQUIPMENT_NAME_DEF),
                    Asset.getInstance().getFuselagesTexture(fuselage.getInt(EQUIPMENT_TEXTURE_ID_DEF)),
                    fuselage.getInt(EQUIPMENT_PRICE_DEF),
                    fuselage.getInt(EQUIPMENT_HP_DEF)
            ));
        }

        for (JsonValue weapon : equipments.get(WEAPONS_DEF)) {
            weaponList.add(new Weapon(
                    weapon.getString(EQUIPMENT_NAME_DEF),
                    Asset.getInstance().getWeaponsTexture(weapon.getInt(EQUIPMENT_TEXTURE_ID_DEF)),
                    weapon.getInt(EQUIPMENT_PRICE_DEF),
                    weapon.getInt(EQUIPMENT_SHOT_SPEED_DEF)
            ));
        }

        for (JsonValue shield : equipments.get(SHIELDS_DEF)) {
            shieldList.add(new Shield(
                    shield.getString(EQUIPMENT_NAME_DEF),
                    Asset.getInstance().getShieldsTexture(shield.getInt(EQUIPMENT_TEXTURE_ID_DEF)),
                    shield.getInt(EQUIPMENT_PRICE_DEF),
                    shield.getInt(EQUIPMENT_HP_DEF)
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
