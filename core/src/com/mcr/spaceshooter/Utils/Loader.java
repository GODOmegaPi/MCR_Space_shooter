package com.mcr.spaceshooter.Utils;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mcr.spaceshooter.Entity.Equipments.Equipment;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe singleton utilisée pour charger la définition des équipements
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
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

    /**
     * Constructeur privé de chargement d'équipements
     */
    private Loader() {
        fuselageList = new ArrayList<>();
        weaponList = new ArrayList<>();
        shieldList = new ArrayList<>();
        init();
    }

    /**
     * Charge les équipements en lisant un fichier JSON
     */
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

    /**
     * Récupère l'instance de la classe courante
     *
     * @return l'instance de la classe courante
     */
    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }
        return instance;
    }

    /**
     * Récupère les fuselages chargés
     *
     * @return les fuselages chargés
     */
    public List<Equipment> getFuselageList() {
        return fuselageList;
    }

    /**
     * Récupère les armes chargées
     *
     * @return les armes chargées
     */
    public List<Equipment> getWeaponList() {
        return weaponList;
    }

    /**
     * Récupère les boucliers chargés
     *
     * @return les boucliers chargés
     */
    public List<Equipment> getShieldList() {
        return shieldList;
    }
}
