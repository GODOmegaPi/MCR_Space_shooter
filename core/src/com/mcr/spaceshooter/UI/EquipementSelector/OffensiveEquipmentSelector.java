package com.mcr.spaceshooter.UI.EquipementSelector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mcr.spaceshooter.Entity.Equipments.Equipment;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Entity.Equipments.OffensiveEquipment;
import com.mcr.spaceshooter.UI.Screen.GarageScreen;

import java.util.List;
import java.util.function.Consumer;


/**
 *  Cette classe implémente le sélecteur d'équipement offensif.
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class OffensiveEquipmentSelector extends EquipementSelector {
    private Label shotFreqLbl;
    private Label nameLbl;
    private Label priceLbl;

    /**
     * Construit le sélecteur d'équipement offensif
     * @param equipments la listes des équipements offensif à afficher.
     * @param skin Skin de l'interface du sélecteur
     * @param buildSetter méthode à appeler lorsqu'on a sélectionné un équipement
     * @param buildCleaner méthode à appeler lorsqu'on veut déséquiper un équipement
     * @param garageScreen référence vers le garage screen
     */
    public OffensiveEquipmentSelector(List<Equipment> equipments, Skin skin, Consumer<Equipment> buildSetter, Runnable buildCleaner, GarageScreen garageScreen) {
        super(equipments, skin, buildSetter, buildCleaner, garageScreen);

        nameLbl = new Label(equipments.get(currentElementIdx).getName(), skin);
        priceLbl = new Label(String.valueOf(equipments.get(currentElementIdx).getPrice()), skin);
        shotFreqLbl = new Label(String.valueOf(((Weapon) equipments.get(currentElementIdx)).getShotFrequency()), skin);
        init();
    }

    /**
     * Ajoute les labels des propritété de l'équipement dans la table
     *
     */
    @Override
    void addSpecs() {
        table.add(nameLbl).width(240).colspan(3);
        table.row();

        table.add(new Label("Tirs/sec.", skin)).width(120).colspan(2);
        table.add(shotFreqLbl);
        table.row();

        table.add(new Label("Prix", skin)).width(120).colspan(2);
        table.add(priceLbl);
        table.row();
    }

    /**
     * Mets à jour les textes de labels.
     */
    @Override
    void updateLabels() {
        Gdx.app.debug(this.getClass().getName(), "LABEL");
        nameLbl.setText(equipments.get(currentElementIdx).getName());
        priceLbl.setText(String.valueOf(equipments.get(currentElementIdx).getPrice()));
        shotFreqLbl.setText(String.valueOf(((Weapon) equipments.get(currentElementIdx)).getShotFrequency()));
    }
}
